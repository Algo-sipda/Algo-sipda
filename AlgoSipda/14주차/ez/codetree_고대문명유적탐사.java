import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class codetree_고대문명유적탐사 {

    static final int N = 5;
    static final int size = 3;
    static int K, M, res;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Integer> pieces;
    static PriorityQueue<Point> pq;

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Point implements Comparable<Point> {
        int r, c, sum, d;
        public Point(int r, int c, int sum, int d) {
            this.r = r;
            this.c = c;
            this.sum = sum;
            this.d = d;
        }
        public int compareTo(Point o) {
            if (this.sum == o.sum) {
                if (this.d == o.d) {
                    if (this.c == o.c) {
                        return this.r - o.r;
                    }
                    return this.c - o.c;
                }
                return this.d - o.d;
            }
            return o.sum - this.sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        pieces = new ArrayDeque<Integer>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pieces.add(Integer.parseInt(st.nextToken()));
        }

        for (int t = 0; t < K; t++) {
            res = 0;
            // 탐사 진행
            pq = new PriorityQueue<>();
            findSquare();

            // 실제로 돌리기
            if (pq.size() == 0 || pq.peek().sum == 0) break;
            Point next = pq.poll();
//			System.out.println(next.r + " " + next.c + " 에서 " + next.d +"번 회전 -> " + next.sum);
            for (int i = 0; i < next.d; i++) {
                map = rotate(map, next.r, next.c);
//				res += next.sum;
            }
//			printMap(map);

            while (true) {
                // 유물 획득
                boolean flag = false;
                visited = new boolean[N + 1][N + 1];
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (!visited[i][j]) {
                            if (bfs(map, i, j) > 2) {
//								System.out.println(res);
                                res += getGift(i, j);
//								System.out.println(cnt);
                                flag = true;
                            }
                        }
                    }
                }
                if (!flag) break;

                //			printMap(map);
                // 유물 채우기
                fillGift();

//				printMap(map);
            }

            System.out.print(res + " ");
        }
    }

    private static void fillGift() {
        for (int i = 1; i <= N; i++) {
            for (int j = N; j > 0; j--) {
                if (map[j][i] == 0) {
                    map[j][i] = pieces.poll();
                }
            }
        }
    }

    private static int getGift(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        boolean[][] check = new boolean[N + 1][N + 1];
        check[r][c] = true;
        queue.add(new int[] {r, c});
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc) || check[nr][nc]) continue;

                if (map[r][c] == map[nr][nc]) {
                    queue.add(new int[] {nr, nc});
                    check[nr][nc] = true;
                    map[nr][nc] = 0;
                    cnt++;
                }
            }
        }
        map[r][c] = 0;
        return cnt;
    }

    private static void findSquare() {
        // 3*3 격자 선택해서 맵 copy, 돌려보기
        for (int i = 1; i <= N - size + 1; i++) {
            for (int j = 1; j <= N - size + 1; j++) {
                int[][] copy = copyMap(map);
                for (int k = 1; k <= 3; k++) { // 90, 180, 270 돌리기
                    copy = rotate(copy, i, j);
                    // 유물 가치 확인
                    int sum = checkSum(copy);
                    pq.add(new Point(i, j, sum, k));
                }
            }
        }

    }

    private static int checkSum(int[][] map) {
        visited = new boolean[N + 1][N + 1];
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    int cnt = bfs(map, i, j);
                    if (cnt > 2) {
                        sum += cnt;
                    }
                }
            }
        }
        return sum;
    }

    private static int bfs(int[][] map, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        visited[r][c] = true;
        queue.add(new int[] {r, c});
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (isOut(nr, nc) || visited[nr][nc]) continue;

                if (map[r][c] == map[nr][nc]) {
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    private static int[][] rotate(int[][] map, int r, int c) {
        int[][] copy = new int[map.length][map[0].length];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                int xr = i - r, xc = j - c;
                int nr = xc, nc = size - xr - 1;
                copy[r + nr][c + nc] = map[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1 ; j <= N; j++) {
                if (copy[i][j] == 0) {
                    copy[i][j] = map[i][j];
                }
            }
        }
        return copy;
    }

    private static void printMap(int[][] map) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
