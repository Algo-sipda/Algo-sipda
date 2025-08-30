import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_ㄷ만들기 {

    static int N, M, bCost, wCost, min, black;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        bCost = Integer.parseInt(st.nextToken());
        wCost = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '#') {
                    black++;
                }
            }
        }

        min = Integer.MAX_VALUE;
        for (int len = 1; len < Math.min(N, M); len++) {
            int cost = start(len);
            min = Math.min(min, cost);
        }

        System.out.println(min);
    }

    private static int start(int len) {
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (i + (len * 3) - 1 >= N) break;
            for (int j = 0; j < M - len; j++) {
                if (j + (len * 3) - 1 >= M) break;
                minCost = Math.min(minCost, getCost(i, j, len));
            }
        }
        return minCost;
    }

    private static int getCost(int r, int c, int len) {
        int bCnt = 0;
        int wCnt = 0;
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + (len * 3); j++) {
                if (map[i][j] == '.') {
                    bCnt++;
                }
            }
        }

        for (int i = r + len; i < r + (len * 2); i++) {
            for (int j = c; j < c + len; j++) {
                if (map[i][j] == '.') {
                    bCnt++;
                }
            }
        }

        for (int i = r + (len * 2); i < r + (len * 3); i++) {
            for (int j = c; j < c + (len * 3); j++) {
                if (map[i][j] == '.') {
                    bCnt++;
                }
            }
        }

        int white = (black + bCnt) - (len * len * 7);
        return (bCnt * bCost) + (white * wCost);
    }

}
