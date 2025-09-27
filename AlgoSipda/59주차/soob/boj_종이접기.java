import java.io.*;
import java.util.*;

public class Main {
    static boolean ok(char[] a, int l, int r) {
        if (l >= r) return true;
        int m = (l + r) >>> 1;
        for (int i = 1; m - i >= l && m + i <= r; i++) if (a[m - i] == a[m + i]) return false;
        return ok(a, l, m - 1) && ok(a, m + 1, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String s = br.readLine().trim();
            while (s.isEmpty()) s = br.readLine().trim();
            char[] a = s.toCharArray();
            sb.append(ok(a, 0, a.length - 1) ? "YES" : "NO").append('\n');
        }
        System.out.print(sb.toString());
    }
}
