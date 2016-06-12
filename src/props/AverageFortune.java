package props;

import main.Game;
import main.Player;
import util.Calculation;
import util.IO;

/**
 * Created by mayezhou on 16/4/8.
 */
public class AverageFortune extends Prop {

    public AverageFortune() {
        name = "均富卡";
        id = 0;
    }

    @Override
    public boolean function(Player player) {
        IO.showMessage(this.getName() + "使用了均富卡！\n所有玩家现金一致");
        Player[] players = Game.getPlayers();
        double cash = 0;
        for (Player p : players) {
            cash += p.getCash();
        }
        cash /= (double) players.length;
        cash = Calculation.roundUpDouble(cash);
        for (Player p : players) {
            p.setCash(cash);
        }
        Game.showAllProperty();
        return true;
    }
}
