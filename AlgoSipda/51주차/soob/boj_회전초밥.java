import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[n];
        for (int i = 0; i < n; i++)
            belt[i] = Integer.parseInt(br.readLine());

        int[] count = new int[d + 1];
        int kind = 0;

        for (int i = 0; i < k; i++) {
            if (count[belt[i]]++ == 0)
                kind++;
        }

        int answer = kind + (count[c] == 0 ? 1 : 0);

        for (int i = 1; i < n; i++) {
            int remove = belt[(i - 1) % n];
            int add = belt[(i + k - 1) % n];

            if (--count[remove] == 0)
                kind--;
            if (count[add]++ == 0)
                kind++;

            int now = kind + (count[c] == 0 ? 1 : 0);
            answer = Math.max(answer, now);
        }

        System.out.println(answer);
    }
}
