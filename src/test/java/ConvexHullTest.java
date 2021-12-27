import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvexHullTest {

    @Test
    //see convexHull.png/convexHullPoints.png for images
    public void convexHullFivePoints() {
        ConvexHull.Point one = new ConvexHull.Point(1, 2);
        ConvexHull.Point two = new ConvexHull.Point(-2, 4);
        ConvexHull.Point three = new ConvexHull.Point(-3, 12);
        ConvexHull.Point four = new ConvexHull.Point(4, -16);
        ConvexHull.Point five = new ConvexHull.Point(-2, 3);
        List<ConvexHull.Point> points = new ArrayList<>();
        points.add(one);
        points.add(two);
        points.add(three);
        points.add(four);
        points.add(five);
        List<ConvexHull.Point> convexHull = ConvexHull.findConvexHull(points);
        assertThat(convexHull).containsExactlyInAnyOrder(one, three, four, five);
    }
}