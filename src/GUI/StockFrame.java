package GUI;

import com.bitagentur.chart.JChartLibBaseChart;
import com.bitagentur.chart.JChartLibLineChart;
import com.bitagentur.data.JChartLibDataSet;
import com.bitagentur.data.JChartLibSerie;
import com.bitagentur.renderer.JChartLibLinechartRenderer;
import com.bitagentur.renderer.JChartLibPanel;
import main.Game;
import main.Player;
import main.Stock;
import main.StockMarket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mayezhou on 16/6/18.
 */
public class StockFrame extends JFrame implements Observer {
    private Player[] players = Game.players;
    private StockMarket stockMarket = Game.stockMarket;
    private Player curPlayer;
    //table
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] title = new String[7];
    private Object[][] data = new Object[10][7];
    //chart
    private JChartLibPanel chartPanel;
    private JChartLibDataSet dataset;
    private JChartLibBaseChart chart;


    public StockFrame(Player player) {
        this.curPlayer = player;
        player.game.registerObserver(this);
        createJChart();
        createTable();
        //frame
        setTitle("股票市场");
        setSize(928, 551);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLayout(new GridLayout(2, 1));
        add(chartPanel);
        add(new JScrollPane(table));
    }

    private void createTable() {
        //title
        title[0] = "股票名";
        title[1] = "今日价格";
        title[2] = "涨跌幅";
        for (int i = 0; i < 4; i++) {
            title[3 + i] = players[i].getName() + "持股";
        }
        //data
        setData();
        //jtable
        tableModel = new DefaultTableModel(data, title) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        //event
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = table.rowAtPoint(e.getPoint());
                //showCertainStockChart(index);
                Object[] options = {"买入", "卖出"};
                int choice = JOptionPane.showOptionDialog(null, "您选中了" + data[index][0], "股票交易", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == JOptionPane.YES_OPTION) {
                    stockMarket.buy(curPlayer, index);
                } else if (choice == JOptionPane.NO_OPTION) {
                    stockMarket.sell(curPlayer, index);
                }
            }
        });
    }

    private void setData() {
        for (int i = 0; i < 10; i++) {
            Stock stock = stockMarket.stocks[i];
            data[i][0] = stock.getName();
            data[i][1] = stock.getPrice();
            data[i][2] = stock.getRate();
            for (int j = 0; j < 4; j++) {
                data[i][3 + j] = players[j].getStockAmount(i);
            }
        }
    }

    private void createJChart() {
        dataset = createDataset();//default: index = 0
        chart = createChart(dataset);
        chartPanel = new JChartLibPanel(chart);
    }

    private void showCertainStockChart(int index) {
        this.removeAll();
        dataset = new JChartLibDataSet();
        Stock stock = stockMarket.stocks[index];
        JChartLibSerie values = new JChartLibSerie(stock.getName());
        for (int j = 0; j < stock.prices.length; j++) {
            values.addValue(stock.prices[j]);
        }
        dataset.addDataSerie(values);
        chart = createChart(dataset);
        chartPanel = new JChartLibPanel(chart);
        this.add(chartPanel);
        this.add(table);
        this.repaint();
    }

    private JChartLibDataSet createDataset() {
        //generating a Dataserie object
        dataset = new JChartLibDataSet();
        for (int i = 0; i < stockMarket.stocks.length; i++) {
            Stock stock = stockMarket.stocks[i];
            JChartLibSerie values = new JChartLibSerie(stock.getName());
            for (int j = 0; j < stock.prices.length; j++) {
                values.addValue(stock.prices[j]);
            }
            dataset.addDataSerie(values);
        }
        return dataset;
    }

    /**
     * Creates a chart
     *
     * @param dataset the data for the chart.
     * @return a new chart
     */
    private JChartLibBaseChart createChart(JChartLibDataSet dataset) {
        // create the chart with title and axis names
        chart = new JChartLibLineChart(
                "股票走势图", // chart title
                "日期", // x axis text
                "价格", // y axis text
                dataset // data
        );
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JChartLibLinechartRenderer renderer = new JChartLibLinechartRenderer(chart);
        chart.setRender(renderer);
        renderer.setDrawdots(true);
        renderer.setShowminmax(true);
        renderer.addXAxisText("-4");
        renderer.addXAxisText("-3");
        renderer.addXAxisText("-2");
        renderer.addXAxisText("one day ago");
        renderer.addXAxisText("today");
        return chart;
    }

    @Override
    public void refresh() {
        setData();
        tableModel = new DefaultTableModel(data, title);
        table.setModel(tableModel);
        this.repaint();
    }
}
