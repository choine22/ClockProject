import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import javazoom.jl.player.Player;


public class CheckAlarm extends SwingWorker<Boolean, String> {
	
	private JFrame frame = new Clock();
	private AddAlarm dialog = new AddAlarm(frame);
	private Calendar currentDate = Calendar.getInstance();
	private ShowAlarm ring = new ShowAlarm();
	private boolean onoffStatus = false;	 
	
	//Thread playerThread;
	boolean stopped;
	
	public CheckAlarm(AddAlarm dlg) {
		stopped=false;
		this.dialog = dlg;
		onoffStatus= dlg.getOnOffStatus();
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		onoffStatus= dialog.getOnOffStatus();
		while(onoffStatus) {
			try {		
				if(stopped) {
					break;
				}
				ring.setLblAlarmName(dialog.getName());
				if(checkTime() || true) {
					ring.setVisible(true);
					
			        // run in new thread to play in background
					playMp3(dialog.p);
					
				}
				ring.cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if( dialog.p != null) {
							dialog.p.close();		
						}
						//if it's not repeat
						dialog.setOnOffStatus(false);
						ring.setVisible(false);
						stopWork();
					}
				});			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("I'm done");
		return false;
	}

	// code to execute when doInBackground completes
	protected void done() {
		ring.setVisible(false);
	}
	
	public boolean checkTime() {
		currentDate.setTimeInMillis( System.currentTimeMillis() ); //Update the calendar's time		
		if(currentDate.get(Calendar.HOUR_OF_DAY) == dialog.getHour()
				&& currentDate.get(Calendar.MINUTE) == dialog.getMinute()) {
			return true;
		}
		return false;
	}
	
	public void stopWork() {
		stopped = true;
	}
	
	
	// use static integer to avoid make duplicated music thread
    static int fileRunning = 0;

    public void playMp3(final Player music) {
    	if(dialog.p != null) {
	        if (fileRunning == 0){
	        	new Thread(this) {
		            public void run() {
		                try { music.play(); }
		                catch (Exception e) { e.printStackTrace(); }
		            }
		        }.start();
	            fileRunning = 1;
	        }
    	}
    }
}








