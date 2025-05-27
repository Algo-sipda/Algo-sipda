import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = y - x;
            int n = (int) Math.sqrt(d);
            if (n * n == d) {
                sb.append(n * 2 - 1);
            } else if (d <= n * n + n) {
                sb.append(n * 2);
            } else {
                sb.append(n * 2 + 1);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
