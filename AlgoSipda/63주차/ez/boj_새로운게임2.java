import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_새로운게임2 {

    static int N, K, turn;
    static int[][] map;
    static State[] horses;
    static List<Integer>[][] list;
    static final int[] dr = {0, 0, 0, -1, 1};
    static final int[] dc = {0, 1, -1, 0, 0};

    static class State {
        int r, c, dir;
        public State(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        list = new ArrayList[N + 1][N + 1];
        horses = new State[K + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horses[i] = new State(r, c, d);
            list[r][c].add(i);
        }

        while (++turn <= 1000) {
            if (!move()) {
                System.out.println(turn);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean move() {
        for (int i = 1; i < K + 1; i++) {
            State horse = horses[i];
            List<Integer> moves = new ArrayList<>();
            int r = horse.r;
            int c = horse.c;

            int start = list[r][c].size();
            for (int j = 0; j < list[r][c].size(); j++) {
                if (list[r][c].get(j) == i) {
                    start = j;
                }
                if (j >= start) {
                    moves.add(list[r][c].get(j));
                }
            }

            int nr = r + dr[horse.dir];
            int nc = c + dc[horse.dir];
            if (isOut(nr, nc) || map[nr][nc] == 2) {
                horse.dir = (horse.dir % 2 != 0) ? horse.dir + 1 : horse.dir - 1;
                nr = r + dr[horse.dir];
                nc = c + dc[horse.dir];
                if (isOut(nr, nc) || map[nr][nc] == 2) continue;
            }

            if (map[nr][nc] == 0) {
                for (int idx : moves) {
                    list[nr][nc].add(idx);
                    horses[idx].r = nr;
                    horses[idx].c = nc;
                }
            } else if (map[nr][nc] == 1) {
                for (int j = moves.size() - 1; j >= 0; j--) {
                    list[nr][nc].add(moves.get(j));
                    horses[moves.get(j)].r = nr;
                    horses[moves.get(j)].c = nc;
                }
            }

            for (int j = list[r][c].size() - 1; j >= start; j--) {
                list[r][c].remove(j);
            }

            if (list[nr][nc].size() >= 4) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }
}