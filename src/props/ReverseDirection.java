package props;

import GUI.ChoosePlayerFrame;
import main.Game;
import main.Player;
import util.Calculation;
import util.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/4/8.
 */
public class ReverseDirection extends Prop {
    public ReverseDirection() {
        name = "转向卡";
        id = 4;
    }

    public void function(Player player, int playerID) {
        Player p = player.game.players[playerID];
        if (Calculation.relativeDistance(player.getPlayerID(), playerID) <= 5) {
            p.setDirection(-p.getDirection());
            IO.print(p.getName()+"已转向!");
        } else {
            IO.warning("该玩家距离您五步开外,不能对其使用转向卡");
        }
    }

    @Override
    public boolean function(Player player) {
        ChoosePlayerFrame frame = new ChoosePlayerFrame();
        ReverseListener listener = new ReverseListener(frame, player);
        frame.registerListener(listener);
        return true;
    }

    private class ReverseListener implements ActionListener {
        private ChoosePlayerFrame frame;
        private Player player;

        public ReverseListener(ChoosePlayerFrame frame, Player player) {
            this.frame = frame;
            this.player = player;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            for (id = 0; id < Game.players.length; id++) {
                if (e.getSource() == frame.playerBtns[id]) {
                    function(player, id);
                    frame.dispose();
                    break;
                }
            }
        }
    }
}
