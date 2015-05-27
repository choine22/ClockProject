import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;


public class SetAlarm extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldAlarmName;
	private JTextField textFieldSong;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;

	/**
	 * Create the frame.
	 */
	public SetAlarm() {
		setResizable(false);
		setTitle("Set Alarm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("357px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JPanel onoffPanel = new JPanel();
		onoffPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("190px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("105px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,}));
		contentPane.add(onoffPanel, "2, 2, fill, fill");
		
		JCheckBox chckbxOnoff = new JCheckBox("On/Off");
		onoffPanel.add(chckbxOnoff, "2, 2");
		
		txtFieldAlarmName = new JTextField();
		onoffPanel.add(txtFieldAlarmName, "2, 4, fill, default");
		txtFieldAlarmName.setColumns(10);
		
		JPanel TimePanel = new JPanel();
		onoffPanel.add(TimePanel, "4, 4, fill, fill");
		TimePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JSpinner spinnerHour = new JSpinner();
		TimePanel.add(spinnerHour);
		
		JSpinner spinnerMinute = new JSpinner();
		TimePanel.add(spinnerMinute);
		
		JSpinner spinnerMeridiem = new JSpinner();
		TimePanel.add(spinnerMeridiem);
		
		textFieldSong = new JTextField();
		onoffPanel.add(textFieldSong, "2, 6, fill, default");
		textFieldSong.setColumns(10);
		
		JButton btnChooseSong = new JButton("Choose Song");
		onoffPanel.add(btnChooseSong, "4, 6");
		
		JPanel repeatPanel = new JPanel();
		contentPane.add(repeatPanel, "2, 3, fill, fill");
		repeatPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JCheckBox chckbxRepeat = new JCheckBox("Repeat");
		repeatPanel.add(chckbxRepeat, "2, 2");
		
		textFieldFrom = new JTextField();
		repeatPanel.add(textFieldFrom, "2, 4, fill, default");
		textFieldFrom.setColumns(10);
		
		JButton buttonFrom = new JButton("...");
		repeatPanel.add(buttonFrom, "4, 4");
		
		JLabel lblTo = new JLabel("to");
		repeatPanel.add(lblTo, "6, 4, center, center");
		
		textFieldTo = new JTextField();
		repeatPanel.add(textFieldTo, "8, 4, fill, default");
		textFieldTo.setColumns(10);
		
		JButton buttonTo = new JButton("...");
		repeatPanel.add(buttonTo, "10, 4");
		
		JPanel panel = new JPanel();
		repeatPanel.add(panel, "2, 6, 9, 1, fill, fill");
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JCheckBox chckbxM = new JCheckBox("M");
		panel.add(chckbxM);
		
		JCheckBox chckbxT = new JCheckBox("T");
		panel.add(chckbxT);
		
		JCheckBox chckbxW = new JCheckBox("W");
		panel.add(chckbxW);
		
		JCheckBox chckbxT_1 = new JCheckBox("T");
		panel.add(chckbxT_1);
		
		JCheckBox chckbxF = new JCheckBox("F");
		panel.add(chckbxF);
		
		JCheckBox chckbxS = new JCheckBox("S");
		panel.add(chckbxS);
		
		JCheckBox chckbxS_1 = new JCheckBox("S");
		panel.add(chckbxS_1);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, "2, 4, fill, fill");
		btnPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnSave = new JButton("Save");
		btnPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnPanel.add(btnCancel);
	}

}
