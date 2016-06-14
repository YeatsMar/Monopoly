package dots;

import main.Player;
import util.Icon;
import util.IO;

import java.util.Random;

/**
 * Created by mayezhou on 16/4/26.
 */
public class LotteryDot extends Dot{
    public LotteryDot(int x, int y) {
        super(x, y);
        symbol = "彩";
        info = "彩票开奖点";
        accessible = true;
        this.setIcon(Icon.lotteryIcon);
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        if (IO.yORn("彩票售卖", "刮刮乐售卖中,是否购买彩票?\n彩票200元,中奖概率10%,奖金2000元~")) {
            if (player.getCash() >= 200) {
                player.addCash(-200);
                if (new Random().nextInt(9) < 1) {//10%
                    IO.print("恭喜中奖!获得2000元奖金");
                    player.addCash(2000);
                    player.printProperty();
                } else {
                    IO.print("很遗憾本次没有中奖,希望下次有料");
                }
            } else {
                IO.print("现金不足,无法购买");
            }
        } else {
            IO.print(player.getName()+"放弃购买彩票");
        }
    }
}
