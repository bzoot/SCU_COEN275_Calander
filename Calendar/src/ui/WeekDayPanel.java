package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * The mid panel displays static week days.
 * @Title WeekDayPanel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-9 14:28:04
 * @version V1.0
 */

@SuppressWarnings("serial")
public class WeekDayPanel extends JPanel {
	
	/**
	 * An array of {@link UnitLabel} which contains seven days' name.
	 */
	private final UnitLabel[] week = {new UnitLabel("Sunday"), new UnitLabel("Monday"),
			new UnitLabel("Tuesday"), new UnitLabel("Wednesday"), new UnitLabel("Thursday"),
			new UnitLabel("Friday"), new UnitLabel("Saturday")};

	/**
	 * Create a default {@link WeekDayPanel}.
	 */
	private WeekDayPanel() {
		this.setLayout(new GridLayout(1, 7, 5, 0));
		this.setPreferredSize(new Dimension(1010, 50));
		this.setBackground(new Color(110, 196, 222));
		
		for (int i = 0; i < 7; i++) {
			this.add(week[i]);
		}
	}
	
	private static WeekDayPanel w;
	
	/**
	 * Singleton object.
	 * @return The {@link WeekDayPanel} object.
	 */
	public static WeekDayPanel getInstance() {
		if (null == w) {
			w = new WeekDayPanel();
		}
		return w;
	}
	
}
