import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.BoxLayout;
import java.awt.Font;

public class Clock extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel Clock;
	private JLabel lblTime;
	private JLabel lblDate;
	private JPanel panel;
	private JButton btnChange;
	private JPanel Alarm;
	private JScrollPane scrollPane;
	private JButton btnAdd;

	JFrame frame = this;

	private Change changeFrame;
	private JPanel frm;

	CheckAlarm worker;

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
		Clock.setLayout(new GridLayout(0, 1, 0, 0));

		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		Clock.add(lblDate);

		panel = new JPanel();
		Clock.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnChange = new JButton("Change date and time");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// open the window to change date and time
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							changeFrame = new Change();
							changeFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(btnChange);
		
		
		/*
		 * This is for Alarm 
		 */
		Alarm = new JPanel();
		tabbedPane.addTab("Alarm", null, Alarm, null);
		Alarm.setLayout(new BorderLayout(0, 0));

		frm = new JPanel();
		
		scrollPane = new JScrollPane(frm);
		frm.setLayout(new GridLayout(0, 1, 0, 0));
		Alarm.add(scrollPane);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final AddAlarm addDlg = new AddAlarm(frame);
				addDlg.setVisible(true);
				
				if(addDlg.getSaveStatus() == true) {
					final AlarmPanel pn = new AlarmPanel(addDlg);
					pn.setAlarmName("   "+addDlg.getTime()+" - "+addDlg.getName());
					pn.setDays("   "+addDlg.getDay());
					
					// update GUI
					frm.add(pn);
					frm.revalidate();
					frm.repaint();					

					pn.btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// remove panel and update GUI
							frm.remove(pn);
							frm.revalidate();
							frm.repaint();
						}
					});	
				}
			}
		});	
		Alarm.add(btnAdd, BorderLayout.SOUTH);
	}
}
