package props;

import main.Player;
import util.*;
import util.Icon;

import javax.swing.*;

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
        Object[] items = {1,2,3,4,5,6};
        int n = (int) JOptionPane.showInputDialog(null, "请输入你想要前进的步数", "遥控骰子",
                JOptionPane.PLAIN_MESSAGE, Icon.diceIcon2, items, 1);
        IO.showMessage(player.getName()+"使用了"+name+"!");
        function(n, player);
        return true;
    }
}
