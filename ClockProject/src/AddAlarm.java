import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javazoom.jl.player.*; //Library to play MP3 file

import com.michaelbaranov.microba.calendar.DatePicker; //Library to pick date

/**
 * @author Minyoung
 *
 */
public class AddAlarm extends JDialog {
	private final JPanel contentPane = new JPanel();
	private JPanel buttonPane;
	private JButton saveButton;
	private JButton cancelButton;
	private JPanel onoffPane;
	private JPanel repeatPane;
	private JCheckBox chckbxOnoff;
	private JTextField txtName;
	private JTextField txtSong;
	private JSpinner spinnerHour;
	private JSpinner spinnerMin;
	private JSpinner spinnerMer;
	private JButton btnChooseSong;
	private JCheckBox chckbxRepeat;
	private JLabel lblTo;
	private JPanel panel;
	private JPanel panelTo;
	private JPanel panelFrom;

	private final DatePicker dateFrom = new DatePicker(new Date());
	private final DatePicker dateTo = new DatePicker(new Date());

	private final Calendar from = Calendar.getInstance();
	private final Calendar to = Calendar.getInstance();

	private JCheckBox[] chkLst = new JCheckBox[8];
	private String[] days = { "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri",	"Sat" };

	/*
	 * Variables to pass the values to parent frame
	 */
	private boolean saveStatus = false;
	private String name;
	private int hour;
	private int minute;
	private String day = "";
	private Player p;

	JDialog dialog = this;

