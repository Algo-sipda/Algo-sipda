import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];
        int kind = 0;

        for (int i = 0; i < k; i++) {
            if (count[belt[i]]++ == 0) kind++;
        }

        int max = count[c] == 0 ? kind + 1 : kind;

        for (int i = 1; i < N; i++) {
            int remove = belt[(i - 1) % N];
            if (--count[remove] == 0) kind--;

            int add = belt[(i + k - 1) % N];
            if (count[add]++ == 0) kind++;

            int total = count[c] == 0 ? kind + 1 : kind;
            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}
