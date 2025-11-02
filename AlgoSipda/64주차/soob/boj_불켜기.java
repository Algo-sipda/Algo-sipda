import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Map<Point, List<Point>> switches;
    static boolean[][] lightOn;
    static boolean[][] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        switches = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switches.computeIfAbsent(new Point(x, y), k -> new ArrayList<>()).add(new Point(a, b));
        }

        lightOn = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        lightOn[1][1] = true;

        boolean changed = true;
        while (changed) {
            changed = false;
            for (int y = 1; y <= N; y++) {
                Arrays.fill(visited[y], false);
            }
            Deque<Point> q = new ArrayDeque<>();
            visited[1][1] = true;
            q.add(new Point(1, 1));

            while (!q.isEmpty()) {
                Point p = q.poll();

                List<Point> list = switches.get(p);
                if (list != null) {
                    for (Point to : list) {
                        if (!lightOn[to.y][to.x]) {
                            lightOn[to.y][to.x] = true;
                            changed = true;
                            if (!visited[to.y][to.x] && hasVisitedNeighbor(to.x, to.y)) {
                                visited[to.y][to.x] = true;
                                q.add(new Point(to.x, to.y));
                            }
                        }
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (nx < 1 || ny < 1 || nx > N || ny > N)
                        continue;
                    if (!lightOn[ny][nx])
                        continue;
                    if (visited[ny][nx])
                        continue;
                    visited[ny][nx] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }

        int ans = 0;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (lightOn[y][x])
                    ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean hasVisitedNeighbor(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 1 || ny < 1 || nx > N || ny > N)
                continue;
            if (visited[ny][nx])
                return true;
        }
        return false;
    }
}
