import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x;
        long cost;

        Point(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] vision = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            vision[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, List<long[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s != n - 1 && vision[s] == 1) continue;
            if (e != n - 1 && vision[e] == 1) continue;

            long d = Long.parseLong(st.nextToken());
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(new long[]{e, d});
            map.computeIfAbsent(e, k -> new ArrayList<>()).add(new long[]{s, d});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        pq.add(new Point(0, 0));

        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.cost > dist[p.x]) continue;

            List<long[]> edges = map.getOrDefault(p.x, Collections.emptyList());
            for (long[] edge : edges) {
                int next = (int) edge[0];
                long weight = edge[1];

                if (dist[next] > dist[p.x] + weight) {
                    dist[next] = dist[p.x] + weight;
                    pq.add(new Point(next, dist[next]));
                }
            }
        }

        System.out.println(dist[n - 1] == Long.MAX_VALUE ? -1 : dist[n - 1]);
    }
}
