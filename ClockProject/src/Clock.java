import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;

import java.awt.Font;

import javax.swing.BoxLayout;

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
	private JList list;
	private JPanel frm; 
 	private JButton btnEdit; 
 	private JButton btnDelete; 
 	
 	Thread workThread;
	
	private SimpleDateFormat currentTime;
	private SimpleDateFormat currentDate;

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

		Date dt = new Date();
		currentTime = new SimpleDateFormat("hh:mm:ss");
		currentDate = new SimpleDateFormat("E, M d, y");
		
		Clock = new JPanel();
		tabbedPane.addTab("Clock", null, Clock, null);
		Clock.setLayout(new GridLayout(0, 1, 0, 0));

		lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setText(currentTime.format(dt).toString());
		Clock.add(lblTime);
		Clock.setLayout(new GridLayout(0, 1, 0, 0));

		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setText(currentDate.format(dt).toString());
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

		Alarm = new JPanel(); 
		tabbedPane.addTab("Alarm", null, Alarm, null); 
		Alarm.setLayout(new BorderLayout(0, 0)); 
	 
		 
		frm = new JPanel(); 
	 		 
	 		scrollPane = new JScrollPane(frm); 
	 		frm.setLayout(new BoxLayout(frm, BoxLayout.Y_AXIS)); 
	 		Alarm.add(scrollPane); 
	 
		 
	 		btnAdd = new JButton("Add"); 
	 		btnAdd.addActionListener(new ActionListener() { 
	 			public void actionPerformed(ActionEvent arg0) { 
	 				AddAlarm addDlg = new AddAlarm(frame); 
	 				addDlg.setVisible(true); 
	 
		 
	 				if(addDlg.getSaveStatus() == true) { 
	 					// get Data and make new panel 
	 					JPanel newAlarm = new JPanel(); 
	 					newAlarm.setLayout(new GridLayout(2,0,0,0)); 
	 					newAlarm.setBorder(new LineBorder(new Color(0, 0, 0))); 
	 					newAlarm.setPreferredSize(new Dimension(frm.getWidth(),50)); 
	 					JLabel lblName = new JLabel("   "+addDlg.getTime()+" - "+addDlg.getName()); 
	 					lblName.setFont(new Font("¡¾¨ù¢¬©÷", Font.PLAIN, 16)); 
	 					JLabel lblDays = new JLabel("   "+addDlg.getDay()); 
	 					newAlarm.add(lblName); 
	 					 
	 					btnEdit = new JButton("Edit"); 
	 					newAlarm.add(btnEdit); 
	 					newAlarm.add(lblDays); 
	 					btnDelete = new JButton("Delete"); 
	 					newAlarm.add(btnDelete); 
	 					 
	 					// update GUI 
	 					frm.add(newAlarm); 
	 					frm.revalidate(); 
	 					frm.repaint(); 
	 					 
	 					// add thread worker, it works at only it checked ON 
	 					if(addDlg.getOnOffStatus() == true) { 
	 						workThread = new Thread(new checkAlarm(addDlg)); 
	 						workThread.start(); 
	 					} 
	 				} 
	 			} 
	 		});	 
	 
		 
	 		Alarm.add(btnAdd, BorderLayout.SOUTH); 
		Alarm = new JPanel();
		tabbedPane.addTab("Alarm", null, Alarm, null);
		Alarm.setLayout(new BorderLayout(0, 0));

		frm = new JPanel();
		
		scrollPane = new JScrollPane(frm);
		frm.setLayout(new BoxLayout(frm, BoxLayout.Y_AXIS));
		Alarm.add(scrollPane);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final AddAlarm addDlg = new AddAlarm(frame);
				addDlg.setVisible(true);

				if(addDlg.getSaveStatus() == true) {
					// get Data and make new panel
					final JPanel newAlarm = new JPanel();
					newAlarm.setLayout(new GridLayout(2,0,0,0));
					newAlarm.setBorder(new LineBorder(new Color(0, 0, 0)));
					newAlarm.setPreferredSize(new Dimension(frm.getWidth(),50));
					JLabel lblName = new JLabel("   "+addDlg.getTime()+" - "+addDlg.getName());
					lblName.setFont(new Font("±¼¸²", Font.PLAIN, 16));
					JLabel lblDays = new JLabel("   "+addDlg.getDay());
					newAlarm.add(lblName);
					
					btnEdit = new JButton("Edit");
					newAlarm.add(btnEdit);
					newAlarm.add(lblDays);
					btnDelete = new JButton("Delete");
					newAlarm.add(btnDelete);
					
					// update GUI
					frm.add(newAlarm);
					frm.revalidate();
					frm.repaint();
					
					// add thread worker, it works at only it checked ON
					if(addDlg.getOnOffStatus() == true) {
						workThread = new Thread(new checkAlarm(addDlg));
						workThread.start();
					}
					
					btnEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// show current dialog
							addDlg.setVisible(true);
							
						}
					});
										
					btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// remove panel and update GUI
							frm.remove(newAlarm);
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
