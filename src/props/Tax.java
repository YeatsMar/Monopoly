package props;

import main.Game;
import main.Player;
import util.Calculation;
import util.InputHandler;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Tax extends Prop {
    public Tax() {
        name = "查税卡";
        id = 6;
    }

    public void function(Player user, int playerID) {
        Player p = Game.getPlayers()[playerID];
        if (Calculation.relativeDistance(user.getPlayerID(), playerID) <= 5) {
            p.setDeposit(p.getDeposit() * 0.7);
            System.out.println("扣除"+p.getName()+"存款30%");
        } else {
            System.out.println("该玩家距离您五步开外,不能对其使用查税卡");
        }
    }

    @Override
    public boolean function(Player player) {
        int playerID = InputHandler.getInt("请输入要控制的玩家ID\t0-"+(Game.getPlayers().length-1));
        while (playerID < 0 || playerID >= Game.getPlayers().length) {
            InputHandler.warning();
            playerID = InputHandler.getInt("请输入要控制的玩家ID\t0-"+(Game.getPlayers().length-1));
        }
        while (playerID == player.getPlayerID()) {
            System.out.println("只能对对手使用_(:зゝ∠)_");
            playerID = InputHandler.getInt("请输入要控制的玩家ID\t0-"+(Game.getPlayers().length-1));
        }
        function(player, playerID);
        return false;
    }
}
