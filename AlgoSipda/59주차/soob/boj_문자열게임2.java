import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (T-- > 0) {
            String s = br.readLine();
            int K = Integer.parseInt(br.readLine());
            List<Integer>[] pos = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                pos[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < s.length(); i++) {
                pos[s.charAt(i) - 'a'].add(i);
            }
            int mn = Integer.MAX_VALUE, mx = 0;
            for (int c = 0; c < 26; c++) {
                if (pos[c].size() < K)
                    continue;
                for (int i = 0; i + K - 1 < pos[c].size(); i++) {
                    int len = pos[c].get(i + K - 1) - pos[c].get(i) + 1;
                    if (len < mn)
                        mn = len;
                    if (len > mx)
                        mx = len;
                }
            }
            if (mx == 0)
                sb.append("-1\n");
            else
                sb.append(mn).append(' ').append(mx).append('\n');
        }

        System.out.print(sb.toString());
    }
}
