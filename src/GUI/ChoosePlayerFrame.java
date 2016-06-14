package GUI;

import main.Game;
import main.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/13.
 */
public class ChoosePlayerFrame extends JFrame {
    public JButton[] playerBtns = new JButton[4];
    private Player[] players = Game.players;

    //show all players
    public ChoosePlayerFrame() {
        setSize(448, 84);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLayout(new GridLayout(1, 3, 2, 2));
        for (int i = 0; i < players.length; i++) {
            playerBtns[i] = new JButton(players[i].getName(), players[i].icon);
            add(playerBtns[i]);
        }
    }

    //show the other players
    public ChoosePlayerFrame(int id) {
        setSize(448, 84);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLayout(new GridLayout(1, 4, 2, 2));
        for (int i = 0; i < players.length; i++) {
            playerBtns[i] = new JButton(players[i].getName(), players[i].icon);
            if (i != id) {
                add(playerBtns[i]);
            }
        }
    }

    public void registerListener(ActionListener listener) {
        for (JButton b:
             playerBtns) {
            b.addActionListener(listener);
        }
    }

}
