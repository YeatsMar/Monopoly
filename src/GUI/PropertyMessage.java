package GUI;

import main.Player;
import util.Icon;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PropertyMessage extends JPanel {
	private Player player;
	private Image BGP = new ImageIcon("wallpaper/羊皮卷.jpg").getImage();
	private JLabel label;
	private JLabel[] labels;
	private Font font = new Font("TimesRoman", Font.PLAIN, 25);

	PropertyMessage(Player player) {
		labels = new JLabel[11];
		this.player = player;
		setLayout((new GridLayout(6, 2, 5, 5)));
		label = new JLabel();
		setLabel();
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

	private void setLabels() {
		for (JLabel l:
			 labels) {
			l.setFont(font);
		}
		this.add(label);
		this.add(labels[0]);
		this.add(labels[9]);
		this.add(labels[10]);
		for (int i = 0; i < 9; i++) {
			this.add(labels[i]);
		}
	}

	private void setLabel() {
		switch (player.getPlayerID()) {
			case 0:
				label = new JLabel(Icon.player0Icon);
				break;
			case 1:
				label = new JLabel(Icon.player1Icon);
				break;
			case 2:
				label = new JLabel(Icon.player2Icon);
				break;
			case 3:
				label = new JLabel(Icon.player3Icon);
				break;
		}
	}


	/**draw wallpaper*/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
	}
	
}
