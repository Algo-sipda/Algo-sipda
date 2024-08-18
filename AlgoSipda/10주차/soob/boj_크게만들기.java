import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            while (cnt < K && sb.length() > 0 && sb.charAt(sb.length() - 1) < number.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
                cnt++;
            }
            sb.append(number.charAt(i));
        }
        System.out.println(sb.substring(0, N - K));
    }
}