package util;

import props.*;

/**
 * Created by mayezhou on 16/4/23.
 */
public class PropFactory {
    public static Prop createProp(int i) {
        Prop prop;
        switch (i) {
            case 0:
                prop = new AverageFortune();
                break;
            case 1:
                prop = new Barricade();
                break;
            case 2:
                prop = new Monster();
                break;
            case 3:
                prop = new RemoteDice();
                break;
            case 4:
                prop = new ReverseDirection();
                break;
            case 5:
                prop = new Stay();
                break;
            case 6:
                prop = new Tax();
                break;
            default:
                prop = null;
        }
        return prop;
    }
}
