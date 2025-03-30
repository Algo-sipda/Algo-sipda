import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int y = 0; y < n; y++) {
            String str = br.readLine();
            for (int x = 0; x < n; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        int[][] memo = new int[n][n];
        for (int y = 0; y < n; y++) {
            Arrays.fill(memo[y], n * n);
        }
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 0, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Status now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    int newChange = now.change;
                    if (map[ny][nx] == 0)
                        newChange++;
                    if (memo[ny][nx] > newChange) {
                        memo[ny][nx] = newChange;
                        queue.add(new Status(nx, ny, newChange));
                    }
                }

            }
        }

        System.out.println(memo[n - 1][n - 1]);
    }

    public static class Status {
        int x;
        int y;
        int change;

        public Status(int x, int y, int change) {
            this.x = x;
            this.y = y;
            this.change = change;
        }
    }


}
