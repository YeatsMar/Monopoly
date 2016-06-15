package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mayezhou on 16/6/1.
 */
public class StartPanel extends JPanel {
    protected JButton jbtNew = new JButton(util.Icon.newStart);
    private Image BGP = new ImageIcon(getClass().getResource("/wallpaper/start.png")).getImage();
    private MyFrame frame;

    public StartPanel(MyFrame frame) {
        this.frame = frame;
        setLayout(null);
        setSize(1050, 720);
        jbtNew.setBounds(new Rectangle(getWidth() / 2 - getWidth() / 5 / 2+30, getHeight() / 2+50,
                getWidth() / 5, getHeight() / 9));
        jbtNew.setRolloverIcon(util.Icon.newStart2);
        add(jbtNew);
        jbtNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.cardLayout.next(frame.mainPanel);
            }
        });
    }

    // draw the BGP
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BGP, 0, 0, getWidth(), getHeight(), this);
    }
}