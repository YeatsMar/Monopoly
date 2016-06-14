package dots;

import main.Player;
import util.Calculation;
import util.Icon;
import util.IO;

import javax.swing.*;

/**
 * Created by mayezhou on 16/4/7.
 */
public class BankDot extends Dot{
    public BankDot(int x, int y) {
        super(x, y);
        symbol = "银";
        this.setIcon(Icon.bankIcon);
        info = "银行";
        accessible = true;
    }

    @Override
    public void event(Player player) {
        IO.print(player.getName()+"到达"+getInfo());
        Object[] options = {"取现", "存款"};
        int choice = JOptionPane.showOptionDialog(null, "您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit(),
                "办理银行业务", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            double cash = IO.getDouble("您的当前存款为"+player.getDeposit()+"\n请输入取现金额:");
            cash = Calculation.roundUpDouble(cash);
            if (cash > player.getDeposit()) {
                IO.warning("余额不足_(:зゝ∠)_");
            }
            else if (cash < 0) {
                IO.warning("金额不能为负!");
            }
            else {
                IO.showMessage("提取现金"+cash);
                player.setCash(cash+player.getCash());
                player.setDeposit(player.getDeposit() - cash);
                IO.print("您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit());
            }
        } else if (choice == JOptionPane.NO_OPTION){
            double deposit = IO.getDouble("您的当前现金为"+player.getCash()+"\n请输入存款金额:");
            deposit = Calculation.roundUpDouble(deposit);
            if (deposit > player.getCash()) {
                IO.warning("现金不足_(:зゝ∠)_");
            }
            else if (deposit < 0) {
                IO.warning("金额不能为负!");
            }
            else {
                IO.showMessage("存款"+deposit);
                player.setCash(player.getCash() - deposit);
                player.setDeposit(deposit+player.getDeposit());
                IO.print("您的当前现金为"+player.getCash()+"\t存款为"+player.getDeposit());
            }
        }
    }
}
