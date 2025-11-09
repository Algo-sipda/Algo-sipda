import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new boolean[H][W];
        for (int h = 0; h < H; h++) {
            map[h] = br.readLine().toCharArray();
        }

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (map[y][x] == 'W')
                    continue;
                if (visited[y][x])
                    continue;
                BFS(x, y);
            }
        }
        System.out.println(answer);
    }

    static void BFS(int x, int y) {
        Set<Point> group = new HashSet<>();
        group.add(new Point(x, y));
        visited[y][x] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        int count = 0;
        while (!queue.isEmpty()) {
            answer = Math.max(answer, count);
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point point = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H)
                        continue;
                    if (map[ny][nx] == 'W' || visited[ny][nx])
                        continue;
                    queue.add(new Point(nx, ny));
                    visited[ny][nx] = true;
                    group.add(new Point(nx, ny));
                }
            }
            count++;
        }
        for (Point p : group) {
            calFar(p.x, p.y);
        }
    }

    static void calFar(int x, int y) {
        boolean[][] isVisited = new boolean[H][W];
        isVisited[y][x] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        int count = 0;
        while (!queue.isEmpty()) {
            answer = Math.max(answer, count);
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point point = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H)
                        continue;
                    if (map[ny][nx] == 'W' || isVisited[ny][nx])
                        continue;
                    queue.add(new Point(nx, ny));
                    isVisited[ny][nx] = true;
                }
            }
            count++;
        }
    }
}
