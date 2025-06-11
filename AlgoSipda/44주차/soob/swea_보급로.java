import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
 
public class Solution {
 
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            int N = Integer.parseInt(br.readLine());    // 지도의 크기
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
 
            int x = 0, y = 0, cost = 0;
            int[][] costSum = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(costSum[i], Integer.MAX_VALUE);
            }
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{x, y, cost});
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    int[] now = queue.poll();
                    int cx = now[0];
                    int cy = now[1];
                    int c = now[2];
                    for (int d = 0; d < 4; d++) {
                        int nx = cx + dx[d];
                        int ny = cy + dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                            continue;
                        int nc = c + map[ny][nx];
                        if (costSum[ny][nx] > nc) {
                            queue.add(new int[]{nx, ny, nc});
                            costSum[ny][nx] = nc;
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + costSum[N - 1][N - 1]);
        }
    }
}
