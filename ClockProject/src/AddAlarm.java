import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import javazoom.jl.player.*;

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

	private JCheckBox[] chkLst = new JCheckBox[7];
	private String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };

	// Variables to pass the values to parent frame
	boolean saveStatus = false;
	String name;
	int hour;
	int minute;
	String day = "";
	Player p;

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
					fc.setFileFilter(new FileNameExtensionFilter("MP3 Files",
							"mp3"));
					int returnVal = fc.showOpenDialog(AddAlarm.this);

					// check if user action
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							File file = fc.getSelectedFile();
							String path = file.getAbsolutePath();
							p = new Player(new FileInputStream(path));
							txtSong.setText(fc.getSelectedFile().getName()); // update label as selected mp3 file
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
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC, }));

			chckbxRepeat = new JCheckBox("Repeat");
			repeatPane.add(chckbxRepeat, "2, 2");

			panelFrom = new JPanel();
			repeatPane.add(panelFrom, "2, 4, fill, fill");
			panelFrom.add(new DatePicker());

			lblTo = new JLabel("to");
			lblTo.setHorizontalAlignment(SwingConstants.CENTER);
			repeatPane.add(lblTo, "4, 4, center, default");

			panelTo = new JPanel();
			repeatPane.add(panelTo, "6, 4, fill, fill");
			panelTo.add(new DatePicker());

			panel = new JPanel();
			repeatPane.add(panel, "2, 6, 5, 1, fill, fill");
			panel.setLayout(new GridLayout(1, 0, 0, 0));

			for (int i = 0; i < 7; i++) {
				chkLst[i] = new JCheckBox(days[i]);
				panel.add(chkLst[i]);
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
						if (spinnerMer.getValue() == "AM")
							hour = (Integer) spinnerHour.getValue();
						else
							hour = (Integer) spinnerHour.getValue() + 12;

						minute = (Integer) spinnerMin.getValue();

						for (int i = 0; i < 7; i++) {
							if (chkLst[i].isSelected() == true) {
								if (day == "")
									day = days[i];
								else
									day = day + ", " + days[i];
							}
						}
						saveStatus = true;
						setVisible(false);
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
						setVisible(false);
						saveStatus = false;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

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
	
	public String getTime() {
		String time = ""+hour+":";
		if(minute<10) {
			time = time+"0"+minute;
		} else {
			time = time+minute;
		}
		return time;
	}

	public Player getMusic() {
		return p;
	}
}