	/**
	 * Create the dialog.
	 */
	public AddAlarm(JFrame parent) {
		super(parent, true);

		setBounds(100, 100, 534, 342);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		{
			onoffPane = new JPanel();
			contentPane.add(onoffPane);
			onoffPane.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC, }));

			chckbxOnoff = new JCheckBox("On/Off");
			onoffPane.add(chckbxOnoff, "2, 2");

			txtName = new JTextField();
			onoffPane.add(txtName, "2, 4, fill, default");
			txtName.setColumns(10);

			spinnerHour = new JSpinner();
			spinnerHour.setModel(new SpinnerNumberModel(1, 1, 12, 1));
			onoffPane.add(spinnerHour, "4, 4");

			spinnerMin = new JSpinner();
			spinnerMin.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			onoffPane.add(spinnerMin, "6, 4");

			spinnerMer = new JSpinner();
			spinnerMer.setModel(new SpinnerListModel(
					new String[] { "AM", "PM" }));
			onoffPane.add(spinnerMer, "8, 4");

			txtSong = new JTextField();
			onoffPane.add(txtSong, "2, 6, fill, default");
			txtSong.setColumns(10);

			btnChooseSong = new JButton("Choose Song");
			btnChooseSong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// create file chooser and show it
					JFileChooser fc = new JFileChooser();
					fc.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3")); //file chooser will get only mp3 files
					int returnVal = fc.showOpenDialog(AddAlarm.this);

					// get file and put into player p
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							File file = fc.getSelectedFile();
							String path = file.getAbsolutePath();
							p = new Player(new FileInputStream(path)); // save file's path into player
							txtSong.setText(fc.getSelectedFile().getName()); // update label as selected mp3 file name
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			onoffPane.add(btnChooseSong, "4, 6, 5, 1");
		}
		{
			repeatPane = new JPanel();
			contentPane.add(repeatPane);
			repeatPane.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(100dlu;default)"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(35dlu;default)"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(100dlu;default)"),
					FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC, }));

			chckbxRepeat = new JCheckBox("Repeat");
			chckbxRepeat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// use checkboxes and date picker when repeat check box is checked
					if(chckbxRepeat.isSelected()) {
						dateFrom.setEnabled(true);
						dateTo.setEnabled(true);
						for (int i = 1; i < 8; i++) {
							chkLst[i].setEnabled(true);
						}
					} else {
						dateFrom.setEnabled(false);
						dateTo.setEnabled(false);
						for (int i = 1; i < 8; i++) {
							chkLst[i].setEnabled(false);
						}
					}
				}
			});
			repeatPane.add(chckbxRepeat, "2, 2");

			panelFrom = new JPanel();
			panelFrom.setLayout(new GridLayout(0, 1, 0, 0));
			panelFrom.add(dateFrom);
			repeatPane.add(panelFrom, "2, 4, fill, center");
			
			dateFrom.setEnabled(false);
			dateFrom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						from.setTime(dateFrom.getDate());
						checkFromTo(from, to);
					} catch (WrongCheckedException e1) {
						JOptionPane.showMessageDialog(null,"First Date should before than Second Date");
					}
				}
			});

			lblTo = new JLabel("to");
			lblTo.setHorizontalAlignment(SwingConstants.CENTER);
			repeatPane.add(lblTo, "4, 4, fill, fill");

			panelTo = new JPanel();
			repeatPane.add(panelTo, "6, 4, fill, center");
			panelTo.setLayout(new GridLayout(0, 1, 0, 0));
			panelTo.add(dateTo);
			
			dateTo.setEnabled(false);
			dateTo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						to.setTime(dateTo.getDate());
						checkFromTo(from, to);
					} catch (WrongCheckedException e1) {
						JOptionPane.showMessageDialog(null,"First Date should before than Second Date");
					}
				}
			});		

			panel = new JPanel();
			repeatPane.add(panel, "2, 6, 5, 1, fill, fill");
			panel.setLayout(new GridLayout(1, 0, 0, 0));

			// get String from days array and create CheckList
			for (int i = 1; i < 8; i++) {
				chkLst[i] = new JCheckBox(days[i]);
				panel.add(chkLst[i]);
				chkLst[i].setEnabled(false);
			}
		}
		{
			buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						name = txtName.getText();
						
						// 12AM will be 0, 12PM will be 12 and use 24hour format
						if (spinnerMer.getValue() == "AM")
							hour = (Integer) spinnerHour.getValue();
						else
							hour = (Integer) spinnerHour.getValue() + 12;
						if (spinnerMer.getValue() == "AM"
								&& (Integer) spinnerHour.getValue() == 12)
							hour = 0;
						if (spinnerMer.getValue() == "PM"
								&& (Integer) spinnerHour.getValue() == 12)
							hour = 12;

						minute = (Integer) spinnerMin.getValue();

						setDay();
						saveStatus = true; // if sucessfully saved, set true the saveStatus flag
						setVisible(false); // close the dialog
					}
				});
				saveButton.setActionCommand("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						name = "";
						hour = 0;
						minute = 0;
						day = "";
						saveStatus = false; // if it's not saved, set false
						setVisible(false);						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	/*
	 * Setters
	 */
	
	/* set chckbxOnoff JCheckBox */
	public void setOnOffStatus(boolean onoff) {
		chckbxOnoff.setSelected(onoff);
	}
	
	/* set String day to add on AlarmPanel */
	public void setDay() {
		day = "";
		for (int i = 1; i < 8; i++) {
			if (chkLst[i].isSelected() == true) {
				if (day == "")
					day = days[i];
				else
					day = day + ", " + days[i];
			}
		}
	}
	
	/*
	 * Getters
	 */

	public boolean getSaveStatus() {
		return saveStatus;
	}

	public boolean getOnOffStatus() {
		return chckbxOnoff.isSelected();
	}

	public boolean getRepeatStatus() {
		return chckbxRepeat.isSelected();
	}

	public String getName() {
		return name;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public String getDay() {
		return day;
	}

	public JCheckBox[] getDays() {
		return chkLst;
	}

	public String getTime() {
		String time = "" + hour + ":";
		if (minute < 10) {
			time = time + "0" + minute;
		} else {
			time = time + minute;
		} // to make time String looks like H:MM
		
		return time;
	}

	public Player getMusic() {
		return p;
	}

	public Calendar getFrom() {
		return from;
	}

	public Calendar getTo() {
		return to;
	}
	
	
	/*
	 * Exception for check 'from' is before 'to'
	 */
	public class WrongCheckedException extends Exception {
		public WrongCheckedException(Calendar from, Calendar to) {			
		}
	}
	public void checkFromTo(Calendar from, Calendar to) throws WrongCheckedException {
		if(from.after(to)) 
			throw new WrongCheckedException(from, to);
	}
}