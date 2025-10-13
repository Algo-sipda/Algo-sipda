import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int N, M, L;
    static int[] pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        pos = new int[N + 2];
        pos[0] = 0;
        pos[N + 1] = L;
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) pos[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos);
        int lo = 1, hi = L - 1, ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (needed(mid) > M)
                lo = mid + 1;
            else {
                ans = mid;
                hi = mid - 1;
            }
        }
        System.out.println(ans);
    }

    static int needed(int d) {
        int cnt = 0;
        for (int i = 1; i < pos.length; i++) {
            int gap = pos[i] - pos[i - 1];
            if (gap > d)
                cnt += (gap - 1) / d;
        }
        return cnt;
    }
}
