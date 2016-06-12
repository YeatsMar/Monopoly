package GUI;

import dots.Dot;
import main.Map;
import util.Icon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mayezhou on 16/6/1.
 */
public class MapPanel extends JPanel {
    private Image BGP = new ImageIcon("wallpaper/timeBGP.jpg").getImage();
    private Map map;
    private JButton dice;

    public MapPanel(Map map) {
        this.map = map;
        this.setPreferredSize(new Dimension(850, 628));
        setLayout(null);
        dice = new JButton(Icon.mapDiceIcon);
        Insets insets = getInsets();
        dice.setBounds(insets.left+505, insets.top+85, 200, 200);
        dice.setVisible(true);
        add(dice);
        dice.addActionListener(new Dicelistener());
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Dot dot = map.getDot(x, y);
                try {
                    ImageIcon imageIcon = (ImageIcon) dot.getIcon();
                    g.drawImage(imageIcon.getImage(), x*46+3, y*46+2, 46, 46, this);
                } catch (NullPointerException e) {
                    //do nothing
                }
            }
        }
    }
}
