import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ShowAlarm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblAlarmName;
	protected JButton cancelButton;

	JDialog dialog = this;

	/**
	 * Create the dialog.
	 */
	public ShowAlarm() {
		setTitle("Alarm!");
		setBounds(100, 100, 318, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblAlarmName = new JLabel("test");
			lblAlarmName.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblAlarmName, BorderLayout.CENTER);		
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void setLblAlarmName(String name) {
		lblAlarmName.setText(name);
	}

}
