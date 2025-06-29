import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, group;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Integer> groupSize = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int idx = 1;
        groupSize.add(0);
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0 && group[y][x] == 0) {
                    groupSize.add(bfs(y, x, idx++));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    sb.append(0);
                } else {
                    Set<Integer> adj = new HashSet<>();
                    int sum = 1;
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                        int gid = group[ny][nx];
                        if (gid > 0 && !adj.contains(gid)) {
                            sum += groupSize.get(gid);
                            adj.add(gid);
                        }
                    }
                    sb.append(sum % 10);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int sy, int sx, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sy, sx});
        group[sy][sx] = idx;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                if (map[ny][nx] == 0 && group[ny][nx] == 0) {
                    group[ny][nx] = idx;
                    q.add(new int[]{ny, nx});
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
