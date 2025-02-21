import java.util.*;
import java.io.*;

public class codetree_코드트리빵 {

    static int N, M, peopleCnt, time;
    static int[][] map;
    static State[] store, people;
    static List<State> baseCamp;
    static final int[] dr = {-1, 0, 0, 1};
    static final int[] dc = {0, -1, 1, 0};

    static class State implements Comparable<State> {
        int r, c, dis;
        boolean flag;

        public State(int r, int c, int dis, boolean flag) {
            this.r = r;
            this.c = c;
            this.dis = dis;
            this.flag = flag;
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
        map = new int[N + 1][N + 1];
        store = new State[M + 1];
        people = new State[M + 1];
        baseCamp = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    baseCamp.add(new State(i, j, 0, true));
                }
            }
        }

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            store[i] = new State(r, c, 0, true);
        }

        for (int i = 0; i < M + 1; i++) {
            people[i] = new State(0, 0, 0, false);
        }

        time = 1;
        while (true) {
            // map에 있는 사람들 한 칸씩 움직이기
            for (int i = 1; i < M + 1; i++) {
                if (people[i].flag) {
                    movePerson(i);
                }
            }
            // 편의점에 도착할 경우 처리
            for (int i = 1; i < M + 1; i++) {
                if (people[i].flag) {
                    if (people[i].r == store[i].r && people[i].c == store[i].c) {
                        people[i].flag = false;
                        store[i].flag = false;
                        peopleCnt++;
                        map[people[i].r][people[i].c] = -2;
                    }
                }
            }

            // t번째 사람 가까운 베이스 캠프에 놓기
            if (time <= M) {
                start(time);
            }

            if (peopleCnt >= M) {
                break;
            }
            time++;
        }

        System.out.println(time);
    }

    private static void movePerson(int num) {
        // 편의점에서 num번 사람에게 가는 최단거리
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new int[] {store[num].r, store[num].c});
        visited[store[num].r][store[num].c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc) || visited[nr][nc]) continue;
                if (nr == people[num].r && nc == people[num].c) {
                    people[num].r = cur[0];
                    people[num].c = cur[1];
                    // System.out.println(num+"번 다음 위치: " + people[num].r + " " + people[num].c);
                    return;
                }
                if (map[nr][nc] == -2) continue;
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }
    }

    private static void start(int num) {
        // 편의점에서 가장 가까운 베캠 찾기(bfs)
        PriorityQueue<State> pq = new PriorityQueue<>();
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        queue.add(new int[] { store[num].r, store[num].c, 0 });
        visited[store[num].r][store[num].c] = true;
        int minDis = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == 1) {
                if (minDis >= cur[2]) {
                    minDis = cur[2];
                    pq.add(new State(cur[0], cur[1], cur[2], true));
                } else {
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == -2) continue;

                visited[nr][nc] = true;
                queue.add(new int[] { nr, nc , cur[2] + 1});
            }
        }

        State clo = pq.poll();
        people[num].r = clo.r;
        people[num].c = clo.c;
        people[num].flag = true;
        map[clo.r][clo.c] = -2;
        // System.out.println(num + "번 시작 위치: " + people[num].r + " " + people[num].c);
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }
}