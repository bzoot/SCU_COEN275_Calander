package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
* The grid for each day.
* @Title DayPanel.java 
* @Package ui 
* @author Pengbin Li   
* @date 2019-2-9 15:30:45
* @version V1.0   
*/

public class DayPanel extends JPanel {
	
	/**
	 * Serial id for store in file.
	 */
	private static final long serialVersionUID = 8378810762316128927L;

	/**
	 * The {@link UnitLabel} includes month and day of this {@link DayPanel}.
	 */
	UnitLabel day;
	
	/**
	 * Month of the day.
	 */
	int month;
	
	/**
	 * Day in the month of the day.
	 */
	int date;
	
	/**
	 * Day of the week of the day.
	 */
	String weekDay;
	
	/**
	 * Check boxes of events which added by user in {@link RightPanel}.
	 */
	private ArrayList<JCheckBox> addedEvents = new ArrayList<JCheckBox>();
	
	/**
	 * Events show in {@link DayPanel}.
	 */
	private ArrayList<UnitLabel> addedEventsTitle = new ArrayList<UnitLabel>();
	
	/**
	 * {@link DayPanel} listener for choose a day.
	 */
	private DayPanelListener listener = new DayPanelListener();
	
	/**
	 * Create a {@link DayPanel}.
	 * @param text Month and day.
	 */
	public DayPanel(String text) {
		UnitLabel ul = new UnitLabel(text);
		ul.setOpaque(true);
		ul.setBackground(new Color(173, 216, 230));
		
		this.setPreferredSize(new Dimension(140, 140));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,0));

		this.day = ul;
		this.add(ul);
		this.addMouseListener(listener);
	}
	
	/**
	 * Create a {@link DayPanel}.
	 * @param text Month and day.
	 * @param c Color for the first day of a month.
	 */
	public DayPanel(String text, Color c) {
		UnitLabel ul = new UnitLabel(text);
		ul.setOpaque(true);
		ul.setBackground(new Color(173, 216, 230));
		ul.setForeground(c);
		
		this.setPreferredSize(new Dimension(140, 140));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,0));

		this.day = ul;
		this.add(ul);
		this.addMouseListener(listener);
	}
	
	/**
	 * Create a {@link DayPanel}.
	 * @param text Month and day.
	 * @param holiday The holiday of the day.
	 */
	public DayPanel(String text, String holiday) {
		UnitLabel ul = new UnitLabel(text);
		ul.setOpaque(true);
		ul.setBackground(new Color(173, 216, 230));
				
		UnitLabel ul2 = new UnitLabel(holiday);
		ul2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		this.setPreferredSize(new Dimension(140, 140));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,0));

		this.day = ul;
		this.add(ul);
		this.add(ul2);
		this.addMouseListener(listener);
	}
	
	/**
	 * Create a {@link DayPanel}.
	 * @param text Month and day.
	 * @param c Color for the first day of a month.
	 * @param holiday The holiday of the day.
	 */
	public DayPanel(String text, Color c, String holiday) {
		UnitLabel ul = new UnitLabel(text);
		ul.setOpaque(true);
		ul.setBackground(new Color(173, 216, 230));
		ul.setForeground(c);
		
		UnitLabel ul2 = new UnitLabel(holiday);
		ul2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		this.setPreferredSize(new Dimension(140, 140));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,0));

		this.day = ul;
		this.add(ul);
		this.add(ul2);
		this.addMouseListener(listener);
	}
	
	private class DayPanelListener implements MouseListener, Serializable {
		/**
		 * Serial id for store in file.
		 */
		private static final long serialVersionUID = 4357686084239315234L;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (month > 0 && month < 13) {
				RightPanel r = RightPanel.getInstance();
				String s = month + "-" + date + "-2019";
				r.setSelectedDate(s);
				r.getDate().setText(s);
				r.getDate().setForeground(Color.BLACK);
				r.getEvents().setText("Events of " + s);
				JPanel checkP = r.getCheckP();
				checkP.removeAll();
				for (JCheckBox jcb : addedEvents) {
					checkP.add(jcb);
				}
				checkP.revalidate();
				checkP.repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}

	public ArrayList<JCheckBox> getAddedEvents() {
		return addedEvents;
	}

	public void setAddedEvents(ArrayList<JCheckBox> addedEvents) {
		this.addedEvents = addedEvents;
	}

	public ArrayList<UnitLabel> getAddedEventsTitle() {
		return addedEventsTitle;
	}

	public void setAddedEventsTitle(ArrayList<UnitLabel> addedEventsTitle) {
		this.addedEventsTitle = addedEventsTitle;
	}
	
}
