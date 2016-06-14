package GUI;

import main.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mayezhou on 16/6/1.
 */
public class MiddlePanel extends JPanel {
    private GamePanel gamePanel;
    private InformationPanel informationPanel;
    private Game game;

    public MiddlePanel(Game game) {
        this.game = game;
        setLayout(new BorderLayout());
        gamePanel = new GamePanel(game);
        add(gamePanel, BorderLayout.WEST);
        informationPanel = new InformationPanel(game);
        add(informationPanel, BorderLayout.EAST);
    }

    public void refresh() {
        gamePanel.refresh();
        informationPanel.refresh();
    }

}
