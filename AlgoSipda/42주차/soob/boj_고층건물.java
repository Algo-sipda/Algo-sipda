import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) h[i] = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i + 1; j < n; j++) {
                boolean visible = true;
                for (int k = i + 1; k < j; k++) {
                    double expect = h[i] + (double)(h[j] - h[i]) * (k - i) / (j - i);
                    if (h[k] >= expect) {
                        visible = false;
                        break;
                    }
                }
                if (visible) cnt++;
            }
            for (int j = i - 1; j >= 0; j--) {
                boolean visible = true;
                for (int k = i - 1; k > j; k--) {
                    double expect = h[i] + (double)(h[j] - h[i]) * (i - k) / (i - j);
                    if (h[k] >= expect) {
                        visible = false;
                        break;
                    }
                }
                if (visible) cnt++;
            }
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }
}
