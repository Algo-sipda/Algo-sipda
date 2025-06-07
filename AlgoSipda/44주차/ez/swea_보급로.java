import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class swea_보급로
{
    static int N, time;
    static int[][] map;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j) - '0';
                }
            }

            time = 0;
            bfs();

            sb.append("#" + test_case + " " + time + "\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        boolean[][] visited = new boolean[N][N];
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        pq.add(new int[] {0, 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == N - 1 && cur[1] == N - 1) {
                time = cur[2];
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                pq.add(new int[] { nr, nc, cur[2] + map[nr][nc]});
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}