package GUI;

import main.Game;
import main.Player;
import util.Icon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/10.
 */
public class Dicelistener extends JFrame implements ActionListener {
    private Player player;
    private DicePanel panel;
    private int step;
    private Image[] dice;

    public Dicelistener() {
        player = Game.curPlayer;
        step = (int) (Math.random()*6) +1;
        dice = new Image[6];
        for (int i = 0; i < 6; i++) {
            dice[i] = Icon.diceIcon[i].getImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("掷骰子");
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//be caution not to terminate the PJ
        frame.setVisible(true);
        try {
            frame.add(new DicePanel());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        new Timer(2000,new ActionListener(){//trigger after 2000mm
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();//close the frame automatically
                ((Timer) e.getSource()).stop();
            }
        }).start();//anonymous class
    }

    private class DicePanel extends JPanel{
        boolean finalIcon = false;

        DicePanel() throws InterruptedException {
            Timer timer = new Timer(50, new TimerListener());
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!finalIcon) {
                g.drawImage(dice[(int) (Math.random() * 6)], 0, 0, 180, 180,
                        this);
            }
            else {//stay to show the step
                g.drawImage(dice[step - 1], 0, 0, 180, 180, this);//index of array - 1
            }
        }

        //TimerListener(Inner class) trigger every 50mm
        class TimerListener implements ActionListener {
            int i = 0;//inner 'timer'

            public void actionPerformed(ActionEvent e) {
                i++;
                if (i == 10) {
                    finalIcon = true;
                }
                else if (i == 13) {
                    ((Timer) e.getSource()).stop();
                    player.go(step);
                    player.game.next();
                }
                repaint();
            }
        }
    }
}
