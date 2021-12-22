package site.jaking.util;

public class LocationCoverUtils {

    public static class CustomizePoint {
        private int x;
        private int y;

        private CustomizePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public static CustomizePoint of(int x, int y) {
            return new CustomizePoint(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static CustomizePoint cover(int width, int height, int x, int y) {
        return CustomizePoint.of(
                x < 0 ? width / 2 - Math.abs(x) : width / 2 + x,
                y < 0 ? height / 2 + Math.abs(y) : height / 2 - y
        );
    }


}
