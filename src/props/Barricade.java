package props;

import dots.Dot;
import main.Game;
import main.Map;
import main.Player;
import util.Calculation;
import util.IO;

/**
 * Created by mayezhou on 16/4/8.
 */
public class Barricade extends Prop {
    public Barricade() {
        name = "路障";
        id = 1;
    }


    private void function(int dotID) {
        Map map = Game.getMap();
        Dot dot = map.getDot(dotID);
        dot.setBlocked(true);
        IO.warning("路障放置成功");
    }

    @Override
    public boolean function(Player player) {
        int input = IO.getInt("请输入放置路障的位置距离当前位置的点数,正数为顺时针+,负数为逆时针+");
        input = Calculation.calculateLocation(player.getLocation(), input);
        function(input);
        return true;
    }
}
