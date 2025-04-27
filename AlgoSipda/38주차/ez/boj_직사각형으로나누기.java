import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_직사각형으로나누기 {

    static int N, M;
    static long res;
    static int[][] map;
    static long[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        sum = new long[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String line = br.readLine();
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (long) map[i][j];
            }
        }

        for (int i = 1; i <= M - 2; i++) {
            for (int j = i + 1; j <= M - 1; j++) {
                long rec1 = sum(1, 1, N, i);
                long rec2 = sum(1, i + 1, N, j);
                long rec3 = sum(1, j + 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }
            }
        }

        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                long rec1 = sum(1, 1, i, M);
                long rec2 = sum (i + 1, 1, j, M);
                long rec3 = sum(j + 1, 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }
            }
        }

        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M - 1; j++) {
                long rec1 = sum(1, 1, N, j);
                long rec2 = sum(1, j + 1, i, M);
                long rec3 = sum(i + 1, j + 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }

                rec1 = sum(1, 1, i, j);
                rec2 = sum(i + 1, 1, N, j);
                rec3 = sum(1, j + 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }
                rec1 = sum(1, 1, i, M);
                rec2 = sum(i + 1, 1, N, j);
                rec3 = sum(i + 1, j + 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }
                rec1 = sum(1, 1, i, j);
                rec2 = sum(1, j + 1, i, M);
                rec3 = sum(i + 1, 1, N, M);
                if (res < rec1 * rec2 * rec3) {
                    res = rec1 * rec2 * rec3;
                }
            }
        }
        System.out.println(res);
    }

    private static long sum(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1];
    }
}
