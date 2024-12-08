import java.util.*;
import java.io.*;

class Move implements Comparable<Move> {
    int y, x, direct, count;

    public Move(int y, int x, int direct, int count) {
        this.y = y;
        this.x = x;
        this.direct = direct;
        this.count = count;
    }

    @Override
    public int compareTo(Move o) {
        return this.count - o.count;
    }
}

public class Main {

    public static int n, m;
    public static boolean[][] map;

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int[] ud = {2, 3};
    public static int[] lr = {0, 1};
    public static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new boolean[n + 1][m + 1];
        visit = new boolean[4][n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                String temp = st.nextToken();
                if (temp.equals("1")) map[i][j] = true;
            }
        }

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int direct = Integer.parseInt(st.nextToken());

        visit[direct - 1][y][x] = true;
        Move start = new Move(y, x, direct, 0);

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        direct = Integer.parseInt(st.nextToken());

        Move end = new Move(y, x, direct, 0);

        PriorityQueue<Move> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {

            Move cur = pq.poll();
            if (cur.y == end.y && cur.x == end.x && cur.direct == end.direct) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 1; i <= 3; i++) {
                int newX = cur.x + dx[cur.direct - 1] * i;
                int newY = cur.y + dy[cur.direct - 1] * i;

                if (newX > m || newY > n || newX <= 0 || newY <= 0 || visit[cur.direct - 1][newY][newX]) {
                    continue;
                }

                if (map[newY][newX]) break;

                visit[cur.direct - 1][newY][newX] = true;
                pq.add(new Move(newY, newX, cur.direct, cur.count + 1));
            }

            for (int i = 0; i < 2; i++) {
                int curDirect = lr[i];

                if (cur.direct == 1 || cur.direct == 2) {
                    curDirect = ud[i];
                }

                if (visit[curDirect][cur.y][cur.x]) continue;

                visit[curDirect][cur.y][cur.x] = true;
                pq.add(new Move(cur.y, cur.x, curDirect + 1, cur.count + 1));
            }
        }
    }
}
