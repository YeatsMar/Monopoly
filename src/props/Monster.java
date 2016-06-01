package props;

import dots.Dot;
import dots.Estate;
import main.Game;
import main.Map;
import main.Player;
import main.Street;

import java.beans.Beans;

/**
 * Created by mayezhou on 16/4/10.
 */
public class Monster extends Prop {
    public Monster() {
        name = "怪兽卡";
        id = 2;
    }

    public void function(int location) {
        Map map = Game.getMap();
        Dot dot = map.getDot(location);
        if (Beans.isInstanceOf(dot, Estate.class)) {
            Street street = ((Estate) dot).getStreet();
            for (Estate e : street.getEstates()) {
                e.setLevel(1);
            }
            System.out.println("当前所在街道所有房屋等级恢复为1");
        } else {
            System.out.println("当前所在点没有街道");
        }
    }

    @Override
    public boolean function(Player player) {
        function(player.getLocation());
        return true;
    }
}
