import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, List<Point>> map = new HashMap<>();
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> (o1.y == o2.y ? o2.x - o1.x : o2.y - o1.y));
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(deadline, k -> new ArrayList<>()).add(new Point(deadline, ramen));
        }
        
        int answer = 0;
        for (int i = N; i >= 1; i--) {
            if (map.containsKey(i))
                pq.addAll(map.get(i));
            if (pq.isEmpty())
                continue;
            Point p = pq.poll();
            answer += p.y;
        }
        
        System.out.println(answer);
    }
}
