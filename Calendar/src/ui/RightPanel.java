package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * New right panel for adding events.
 * @Title RightPanel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-3-2 18:01:25
 * @version V1.0
 */

@SuppressWarnings("serial")
public class RightPanel extends JPanel {

	private final JLabel title = new JLabel("Customize Calendar");
	private final JLabel eventName = new JLabel("Event Name");
	private final JLabel eventDate = new JLabel("Event Date");
	/**
	 * Text field for event name.
	 */
	private JTextField name = new JTextField(10);
	/**
	 * Text field for event date.
	 */
	private JTextField date = new JTextField("MM-dd-yyyy");
	/**
	 * Current selected date. Can be change by click {@link DayPanel} or successfully add a new event.
	 */
	private String selectedDate = "";
	private JButton addEvent = new JButton("Add Event");
	private final JLabel chooseColor = new JLabel("Change Text Color");
	/**
	 * Name for {@link #colors}.
	 */
	private final String[] c = new String[] { "Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Magenta" };
	/**
	 * Value for {@link #colors}.
	 */
	private final Color[] rc = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE,
			Color.MAGENTA };
	/**
	 * Single select to choose color for events.
	 */
	private JComboBox<String> colors = new JComboBox<String>(c);
	private final JLabel events = new JLabel("Events");
	/**
	 * Panel for {@link #events}.
	 */
	private JPanel checkP = new JPanel();
	/**
	 * Scroll panel for {@link #checkP}.
	 */
	private JScrollPane jsp = new JScrollPane(checkP);
	private JButton deletEvent = new JButton("Delete Event");

	/**
	 * All {@link DayPanel} from {@link CalendarPanel}.
	 */
	private ArrayList<ArrayList<DayPanel>> days = CalendarPanel.getInstance().getDays();

	/**
	 * Create a default {@link RightPanel}.
	 */
	private RightPanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(370, 780));

		title.setBounds(90, 10, 210, 30);
		title.setFont(new Font("Times New Roman", Font.BOLD, 24));
		eventName.setBounds(20, 50, 130, 30);
		eventName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		eventDate.setBounds(170, 50, 70, 30);
		eventDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		name.setBounds(20, 90, 130, 30);
		date.setBounds(170, 90, 70, 30);
		date.setForeground(Color.GRAY);
		date.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (null != date.getText() && date.getText().equals("MM-dd-yyyy")) {
					date.setForeground(Color.BLACK);
					date.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (null == date.getText() || date.getText().length() <= 0) {
					date.setForeground(Color.GRAY);
					date.setText("MM-dd-yyyy");
				}
			}
		});
		addEvent.setBounds(260, 90, 100, 30);
		chooseColor.setBounds(20, 130, 200, 30);
		chooseColor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		colors.setBounds(20, 170, 200, 30);
		events.setBounds(20, 210, 200, 30);
		events.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkP.setLayout(null);
		jsp.setPreferredSize(new Dimension(330, 330));
		jsp.setBounds(20, 250, 330, 330);
		deletEvent.setBounds(115, 590, 150, 30);

		addEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = date.getText();
				if (null != s && s.substring(s.lastIndexOf('-') + 1).equals("2019")) {
					try {
						DayPanel d = days.get(Integer.parseInt(s.substring(0, s.indexOf('-'))))
								.get(Integer.parseInt(s.substring(s.indexOf('-') + 1, s.lastIndexOf('-'))) - 1);
						selectedDate = s;
						events.setText("Events of " + s);
						String n = name.getText();
						JCheckBox check = new JCheckBox(n);
						check.setFont(new Font("Times New Roman", Font.PLAIN, 16));
						d.getAddedEvents().add(check);
						for (Component comp : checkP.getComponents()) {
							checkP.remove(comp);
						}
						for (int i = 0; i < d.getAddedEvents().size(); i++) {
							JCheckBox jcb = d.getAddedEvents().get(i);
							jcb.setBounds(15, 10 + 40 * i, 270, 30);
							checkP.add(jcb);
						}
						checkP.setPreferredSize(new Dimension(300, 40 * d.getAddedEvents().size()));
						checkP.revalidate();
						checkP.repaint();
						n = n.length() > 15 ? n.substring(0, 15) + "..." : n;
						UnitLabel ul = new UnitLabel(n);
						ul.setFont(new Font("Times New Roman", Font.PLAIN, 16));
						ul.setPreferredSize(new Dimension(600, 18));
						ul.setForeground(rc[colors.getSelectedIndex()]);
						d.add(ul);
						ArrayList<UnitLabel> uls = d.getAddedEventsTitle();
						uls.add(ul);
						d.setAddedEventsTitle(uls);
						d.revalidate();
						d.repaint();
					} catch (IndexOutOfBoundsException | NumberFormatException e1) {
						JOptionPane.showMessageDialog(new JPanel(),
								"Wrong date format! Month and/or day is not allowed!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JPanel(), "Wrong date format! Year must be 2019!",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		deletEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (null != selectedDate && selectedDate.substring(selectedDate.lastIndexOf('-') + 1).equals("2019")) {
					try {
						DayPanel d = days.get(Integer.parseInt(selectedDate.substring(0, selectedDate.indexOf('-'))))
								.get(Integer.parseInt(selectedDate.substring(selectedDate.indexOf('-') + 1,
										selectedDate.lastIndexOf('-'))) - 1);
						for (UnitLabel ul : d.getAddedEventsTitle()) {
							d.remove(ul);
						}
						for (int i = 0; i < d.getAddedEvents().size(); i++) {
							if (d.getAddedEvents().get(i).isSelected()) {
								ArrayList<JCheckBox> jcbs = d.getAddedEvents();
								jcbs.remove(i);
								d.setAddedEvents(jcbs);
								ArrayList<UnitLabel> uls = d.getAddedEventsTitle();
								uls.remove(i--);
								d.setAddedEventsTitle(uls);
							}
						}
						for (Component comp : checkP.getComponents()) {
							checkP.remove(comp);
						}
						for (int i = 0; i < d.getAddedEvents().size(); i++) {
							JCheckBox jcb = d.getAddedEvents().get(i);
							jcb.setBounds(15, 10 + 40 * i, 270, 30);
							checkP.add(jcb);
						}
						for (int i = 0; i < d.getAddedEventsTitle().size(); i++) {
							UnitLabel ul = d.getAddedEventsTitle().get(i);
							d.add(ul);
						}
						checkP.setPreferredSize(new Dimension(300, 40 * d.getAddedEvents().size()));
						checkP.revalidate();
						checkP.repaint();
						d.revalidate();
						d.repaint();
					} catch (IndexOutOfBoundsException e1) {
					}
				}
			}
		});
		colors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (ArrayList<DayPanel> l : days) {
					for (DayPanel d : l) {
						ArrayList<UnitLabel> uls = d.getAddedEventsTitle();
						for (UnitLabel ul : uls) {
							ul.setForeground(rc[colors.getSelectedIndex()]);
						}
						d.revalidate();
						d.repaint();
					}
				}
			}
		});

		this.add(title);
		this.add(eventName);
		this.add(eventDate);
		this.add(name);
		this.add(date);
		this.add(addEvent);
		this.add(chooseColor);
		this.add(colors);
		this.add(events);
		this.add(jsp);
		this.add(deletEvent);
	}

	private static RightPanel r;

	public static RightPanel getInstance() {
		if (null == r) {
			r = new RightPanel();
		}
		return r;
	}

	public JPanel getCheckP() {
		return checkP;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public JTextField getDate() {
		return date;
	}

	public JLabel getEvents() {
		return events;
	}

}
