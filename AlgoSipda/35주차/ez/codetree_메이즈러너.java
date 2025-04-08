import java.io.*;
import java.util.StringTokenizer;

public class codetree_메이즈러너 {

    static int N, M, K, cnt, res, squareLen;
    static int[][] wall;
    static Point[] person;
    static Point exit, square;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int num, r, c;
        boolean isOut;

        public Point (int num, int r, int c, boolean isOut) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.isOut = isOut;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wall = new int[N + 1][N + 1];
        person = new Point[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                wall[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            person[i + 1] = new Point(i + 1, r, c, false);
        }

        st = new StringTokenizer(br.readLine());
        exit = new Point(-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false);

        for (int i = 0; i < K; i++) {
            // 모든 참가자가 탈출하면 종료
            if (cnt >= M) {
                break;
            }

            // 사람 이동
            if (movePerson()) {
                break;
            }

            // 가장 작은 정사각형 찾기
            findSquare();

            // 90도 회전
            rotate();
        }

        System.out.println(res);
        System.out.println(exit.r + " " + exit.c);
    }

    private static void rotate() {
        int[][] temp = new int[N + 1][N + 1];
        // 지도 돌리기
        for (int i = square.r; i < square.r + squareLen; i++) {
            for (int j = square.c; j < square.c + squareLen; j++) {
                int r = i - square.r, c = j - square.c;
                int nr = c, nc = squareLen - r - 1;
                temp[square.r + nr][square.c + nc] = (wall[i][j] > 0 ? wall[i][j] - 1 : wall[i][j]);
            }
        }


        // 지도 반영
        for (int i = square.r; i < square.r + squareLen; i++) {
            for (int j = square.c; j < square.c + squareLen; j++) {
                wall[i][j] = temp[i][j];
            }
        }

        // 사람 위치 변경
        for (Point p : person) {
            if (p == null || p.isOut) continue;
            if (p.r >= square.r && p.r < square.r + squareLen && p.c >= square.c && p.c < square.c + squareLen) {
                int r = p.r - square.r, c = p.c - square.c;
                int nr = c, nc = squareLen - r - 1;
                p.r = nr + square.r;
                p.c = nc + square.c;
            }
        }

        // 출구 위치 변경
        if (exit.r >= square.r && exit.r < square.r + squareLen && exit.c >= square.c && exit.c < square.c + squareLen) {
            int r = exit.r - square.r, c = exit.c - square.c;
            int nr = c, nc = squareLen - r - 1;
            exit.r = nr + square.r;
            exit.c = nc + square.c;
        }
    }

    private static void findSquare() {
        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                for (int j = 1; j <= N - len + 1; j++) {

                    // 출구가 여기 안에 없으면 continue
                    if (!(exit.r >= i && exit.r <= i + len - 1 && exit.c >= j && exit.c <= j + len - 1)) continue;

                    // 사람들 중 이 범위 안에 있으면 찾기 성공
                    boolean isIn = false;
                    for (Point p : person) {
                        if (p == null || p.isOut) continue;
                        if (p.r >= i && p.r <= i + len - 1 && p.c >= j && p.c <= j + len - 1) {
                            isIn = true;
                        }
                    }

                    if (isIn) {
                        square = new Point(-1, i, j, false);
                        squareLen = len;
                        return;
                    }
                }
            }
        }
    }

    private static boolean movePerson() {
        for (int i = 1; i <= M; i++) {
            if (person[i].isOut) continue;
            int curDistance = Math.abs(person[i].r - exit.r) + Math.abs(person[i].c - exit.c);
            int dir = 0;
            boolean isChange = false;
            for (int j = 0; j < 4; j++) {
                int nr = person[i].r + dr[j];
                int nc = person[i].c + dc[j];

                if (isOutArea(nr, nc) || wall[nr][nc] > 0) continue;

                // 거리 계산
                int nextDistance = Math.abs(nr - exit.r) + Math.abs(nc - exit.c);
                // curDistance 보다 작으면 갱신
                if (curDistance > nextDistance) {
                    curDistance = nextDistance;
                    dir = j;
                    isChange = true;
                    res++;
                }
            }
            if (!isChange) continue;
            person[i].r += dr[dir];
            person[i].c += dc[dir];
            // 출구에 도착하면 값 갱신
            if (exit.r == person[i].r && exit.c == person[i].c) {
                person[i].isOut = true;
                cnt++;
                if (cnt >= M) return true;
            }
        }
        return false;
    }

    private static boolean isOutArea(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }

    private static void printMap(int[][] map) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
