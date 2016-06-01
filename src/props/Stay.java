package props;

import main.Player;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Stay extends Prop{
    public Stay() {
        name = "滞留卡";
        id = 5;
    }

    public boolean function(Player player) {
        player.go(0);
        return false;
    }
}
