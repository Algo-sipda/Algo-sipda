import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_주사위쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        int res = 0;
        int[] topArr = {5, 3, 4, 1, 2, 0};
        for (int i = 0; i < 6; i++) {
            int bottom = dice[0][i];
            int top = dice[0][topArr[i]];
            for (int j = 0; j < N; j++) {
                int max = 0;
                for (int k = 0; k < 6; k++) {
                    if (dice[j][k] == top) {
                        bottom = top;
                        top = dice[j][topArr[k]];
                        max = findMax(bottom, top);
                        break;
                    }
                }
                sum += max;
            }
            res = Math.max(res, sum);
            sum = 0;
        }
        System.out.println(res);
    }

    private static int findMax(int bottom, int top) {
        for (int i = 6; i > 0; i--) {
            if (i != bottom && i != top) {
                return i;
            }
        }
        return 0;
    }
}
