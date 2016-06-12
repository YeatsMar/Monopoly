package GUI;

import main.Game;
import main.Player;
import util.IO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/1.
 */
public class GamePanel extends JPanel {
    private JButton props = new JButton("使用道具");
    private JButton see = new JButton("一探究竟");
    private JButton lose = new JButton("放弃游戏");
    private JButton property = new JButton("玩家信息");
    private JButton stock = new JButton("进入股市");
    private JPanel menu = new JPanel(new FlowLayout(10));
    private JLabel warning = new JLabel("前方十步示警信息：");
    private MapPanel map;
    private Game game;
    private Player player;

    public GamePanel(Game game) {
        menu.add(props);
        menu.add(see);
        menu.add(lose);
        menu.add(property);
        menu.add(stock);
        this.setLayout(new BorderLayout());
        this.add(menu, BorderLayout.NORTH);
        map = new MapPanel(game.map);
        this.add(map, BorderLayout.CENTER);
        this.add(warning, BorderLayout.SOUTH);
        this.game = game;
        this.player = game.curPlayer;
        setProps();
        setSee();
        setLose();
        setProperty();
        setStock();
        setWarning();
    }

    private void setWarning() {
        warning.setText(game.showWarning(game.curPlayer));
    }

    private void setStock() {
        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.stockMarket.enter(game.date, player);
            }
        });
    }

    private void setProperty() {
        property.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showAllProperty();
            }
        });
    }

    private void setLose() {
        lose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setBankrupt(true);
                game.next();
            }
        });
    }

    private void setSee() {
        see.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showCertainDotInfo(player);
            }
        });
    }

    private void setProps() {
        props.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PropsFrame frame = new PropsFrame(player);
                frame.setLabel("点击道具即可使用:");
                PropsFrame.PropUsingListener listener = frame.new PropUsingListener(frame);
                frame.setListener(listener);
            }
        });
    }

    public void refresh() {
        this.player = game.curPlayer;
        map.repaint();
    }
}
