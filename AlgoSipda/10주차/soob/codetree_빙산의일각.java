import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        int maxH = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            maxH = Math.max(n, maxH);
            list.add(new Point(i, n));
        }
        Collections.sort(list, (o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o2.y - o1.y);

        int height = list.get(0).y - 1;
        int answer = 0;
        boolean[] checked = new boolean[N];
        int icebergCnt = 0;

        for (Point p : list) {
            if (height != p.y - 1) {
                answer = Math.max(answer, icebergCnt);
                height = p.y - 1;
            }

            if (p.x == 0) {
                if (!checked[p.x + 1])
                    icebergCnt++;
            } else if (p.x == N - 1) {
                if (!checked[p.x - 1])
                    icebergCnt++;
            } else {
                if (checked[p.x - 1] && checked[p.x + 1])
                    icebergCnt--;
                else if (!checked[p.x - 1] && !checked[p.x + 1])
                    icebergCnt++;
            }
            checked[p.x] = true;
        }

        System.out.println(answer);
    }
}