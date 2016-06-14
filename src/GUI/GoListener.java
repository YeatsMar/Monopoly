package GUI;

import dots.BankDot;
import dots.Dot;
import main.Game;
import main.Player;
import util.Calculation;
import util.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/10.
 */
public class GoListener implements ActionListener {
    private int step;
    private Player player;
    private Game game;

    public GoListener(int step, Player player) {
        this.step = step;
        this.player = player;
        this.game = player.game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int l = player.getLocation();
        if (step > 0) {
            l = Calculation.calculateLocation(l, player.getDirection());
            Dot dot = game.map.getDot(l);
            if (dot.isBlocked()) {
                IO.print("遇到路障!");
                ((Timer) e.getSource()).stop();
                player.setLocation(l);
                dot.event(player);
                player.game.next();
                return;
            } else if (dot instanceof BankDot && step != 1) {
                IO.print("途经银行可顺道办理业务~");
                player.setLocation(l);
                dot.event(player);
            }
            player.setLocation(l);
            step--;
        } else {
            ((Timer) e.getSource()).stop();
            Dot dot = game.map.getDot(l);
            dot.event(player);
            player.game.next();
        }
    }
}
