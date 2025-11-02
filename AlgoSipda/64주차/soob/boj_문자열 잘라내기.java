import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static String[] cols;

    static boolean unique(int cut) {
        Set<String> set = new HashSet<>(C * 2);
        for (int c = 0; c < C; c++) {
            String s = cols[c].substring(cut);
            if (!set.add(s)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[] rows = new String[R];
        for (int r = 0; r < R; r++) rows[r] = br.readLine();

        cols = new String[C];
        for (int c = 0; c < C; c++) {
            StringBuilder sb = new StringBuilder(R);
            for (int r = 0; r < R; r++) sb.append(rows[r].charAt(c));
            cols[c] = sb.toString();
        }

        int lo = 0, hi = R - 1, ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (unique(mid)) {
                ans = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }
        System.out.println(ans);
    }
}
