import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int sum;
    static int[] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        scores = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[n];
        sum[0] = scores[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + scores[i];
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            Main.sum = a == 0 ? sum[b] : sum[b] - sum[a - 1];

            out.append(String.format("%.2f\n", (double) Main.sum / (b - a + 1)) + "\n");
        }

        System.out.println(out);
    }
}