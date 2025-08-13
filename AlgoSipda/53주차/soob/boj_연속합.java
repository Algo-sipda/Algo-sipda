import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cur = Integer.parseInt(st.nextToken());
        int ans = cur;
        for (int i = 1; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            cur = Math.max(v, cur + v);
            if (cur > ans)
                ans = cur;
        }
        System.out.println(ans);
    }
}