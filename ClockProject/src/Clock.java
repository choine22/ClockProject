import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.FlowLayout;


public class Clock extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel Clock;
	private JLabel lblTime;
	private JLabel lblDate;
	private JPanel panel;
	private JButton btnChange;
	private JPanel Alarm;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clock frame = new Clock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Clock() {
		setTitle("Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		Clock = new JPanel();
		tabbedPane.addTab("Clock", null, Clock, null);
		Clock.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		Clock.add(lblTime);
		
		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		Clock.add(lblDate);
		
		panel = new JPanel();
		Clock.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnChange = new JButton("Change date and time");
		panel.add(btnChange);
		
		Alarm = new JPanel();
		tabbedPane.addTab("Alarm", null, Alarm, null);
		Alarm.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		Alarm.add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.add(panel_1);
		
		btnAdd = new JButton("Add");
		Alarm.add(btnAdd, BorderLayout.SOUTH);
	}

}
