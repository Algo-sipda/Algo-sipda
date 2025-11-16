import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0;
        int hi = 0;
        for (int i = 0; i < n; i++) {
            if (lo < a[i]) lo = a[i];
            hi += a[i];
        }

        int ans = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int need = count(mid);
            if (need <= m) {
                ans = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }

        System.out.println(ans);
    }

    static int count(int cap) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + a[i] > cap) {
                cnt++;
                sum = 0;
            }
            sum += a[i];
        }
        return cnt;
    }
}
