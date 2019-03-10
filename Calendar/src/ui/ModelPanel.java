package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 
 * @Title ModelPanel.java
 * @Package ui
 * @author Pengbin Li
 * @date 2019-3-4 10:47:45
 * @version V1.0
 */

@SuppressWarnings("serial")
public class ModelPanel extends JPanel {
	
	private JRadioButton monthly = new JRadioButton("monthly");
	private JRadioButton weekly = new JRadioButton("weekly");
	private JRadioButton daily = new JRadioButton("daily");
	private ButtonGroup model = new ButtonGroup();
	
	/**
	 * The 2D-list of all {@link DayPanel} objects.
	 */
	private ArrayList<ArrayList<DayPanel>> days = CalendarPanel.getInstance().getDays();
	
	public ModelPanel() {
		monthly.setBackground(new Color(170, 170, 170));
		monthly.setPreferredSize(new Dimension(80, 80));
		monthly.setSelected(true);
		weekly.setBackground(new Color(170, 170, 170));
		weekly.setPreferredSize(new Dimension(80, 80));
		daily.setBackground(new Color(170, 170, 170));
		daily.setPreferredSize(new Dimension(80, 80));
		
		ItemListener m = new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == monthly) {
					WeekDayPanel.getInstance().setVisible(true);
					for (ArrayList<DayPanel> l : days) {
						for (DayPanel d : l) {
							d.setPreferredSize(new Dimension(140, 140));
							String s = d.day.getText();
							int i = s.indexOf(",");
							if (i != -1) {
								s = s.substring(i + 2);
								d.day.setText(s);
							}
							CalendarPanel.getInstance().setLayout(new GridLayout(53, 7, 5, 5));
							d.revalidate();
							d.repaint();
						}
					}
				} else if (e.getSource() == weekly) {
					WeekDayPanel.getInstance().setVisible(true);
					for (ArrayList<DayPanel> l : days) {
						for (DayPanel d : l) {
							d.setPreferredSize(new Dimension(140, 720));
							String s = d.day.getText();
							int i = s.indexOf(",");
							if (i != -1) {
								s = s.substring(i + 2);
								d.day.setText(s);
							}
							CalendarPanel.getInstance().setLayout(new GridLayout(53, 7, 5, 5));
							d.revalidate();
							d.repaint();
						}
					}
				} else if (e.getSource() == daily) {
					WeekDayPanel.getInstance().setVisible(false);
					for (ArrayList<DayPanel> l : days) {
						for (DayPanel d : l) {
							d.setPreferredSize(new Dimension(1010, 770));
							String s = d.day.getText();
							int i = s.indexOf(",");
							if (i == -1) {
								d.day.setText(d.weekDay + ", " + s);
							}
							CalendarPanel.getInstance().setLayout(new GridLayout(371, 1, 5, 5));
							d.revalidate();
							d.repaint();
						}
					}
				}
			}
		};
		monthly.addItemListener(m);
		weekly.addItemListener(m);
		daily.addItemListener(m);
		
		model.add(monthly);
		model.add(weekly);
		model.add(daily);
		
		this.setBackground(new Color(170, 170, 170));
		this.setPreferredSize(new Dimension(260, 80));
		
		this.add(monthly);
		this.add(weekly);
		this.add(daily);
	}
	
	public boolean isMonthly() {
		return monthly.isSelected();
	}
	
	public void setMonthly() {
		monthly.setSelected(true);
	}

	public boolean isWeekly() {
		return weekly.isSelected();
	}

	public boolean isDaily() {
		return daily.isSelected();
	}
	
}
