package GUI;

import main.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mayezhou on 16/6/1.
 */
public class InformationPanel extends JPanel{
    private DateMessage datePanel;
    private PropertyMessage propertyPanel;
    private TextMessage textPanel;
    private Game game;

    public InformationPanel(Game game) {
        this.setPreferredSize(new Dimension(300, 678));
        setLayout(null);
        Insets insets = getInsets();
        this.game = game;
        datePanel = new DateMessage(game.date);
        datePanel.setBounds(insets.left, insets.top, 300, 78);
        datePanel.setVisible(true);
        add(datePanel);
        textPanel = new TextMessage();
        textPanel.setBounds(insets.left, insets.top+78, 300, 400);
        textPanel.setVisible(true);
        add(textPanel);
        propertyPanel = new PropertyMessage(game.curPlayer);
        propertyPanel.setBounds(insets.left, insets.top+478, 300, 200);
        add(propertyPanel);
        repaint();
    }

    public void refresh() {
        datePanel.repaint();
        propertyPanel.refresh();
    }
}
