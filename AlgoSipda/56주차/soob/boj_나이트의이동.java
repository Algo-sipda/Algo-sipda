import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int I;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            I = Integer.parseInt(br.readLine());    // 체스판의 길이
            StringTokenizer st = new StringTokenizer(br.readLine());
            int knightX = Integer.parseInt(st.nextToken());
            int knightY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());
            System.out.println(BFS(knightX, knightY, targetX, targetY));
        }

    }

    public static int BFS(int knightX, int knightY, int targetX, int targetY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(knightX, knightY));
        int answer = 0;
        boolean[][] visited = new boolean[I][I];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point p = queue.poll();
                if (p.x == targetX && p.y == targetY)
                    return answer;

                for (int d = 0; d < 8; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
                    if (visited[ny][nx]) continue;
                    queue.add(new Point(nx, ny));
                    visited[ny][nx] = true;
                }
            }
            answer++;
        }

        return answer;
    }
}
