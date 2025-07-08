import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] count = new int[1000001];
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int min = 1000001, max = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            count[left]++;
            count[right]--;
            min = Math.min(min, left);
            max = Math.max(max, right);
        }

        for (int i = min + 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        int s_id = min, e_id = min, s = 0, A = 0, B = 0;

        while (e_id <= max) {
            if (s < K) {
                s += count[e_id++];
            } else if (s == K) {
                A = s_id;
                B = e_id;
                break;
            } else {
                s -= count[s_id++];
            }
        }

        if (A == min) A = 0;
        System.out.println(A + " " + B);
    }
}
