import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import javazoom.jl.player.Player;

public class CheckAlarm extends SwingWorker<Boolean, String> {

	private JFrame frame = new Clock();
	private AddAlarm dialog = new AddAlarm(frame);
	private Calendar currentDate;
	private ShowAlarm ring = new ShowAlarm();
	private boolean onoffStatus;
	private boolean stopped;
	
	// flags to avoid duplicate
	boolean fileRunning;
	boolean dialogRunning;

	public CheckAlarm(AddAlarm dlg) {
		stopped = false;
		fileRunning = false;
		dialogRunning = false;
		
		this.dialog = dlg;
		onoffStatus = dlg.getOnOffStatus();
		currentDate = Calendar.getInstance();
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		System.out.println(dialog.getName()+" is Running");
		onoffStatus = dialog.getOnOffStatus();
		while (onoffStatus) {
			try {
				if (stopped) {
					break;
				}
				if (checkRepeat()) {			
					if (checkTime()) {
						publish(dialog.getName());

						// run in new thread to play in background
						playMp3(dialog.getMusic());	
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(dialog.getName()+" is done");

		return false;
	}

	// displays published values
	protected void process(List<String> dialogName) {
		if(dialogRunning == false) {
			ring.setLblAlarmName(dialog.getName());
			ring.setVisible(true);
			dialogRunning = true;
		}	
		
		ring.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialog.getMusic() != null) {
					dialog.getMusic().close();
					fileRunning = false;
				}
				ring.setVisible(false);							
				dialogRunning = false;
				
				// if it's not repeat, turn off the alarm
				if (!dialog.getRepeatStatus()) {
					dialog.setOnOffStatus(false);
					stopWork();
				} else {
					if(currentDate.get(Calendar.MINUTE) == dialog.getMinute()) {
						dialogRunning = true;
					}					
				}				
			}
		});			
	} // end method process

	// code to execute when doInBackground completes
	protected void done() {
		ring.setVisible(false);
		dialogRunning = false;
		fileRunning = false;
	}

	public void playMp3(final Player music) {
		if (dialog.getMusic() != null) {
			if (fileRunning == false) {
				new Thread(this) {
					public void run() {
						try {
							music.play();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
				fileRunning = true;
			}
		}
	}
	
	public boolean checkTime() throws InterruptedException {
		boolean isEqual = false;
		
		if (currentDate.get(Calendar.HOUR_OF_DAY) == dialog.getHour()
				&& currentDate.get(Calendar.MINUTE) == dialog.getMinute()) {
			isEqual = true;
		} else isEqual = false;
		
		Thread.sleep(1000);
		currentDate.add(Calendar.SECOND, 1);
		System.out.println(currentDate.get(Calendar.HOUR_OF_DAY)+":"+currentDate.get(Calendar.MINUTE)+":"+currentDate.get(Calendar.SECOND));

		return isEqual;
	}

	public boolean checkRepeat() {
		Calendar cur = currentDate;
		Calendar from = dialog.getFrom();
		Calendar to = dialog.getTo();

		if (dialog.getRepeatStatus()) {
			if ((cur.get(Calendar.YEAR) == from.get(Calendar.YEAR) 
					&& cur.get(Calendar.MONTH) == from.get(Calendar.MONTH)
					&& cur.get(Calendar.DATE) == from.get(Calendar.DATE))
				|| (cur.get(Calendar.YEAR) == to.get(Calendar.YEAR) 
					&& cur.get(Calendar.MONTH) == to.get(Calendar.MONTH)
					&& cur.get(Calendar.DATE) == to.get(Calendar.DATE))
				|| (from.before(cur) && to.after(cur))) {
				for (int i = 1; i < 8; i++) {
					if (dialog.getDays()[i].isSelected()) {
						if (i == cur.get(Calendar.DAY_OF_WEEK))
							return true;
					}
				}
			}
		} else
			return true;

		return false;
	}

	public void stopWork() {
		stopped = true;
	}
	
	public void startWork() {
		stopped = false;
	}


}
