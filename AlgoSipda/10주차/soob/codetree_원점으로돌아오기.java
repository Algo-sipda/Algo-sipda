import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int answer = 0;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        points = new Point[N + 1];
        boolean[] visited = new boolean[N + 1];
        points[0] = new Point(0, 0);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        DFS(0, 0, 0, 0, visited);
        System.out.println(answer);
    }

    private static void DFS(int x, int y, int direction, int visit, boolean[] visited) {
        if (visit == N + 1 && x == 0 && y == 0) {
            answer++;
            return;
        }

        for (int i = 0; i <= N; i++) {
            if (!visited[i]) {
                Point p = points[i];
                if (isValidMove(x, y, p.x, p.y, direction)) {
                    visited[i] = true;
                    DFS(p.x, p.y, getNewDirection(x, y, p.x, p.y), visit + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }

    private static boolean isValidMove(int x1, int y1, int x2, int y2, int direction) {
        // x축 평행 이동
        if (x1 == x2) {
            return (direction != 1 && y2 > y1) || (direction != 3 && y2 < y1);
        }
        // y축 평행 이동
        if (y1 == y2) {
            return (direction != 2 && x2 > x1) || (direction != 4 && x2 < x1);
        }
        return false;
    }

    private static int getNewDirection(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return (y2 > y1) ? 1 : 3;  // 위쪽 이동: 1, 아래쪽 이동: 3
        } else {
            return (x2 > x1) ? 2 : 4;  // 오른쪽 이동: 2, 왼쪽 이동: 4
        }
    }
}