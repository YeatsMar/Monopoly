package dots;

import GUI.PropsFrame;
import GUI.PropsFrame.*;
import main.Player;
import util.IO;
import util.Icon;

/**
 * Created by mayezhou on 16/4/7.
 */
public class PropShopDot extends Dot{
    public PropShopDot(int x, int y) {
        super(x, y);
        symbol = "道";
        info = "道具店";
        accessible = true;
        this.setIcon(Icon.propsIcon);
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        PropsFrame frame = new PropsFrame(player);
        PropbuyingListener listener = frame.new PropbuyingListener();
        frame.setListener(listener);
        frame.setLabel("欢迎来到道具店,三张点券购买一张道具卡，当前您的点券数：" + player.getTicketPoint());
    }
}
