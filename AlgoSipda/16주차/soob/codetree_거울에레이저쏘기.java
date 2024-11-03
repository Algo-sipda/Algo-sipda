import java.util.*;
import java.io.*;

public class Main{
    static int[] dx = {1, 0, -1, 0};    // 우상좌하
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(map[i], -1);
        }
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = str.charAt(j) == '/' ? 1 : 0;
            }
        }

        Queue<Razor> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.add(new Razor(1, i, 0));
            q.add(new Razor(M, i, 2));
        }
        for (int i = 1; i <= M; i++) {
            q.add(new Razor(i, 1, 3));
            q.add(new Razor(i, N, 1));
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Razor r = q.poll();
                int x = r.x;
                int y = r.y;
                int d = r.d;
                if (map[y][x] == -1)
                    continue;

                if (map[y][x] == 0) {   // / : 우상좌하 -> 하좌상우
                    q.add(new Razor(x + dx[3 - d], y + dy[3 - d], 3 - d));
                } else {     // \ : 우상좌하 -> 상우하좌
                    int nd = d % 2 == 0 ? d + 1 : d - 1;
                    q.add(new Razor(x + dx[nd], y + dy[nd], nd));
                }
            }
            cnt++;
        }

        System.out.println(cnt - 1);
    }

    public static class Razor {
        int x, y, d;

        public Razor(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}