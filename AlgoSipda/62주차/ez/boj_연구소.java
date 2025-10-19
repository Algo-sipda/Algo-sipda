import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_연구소 {
    static int N, M, res;
    static int[][] map;
    static int[] walls;
    static List<int[]> emptyList, virusList;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        walls = new int[3];
        emptyList = new ArrayList<int[]>();
        virusList = new ArrayList<int[]>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyList.add(new int[] {i, j});
                } else if (map[i][j] == 2) {
                    virusList.add(new int[] {i, j});
                }
            }
        }

        combi(0, 0);

        System.out.println(res);
    }

    private static void combi(int cnt, int start) {
        if (cnt == 3) {
            int[][] copied = new int[N][M];
            copied = copyMap(map);
            for (int i = 0; i < walls.length; i++) {
                int[] point = emptyList.get(walls[i]);
                copied[point[0]][point[1]] = 1;
            }
            copied = spreadVirus(copied);
            res = Math.max(res, calcSafeArea(copied));
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            walls[cnt] = i;
            combi(cnt + 1, i + 1);
        }
    }

    private static int[][] spreadVirus(int[][] copied) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        for (int[] point : virusList) {
            queue.add(new int[] {point[0], point[1]});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || copied[nr][nc] != 0) continue;

                copied[nr][nc] = 2;
                queue.add(new int[] {nr, nc});
            }
        }
        return copied;
    }

    private static int calcSafeArea(int[][] copied) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copied[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], map[i].length);
        }

        return copy;
    }
}