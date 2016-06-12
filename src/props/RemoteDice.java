package props;

import main.Player;
import util.IO;

/**
 * Created by mayezhou on 16/4/8.
 */
public class RemoteDice extends Prop {
    public RemoteDice() {
        name = "遥控骰子";
        id = 3;
    }

    public void function(int n, Player player) {
        player.go(n);
    }

    @Override
    public boolean function(Player player) {
        int n = IO.getInt("请输入想要前进的步数:1-6");
        while (n < 1 || n > 6) {
            IO.warning();
            n = IO.getInt("请输入想要前进的步数:1-6");
        }
        function(n, player);
        return false;
    }
}
