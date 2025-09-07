import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_쿼드트리 {

    static int N;
    static char[][] map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder('(');
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        recur(0, 0, N);

        System.out.println(sb);
    }

    private static void recur(int r, int c, int len) {
        if (isSame(r, c, len)) {
            sb.append(map[r][c]);
        } else {
            sb.append("(");
            int next = len / 2;
            recur(r, c, next);
            recur(r, c + next , next);
            recur(r + next, c, next);
            recur(r + next, c + next, next);
            sb.append(")");
        }
    }

    private static boolean isSame(int r, int c, int len) {
        char num = map[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (map[i][j] != num) {
                    return false;
                }
            }
        }
        return true;
    }
}
