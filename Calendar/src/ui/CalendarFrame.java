package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * Main frame for the application
 * @Title CalendarFrame.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-9 14:14:22
 * @version V1.0
 */

@SuppressWarnings("serial")
public class CalendarFrame extends JFrame {

	/**
	 * The vertical {@link JScrollBar} of the {@link CalendarPanel}.
	 */
	private static JScrollBar jsb;
	
	/**
	 * Create a default {@link CalendarFrame}.
	 */
	public CalendarFrame() {
		TopPanel t = new TopPanel();
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		//jp1.add(t, BorderLayout.NORTH);
		jp1.add(WeekDayPanel.getInstance(), BorderLayout.SOUTH);

		CalendarPanel c = CalendarPanel.getInstance();
		JScrollPane jsp1 = new JScrollPane(c, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp1.setPreferredSize(new Dimension(1010, 0));
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout());
		jp2.add(jp1, BorderLayout.NORTH);
		jp2.add(jsp1, BorderLayout.CENTER);

		jsb = jsp1.getVerticalScrollBar();
		jsb.setUnitIncrement(23);
		jsb.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				ModelPanel mp = t.getModels();
				//Use scrollbar value to get position of scrollpane
				int i = (arg0.getValue());
				if (mp.isMonthly()) {
					//Each grid has a height 140px, gap for each row is 5px
					//Calculate month, reset month label in TopPanel and repaint all DayPanel
					if (i >= 6670) {
						t.setMonthOrWeekOrDay("December");
						c.repaintDaysMonthly(12);
					} else if (i >= 5945) {
						t.setMonthOrWeekOrDay("November");
						c.repaintDaysMonthly(11);
					} else if (i >= 5365) {
						t.setMonthOrWeekOrDay("October");
						c.repaintDaysMonthly(10);
					} else if (i >= 4785) {
						t.setMonthOrWeekOrDay("September");
						c.repaintDaysMonthly(9);
					} else if (i >= 4060) {
						t.setMonthOrWeekOrDay("August");
						c.repaintDaysMonthly(8);
					} else if (i >= 3480) {
						t.setMonthOrWeekOrDay("July");
						c.repaintDaysMonthly(7);
					} else if (i >= 2755) {
						t.setMonthOrWeekOrDay("June");
						c.repaintDaysMonthly(6);
					} else if (i >= 2175) {
						t.setMonthOrWeekOrDay("May");
						c.repaintDaysMonthly(5);
					} else if (i >= 1595) {
						t.setMonthOrWeekOrDay("April");
						c.repaintDaysMonthly(4);
					} else if (i >= 870) {
						t.setMonthOrWeekOrDay("March");
						c.repaintDaysMonthly(3);
					} else if (i >= 290) {
						t.setMonthOrWeekOrDay("February");
						c.repaintDaysMonthly(2);
					} else {
						t.setMonthOrWeekOrDay("January");
						c.repaintDaysMonthly(1);
					}
				} else if (mp.isWeekly()) {
					int week = (i + 362) / 725;
					t.setMonthOrWeekOrDay("Week " + (week + 1));
					c.repaintDaysWeekly(week);
				} else if (mp.isDaily()) {
					int day = (i + 388) / 775;
					t.setMonthOrWeekOrDay(day > 1 ? day < 367 ? "Day " + ( day - 1) : "Next year" : "Last year");
					c.repaintDaysDaily(day);
				}
			}
		});

		JScrollPane jsp2 = new JScrollPane(jp2, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp2.setPreferredSize(new Dimension(1034, 770));
		
		JPanel whole = new JPanel();
		whole.setLayout(new BoxLayout(whole, BoxLayout.Y_AXIS));
		whole.add(t);
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bottom.add(jsp2);
		bottom.add(RightPanel.getInstance());
		bottom.setMaximumSize(new Dimension(4000, 780));
		whole.add(bottom);
		this.add(new JScrollPane(whole));

		this.setSize(1430, 910);
		this.setMinimumSize(new Dimension(550, 250));
		this.setTitle("Calendar");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					t.getModels().setMonthly();
					File file = new File("Days.dat");
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(CalendarPanel.getInstance().getDays());
					oos.flush();
					oos.close();
				} catch (IOException | SecurityException | NullPointerException e1) {}
			}
		});
	}
	
	/**
	 * Set the value of {@link #jsb}.
	 * @param i
	 */
	public static void skipToMonth(int i) {
		DayPanel d = CalendarPanel.getInstance().getDays().get(0).get(0);
		int w = d.getWidth() + 5;
		int h = d.getHeight() + 5;
		if (w < 1010) {
			jsb.setValue(i / 145 * h);
		} else {
			int v = i / 145 * h * 7;
			switch (i) {
				case 0: 
					v += 2 * h;
					break;
				case 580: 
					v += 5 * h;
					break;
				case 1160: 
					v += 5 * h;
					break;
				case 1885: 
					v += 1 * h;
					break;
				case 2465: 
					v += 3 * h;
					break;
				case 3045: 
					v += 6 * h;
					break;
				case 3770: 
					v += 1 * h;
					break;
				case 4350: 
					v += 4 * h;
					break;
				case 5655: 
					v += 2 * h;
					break;
				case 6235: 
					v += 5 * h;
					break;
				default: break;
			}
			jsb.setValue(v);
		}
	}

	/**
	 * Entrance of the application.
	 * @param args
	 */
	public static void main(String[] args) {
		new CalendarFrame();
	}

}
