
/*
너무 어려워서 끝까지 못 풀었어여...

문제 상에서 이해가 안 되는 부분 적어놨습니다.

1. 한 턴은 어떤 순서로 진행되는 것인지?
(1) 검은색 킹 이동 -> 검은색 폰 이동 -> 흰색 킹 이동 -> 흰색 폰 이동
(2) 검은색 킹 이동 -> 흰색 킹 이동 -> 검은색 폰 이동 -> 흰색 폰 이동

2. 문제 상에서 (3) 킹의 움직임 1번 조건 중에
"제일 먼저 자신의 폰과 상대의 영역 사이의 거리가 가장 작아지는 곳으로 움직입니다"
-> "자신의 폰" -> "자신의 킹" 을 말하는 건지?

 */
import java.util.*;
import java.io.*;

public class codetree_코드트리갬빗 {

    static int H, W, N, K, A, B;
    static String[][] map;
    static Piece[] black, white;
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Piece {
        int r, c, hp;
        String name;
        boolean alive;
        int[] prefDir;

        public Piece(String name, int r, int c, boolean alive) {
            this.name = name;
            this.r = r;
            this.c = c;
            this.alive = alive;
        }

        public void setHP(int hp) {
            this.hp = hp;
        }

        public void setPrefDir(int[] prefDir) {
            this.prefDir = prefDir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        black = new Piece[N + 2];
        white = new Piece[N + 2];
        map = new String[H + 1][W + 1];
        for (int i = 1; i < N + 2; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            white[i] = new Piece(name, r, c, true);
            map[r][c] = name;
        }

        for (int i = 1; i < N + 2; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            black[i] = new Piece(name, r, c, true);
            map[r][c] = name;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 2; i++) {
            char[] cdirs = st.nextToken().toCharArray();
            int[] dirs = new int[cdirs.length];
            for (int j = 0; j < cdirs.length; j++) {
                dirs[j] = cdirs[j] - '0';
            }
            white[i].setPrefDir(dirs);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 2; i++) {
            char[] cdirs = st.nextToken().toCharArray();
            int[] dirs = new int[cdirs.length];
            for (int j = 0; j < cdirs.length; j++) {
                dirs[j] = cdirs[j] - '0';
            }
            black[i].setPrefDir(dirs);
        }

        for (int i = 1; i < N + 1; i++) {
            int hp = Integer.parseInt(br.readLine());
            white[i].setHP(hp);
        }

        for (int i = 1; i < N + 1; i++) {
            int hp = Integer.parseInt(br.readLine());
            black[i].setHP(hp);
        }

        while (K-- > 0) {
            // 검은색 킹 이동
            for (int i = 1; i < N + 1; i++) {
                if (!black[i].alive) continue;
                moveKing(black, i, N);
            }

            // 검은색 폰 이동
            movePawn(black[N + 1], true);

            // 흰색 킹 이동
            for (int i = 1; i < N + 1; i++) {
                if (!white[i].alive) continue;
                moveKing(white, i, 1);
            }

            // 흰색 폰 이동
            movePawn(white[N + 1], false);

            // 승진 여부 확인
            if (black[N + 1].r == H) {
                black[N + 1].name = "q";
                map[black[N + 1].r][black[N + 1].c] = "q";
            }
            if (white[N + 1].r == 1) {
                white[N + 1].name = "Q";
                map[white[N + 1].r][white[N + 1].c] = "Q";
            }
        }

        printMap();
    }

    private static void printMap() {
        for (int i = 1; i < H + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if (map[i][j].equals("")) {
                    System.out.print(". ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static List<Integer> kings;

    private static void movePawn(Piece pawn, boolean blackOrWhite) {
        // 8가지 방향으로 인접한 기물 그룹 찾기(폰이 속한 그룹)
        kings = new ArrayList<>();
        bfs(pawn, blackOrWhite);

        // 그룹 기물 수가 5이상인 경우 자리 바꾸기
        // 1. 승진할 수 있는 사이의 거리가 작아지는 기문
        // 2. 여러개일 경우, 거리가 가까운 킹
        // 3. 열 번호 작은 기물
        if (kings.size() >= 5) {
            switchPawn(pawn, blackOrWhite);
        } else {
            // 아닌 경우 빈칸으로 이동
            // 1. 같은 팀의 모든 킹들과의 거리의 합이 가장 작아지는 빈칸? -> 가장 가까운 킹 & 빈칸
            // 2. 여러개일 경우, 자신의 방향 선호도
            // 3. 움질일 수 없으면 가만히
            switchKing();
        }

    }

    private static void switchPawn(Piece pawn, boolean blackOrWhite) {
        // 0: r, 1: c, 2: disDestination, 3: disKing
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    if (o1[3] == o2[3]) {
                        return o1[1] - o2[1];
                    }
                    return o1[3] - o2[3];
                }
                return o1[2] - o2[2];
            }
        });

        for (Integer idx : kings) {
            if (blackOrWhite) {
                int disDestination = Math.abs(H - black[idx].r);
                int disKing = (int) (Math.pow(Math.abs(black[idx].r - pawn.r), 2) + Math.pow(Math.abs(black[idx].c - pawn.c), 2));
                pq.add(new int[] {black[idx].r, black[idx].c, disDestination, disKing, idx});
            } else {
                int disDestination = Math.abs(1 - white[idx].r);
                int disKing = (int) (Math.pow(Math.abs(white[idx].r - pawn.r), 2) + Math.pow(Math.abs(white[idx].c - pawn.c), 2));
                pq.add(new int[] {white[idx].r, white[idx].c, disDestination, disKing, idx});
            }
        }

        int[] best = pq.poll();
        if (blackOrWhite) {
            switchInfo(black, best, pawn);
        } else {
            switchInfo(white, best, pawn);
        }
    }

    private static void switchInfo(Piece[] kings, int[] best, Piece pawn) {
        int tempR = kings[best[4]].r;
        int tempC = kings[best[4]].c;
        kings[best[4]].r = pawn.r;
        kings[best[4]].c = pawn.c;
        pawn.r = tempR;
        pawn.c = tempC;
        map[kings[best[4]].r][kings[best[4]].c] = kings[best[4]].name;
        map[pawn.r][pawn.c] = pawn.name;
    }

    private static void switchKing() {
        // 아닌 경우 빈칸으로 이동
        // 1. 같은 팀의 모든 킹들과의 거리의 합이 가장 작아지는 빈칸? -> 가장 가까운 킹 & 빈칸
        // 2. 여러개일 경우, 자신의 방향 선호도
        // 3. 움질일 수 없으면 가만히

    }

    private static void bfs(Piece pawn, boolean blackOrWhite) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[H + 1][W + 1];
        queue.add(new int[] {pawn.r, pawn.c});
        visited[pawn.r][pawn.c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                String bw = map[nr][nc].substring(0, 1);
                if (blackOrWhite) {
                    if (bw.equals("k") || bw.equals("p")) {
                        visited[nr][nc] = true;
                        queue.add(new int[] {nr, nc});
                        int[] res = findPiece(map[nr][nc], map[nr][nc]);
                        kings.add(res[1]);
                    }
                } else {
                    if (bw.equals("K") || bw.equals("Q")) {
                        visited[nr][nc] = true;
                        queue.add(new int[] {nr, nc});
                        int[] res = findPiece(map[nr][nc], map[nr][nc]);
                        kings.add(res[1]);
                    }
                }
            }
        }
    }

    private static void moveKing(Piece[] king, int idx, int dRow) {
        // 여덟가지 방향 중
        // 1. 영역 사이의 거리가 작아지는 곳
        // 2. 행 번호 작은 순, 방향 선호도 제일 높은 곳
        // 0: row, 1: col, 2: dis, 3: dir
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    return o1[0] - o2[0];
                }
                return o1[2] - o2[2];
            }
        });
        int[] prefDir = king[idx].prefDir;
        for (int i = 0; i < 8; i++) {
            int nr = king[idx].r + dr[prefDir[i]];
            int nc = king[idx].c + dc[prefDir[i]];
            if (isOut(nr, nc)) continue;
            int dis = Math.abs(dRow - nr);
            pq.add(new int[] {nr, nc, dis, prefDir[i]});
        }

        int[] best = pq.poll();
        if (map[best[0]][best[1]].equals("")) {
            map[king[idx].r][king[idx].c] = "";
            map[best[0]][best[1]] = king[idx].name;
            king[idx].r = best[0];
            king[idx].c = best[1];
        } else {
            // 해당 위치에 어떤 기물 존재하면 "연쇄 밀치기"
            push(king, idx, best[3]);
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > H || nc > W;
    }

    private static boolean isPawn(int nr, int nc) {
        return (map[nr][nc].equals("P") || map[nr][nc].equals("p") || map[nr][nc].equals("Q") || map[nr][nc].equals("q"));
    }

    private static int[] findPiece(String org, String name) {
        // 0: black(0) or white(1), 1 : idx, 2 : notSame(0) isSame(1)
        int[] res = new int[3];
        for (int i = 1; i < N + 2; i++) {
            if (black[i].name.equals(name)) {
                res[0] = 0;
                res[1] = i;
            }
        }

        for (int i = 1; i < N + 2; i++) {
            if (white[i].name.equals(name)) {
                res[0] = 1;
                res[1] = i;
            }
        }

        if (org.substring(0, 1).equals(name.substring(0, 1))) {
            res[2] = 1;
        }

        return res;
    }

    private static void push(Piece[] king, int idx, int dir) { // "연쇄 밀치기"
        // 아무 기물도 존재하지 않거나 / 밀쳐진 기물이 체스판 밖으로 나갈 때까지 계속 재귀
        // 폰이 있으면 그만
        int nr = king[idx].r + dr[dir];
        int nc = king[idx].c + dc[dir];
        if (isOut(nr, nc) || isPawn(nr, nc)) {
            king[idx].alive = false;
            map[king[idx].r][king[idx].c] = "";
            return;
        }
        if (map[nr][nc].equals("")) {
            map[nr][nc] = king[idx].name;
            map[king[idx].r][king[idx].c] = "";
            king[idx].r = nr;
            king[idx].c = nc;
            return;
        }
        // 이동 방향 그대로 밀치기
        int[] res = findPiece(king[idx].name, map[nr][nc]);
        Piece[] nextPiece = res[0] == 0 ? black : white;
        if (res[2] == 1) { // 자기 팀 킹을 밀치면, 밀쳐진 킹은 A 대미지
            nextPiece[res[1]].hp -= A;
        } else { // 상대 팀 킹을 밀치면, 밀쳐진 킹은 B 대미지
            nextPiece[res[1]].hp -= B;
        }
        if (nextPiece[res[1]].hp < 1) {
            nextPiece[res[1]].alive = false;
        }
        map[king[idx].r][king[idx].c] = "";
        king[idx].r = nr;
        king[idx].c = nc;
        map[nr][nc] = king[idx].name;
        push(nextPiece, res[1], dir);

        // 자기, 상대 팀 폰을 밀치면, 해당 킹 파괴
    }
}
