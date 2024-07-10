import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        List<Point> list = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list.add(new Point(p, s));
        }
        Collections.sort(list, (o1, o2) -> o1.x + o1.y == o2.x + o2.y ? o1.x - o2.x : (o1.x + o1.y) - (o2.x + o2.y));

        int cost = 0;
        boolean useCoupon = false;
        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            Point p = list.get(i);
            answer = i;
            cost += p.x + p.y;
            if (cost >= B) {
                if (!useCoupon)
                    if (cost - (p.x / 2) < B)
                        cost -= p.x / 2;
                    else {
                        answer = i;
                        break;
                    }
            }
        }

        System.out.println(answer);
    }
}