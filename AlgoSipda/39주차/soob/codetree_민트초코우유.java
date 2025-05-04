import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 55;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int N, D;
    static int[][] food = new int[MAX][MAX];
    static int[][] faith = new int[MAX][MAX];
    static boolean[][] visited = new boolean[MAX][MAX];
    static boolean[][] blocked = new boolean[MAX][MAX];
    static String[] board = new String[MAX];
    static List<Leader> leaders = new ArrayList<>();
    static List<Member> members = new ArrayList<>();
    static int groupSize;

    static class Pair {
        int x, y;
        Pair(int x, int y) { this.x = x; this.y = y; }
    }

    static class Leader {
        int foodCnt, negFaith, x, y;
        Leader(int foodCnt, int negFaith, int x, int y) {
            this.foodCnt = foodCnt;
            this.negFaith = negFaith;
            this.x = x;
            this.y = y;
        }
    }

    static class Member {
        int negFaith, x, y;
        Member(int negFaith, int x, int y) {
            this.negFaith = negFaith;
            this.x = x;
            this.y = y;
        }
    }

    static boolean out(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    static int getFoodCnt(int val) {
        int cnt = 0;
        if ((val & 1) != 0) cnt++;
        if ((val & 2) != 0) cnt++;
        if ((val & 4) != 0) cnt++;
        return cnt;
    }

    static void dfs(int x, int y, int type) {
        visited[x][y] = true;
        groupSize++;
        members.add(new Member(-faith[x][y], x, y));

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (out(nx, ny) || visited[nx][ny] || food[nx][ny] != type) continue;
            dfs(nx, ny, type);
        }
    }

    static void lunch() {
        leaders.clear();
        for (int i = 1; i <= N; i++) Arrays.fill(visited[i], false);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j]) continue;
                members.clear();
                groupSize = 0;
                dfs(i, j, food[i][j]);

                Collections.sort(members, (a, b) -> {
                    if (a.negFaith != b.negFaith) return a.negFaith - b.negFaith;
                    if (a.x != b.x) return a.x - b.x;
                    return a.y - b.y;
                });

                int lx = members.get(0).x, ly = members.get(0).y;
                faith[lx][ly] += groupSize;
                int cnt = getFoodCnt(food[lx][ly]);
                leaders.add(new Leader(cnt, -faith[lx][ly], lx, ly));
            }
        }
    }

    static void evening() {
        for (int i = 1; i <= N; i++) Arrays.fill(blocked[i], false);

        Collections.sort(leaders, (a, b) -> {
            if (a.foodCnt != b.foodCnt) return a.foodCnt - b.foodCnt;
            if (a.negFaith != b.negFaith) return a.negFaith - b.negFaith;
            if (a.x != b.x) return a.x - b.x;
            return a.y - b.y;
        });

        for (Leader l : leaders) {
            if (blocked[l.x][l.y]) continue;
            int dir = faith[l.x][l.y] % 4;
            int x = l.x, y = l.y;
            int p = faith[x][y] - 1;
            faith[x][y] = 1;

            while (true) {
                x += dx[dir];
                y += dy[dir];
                if (out(x, y)) break;
                if (food[x][y] == food[l.x][l.y]) continue;

                int f = faith[x][y];
                if (p > f) {
                    faith[x][y] += 1;
                    p -= (f + 1);
                    food[x][y] = food[l.x][l.y];
                    blocked[x][y] = true;
                } else {
                    faith[x][y] += p;
                    food[x][y] |= food[l.x][l.y];
                    blocked[x][y] = true;
                    break;
                }
                if (p == 0) break;
            }
        }
    }

    static void printAnswer() {
        int[] sum = new int[8];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[food[i][j]] += faith[i][j];
            }
        }
        System.out.println(sum[7] + " " + sum[3] + " " + sum[5] + " " + sum[6] + " " + sum[4] + " " + sum[2] + " " + sum[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            board[i] = " " + br.readLine();
            for (int j = 1; j <= N; j++) {
                char c = board[i].charAt(j);
                if (c == 'T') food[i][j] = 1;
                else if (c == 'C') food[i][j] = 2;
                else if (c == 'M') food[i][j] = 4;
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                faith[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int d = 0; d < D; d++) {
            lunch();
            evening();
            printAnswer();
        }
    }
}