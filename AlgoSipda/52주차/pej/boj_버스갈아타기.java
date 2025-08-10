import java.io.*;
import java.util.*;
// 수직일때는 x값 동일하고 y값 범위가 일치
// 수평일때는 y값 동일하고 x값 범위가 일치  
// 수직 + 수평일 때는 가로 노선의 y값이 세로 노선의 y범위에 포함되어야하며 동시에 세로 노선의 x값이 가로 노선의 x범위에 포함되어야함
// 그래프를 직접만들어야하는 문제
class Bus {
    boolean horizontal;
    int fixed; // 수평이면 y값, 수직이면 x값
    int start, end; // 구간 범위 (정렬됨)
    Bus(int x1, int y1, int x2, int y2) {
        if (y1 == y2) { // 수평
            horizontal = true;
            fixed = y1;
            start = Math.min(x1, x2);
            end = Math.max(x1, x2);
        } else { // 수직
            horizontal = false;
            fixed = x1;
            start = Math.min(y1, y2);
            end = Math.max(y1, y2);
        }
    } 
    boolean contains(int x, int y) { 
        if (horizontal) return (y == fixed && x >= start && x <= end);
        else return (x == fixed && y >= start && y <= end);
    }
}

public class Main {
    static int m, n, k;
    static Bus[] buses;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());
        buses = new Bus[k+1];

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 버스 번호 (입력 순서와 동일)
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            buses[i] = new Bus(x1, y1, x2, y2);
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int dx = Integer.parseInt(st.nextToken());
        int dy = Integer.parseInt(st.nextToken());

        // 그래프 구성
        graph = new ArrayList[k+1];
        for (int i = 1; i <= k; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= k; i++) {
            for (int j = i+1; j <= k; j++) {
                if (intersect(buses[i], buses[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[k+1];
        Arrays.fill(dist, -1);

        // 시작점 포함 버스들 큐에 추가
        for (int i = 1; i <= k; i++) {
            if (buses[i].contains(sx, sy)) {
                q.add(i);
                dist[i] = 1;
            }
        }

        // BFS 탐색
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (buses[cur].contains(dx, dy)) {
                System.out.println(dist[cur]);
                return;
            }
            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
    }

    static boolean intersect(Bus a, Bus b) {
        if (a.horizontal && b.horizontal) {
            return a.fixed == b.fixed && !(a.end < b.start || b.end < a.start);
        }
        if (!a.horizontal && !b.horizontal) {
            return a.fixed == b.fixed && !(a.end < b.start || b.end < a.start);
        }
        // 수평 vs 수직
        Bus h = a.horizontal ? a : b;
        Bus v = a.horizontal ? b : a;
        return (v.fixed >= h.start && v.fixed <= h.end) &&
               (h.fixed >= v.start && h.fixed <= v.end);
    }
}
