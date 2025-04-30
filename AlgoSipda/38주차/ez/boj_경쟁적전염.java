import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_경쟁적전염 {

    static int N, K, S;
    static int[][] map;
    static Queue<Virus> queue;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Virus {
        int r, c, num, time;

        public Virus(int r, int c, int num, int time) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        List<Virus> list = new ArrayList<>();
        queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    list.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        Collections.sort(list, new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                return o1.num - o2.num;
            }
        });

        for (Virus virus : list) {
            queue.add(virus);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(map[r - 1][c - 1]);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Virus cur = queue.poll();
            if (cur.time == S) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] == 0) {
                    map[nr][nc] = cur.num;
                    queue.add(new Virus(nr, nc, cur.num, cur.time + 1));
                }
            }
        }
    }
}
