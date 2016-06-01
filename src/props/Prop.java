package props;

import main.Player;

/**
 * Created by mayezhou on 16/4/8.
 */
public abstract class Prop {
    public final static String[] names = {"均富卡", "路障", "怪兽卡", "遥控骰子", "转向卡", "滞留卡", "查税卡"};
    protected String name;
    protected int id;

    public String getName() {
        return name;
    }

    public abstract boolean function(Player player);
}
