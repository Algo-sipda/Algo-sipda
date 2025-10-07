import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        {
            StringTokenizer st = null;
            int idx = 0;
            while (idx < N) {
                if (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens() && idx < N) {
                    a[idx++] = Integer.parseInt(st.nextToken());
                }
            }
        }

        boolean[] seen = new boolean[100001];
        long ans = 0;
        int r = 0;
        for (int l = 0; l < N; l++) {
            while (r < N && !seen[a[r]]) {
                seen[a[r]] = true;
                r++;
            }
            ans += r - l;
            seen[a[l]] = false;
        }
        System.out.println(ans);
    }
}
