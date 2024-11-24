import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list.sort((p1, p2) -> p1.x - p2.x);

        int infectDistance = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) { // 최소 감염 거리 찾기
            Point p = list.get(i);
            if (p.y == 0) {
                if (i != 0) {
                    if (list.get(i - 1).y == 1)
                        infectDistance = Math.min(infectDistance, p.x - list.get(i - 1).x - 1);
                }
                if (i != list.size() - 1) {
                    if (list.get(i + 1).y == 1)
                        infectDistance = Math.min(infectDistance, list.get(i + 1).x - p.x - 1);
                }
            }
        }

        int lastInfected = Integer.MIN_VALUE;
        int answer = 0;
        for (Point p : list) {
            if (p.y == 0)
                continue;
            if (lastInfected + infectDistance < p.x) {
                answer++;
            }
            lastInfected = p.x;
        }

        System.out.println(answer);
    }
}
