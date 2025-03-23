import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            boolean[][] visited = new boolean[N][M];
            dfs(map, visited, 0, 0);
            boolean isCheese = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        isCheese = true;
                        break;
                    }
                }
                if (isCheese) break;
            }
            if (!isCheese) break;
            time++;
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        int cnt = 0;
                        if (i > 0 && visited[i - 1][j]) cnt++;
                        if (i < N - 1 && visited[i + 1][j]) cnt++;
                        if (j > 0 && visited[i][j - 1]) cnt++;
                        if (j < M - 1 && visited[i][j + 1]) cnt++;
                        if (cnt >= 2) temp[i][j] = 0;
                        else temp[i][j] = 1;
                    }
                }
            }
            map = temp;
        }
        System.out.println(time);
    }

    private static void dfs(int[][] map, boolean[][] visited, int r, int c) {
        visited[r][c] = true;
        if (r > 0 && map[r - 1][c] == 0 && !visited[r - 1][c]) dfs(map, visited, r - 1, c);
        if (r < map.length - 1 && map[r + 1][c] == 0 && !visited[r + 1][c]) dfs(map, visited, r + 1, c);
        if (c > 0 && map[r][c - 1] == 0 && !visited[r][c - 1]) dfs(map, visited, r, c - 1);
        if (c < map[0].length - 1 && map[r][c + 1] == 0 && !visited[r][c + 1]) dfs(map, visited, r, c + 1);
    }
}