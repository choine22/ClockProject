import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;

import org.jdatepicker.JDatePanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Window.Type;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class Change extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JCheckBox chckbxUse24HourFormat;
	private JSpinner spinnerHour;
	private JSpinner spinnerMinute;
	private JSpinner spinnerSecond;
	private JSpinner spinnerMeridiem;
	private JButton btnSave;
	private JButton btnCancel;
	
	
	/**
	 * Create the frame.
	 */
	public Change() {
		setTitle("Set Time");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.add(new DatePanel());
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC,
				ColumnSpec.decode("20dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu"),
				FormFactory.GLUE_COLSPEC,},
			new RowSpec[] {
				FormFactory.GLUE_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.MIN_ROWSPEC,
				FormFactory.GLUE_ROWSPEC,}));
		
		chckbxUse24HourFormat = new JCheckBox("Use 24 hour format");
		chckbxUse24HourFormat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		chckbxUse24HourFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.remove(spinnerHour);
				panel_1.remove(spinnerMeridiem);
				spinnerHour = new JSpinner();
				spinnerHour.setModel(new SpinnerNumberModel(0, 0, 23, 1));
				panel_1.add(spinnerHour, "2, 4");
				
				panel_1.revalidate();
				panel_1.repaint();
				
			}
		});
		panel_1.add(chckbxUse24HourFormat, "2, 2, 7, 1, fill, default");
		
		spinnerHour = new JSpinner();
		spinnerHour.setModel(new SpinnerNumberModel(0, 0, 12, 1));
		panel_1.add(spinnerHour, "2, 4");
		
		spinnerMinute = new JSpinner();
		spinnerMinute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		panel_1.add(spinnerMinute, "4, 4");
		
		spinnerSecond = new JSpinner();
		spinnerSecond.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		panel_1.add(spinnerSecond, "6, 4");
		
		spinnerMeridiem = new JSpinner();
		spinnerMeridiem.setModel(new SpinnerListModel(new String[] {"AM", "PM"}));
		panel_1.add(spinnerMeridiem, "8, 4");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		panel_1.add(btnSave, "2, 6, 3, 1");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel_1.add(btnCancel, "6, 6, 3, 1, default, top");
	}

}
