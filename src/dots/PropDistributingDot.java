package dots;

import main.Player;
import props.Prop;
import util.IO;
import util.Icon;

/**
 * Created by mayezhou on 16/4/7.
 */
public class PropDistributingDot extends Dot{
    public PropDistributingDot(int x, int y) {
        super(x, y);
        symbol = "卡";
        info = "赠送道具卡点";
        accessible = true;
        this.setIcon(Icon.cardIcon);
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        int random = (int) Math.random() * 5;
        IO.print("随机获得道具卡" + Prop.names[random] + "一张");
        player.addCard(random, 1);
    }
}

