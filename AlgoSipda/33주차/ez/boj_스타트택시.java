import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_스타트택시 {

    static int N, M, fuel;
    static int[][] map;
    static State[] people;
    static State taxi;

    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0, -1};

    static class State implements Comparable<State> {
        int num, r, c, endR, endC, dis;
        boolean flag;

        public State(int num, int r, int c, int endR, int endC, int dis, boolean flag) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.endR = endR;
            this.endC = endC;
            this.dis = dis;
            this.flag = flag;
        }

        public State(int num, int r, int c, int dis) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.dis = dis;
        }

        public int compareTo(State o) {
            if (this.dis == o.dis) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.dis - o.dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        people = new State[M + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        taxi = new State(0, r, c, 0);

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());
            int dis = bfs(startR, startC, endR, endC);
            people[i] = new State(i, startR, startC, endR, endC, dis, true);
            map[startR][startC] = i * -1;
        }

        while (M > 0) {
            // 택시에 인접한 손님 찾아서 거기로 가기
            State cur = findPerson();
            if (cur == null || cur.dis > fuel || cur.dis == -1) {
                fuel = -1;
                break;
            }
            fuel -= cur.dis;
            taxi.r = cur.r;
            taxi.c = cur.c;

            // 손님 목적지까지 모시기
            if (people[cur.num].dis > fuel || people[cur.num].dis == -1) {
                fuel = -1;
                break;
            }
            taxi.r = people[cur.num].endR;
            taxi.c = people[cur.num].endC;

            people[cur.num].flag = false;
            M--;
            fuel += people[cur.num].dis;
        }

        System.out.println(fuel);
    }

    private static State findPerson() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new State(0, taxi.r, taxi.c, 0));
        visited[taxi.r][taxi.c] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (map[cur.r][cur.c] < 0 && people[map[cur.r][cur.c] * -1].flag) {
                pq.add(new State(map[cur.r][cur.c] * -1, cur.r, cur.c, cur.dis));
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                queue.add(new State(0, nr, nc, cur.dis + 1));
            }
        }

        return pq.poll();
    }

    private static int bfs(int startR, int startC) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new State(0, startR, startC, 0));
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();


            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                queue.add(new State(0, nr, nc, cur.dis + 1));
            }
        }
        return -1;
    }

    private static int bfs(int startR, int startC, int endR, int endC) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new State(0, startR, startC, 0));
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.r == endR && cur.c == endC) {
                return cur.dis;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                queue.add(new State(0, nr, nc, cur.dis + 1));
            }
        }
        return -1;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }
}