import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            if (n > 0) {
                s = s.substring(1, s.length() - 1);
                StringTokenizer st = new StringTokenizer(s, ",");
                for (int i = 0; i < n; i++) {
                    dq.addLast(Integer.parseInt(st.nextToken()));
                }
            }
            boolean rev = false, err = false;
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                if (c == 'R') {
                    rev = !rev;
                } else {
                    if (dq.isEmpty()) {
                        err = true;
                        break;
                    }
                    if (!rev) {
                        dq.pollFirst();
                    } else {
                        dq.pollLast();
                    }
                }
            }
            if (err) {
                out.append("error\n");
            } else {
                out.append('[');
                if (!dq.isEmpty()) {
                    if (!rev) {
                        while (dq.size() > 1) {
                            out.append(dq.pollFirst()).append(',');
                        }
                        out.append(dq.pollFirst());
                    } else {
                        while (dq.size() > 1) {
                            out.append(dq.pollLast()).append(',');
                        }
                        out.append(dq.pollLast());
                    }
                }
                out.append("]\n");
            }
        }
        System.out.print(out.toString());
    }
}