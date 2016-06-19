package props;

import dots.Dot;
import dots.Estate;
import main.Game;
import main.Map;
import main.Player;
import main.Street;
import util.IO;

import java.beans.Beans;

/**
 * Created by mayezhou on 16/4/10.
 */
public class Monster extends Prop {
    public Monster() {
        name = "怪兽卡";
        id = 2;
    }

    public void function(int location, Map map) {
        Dot dot = map.getDot(location);
        try {
            Street street = ((Estate) dot).getStreet();//ClassCastException
            for (Estate e : street.getEstates()) {//NullPointerException
                e.setLevel(1);
            }
            IO.showMessage("当前所在街道所有房屋等级恢复为1");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            IO.showMessage("当前所在点没有街道_(:зゝ∠)_");
        }
    }

    @Override
    public boolean function(Player player) {
        function(player.getLocation(), player.game.map);
        return true;
    }
}
