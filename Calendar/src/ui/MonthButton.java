package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Buttons for skip to certain month.
 * @Title MonthButton.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-2-13 11:26:19
 * @version V1.0
 */

@SuppressWarnings("serial")
public class MonthButton extends JButton {

	/**
	 * Full name of months.
	 */
	private final String[] fullMonths = {"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"};
	
	/**
	 * Short name of months.
	 */
	private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	/**
	 * Index of the month - 1.
	 */
	private int month;
	
	/**
	 * Create the {@link MonthButton} of certain month
	 * @param i Index of the month - 1.
	 */
	public MonthButton(int i) {
		this.month = i;
		
		this.setPreferredSize(new Dimension(40, 40));
		this.setMargin(new Insets(0,0,0,0));
		this.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		this.setText(months[i]);
		this.setToolTipText(fullMonths[i]);
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (month) {
				case 0:
					CalendarFrame.skipToMonth(0);
					break;
				case 1:
					CalendarFrame.skipToMonth(580);
					break;
				case 2:
					CalendarFrame.skipToMonth(1160);
					break;
				case 3:
					CalendarFrame.skipToMonth(1885);
					break;
				case 4:
					CalendarFrame.skipToMonth(2465);
					break;
				case 5:
					CalendarFrame.skipToMonth(3045);
					break;
				case 6:
					CalendarFrame.skipToMonth(3770);
					break;
				case 7:
					CalendarFrame.skipToMonth(4350);
					break;
				case 8:
					CalendarFrame.skipToMonth(5075);
					break;
				case 9:
					CalendarFrame.skipToMonth(5655);
					break;
				case 10:
					CalendarFrame.skipToMonth(6235);
					break;
				case 11:
					CalendarFrame.skipToMonth(6960);
					break;
				default: 
					break;
				}
			}			
		});
	}
	
}
