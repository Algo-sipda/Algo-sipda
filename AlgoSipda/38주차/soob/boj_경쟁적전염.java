import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, S, X, Y;
    static int[][] map;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        Queue<Point>[] pointQueue = new Queue[K + 1];
        for (int i = 0; i <= K; i++) {
            pointQueue[i] = new LinkedList<>();
        }
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int n = Integer.parseInt(st.nextToken());
                map[y][x] = n;
                pointQueue[n].add(new Point(x, y));
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < S; i++) {
            for (int k = 0; k <= K; k++) {
                int size = pointQueue[k].size();
                for (int j = 0; j < size; j++) {
                    Point p = pointQueue[k].poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = p.x + dx[d];
                        int ny = p.y + dy[d];
                        if (canGo(nx, ny)) {
                            if (map[ny][nx] == 0) {
                                map[ny][nx] = k;
                                pointQueue[k].add(new Point(nx, ny));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(map[X - 1][Y - 1]);

    }

    public static boolean canGo(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}