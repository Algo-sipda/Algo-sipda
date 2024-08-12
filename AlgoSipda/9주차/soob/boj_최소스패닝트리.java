import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Map<Integer, List<Point>> map = new HashMap<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(a, k -> new ArrayList<>()).add(new Point(b, c));
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(new Point(a, c));
        }

        int start = 1;
        PriorityQueue<Edge> list = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        boolean[] visited = new boolean[V + 1];
        visited[start] = true;

        for (Point point : map.get(start)) {
            list.add(new Edge(start, point.x, point.y));
        }

        int answer = 0;
        while (!list.isEmpty()) {
            Edge edge = list.poll();
            if (visited[edge.end]) continue;
            visited[edge.end] = true;
            answer += edge.weight;
            for (Point point : map.get(edge.end)) {
                list.add(new Edge(edge.end, point.x, point.y));
            }
        }

        System.out.println(answer);
    }
}