package GUI;

import main.Game;
import main.Player;
import props.Prop;
import util.IO;
import util.Icon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/1.
 */
public class PropsFrame extends JFrame {
    private JButton[] cards;
    private Player player;
    private JLabel label;
    private JPanel panel;

    public PropsFrame(Player player) {
        this.player = player;
        label = new JLabel();
        panel = new JPanel(new GridLayout(1, 7, 20, 20));// 可以算出每张卡片的图片大小为140x200
        cards = new JButton[7];
        cards[0] = new JButton("均富卡（" + player.getCard(0) + "）", Icon.average);
        cards[1] = new JButton("路障（" + player.getCard(1) + "）", Icon.barrier);
        cards[2] = new JButton("怪兽卡（" + player.getCard(2) + "）", Icon.monster);
        cards[3] = new JButton("遥控骰子\n（" + player.getCard(3) + "）", Icon.remoteDice);
        cards[4] = new JButton("转向卡（" + player.getCard(4) + "）", Icon.reverse);
        cards[5] = new JButton("滞留卡（" + player.getCard(5) + "）", Icon.residence);
        cards[6] = new JButton("查税卡（" + player.getCard(6) + "）", Icon.tax);
        for (JButton e :
                cards) {
            panel.add(e);
        }
        setSize(1000, 300);
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
    }

    public void setListener(ActionListener listener) {
        for (JButton jbt :
                cards) {
            jbt.addActionListener(listener);
        }
    }

    public void setLabel(String s) {
        label.setText(s);
    }

    public class PropbuyingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int card;
            for (card = 0; card < 5; card++) {
                if (cards[card] == e.getSource()) {
                    break;
                }
            }
            int n = IO.getInt("请输入购买数量:");
            while (n < 0) {
                IO.warning("购买数量不能为负!");
                n = IO.getInt("请输入购买数量:");
            }
            if (player.getTicketPoint() >= 3 * n) {
                player.addCard(card, n);
                player.setTicketPoint(player.getTicketPoint() - 3 * n);
                IO.print("玩家" + player.getName() + "成功购买" + Prop.names[card] + n + "张");
            } else {
                IO.print("点券不足,本次购买失败");
            }
        }
    }

    public class PropUsingListener implements ActionListener {
        private PropsFrame frame;

        public PropUsingListener(PropsFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int card;
            for (card = 0; card < 5; card++) {
                if (cards[card] == e.getSource()) {
                    break;
                }
            }
            player.useProp(card);
            frame.dispose();
        }
    }
}

