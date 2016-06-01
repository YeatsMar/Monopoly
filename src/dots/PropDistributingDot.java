package dots;

import main.Player;
import props.Prop;

/**
 * Created by mayezhou on 16/4/7.
 */
public class PropDistributingDot extends Dot{
    public PropDistributingDot(int x, int y) {
        super(x, y);
        symbol = "卡";
        info = "赠送道具卡点";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        int random = (int) Math.random() * 5;
        System.out.println("随机获得道具卡" + Prop.names[random] + "一张");
        player.getCard()[random]++;
    }
}

