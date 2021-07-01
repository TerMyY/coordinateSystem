import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class MainFrame extends JFrame {

    JFrame mainFrame = new JFrame("Coordinate System");
    private final Pattern isNumber = Pattern.compile("-?\\d+(\\.\\d+)?");


    private static final Dimension defaultSize = new Dimension
    ( (int) (Toolkit.getDefaultToolkit().getScreenSize().width / 1.45),
    (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 1.45) );

    public static Dimension getDefaultSize() { return defaultSize; }

    Graphics2D graphics;
    Line2D xAxis = new Line2D.Float();
    Line2D yAxis = new Line2D.Float();
    Line2D argumentLine = new Line2D.Float();
    Point2D pointStart = new Point2D.Double();
    Point2D pointEndX = new Point2D.Double();
    Point2D pointEndY = new Point2D.Double();
    Point2D pointEndArgument = new Point2D.Double();

    JPanel mainPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JPanel graphPanel = new JPanel() {

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            graphics = (Graphics2D) g;
            graphics.setStroke(new BasicStroke(4));
            pointStart.setLocation(getWidth() / 15f, getHeight() - getHeight() / 15f);
            pointEndX.setLocation(getWidth() - getWidth() / 15f,getHeight() - getHeight() / 15f);
            pointEndY.setLocation(getWidth() / 15f,  getHeight() / 15f);

            if(parseDouble(xTextPane.getText()) < 0)
                xTextPane.setText("0");
            if(parseDouble(yTextPane.getText()) < 0)
                yTextPane.setText("0");

                pointEndArgument.setLocation((getWidth() / 15f) + (parseDouble(xTextPane.getText()) * 38), (getHeight() - getHeight() / 15f) - (parseDouble(yTextPane.getText()) * 38));
                xAxis.setLine(pointStart, pointEndX);
                yAxis.setLine(pointStart, pointEndY);
                argumentLine.setLine(pointStart, pointEndArgument);
                graphics.draw(xAxis);
                graphics.draw(yAxis);
                graphics.draw(argumentLine);
        }

    };


    JTextPane xTextPane = new JTextPane();
    JTextPane yTextPane = new JTextPane();
    JLabel xLabel = new JLabel("x = ");
    JLabel yLabel = new JLabel(" = y");
    JButton refreshButton = new JButton(" \uD83D\uDD01 ");
    ActionListener refresh = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };


    BorderLayout borderLayout = new BorderLayout();


     MainFrame() {

        controlPanel.setBackground(Color.WHITE);
        graphPanel.setBackground(Color.WHITE);

        xTextPane.setText("0");
        yTextPane.setText("0");
        xTextPane.setFont(new Font("Comic Sans", Font.BOLD, 18));
        yTextPane.setFont(new Font("Comic Sans", Font.BOLD, 18));
        xLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        yLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));

        refreshButton.addActionListener(refresh);
        refreshButton.setBackground(Color.BLACK);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setPreferredSize( new Dimension(45, 30) );
        refreshButton.setBorder(null);

        mainPanel.setLayout(borderLayout);
        mainPanel.add(graphPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.PAGE_END);

        controlPanel.add(xLabel);
        controlPanel.add(xTextPane);
        controlPanel.add(yTextPane);
        controlPanel.add(yLabel);
        controlPanel.add(refreshButton);



        add(mainPanel);
        setTitle(mainFrame.getTitle());
        setSize(defaultSize);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    static void invoke() {

        SwingUtilities.invokeLater(MainFrame::new);

    }



}
