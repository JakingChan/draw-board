package site.jaking.ui;

import site.jaking.util.LocationCoverUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PicturePanel extends Canvas {

    int x1, y1, x2, y2, flag = -1;

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g.create();
        drawAxis(graphics);
        if (flag != -1) {
            if (!historyList.isEmpty()) {
                for (History history : historyList) {
                    graphics.drawLine(history.getX1(), history.getY1()
                            , history.getX2(), history.getY2());
                }
            }
            graphics.drawLine(x1, y1, x2, y2);
            System.out.printf("A(%d,%d) => B(%d,%d)\n", x1, y1, x2, y2);
            historyList.add(History.of(x1, y1, x2, y2));
            flag = -1;
        }
        graphics.dispose();
    }

    private void drawAxis(Graphics2D graphics) {
        int width = getWidth();
        int height = getHeight();
        // 画数轴
        LocationCoverUtils.CustomizePoint origin = LocationCoverUtils.cover(width, height, -width / 2, 0);
        LocationCoverUtils.CustomizePoint target = LocationCoverUtils.cover(width, height, width / 2, 0);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        origin = LocationCoverUtils.cover(width, height, 0, height / 2);
        target = LocationCoverUtils.cover(width, height, 0, -height / 2);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        // 画x轴箭头
        origin = LocationCoverUtils.cover(width, height, width / 2 - 10, 3);
        target = LocationCoverUtils.cover(width, height, width / 2, 0);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        origin = LocationCoverUtils.cover(width, height, width / 2 - 10, -3);
        target = LocationCoverUtils.cover(width, height, width / 2, 0);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        origin = LocationCoverUtils.cover(width, height, width / 2 - 10, -15);
        graphics.drawString("X", origin.getX(), origin.getY());

        // 画y轴箭头
        origin = LocationCoverUtils.cover(width, height, 3, height / 2 - 10);
        target = LocationCoverUtils.cover(width, height, 0, height / 2);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        origin = LocationCoverUtils.cover(width, height, -3, height / 2 - 10);
        target = LocationCoverUtils.cover(width, height, 0, height / 2);
        graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());

        origin = LocationCoverUtils.cover(width, height, 15, height / 2 - 10);
        graphics.drawString("Y", origin.getX(), origin.getY());

        // 画原点O
        origin = LocationCoverUtils.cover(width, height, -11, -11);
        graphics.drawString("O", origin.getX(), origin.getY());


        // 画单位长度
        for (int i = -20, j = 20; i >= -width || j <= width; i -= 20, j += 20) {
            if (j + 20 >= width / 2) {
                break;
            }
            origin = LocationCoverUtils.cover(width, height, i, 0);
            target = LocationCoverUtils.cover(width, height, i, 5);
            graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());
            origin = LocationCoverUtils.cover(width, height, j, 0);
            target = LocationCoverUtils.cover(width, height, j, 5);
            graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());
        }

        for (int i = -20, j = 20; i >= -height || j <= height; i -= 20, j += 20) {
            if (j + 10 >= height / 2) {
                break;
            }
            origin = LocationCoverUtils.cover(width, height, 0, i);
            target = LocationCoverUtils.cover(width, height, 5, i);
            graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());
            origin = LocationCoverUtils.cover(width, height, 0, j);
            target = LocationCoverUtils.cover(width, height, 5, j);
            graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());
        }

        for (int i = height / 2; i >= -height / 2; i -= 20) {
            for (int j = -width / 2; j <= width; j += 20) {
                origin = LocationCoverUtils.cover(width, height, i, j);
                target = LocationCoverUtils.cover(width, height, i, j);
                graphics.drawLine(origin.getX(), origin.getY(), target.getX(), target.getY());
            }
        }
    }

    private List<History> historyList = new ArrayList<>();

    public void drawLine(List<Integer> indexs) {
        int width = getWidth();
        int height = getHeight();
        LocationCoverUtils.CustomizePoint origin = LocationCoverUtils.cover(width, height, indexs.get(0), indexs.get(1));
        x1 = origin.getX();
        y1 = origin.getY();

        LocationCoverUtils.CustomizePoint target = LocationCoverUtils.cover(width, height, indexs.get(2), indexs.get(3));
        x2 = target.getX();
        y2 = target.getY();

        flag++;
        repaint();
    }

    public void empty() {
        flag = -1;
        historyList.clear();
        repaint();
    }

    public static class History {
        private int x1, y1, x2, y2;

        public History(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public static History of(int x1, int y1, int x2, int y2) {
            return new History(x1, y1, x2, y2);
        }

        public int getX1() {
            return x1;
        }

        public void setX1(int x1) {
            this.x1 = x1;
        }

        public int getY1() {
            return y1;
        }

        public void setY1(int y1) {
            this.y1 = y1;
        }

        public int getX2() {
            return x2;
        }

        public void setX2(int x2) {
            this.x2 = x2;
        }

        public int getY2() {
            return y2;
        }

        public void setY2(int y2) {
            this.y2 = y2;
        }
    }
}
