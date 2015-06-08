import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class AlarmPanel extends JPanel {
	private JLabel lblName;
	private JLabel lblDays;
	private JButton btnEdit;
	protected JButton btnDelete;

	JPanel pnl = this;
	CheckAlarm workThread;
	
	/**
	 * Create the panel.
	 */
	public AlarmPanel(final AddAlarm parent) {
		super(true);
		workThread = new CheckAlarm(parent);
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		lblName = new JLabel("New label");
		add(lblName, "2, 2");
		
		btnEdit = new JButton("Edit");
		add(btnEdit, "4, 2");
		
		lblDays = new JLabel("New label");
		add(lblDays, "2, 4");
		
		btnDelete = new JButton("Delete");
		add(btnDelete, "4, 4");
		
		if(parent.getOnOffStatus() == true ) {
			workThread.execute();
		}
			
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// show current Dialog
				parent.setVisible(true);

				// if successfully edited, check on/off button and run the thread
				if(parent.getSaveStatus() == true) {
					if(parent.getOnOffStatus() == true) {						
						workThread.execute();
					} else {
						workThread.stopWork();
					}
					
					// update GUI
					parent.setDay(); //
					setAlarmName("   "+parent.getTime()+" - "+parent.getName());
					setDays("   "+parent.getDay());
				}							
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// stop the thread
				workThread.stopWork();
			}
		});
	}
	
	public void setAlarmName(String name) {
		lblName.setText(name);
	}
	
	public void setDays(String days) {
		lblDays.setText(days);
	}
	
	public String getAlarmName() {
		return lblName.getText();
	}
	
	public String getDays() {
		return lblDays.getText();
	}

}
