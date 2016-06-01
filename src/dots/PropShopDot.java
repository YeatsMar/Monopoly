package dots;

import main.Player;
import props.Prop;
import util.InputHandler;

/**
 * Created by mayezhou on 16/4/7.
 */
public class PropShopDot extends Dot{
    public PropShopDot(int x, int y) {
        super(x, y);
        symbol = "道";
        info = "道具店";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        while (true) {
            System.out.println("欢迎来到道具店,三张点券购买一张道具卡，当前您的点券数：" + player.getTicketPoint());
            System.out.println("本店销售如下道具:");
            for (int i = 0; i < Prop.names.length; i++) {
                System.out.println(i+". "+ Prop.names[i]);
            }
            int leave = Prop.names.length+1;
            int input;
            input = InputHandler.getInt("请输入您想要的卡片编号,输入"+leave+"离开");
            if (input == leave) {
                return;
            } else if (input < 0 || input > leave) {
                System.out.println("输入有误,根据指示重新输入");
            } else {
                int n = InputHandler.getInt("请输入要购买的数量, 输入0返回上一层");
                while (n < 0) {
                    System.out.println("输入有误,根据指示重新输入");
                    n = InputHandler.getInt("请输入要购买的数量, 输入0返回上一层");
                }
                if (n == 0) {
                    continue;
                }
                if (player.getTicketPoint() >= 3*n) {
                    player.getCard()[input] += n;
                    player.setTicketPoint(player.getTicketPoint()-3*n);
                    System.out.println("成功购买");
                } else {
                    System.out.println("点券不足,本次购买失败");
                }
            }
        }
    }
}
