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
        this.game = game;
        setLayout(new GridLayout(3, 1, 5, 5));
        datePanel = new DateMessage(game.date);
        add(datePanel);
        textPanel = new TextMessage();
        add(textPanel);
        propertyPanel = new PropertyMessage(game.curPlayer);
        add(propertyPanel);
    }

    public void refresh() {
        datePanel.repaint();
        propertyPanel.refresh();
    }
}
