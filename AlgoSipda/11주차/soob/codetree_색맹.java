import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        char[][] map2 = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map2[i][j] = map[i][j] == 'R' ? 'G' : map[i][j];
            }
        }
        System.out.println(getCount(map) + " " + getCount(map2));
    }

    private static int getCount(char[][] map) {
        int N = map.length;
        boolean[][] visited = new boolean[N][N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char color = map[i][j];
                if (!visited[i][j]) {
                    dfs(map, visited, i, j, color);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] map, boolean[][] visited, int i, int j, char color) {
        int N = map.length;
        if (i < 0 || i >= N || j < 0 || j >= N || visited[i][j] || map[i][j] != color) {
            return;
        }
        visited[i][j] = true;
        dfs(map, visited, i - 1, j, color);
        dfs(map, visited, i + 1, j, color);
        dfs(map, visited, i, j - 1, color);
        dfs(map, visited, i, j + 1, color);
    }
}