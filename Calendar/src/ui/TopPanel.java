package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The top panel, includes logo, year and month display.
 * @Title TopPanel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-9 13:40:05
 * @version V1.0
 */

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	
	/**
	 * The logo at top.
	 */
	private final JLabel logo = new JLabel(new ImageIcon("SCULOGO.jpg"));
	
	/**
	 * The year.
	 */
	private final JLabel year = new JLabel("2019-");
	
	/**
	 * Current month.
	 */
	private JLabel monthOrWeekOrDay = new JLabel("");
	
	/**
	 * Panel for month buttons
	 */
	private JPanel buttons = new JPanel();
	
	/**
	 * Panel for alt model.
	 */
	private ModelPanel models = new ModelPanel();
	
	/**
	 * Create a default {@link TopPanel}.
	 */
	public TopPanel() {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1010, 85));
		this.setBackground(new Color(170, 170, 170));
		this.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,0));

		year.setFont(new Font("Times New Roman", Font.PLAIN, 56));
		monthOrWeekOrDay.setFont(new Font("Times New Roman", Font.PLAIN, 56));
		monthOrWeekOrDay.setPreferredSize(new Dimension(260, 85));
		
		buttons.setLayout(new GridLayout(2, 6, 0, 0));
		buttons.setPreferredSize(new Dimension(240,80));
		for (int i = 0; i < 12; i++) {
			buttons.add(new MonthButton(i));
		}
		
		this.add(logo);
		this.add(year);
		this.add(monthOrWeekOrDay);
		this.add(models);
		this.add(buttons);
	}
	
	/**
	 * Reset text of {@link #monthOrWeekOrDay} in {@link TopPanel}.
	 * @param month The number of current month.
	 */
	public void setMonthOrWeekOrDay(String month) {
		this.monthOrWeekOrDay.setText(month);
	}

	public ModelPanel getModels() {
		return models;
	}

}
