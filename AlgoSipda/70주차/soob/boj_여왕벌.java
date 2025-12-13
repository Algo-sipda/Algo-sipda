import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int len = 2 * M - 1;
        int[] border = new int[len];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int k = a; k < a + b; k++) {
                border[k] += 1;
            }
            for (int k = a + b; k < len; k++) {
                border[k] += 2;
            }
        }

        int[][] map = new int[M][M];

        int idx = 0;
        for (int y = M - 1; y >= 0; y--) {
            map[y][0] = 1 + border[idx++];
        }
        for (int x = 1; x < M; x++) {
            map[0][x] = 1 + border[idx++];
        }

        for (int y = 1; y < M; y++) {
            for (int x = 1; x < M; x++) {
                map[y][x] = Math.max(map[y - 1][x], map[y][x - 1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                sb.append(map[y][x]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
