import java.util.*;
import java.io.*;

public class codetree_마법의숲탐색 {

    static int R, C, K, res;
    static int[][] map;
    static boolean[][] exit;
    static Elf[] elfs;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Elf {
        int r, c, d;
        public Elf(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        elfs = new Elf[K + 1];
        map = new int[R + 1][C + 1];
        exit = new boolean[R + 1][C + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            elfs[i] = new Elf(-1, c, d);

            // 가장 남쪽으로 내려오기
            if (!moveDown(i)) {
                continue;
            }

            // print();

            // 정령은 가장 밑으로 움직이기
            moveElf(i);
        }

        System.out.println(res);
    }

    private static void moveElf(int idx) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R + 1][C + 1];
        queue.add(new int[] { elfs[idx].r , elfs[idx].c, idx });
        visited[elfs[idx].r][elfs[idx].c] = true;
        int max = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            max = Math.max(max, cur[0]);
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                int num = cur[2];
                if (isOut(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
                if (exit[cur[0]][cur[1]]) { // 출구에 있으면
                    queue.add(new int[] { nr, nc, map[nr][nc]});
                    visited[nr][nc] = true;
                } else { // 출구에 없으면
                    if (map[cur[0]][cur[1]] == map[nr][nc]) { // 같은 골룸 안에서만 이동 가능
                        queue.add(new int[] { nr, nc, map[nr][nc]});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        // System.out.println(idx+ "번 점수: " + max);
        // System.out.println();
        res += max;
    }

    private static boolean moveDown(int idx) {
        int nr = elfs[idx].r;
        int nc = elfs[idx].c;
        int nd = elfs[idx].d;

        // System.out.println(idx+"번");
        while (nr <= R - 2) {
            if (isEmpty(nr + 1, nc + 1) && isEmpty(nr + 1, nc - 1) && isEmpty(nr + 2, nc)) { // 남쪽으로 내려갈 수 있으면
                // System.out.println("아래");
                nr += 1;
                continue;
            }
            if (nc > 2 && isEmpty(nr, nc - 2) && isEmpty(nr - 1, nc - 1) && isEmpty(nr + 1, nc - 1) && isEmpty(nr + 1, nc - 2) && isEmpty(nr + 2, nc - 1)) { // 서쪽
                // System.out.println("왼");
                nr += 1;
                nc -= 1;
                nd = (nd + 3) % 4;
                continue;
            }
            if (nc < C - 1 && isEmpty(nr, nc + 2) && isEmpty(nr - 1, nc + 1) && isEmpty(nr + 1, nc + 1) && isEmpty(nr + 1, nc + 2) && isEmpty(nr + 2, nc + 1)) { // 동쪽
                // System.out.println("오");
                nr += 1;
                nc += 1;
                nd = (nd + 1) % 4;
                continue;
            }
            break;
        }

        // 골렘이 숲 안에 완전히 존재하는지 확인
        if (nr < 2) {
            map = new int[R + 1][C + 1];
            exit = new boolean[R + 1][C + 1];
            return false;
        }

        // 가장 밑으로 내려오면 값들 갱신하기
        elfs[idx].r = nr;
        elfs[idx].c = nc;
        elfs[idx].d = nd;
        map[nr][nc] = idx;
        exit[nr + dr[nd]][nc + dc[nd]] = true;
        for (int i = 0; i < 4; i++) {
            map[nr + dr[i]][nc + dc[i]] = idx;
        }
        return true;
    }

    private static boolean isEmpty(int r, int c) {
        if (r <= 0) return true;
        if (r < -1 || c < 0 || r > R || c > C) {
            return false;
        }
        return map[r][c] == 0;
    }

    private static boolean isOut(int nr, int nc) {
        return (nr < 1 || nc < 1 || nr > R || nc > C);
    }

    private static void print() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("    ");
            for (int j = 1; j <= C ; j++) {
                System.out.print(exit[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
