import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int answer = 0;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1][M + 1];

        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int depth) {
        if (depth == N * M) {
            answer++;
            return;
        }

        int x = depth / M + 1;
        int y = depth % M + 1;

        if (map[x - 1][y] && map[x][y - 1] && map[x - 1][y - 1]) {
            dfs(depth + 1);
        } else {
            dfs(depth + 1);
            map[x][y] = true;
            dfs(depth + 1);
            map[x][y] = false;
        }
    }
}
