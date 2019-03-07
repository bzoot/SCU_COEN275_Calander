package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Label in {@link DayPanel} and {@link WeekDayPanel}.
 * @Title UnitLabel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-9 14:29:44
 * @version V1.0
 */

@SuppressWarnings("serial")
public class UnitLabel extends JLabel {
	
	/**
	 * Create a {@link UnitLabel} with text.
	 * @param text The text in the {@link UnitLabel}.
	 */
	public UnitLabel(String text) {
		super(text);
		
		this.setLayout(new FlowLayout());
		this.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setPreferredSize(new Dimension(1020, 30));
	}
	
}
