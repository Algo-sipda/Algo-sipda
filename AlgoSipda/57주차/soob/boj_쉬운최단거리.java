import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], 0);
        }

        Point startP = new Point();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startP = new Point(j, i);
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        int[][] answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(answer[i], -1);
        }
        answer[startP.y][startP.x] = 0;
        visited[startP.y][startP.x] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.add(startP);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                int cost = answer[p.y][p.x];
                for (int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        if (!visited[ny][nx]) {
                            if (map[ny][nx] == 1) {
                                answer[ny][nx] = cost + 1;
                                visited[ny][nx] = true;
                                queue.add(new Point(nx, ny));
                            } else if (map[ny][nx] == 0) {
                                answer[ny][nx] = 0;
                            }
                        }
                    }
                }
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if(map[y][x] == 0)
                    System.out.print(0 + " ");
                else
                    System.out.print(answer[y][x] + " ");
            }
            System.out.println();
        }

    }

}