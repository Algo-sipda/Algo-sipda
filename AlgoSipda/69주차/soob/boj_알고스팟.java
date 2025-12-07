import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];
        for (int y = 0; y < M; y++) {
            String string = br.readLine();
            for (int x = 0; x < N; x++) {
                map[y][x] = string.charAt(x) - '0';
            }
        }

        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, 0));
        int[][] visited = new int[M][N];
        for (int y = 0; y < M; y++) {
            Arrays.fill(visited[y], N * M);
        }
        visited[0][0] = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Status status = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = status.x + dx[d];
                    int ny = status.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    int cnt = map[ny][nx] == 0 ? status.cnt : status.cnt + 1;
                    if (cnt >= visited[ny][nx])
                        continue;
                    queue.add(new Status(nx, ny, cnt));
                    visited[ny][nx] = cnt;
                }
            }
        }

        System.out.println(visited[M - 1][N - 1]);
    }

    public static class Status {
        int x, y, cnt;

        public Status(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
