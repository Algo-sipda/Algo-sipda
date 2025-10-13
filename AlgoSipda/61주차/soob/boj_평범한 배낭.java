import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Point point = new Point(sc.nextInt(), sc.nextInt());
            list.add(point);
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x - o2.x;
            }
        });
        int[] dp = new int[K + 1];

        for (Point p : list) {
            int w = p.x;
            int v = p.y;
            for (int i = K; i >= w; i--) {
                dp[i] = Math.max(dp[i], dp[i - w] + v);
            }
        }

        System.out.println(dp[K]);

    }
}