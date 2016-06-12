package dots;

import main.Player;
import util.IO;
import util.Icon;

/**
 * Created by mayezhou on 16/4/7.
 */
public class EmptyDot extends Dot{
    public EmptyDot(int x, int y) {
        super(x, y);
        symbol = "空";
        info = "空地";
        accessible = true;
        this.setIcon(Icon.emptyIcon);
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
    }
}
