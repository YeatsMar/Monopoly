package props;

import main.Game;
import main.Player;
import util.Calculation;
import util.IO;

/**
 * Created by mayezhou on 16/4/8.
 */
public class ReverseDirection extends Prop {
    public ReverseDirection() {
        name = "转向卡";
        id = 4;
    }

    public void function(Player player, int playerID) {
        Player p = Game.getPlayers()[playerID];
        if (Calculation.relativeDistance(player.getPlayerID(), playerID) <= 5) {
            p.setDirection(-p.getDirection());
        } else {
            System.out.println("该玩家距离您五步开外,不能对其使用转向卡");
        }
    }

    @Override
    public boolean function(Player player) {
        int playerID = IO.getInt("请输入要控制的玩家ID\t0-"+(Game.getPlayers().length-1));
        while (playerID < 0 || playerID >= Game.getPlayers().length) {
            IO.warning();
            playerID = IO.getInt("请输入要控制的玩家ID\t0-"+(Game.getPlayers().length-1));
        }
        function(player, playerID);
        return true;
    }
}
