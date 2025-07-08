import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_주사위굴리기2 {

    static int N, M, K, result, dir;
    static int[][] map;
    static int[] dice = {1, 2, 3, 4, 5, 6};
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};
    static Node cur;

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        cur = new Node(1, 1);

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (K-- > 0) {
            moveDice();
            getResult();
            changeDir();
        }

        System.out.println(result);
    }

    private static void changeDir() {
        if (dice[5] > map[cur.r][cur.c]) {
            dir = (dir + 1) % 4;
        } else if (dice[5] < map[cur.r][cur.c]) {
            if (dir == 0) {
                dir = 3;
            } else {
                dir -= 1;
            }
        }
    }

    private static void getResult() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];
        int cnt = 1;
        queue.add(new Node(cur.r, cur.c));
        visited[cur.r][cur.c] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] != map[node.r][node.c]) continue;

                visited[nr][nc] = true;
                cnt++;
                queue.add(new Node(nr, nc));
            }
        }

        result += (cnt * map[cur.r][cur.c]);
    }

    private static void moveDice() {
        int nr = cur.r + dr[dir];
        int nc = cur.c + dc[dir];

        if (isOut(nr, nc)) {
            dir = (dir + 2) % 4;
            nr = cur.r + dr[dir];
            nc = cur.c + dc[dir];
        }

        rollDice();

        cur.r = nr;
        cur.c = nc;
    }

    private static void rollDice() {
        if (dir == 0) {
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;
        } else if (dir == 1) {
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        } else if (dir == 2) {
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        } else if (dir == 3) {
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > M;
    }
}
