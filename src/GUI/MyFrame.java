package GUI;

import main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by mayezhou on 16/6/1.
 */
public class MyFrame extends JFrame implements Observer{
    public CardLayout cardLayout;
    public JPanel mainPanel;
    private Game game;
    public StartPanel startPanel;
    public MiddlePanel middlePanel;
    public EndPanel endPanel;

    public MyFrame(Game game) {
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        this.game = game;
        startPanel = new StartPanel(this);
        middlePanel = new MiddlePanel(game);
        endPanel = new EndPanel();
        Container container = this.getContentPane();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(startPanel, "first");
        mainPanel.add(middlePanel, "second");
        mainPanel.add(endPanel, "third");
        container.add(mainPanel);
        this.setTitle("大富翁");
        this.setSize(1074, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
    }

    @Override
    public void refresh() {
        middlePanel.refresh();
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        MyFrame frame = new MyFrame(game);
        game.registerObserver(frame);
        game.start();
    }

}
