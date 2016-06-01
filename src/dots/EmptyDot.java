package dots;

import main.Player;

/**
 * Created by mayezhou on 16/4/7.
 */
public class EmptyDot extends Dot{
    public EmptyDot(int x, int y) {
        super(x, y);
        symbol = "空";
        info = "空地";
        accessible = true;
    }

    @Override
    public void event(Player player) {

    }
}
