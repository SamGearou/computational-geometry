import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConvexHull {

    // O(nlogn)
    public static List<Point> findConvexHull(List<Point> points) {
        if (points == null || points.size() < 3) {
            return points;
        }
        Collections.sort(points);
        List<Point> upperHull = new ArrayList<>();
        upperHull.add(points.get(0));
        upperHull.add(points.get(1));
        for (int i = 2; i < points.size(); i++) {
            while (upperHull.size() > 1 && !isRightTurn(upperHull, points.get(i))) {
                upperHull.remove(upperHull.size() - 1);
            }
            upperHull.add(points.get(i));
        }
        List<Point> lowerHull = new ArrayList<>();
        int len = points.size();
        lowerHull.add(points.get(len - 1));
        lowerHull.add(points.get(len - 2));
        for (int i = len - 3; i >= 0; i--) {
            while (lowerHull.size() > 1 && !isRightTurn(lowerHull, points.get(i))) {
                lowerHull.remove(lowerHull.size() - 1);
            }
            lowerHull.add(points.get(i));
        }
        for (int i = 1; i < lowerHull.size() - 1; i++) {
            upperHull.add(lowerHull.get(i));
        }
        return upperHull;
    }

    private static boolean isRightTurn(List<Point> upperHull, Point three) {
        Point one = upperHull.get(upperHull.size() - 2);
        Point two = upperHull.get(upperHull.size() - 1);
        return det3By3(one, two, three) < 0;
    }

    // |1 one.x   one.y   |
    // |1 two.x   two.y   |
    // |1 three.x three.y |
    private static int det3By3(Point one, Point two, Point three) {
        int a = 1;
        int b = one.x;
        int c = one.y;
        int intermediateOne = (two.x * three.y) - (two.y * three.x);
        int intermediateTwo = (three.y) - (two.y);
        int intermediateThree = (three.x) - (two.x);
        return (a * intermediateOne) + (-b * intermediateTwo) + (c * intermediateThree);
    }

    public static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (x == o.x) {
                return y - o.y;
            }
            return x - o.x;
        }
    }
}
