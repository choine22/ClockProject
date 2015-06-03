import javax.swing.JDialog;
import javax.swing.JFrame;


public class checkAlarm implements Runnable {

	private JFrame frame = new JFrame();
	private AddAlarm dialog = new AddAlarm(frame);
	
	public checkAlarm(AddAlarm dlg) {
		this.dialog = dlg;
	}
			
	@Override
	public void run() {
		System.out.printf(this.dialog.getName()+" is Running");
	}

}
