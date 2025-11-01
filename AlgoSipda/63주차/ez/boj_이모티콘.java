import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj_이모티콘 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[2001][2001];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 1, 0, 0});
        visited[1][0] = true;
        int res = -1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == S) {
                res = cur[2];
                break;
            }
            if (!visited[cur[0]][cur[0]]) {
                visited[cur[0]][cur[0]] = true;
                queue.add(new int[] {cur[0], cur[0], cur[2] + 1});
            }
            if (cur[1] > 0 && cur[0] + cur[1] <= 2000) {
                if (!visited[cur[0] + cur[1]][cur[1]]) {
                    visited[cur[0] + cur[1]][cur[1]] = true;
                    queue.add(new int[] {cur[0] + cur[1], cur[1], cur[2] + 1});
                }
            }
            if (cur[0] > 0) {
                if (!visited[cur[0] - 1][cur[1]]) {
                    visited[cur[0] - 1][cur[1]] = true;
                    queue.add(new int[] {cur[0] - 1, cur[1], cur[2] + 1});
                }
            }
        }
        System.out.println(res);
    }
}
