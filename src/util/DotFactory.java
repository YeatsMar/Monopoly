package util;

import dots.*;

/**
 * Created by mayezhou on 16/4/8.
 */
public class DotFactory {
    public static Dot createDot(int x, int y, char symbol) {
        Dot dot;
        switch (symbol) {
            case 'E':
                dot = new Estate(x, y);
                break;
            case 'V':
                dot = new EmptyDot(x, y);
                break;
            case 'B':
                dot = new BankDot(x, y);
                break;
            case 'T':
                dot = new TicketDot(x, y);
                break;
            case 'L':
                dot = new LotteryDot(x, y);
                break;
            case 'N':
                dot = new NewsDot(x, y);
                break;
            case 'C':
                dot = new PropDistributingDot(x, y);
                break;
            case 'P':
                dot = new PropShopDot(x, y);
                break;
            case 'H':
                dot = new HospitalDot(x, y);
                break;
            default:
                dot = new Nothing(x, y);
        }
        return  dot;
    }
}
