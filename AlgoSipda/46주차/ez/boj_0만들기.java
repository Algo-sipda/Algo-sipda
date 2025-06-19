import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_0만들기 {

    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            recur(1, 1, 0, 1, "1");
            System.out.println(sb);
        }
    }

    private static void recur(int idx, int num, int sum, int op, String s) {
        if (idx == N) {
            sum += (num * op);
            if (sum == 0) {
                sb.append(s + "\n");
            }
            return;
        }

        recur(idx + 1, num * 10 + (idx + 1), sum, op, s + " " + (idx + 1));
        recur(idx + 1, idx + 1, sum + (num * op), 1, s + "+" +(idx + 1));
        recur(idx + 1, idx + 1, sum + (num * op), -1, s + "-" + (idx + 1));

    }
}
