package props;

import main.Player;
import util.IO;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Stay extends Prop{
    public Stay() {
        name = "滞留卡";
        id = 5;
    }

    public boolean function(Player player) {
        IO.showMessage(player.getName()+"使用了"+name+"!");
        player.go(0);
        return true;
    }
}
