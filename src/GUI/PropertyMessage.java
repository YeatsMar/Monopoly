package GUI;

import main.Game;
import main.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PropertyMessage extends JPanel {
	private Player player;
	private Image BGP = new ImageIcon("wallpaper/羊皮卷.jpg").getImage();
	private JLabel playerImage;
	private JLabel[] labels;
	private Font font = new Font("TimesRoman", Font.PLAIN, 12);

	PropertyMessage(Player player) {
		labels = new JLabel[11];
		this.player = player;
		setLayout((new GridLayout(6, 2, 1, 1)));
		playerImage = new JLabel(player.icon);
		labels[0] = new JLabel(player.getName());
		labels[9] = new JLabel("前进方向");
		labels[10] = new JLabel(player.getDirectionString());
		labels[1] = new JLabel("现金");
		labels[2] = new JLabel(player.getCash() + "");
		labels[3] = new JLabel("存款");
		labels[4] = new JLabel(player.getDeposit() + "");
		labels[5] = new JLabel("点券");
		labels[6] = new JLabel(player.getTicketPoint() + "");
		labels[7] = new JLabel("资产");
		labels[8] = new JLabel(player.getTotalProperty() + "");
		setLabels();
		setBorder(new TitledBorder("玩家信息"));
	}

	public void refresh() {
		this.player = Game.curPlayer;
		playerImage.setIcon(player.icon);
		labels[0].setText(player.getName());
		labels[10].setText(player.getDirectionString());
		labels[2].setText(player.getCash() + "");
		labels[4].setText(player.getDeposit() + "");
		labels[6].setText(player.getTicketPoint() + "");
		labels[8].setText(player.getTotalProperty() + "");
		repaint();
	}

	private void setLabels() {
		for (JLabel l:
			 labels) {
			l.setFont(font);
		}
		this.add(playerImage);
		this.add(labels[0]);//name
		this.add(labels[9]);//direction
		this.add(labels[10]);
		for (int i = 1; i < 9; i++) {
			this.add(labels[i]);
		}
	}

	/**draw wallpaper*/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
	}
	
}
