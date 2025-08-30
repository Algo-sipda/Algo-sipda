import java.util.*;
import java.io.*;

public class codetree_플레이페어암호 {
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String key = br.readLine();
        char[][] map = new char[5][5];
        List<Character> alpha = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if ((char)'A' + i =='J') continue;
            alpha.add((char)('A' + i));
        }

        int r = 0;
        int c = 0;

        for (int i = 0; i < key.length(); i++) {
            if (alpha.contains(key.charAt(i))) {
                map[r][c] = key.charAt(i);
                alpha.remove(Character.valueOf(map[r][c]));
                c++;
                if (c == 5) {
                    c = 0;
                    r++;
                }
            }
        }

        for (int i = 0; i < alpha.size(); i++) {
            map[r][c] = alpha.get(i);
            c++;
            if (c == 5) {
                c = 0;
                r++;
            }
        }

        sb.append(str);
        int idx = 0;
        while (idx < sb.length() - 1) {
            if (sb.charAt(idx) == sb.charAt(idx + 1)) {
                if (sb.charAt(idx) == 'X') {
                    sb.insert(idx + 1, 'Q');
                } else {
                    sb.insert(idx + 1, 'X');
                }
            }
            idx += 2;
        }

        if (sb.length() % 2 == 1) {
            sb.append('X');
        }

        idx = 0;
        while (idx < sb.length() - 1) {
            char c1 = sb.charAt(idx);
            char c2 = sb.charAt(idx + 1);
            int cr = 0, cc = 0, cr2 = 0, cc2 = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (c1 == map[i][j]) {
                        cr = i;
                        cc = j;
                    }
                    if (c2 == map[i][j]) {
                        cr2 = i;
                        cc2 = j;
                    }
                }
            }

            if (cr == cr2) {
                if (cc + 1 >= 5)
                    cc = -1;
                if (cc2 + 1 >= 5)
                    cc2 = -1;
                sb.setCharAt(idx, map[cr][cc + 1]);
                sb.setCharAt(idx + 1, map[cr2][cc2 + 1]);
                idx += 2;
                continue;
            }

            if (cc == cc2) {
                if (cr + 1 >= 5)
                    cr = -1;
                if (cr2 + 1 >= 5)
                    cr2 = -1;
                sb.setCharAt(idx, map[cr + 1][cc]);
                sb.setCharAt(idx + 1, map[cr2 + 1][cc]);
                idx += 2;
                continue;
            }

            sb.setCharAt(idx, map[cr][cc2]);
            sb.setCharAt(idx + 1, map[cr2][cc]);

            idx += 2;
        }

        System.out.print(sb);
    }
}