package GUI;

import java.awt.*;
import util.Date;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class DateMessage extends JPanel {
	private Image BGP = new ImageIcon("wallpaper/1.jpg").getImage();
	private Date date;
	
	DateMessage(Date date) {
		this.date = date;
		setFont(new Font("Californian FB", Font.BOLD, 30));
		setBorder(new TitledBorder("游戏时间"));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/**draw wallpaper*/
		g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
		
		/**show the time in the game*/
		// Get font metrics for the font
		FontMetrics fm = g.getFontMetrics();
		// find the center location to display
		int stringWidth = fm.stringWidth(date.getYear() + "/" + date.getMonth()
				+ "/" + date.getDay());
		int stringAscent = fm.getAscent();
		int xCoordinate = getWidth() / 2 - stringWidth / 2;
		int yCoordinate = getHeight() / 2 + stringAscent / 2;
		g.drawString(
				date.getYear() + "/" + date.getMonth() + "/" + date.getDay(),
				xCoordinate, yCoordinate);
	}
}