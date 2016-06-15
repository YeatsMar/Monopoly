package GUI;

import main.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mayezhou on 16/6/9.
 */
public class EndPanel extends JPanel {
    private Image BGP = new ImageIcon(getClass().getResource("/wallpaper/2.jpg")).getImage();
    private String s;

    public EndPanel() {
        setFont(new Font("Californian FB", Font.BOLD, 20));
    }

    public void setWinner(String s) {
        this.s = s;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
        g.drawString(s, 230, 440);
    }
}
