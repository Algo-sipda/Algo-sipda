import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[][] arr = new long[N + 1][M + 1];
        for (int n = 1; n <= N; n++) {
            String str = br.readLine();
            for (int m = 1; m <= M; m++) {
                int x = str.charAt(m - 1) - '0';
                arr[n][m] = x + arr[n - 1][m] + arr[n][m - 1] - arr[n - 1][m - 1];
            }
        }

        long max = 0;

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                long s1 = arr[n][m];
                long s2 = arr[n][M] - s1;
                long s3 = arr[N][m] - s1;
                long s4 = arr[N][M] - s1 - s2 - s3;

                long p1 = (s1 + s2) * s3 * s4;
                long p2 = (s1 + s3) * s2 * s4;
                long p3 = (s2 + s4) * s1 * s3;
                long p4 = (s3 + s4) * s1 * s2;

                max = Math.max(max, Math.max(Math.max(p1, p2), Math.max(p3, p4)));
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                long s1 = arr[i][M];
                long s2 = arr[j][M] - s1;
                long s3 = arr[N][M] - s1 - s2;
                max = Math.max(max, s1 * s2 * s3);
            }
        }

        for (int i = 1; i <= M; i++) {
            for (int j = i + 1; j <= M; j++) {
                long s1 = arr[N][i];
                long s2 = arr[N][j] - s1;
                long s3 = arr[N][M] - s1 - s2;
                max = Math.max(max, s1 * s2 * s3);
            }
        }

        System.out.println(max);
    }
}