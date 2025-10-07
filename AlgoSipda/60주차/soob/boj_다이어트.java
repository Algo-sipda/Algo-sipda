import java.io.*;

public class Main {
    static int g;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine()); // 몸무게 차이
        solve();
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();
        int l = 1, r = 2;
        while (r <= 100000) {
            long diff = (long) r * r - (long) l * l;
            if (diff == g) {
                sb.append(r).append("\n");
                l++;
                r++;
            } else if (diff < g)
                r++;
            else
                l++;
        }
        if (sb.length() == 0)
            System.out.println(-1);
        else
            System.out.print(sb);
    }
}
