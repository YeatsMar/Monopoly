package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	public static final int DEFAULT_DOT_WIDTH = 45;
	public static final int DEFAULT_DOT_HEIGHT = 45;
	public static final int DEFAULT_CARD_WIDTH = 150;
	public static final int DEFAULT_CARD_HEIGHT = 232;
	public static final int DEFAULT_START_BUTTON_WIDTH = 214;
	public static final int DEFAULT_START_BUTTON_HEIGHT = 157;
	public static final int DEFAULT_GAME_BUTTON_WIDTH = 86;
	public static final int DEFAULT_GAME_BUTTON_HEIGHT = 27;


	//player
	public static ImageIcon[] playerIcon = new ImageIcon[4];
	public static ImageIcon player0Icon = getImageIcon("icons/ava1.jpg",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon player1Icon = getImageIcon("icons/ava2.jpg",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon player2Icon = getImageIcon("icons/ava3.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon player3Icon = getImageIcon("icons/ava4.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	static {
		playerIcon[0] = player0Icon;
		playerIcon[1] = player1Icon;
		playerIcon[2] = player2Icon;
		playerIcon[3] = player3Icon;
	}

	//dice
	public static ImageIcon mapDiceIcon = getImageIcon("icons/dice.png",
			200, 200);
	public static ImageIcon diceIcon2 = getImageIcon("icons/dice.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);

	// dot
	public static ImageIcon noownerIcon = getImageIcon("icons/noowner0.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon[] ownerAIcon = new ImageIcon[6];
	public static ImageIcon[] ownerBIcon = new ImageIcon[6];
	public static ImageIcon[] ownerCIcon = new ImageIcon[6];
	public static ImageIcon[] ownerDIcon = new ImageIcon[6];
	public static ImageIcon bankIcon = getImageIcon("icons/bank.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon cardIcon = getImageIcon("icons/gift.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);// free card as gifts
	public static ImageIcon lotteryIcon = getImageIcon("icons/lottery.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon ticketIcon = getImageIcon("icons/ticket.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon[] diceIcon = new ImageIcon[6];
	public static ImageIcon emptyIcon = getImageIcon("icons/empty.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon propsIcon = getImageIcon("icons/props.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon newsIcon = getImageIcon("icons/news.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	public static ImageIcon hospital = getImageIcon("icons/hospital.png",
			DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
	static {
		for (int i = 0; i < 6; i++) {
			ownerAIcon[i] = getImageIcon("icons/ownerA" + i + ".png",
					DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
			ownerBIcon[i] = getImageIcon("icons/ownerB" + i + ".png",
					DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
			ownerCIcon[i] = getImageIcon("icons/ownerC" + i + ".png",
					DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
			ownerDIcon[i] = getImageIcon("icons/ownerD" + i + ".png",
					DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);
		}
		for (int i = 0; i < 6; i++) {
			diceIcon[i] =getImageIcon("icons/dice" + (i+1) + ".png",
					180, 180);
		}
		
	}
	public static ImageIcon barricade = getImageIcon("icons/barrage.png", DEFAULT_DOT_WIDTH, DEFAULT_DOT_HEIGHT);

	//Start Button
	public static ImageIcon newStart = getImageIcon("icons/start.png", DEFAULT_START_BUTTON_WIDTH,
			DEFAULT_START_BUTTON_HEIGHT);
	public static ImageIcon continueGame = getImageIcon("icons/Continue.png",
			DEFAULT_START_BUTTON_WIDTH, DEFAULT_START_BUTTON_HEIGHT);
	public static ImageIcon newStart2 = getImageIcon("icons/start2.png",
			DEFAULT_START_BUTTON_WIDTH, DEFAULT_START_BUTTON_HEIGHT);
	public static ImageIcon continueGame2 = getImageIcon("icons/Continue2.png",
			DEFAULT_START_BUTTON_WIDTH, DEFAULT_START_BUTTON_HEIGHT);

	//card
	public static ImageIcon reverse = getImageIcon("icons/reverse.png", DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT);
	public static ImageIcon residence =getImageIcon("icons/residence.png", DEFAULT_CARD_WIDTH,
			DEFAULT_CARD_HEIGHT);
	public static ImageIcon average = getImageIcon("icons/average.png", DEFAULT_CARD_WIDTH,
			DEFAULT_CARD_HEIGHT);
	public static ImageIcon tax = getImageIcon("icons/tax.png", DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT);
	public static ImageIcon remoteDice = getImageIcon("icons/remoteDice.png", DEFAULT_CARD_WIDTH,
			DEFAULT_CARD_HEIGHT);
	public static ImageIcon monster = getImageIcon("icons/monster.jpg", DEFAULT_CARD_WIDTH,
			DEFAULT_CARD_HEIGHT);
	public static ImageIcon barrier = getImageIcon("icons/barrier.jpg", DEFAULT_CARD_WIDTH,
			DEFAULT_CARD_HEIGHT);
	
	// get scaled image icon, a substitute for ImageIcon constructor
	private static ImageIcon getImageIcon(String imageFilename, int width,
			int height) {
		ImageIcon icon = new ImageIcon(imageFilename);
		icon.setImage(icon.getImage().getScaledInstance(width, height,
				Image.SCALE_SMOOTH));
		return icon;
	}
}
