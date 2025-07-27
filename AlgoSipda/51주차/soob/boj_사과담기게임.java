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
        int J = Integer.parseInt(br.readLine());
        int s = 0;
        int e = M - 1;
        int answer = 0;
        for (int j = 0; j < J; j++) {
            int x = Integer.parseInt(br.readLine()) - 1;
            if (s <= x && x <= e)
                continue;
            if (x <= s) {
                answer += s - x;
                s = x;
                e = s + M - 1;
            } else if (e <= x) {
                answer += x - e;
                e = x;
                s = e - M + 1;
            }
        }

        System.out.println(answer);
    }
}
