import java.io.*;
import java.util.*;

public class Main {
    static int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < x) l = m + 1;
            else r = m;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] stand = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) stand[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(stand);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (y > L) continue;
            int idx = lowerBound(stand, x);
            boolean ok = false;
            if (idx < M) {
                if (Math.abs(stand[idx] - x) + y <= L) ok = true;
            }
            if (!ok && idx > 0) {
                if (Math.abs(stand[idx - 1] - x) + y <= L) ok = true;
            }
            if (ok) answer++;
        }
        System.out.println(answer);
    }
}
