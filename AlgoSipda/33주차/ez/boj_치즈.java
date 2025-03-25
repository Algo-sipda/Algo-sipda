import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_치즈 {

    static int N, M, cheese;
    static int[][] map;
    static boolean[][] air;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        int time = 0;
        while (cheese > 0) {
            air = new boolean[N][M];
            airCheck();

            int[][] copy = copyMap(map);
            findCheese(copy);
            time++;
        }

        System.out.println(time);
    }

    private static void findCheese(int[][] copy) {

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (copy[i][j] == 1) {
                    int empty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (!air[nr][nc]) continue;
                        if (map[nr][nc] == 0) {
                            empty++;
                        }
                    }
                    if (empty > 1) {
                        copy[i][j] = 0;
                        cheese--;
                    }
                }
            }
        }
        map = copyMap(copy);
    }

    private static void airCheck() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0});
        air[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || air[nr][nc] || map[nr][nc] == 1) continue;

                air[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[map.length][map[0].length];

        for (int i = 0; i < copy.length; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }
}