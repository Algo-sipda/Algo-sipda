import java.io.*;
import java.util.*;

public class softeer_나무공격 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken()) - 1;
            int R = Integer.parseInt(st.nextToken()) - 1;

            for (int r = L; r <= R; r++) {
                attack(r, 0);
            }
        }

        System.out.println(getCnt());
    }

    private static void attack(int r, int c) {
        if (map[r][c] == 1) {
            map[r][c] = 0;
            return;
        }

        if (c + 1 >= M) {
            return;
        }

        attack(r, c + 1);
    }

    private static int getCnt() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
