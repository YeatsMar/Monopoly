package dots;

import main.Player;
import util.Calculation;
import util.InputHandler;

/**
 * Created by mayezhou on 16/4/7.
 */
public class BankDot extends Dot{
    public BankDot(int x, int y) {
        super(x, y);
        symbol = "银";
        info = "银行";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        System.out.println("您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit());
        int input = InputHandler.getInt("可进行操作如下:\n1. 取现\t2. 存款\t3. 离开\n请输入操作序号:");
        while (input < 1 || input > 3) {
            System.out.println("输入有误!请根据指示重新输入");
            input = InputHandler.getInt("可进行操作如下:\n1. 取现\t2. 存款\t3. 离开\n请输入操作序号:");
        }
        switch (input) {
            case 1:
                double cash = InputHandler.getDouble("请输入取现金额:");
                while (cash > player.getDeposit()) {
                    cash = InputHandler.getDouble("余额不足,请重新输入");
                }
                while (cash < 0) {
                    cash = InputHandler.getDouble("金额不能为负,请重新输入");
                }
                cash = Calculation.roundUpDouble(cash);
                System.out.println("提取现金"+cash);
                player.setCash(cash+player.getCash());
                player.setDeposit(player.getDeposit() - cash);
                System.out.println("您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit());
                break;
            case 2:
                double deposit = InputHandler.getDouble("请输入存款金额:");
                while (deposit > player.getCash()) {
                    deposit = InputHandler.getDouble("现金不足,请重新输入");
                }
                while (deposit < 0) {
                    deposit = InputHandler.getDouble("金额不能为负,请重新输入");
                }
                deposit = Calculation.roundUpDouble(deposit);
                System.out.println("存款"+deposit);
                player.setCash(player.getCash() - deposit);
                player.setDeposit(deposit+player.getDeposit());
                System.out.println("您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit());
                break;
            case 3:
                return;
        }
    }
}