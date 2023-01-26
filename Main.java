package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

    public void paint(Graphics g) {
        paintLight(g, -400, 0, 1000);
        //paintLight(g, 400, 200, 100);
    }

    public void paintLight(Graphics g, int x, int y, int size) {
        Color gray = new Color(82, 82, 82);

        paintFCircle(g, (int) (size * 0.775 + x), (int) (size * 0.04 + y), size / 5, gray);
        paintRRect(g, (int) (size * 0.75 + x), (int) (size * 0.125 + y), size / 4, (int) (size * 0.75), gray);
        paintRRect(g, (int) (size * 0.825 + x), (int) (size * 0.8875 + y), size / 10, (int) (size * 0.15), gray);

        paintFigure1(g, (int) (size * 0.755 + x), (int) (size * 0.13 + y), (int) (size * 0.12), Color.WHITE, gray);
        paintFigure1(g, (int) (size * 0.755 + x), (int) (size * 0.3675 + y), (int) (size * 0.12), Color.WHITE, gray);
        paintFigure1(g, (int) (size * 0.755 + x), (int) (size * 0.605 + y), (int) (size * 0.12), Color.WHITE, gray);

        paintCircle(g, (int) (size * 0.775 + x), (int) (size * 0.1625 + y), size / 10, Color.RED);
        paintCircle(g, (int) (size * 0.775 + x), (int) (size * 0.4 + y), size / 10, Color.YELLOW);
        paintCircle(g, (int) (size * 0.775 + x), (int) (size * 0.6375 + y), size / 10, Color.GREEN);

        paintCircleTriangle(g, (int) (size * 0.70 + x), (int) (size * 0.175 + y), (int) (size * 0.1125), 1, gray);
        paintCircleTriangle(g, (int) (size * 0.70 + x), (int) (size * 0.4125 + y), (int) (size * 0.1125), 1, gray);
        paintCircleTriangle(g, (int) (size * 0.70 + x), (int) (size * 0.65 + y), (int) (size * 0.1125), 1, gray);

        paintCircleTriangle(g, (int) (size * 1.01 + x), (int) (size * 0.175 + y), (int) (size * 0.1125), 0, gray);
        paintCircleTriangle(g, (int) (size * 1.01 + x), (int) (size * 0.4125 + y), (int) (size * 0.1125), 0, gray);
        paintCircleTriangle(g, (int) (size * 1.01 + x), (int) (size * 0.65 + y), (int) (size * 0.1125), 0, gray);
    }

    public void paintCircle(Graphics g, int x, int y, int r, Color color) {
        g.setColor(color);
        g.fillOval(x, y, r * 2, r * 2);
    }

    public void paintFCircle(Graphics g, int x, int y, int size, Color color) {
        paintCircle(g, x, y, size / 2, color);
        g.clearRect(x, (int) (size * 0.3) + y, size, size);
        g.fillArc((int) (size * 0.042) + x, (int) (size * 0.225) + y, size / 5, (int) (size * 0.15), 180, 180);
        g.fillArc((int) (size * 0.758) + x, (int) (size * 0.225) + y, size / 5, (int) (size * 0.15), 180, 180);
        g.fillRect((int) (size * 0.142) + x, (int) (size * 0.225) + y, (int) (size * 0.716), (int) (size * 0.15));
    }

    public void paintCircle(Graphics g, int x, int y, int r) {
        g.fillOval(x, y, r * 2, r * 2);
    }

    public void paintFigure1(Graphics g, int x, int y, int r, Color color1, Color color2) {
        paintCircle(g, x, y, r, color1);
        g.setColor(color2);
        g.fillOval(x, (int) (y + r * 0.2), r * 2, (int) (r * 1.6));
        g.fillRect(x, y + r, r * 2, r);
    }

    public void paintRRect(Graphics g, int x, int y, int width, int height, Color color) {
        g.setColor(color);
        int arc = (width + height) / 10;
        g.fillRoundRect(x, y, width, height, arc, arc);
    }

    public void paintTriangle(Graphics g, int x, int y, int katet, int rotate) {
        int[] hand = handlerTriangle(x, y, katet, rotate);

        g.fillPolygon(new int[] {x, hand[0], x}, new int[] {y, y, hand[1]}, 3);
    }

    public void paintCircleTriangle(Graphics g, int x, int y, int katet, int rotate, Color color) {
        g.setColor(color);

        int[] hand = handlerTriangle(x, y, katet, rotate);
        int r = katet / 5;
        int[] rn = handlerLine(r, rotate);
        final int CONST = (int) (r / Math.sqrt(2));
        int[] C = handlerConst(CONST, rotate);
        paintCircle(g, x, y, r);
        paintCircle(g, hand[0], y, r);
        paintCircle(g, x, hand[1], r);
        g.fillPolygon(new int[] {hand[0] + r, x + r, x + rn[0], x + rn[0], x + r + C[0], hand[0] + r + C[0]}, new int[] {y + rn[1], y + rn[1], y + r, hand[1] + r, hand[1] + r - C[1], y + r - C[1]}, 6);
    }

    private int[] handlerTriangle(int x, int y, int katet, int rotate) {
        int x2 = x + katet;
        int y2 = y + katet;
        if(rotate == 1) {
            x2 = x - katet;
        }
        else if(rotate == 2) {
            y2 = y - katet;
        }
        else if(rotate == 3) {
            x2 = x - katet;
            y2 = y - katet;
        }
        return new int[] {x2, y2};
    }

    private int[] handlerLine(int r, int rotate) {
        int rx1 = 0;
        int ry2 = 0;
        if(rotate == 1) {
            rx1 = r * 2;
        }
        else if (rotate == 2) {
            ry2 = r * 2;
        }
        else if (rotate == 3) {
            rx1 = r * 2;
            ry2 = r * 2;
        }
        return new int[] {rx1, ry2};
    }

    private int[] handlerConst(int CONST, int rotate) {
        int cx = CONST;
        int cy = CONST;
        if(rotate == 3) {
            cx = -CONST;
        }
        else if(rotate == 0) {
            cy = -CONST;
        }
        else if(rotate == 1) {
            cx = -CONST;
            cy = -CONST;
        }
        return new int[] {cx, cy};
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new Main());
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20,20, 500,500);
        frame.setVisible(true);
    }
}