import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.add(new Point(d, t));
        }

        list.sort((o1, o2) -> o2.y - o1.y);

        int emptyMax = 1_000_000_000;
        for (Point point : list) {
            emptyMax = Math.min(emptyMax, point.y);
            emptyMax -= point.x;
        }

        System.out.println(emptyMax);
    }
}
