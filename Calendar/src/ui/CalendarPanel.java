package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The main bottom panel, includes all {@link DayPanel}.
 * 
 * @Title CalendarPanel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-9 15:29:53
 * @version V1.0
 */

@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {

	/**
	 * The 2-dimension list stores all {@link DayPanel}
	 */
	private ArrayList<ArrayList<DayPanel>> days = new ArrayList<ArrayList<DayPanel>>();

	/**
	 * The list stores all {@link DayPanel}
	 */
	private ArrayList<DayPanel> dayByDay = new ArrayList<DayPanel>();

	/**
	 * Array of weekday.
	 */
	private String[] weekday = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday",
											"Thursday", "Friday", "Saturday" };

	/**
	 * Create a default {@link CalendarPanel}
	 */
	@SuppressWarnings("unchecked")
	private CalendarPanel() {
		this.setLayout(new GridLayout(53, 7, 5, 5));
		this.setBackground(new Color(170, 170, 170));

		File file = new File("Days.dat");
		if (file.exists() && file.canRead()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				days = (ArrayList<ArrayList<DayPanel>>) ois.readObject();
				for (ArrayList<DayPanel> l : days) {
					for (DayPanel d : l) {
						this.dayByDay.add(d);
						this.add(d);
					}
				}
				ois.close();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				constructWithoutFile();
			}
		} else {
			constructWithoutFile();
		}
	}

	private static CalendarPanel c;

	/**
	 * Singleton object.
	 * 
	 * @return The {@link CalendarPanel} object.
	 */
	public static CalendarPanel getInstance() {
		if (null == c) {
			c = new CalendarPanel();
		}
		return c;
	}

	public ArrayList<ArrayList<DayPanel>> getDays() {
		return this.days;
	}

	public ArrayList<DayPanel> getDayByDay() {
		return dayByDay;
	}

	/**
	 * Repaint all {@link DayPanel} to proper style
	 * 
	 * @param i
	 *            The number of current month.
	 */
	public void repaintDaysMonthly(int i) {
		for (int j = 0; j < 14; j++) {
			ArrayList<DayPanel> list = this.days.get(j);
			if (j == i) {
				for (DayPanel d : list) {
					d.day.setBackground(new Color(173, 216, 230));
					d.setBackground(Color.WHITE);
				}
			} else {
				for (DayPanel d : list) {
					d.day.setBackground(new Color(160, 160, 160));
					d.setBackground(new Color(150, 150, 150));
				}
			}
		}
	}

	/**
	 * Repaint all {@link DayPanel} to proper style
	 * 
	 * @param i
	 *            The number of current week.
	 */
	public void repaintDaysWeekly(int i) {
		for (int j = 0; j < 53; j++) {
			if (j == i) {
				for (int d = 0; d < 7; d++) {
					DayPanel dp = dayByDay.get(7 * j + d);
					dp.day.setBackground(new Color(173, 216, 230));
					dp.setBackground(Color.WHITE);
				}
			} else {
				for (int d = 0; d < 7; d++) {
					DayPanel dp = dayByDay.get(7 * j + d);
					dp.day.setBackground(new Color(160, 160, 160));
					dp.setBackground(new Color(150, 150, 150));
				}
			}
		}
	}

	/**
	 * Repaint all {@link DayPanel} to proper style
	 * 
	 * @param i
	 *            The number of current day.
	 */
	public void repaintDaysDaily(int i) {
		for (int j = 0; j < dayByDay.size(); j++) {
			if (j == i) {
				DayPanel dp = dayByDay.get(j);
				dp.day.setBackground(new Color(173, 216, 230));
				dp.setBackground(Color.WHITE);
			} else {
				DayPanel dp = dayByDay.get(j);
				dp.day.setBackground(new Color(160, 160, 160));
				dp.setBackground(new Color(150, 150, 150));
			}
		}
	}
	
	/**
	 * Constructor when there is no input file.
	 */
	private void constructWithoutFile () {
		int j = -1;
		for (int l = 0; l < 53; l++) {
			for (int m = 0; m < 7; m++) {
				String[] k = getDate(j++);
				int month = Integer.parseInt(k[3]);
				DayPanel day;
				if (k[1].length() > 0 && k[2].length() > 0) {
					day = new DayPanel(k[0], Color.RED, k[2]);
				} else if (k[1].length() > 0) {
					day = new DayPanel(k[0], Color.RED);
				} else if (k[2].length() > 0) {
					day = new DayPanel(k[0], k[2]);
				} else {
					day = new DayPanel(k[0]);
				}
				day.month = month;
				day.weekDay = weekday[m];
				this.add(day);
				if (month == days.size()) {
					ArrayList<DayPanel> list = new ArrayList<DayPanel>();
					day.date = 1;
					list.add(day);
					days.add(list);
				} else {
					ArrayList<DayPanel> list = days.get(month);
					day.date = list.size() + 1;
					list.add(day);
				}
				dayByDay.add(day);
			}
		}
	}

	/**
	 * Calculate parameters for create {@link DayPanel}
	 * 
	 * @param i
	 *            The day of the year.
	 * @return An array includes the date, whether it is the first day of a
	 *         month, any holiday and month index.
	 */
	private String[] getDate(int i) {
		// res[0] date
		// res[1] whether it is the first day of a month
		// res[2] any holiday
		// res[3] month index
		String[] res = { "", "", "", "" };

		int j = 0;
		// Condition is the day of the first day of the month
		if (i > 365) {
			j = i - 365;
			res[0] = "Jan-" + j;
			res[3] = "13";
			if (j == 1) {
				res[2] = "New Year’s Day";
			} else if (j == 21) {
				res[2] = "<html><body>Birthday of Martin <br/> Luther King, Jr.</html>";
			}
		} else if (i > 334) {
			j = i - 334;
			res[0] = "Dec-" + j;
			res[3] = "12";
			if (j == 25) {
				res[2] = "Christmas Day";
			}
		} else if (i > 304) {
			j = i - 304;
			res[0] = "Nov-" + j;
			res[3] = "11";
			if (j == 11) {
				res[2] = "Veterans Day";
			} else if (j == 28) {
				res[2] = "Thanksgiving Day";
			}
		} else if (i > 273) {
			j = i - 273;
			res[0] = "Oct-" + j;
			res[3] = "10";
			if (j == 14) {
				res[2] = "Columbus Day";
			}
		} else if (i > 243) {
			j = i - 243;
			res[0] = "Sep-" + j;
			res[3] = "9";
			if (j == 2) {
				res[2] = "Labor Day";
			}
		} else if (i > 212) {
			j = i - 212;
			res[0] = "Aug-" + j;
			res[3] = "8";
		} else if (i > 181) {
			j = i - 181;
			res[0] = "Jul-" + j;
			res[3] = "7";
			if (j == 4) {
				res[2] = "Independence Day";
			}
		} else if (i > 151) {
			j = i - 151;
			res[0] = "Jun-" + j;
			res[3] = "6";
		} else if (i > 120) {
			j = i - 120;
			res[0] = "May-" + j;
			res[3] = "5";
			if (j == 27) {
				res[2] = "Memorial Day";
			}
		} else if (i > 90) {
			j = i - 90;
			res[0] = "Apr-" + j;
			res[3] = "4";
		} else if (i > 59) {
			j = i - 59;
			res[0] = "Mar-" + j;
			res[3] = "3";
		} else if (i > 31) {
			j = i - 31;
			res[0] = "Feb-" + j;
			res[3] = "2";
			if (j == 18) {
				res[2] = "Washington’s Birthday";
			}
		} else if (i > 0) {
			j = i;
			res[0] = "Jan-" + i;
			res[3] = "1";
			if (j == 1) {
				res[2] = "New Year’s Day";
			} else if (j == 21) {
				res[2] = "<html><body>Birthday of Martin <br/> Luther King, Jr.</html>";
			}
		} else {
			j = i + 31;
			res[0] = "Dec-" + j;
			res[3] = "0";
			if (j == 25) {
				res[2] = "Christmas Day";
			}
		}

		if (j == 1) {
			res[1] = "1";
		}

		return res;
	}

}
