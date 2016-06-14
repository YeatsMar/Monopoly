package props;

import GUI.ChoosePlayerFrame;
import main.Game;
import main.Player;
import util.Calculation;
import util.IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Tax extends Prop {
    public Tax() {
        name = "查税卡";
        id = 6;
    }

    private void function(Player user, int playerID) {
        Player p = user.game.players[playerID];
        if (Calculation.relativeDistance(user.getPlayerID(), playerID) <= 5) {
            p.setDeposit(p.getDeposit() * 0.7);
            IO.print(user.getName()+"对"+p.getName()+"使用了查税卡!");
            IO.print("扣除"+p.getName()+"存款30%");
        } else {
            IO.warning("该玩家距离您五步开外,不能对其使用查税卡");
        }
    }

    @Override
    public boolean function(Player player) {
        ChoosePlayerFrame frame = new ChoosePlayerFrame(player.getPlayerID());
        TaxListener listener = new TaxListener(frame, player);
        frame.registerListener(listener);
        return true;
    }

    private class TaxListener implements ActionListener {
        private ChoosePlayerFrame frame;
        private Player player;

        public TaxListener(ChoosePlayerFrame frame, Player player) {
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
