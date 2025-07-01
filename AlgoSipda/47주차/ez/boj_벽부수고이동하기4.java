import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_벽부수고이동하기4 {

    static int N, M;
    static int[][] map;
    static Map<Integer, Integer> zeroMap;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        zeroMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int idx = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j, idx++);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    sb.append(getAround(i, j));
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getAround(int row, int col) {
        int sum = 0;
        ArrayList<Integer> selected = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                continue;

            if (map[nr][nc] != 1 && !selected.contains(map[nr][nc])) {
                selected.add(map[nr][nc]);
                sum += zeroMap.get(map[nr][nc]);
            }
        }
        return (sum + 1) % 10;
    }

    private static void bfs(int row, int col, int idx) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[] { row, col });
        map[row][col] = idx;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;
                if (map[nr][nc] == 1)
                    continue;

                if (map[nr][nc] == 0) {
                    queue.add(new int[] { nr, nc });
                    map[nr][nc] = idx;
                    cnt++;
                }
            }
        }
        zeroMap.put(idx, cnt);
    }
}
