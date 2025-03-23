import java.io.*;
import java.util.*;

public class Main {
    static int N, M, fuel;
    static int[][] map;
    static int[] taxi;
    static Map<String, int[]> passengers = new HashMap<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new int[2];
        taxi[0] = Integer.parseInt(st.nextToken()) - 1;
        taxi[1] = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            passengers.put(sx + "," + sy, new int[]{ex, ey});
        }

        for (int i = 0; i < M; i++) {
            if (!findAndMovePassenger()) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(fuel);
    }

    static boolean findAndMovePassenger() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{taxi[0], taxi[1], 0});
        visited[taxi[0]][taxi[1]] = true;

        List<int[]> candidates = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (dist > minDist) break;

            if (passengers.containsKey(x + "," + y)) {
                minDist = dist;
                candidates.add(new int[]{x, y, dist});
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] == 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }

        if (candidates.isEmpty()) return false;

        candidates.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int px = candidates.get(0)[0];
        int py = candidates.get(0)[1];
        int distToPassenger = candidates.get(0)[2];

        if (fuel < distToPassenger) return false;

        fuel -= distToPassenger;
        taxi[0] = px;
        taxi[1] = py;

        int[] destination = passengers.get(px + "," + py);
        passengers.remove(px + "," + py);

        int distToDest = bfs(px, py, destination[0], destination[1]);
        if (distToDest == -1 || fuel < distToDest) return false;

        fuel -= distToDest;
        fuel += distToDest * 2;
        taxi[0] = destination[0];
        taxi[1] = destination[1];

        return true;
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == ex && y == ey) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] == 1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }

        return -1;
    }
}
