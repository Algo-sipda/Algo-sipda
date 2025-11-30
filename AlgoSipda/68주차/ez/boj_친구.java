import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj_친구 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, bfs(map, i, N));
        }

        System.out.println(answer);
    }

    private static int bfs(char[][] map, int cur, int N) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(cur);
        boolean[] visited = new boolean[N];
        visited[cur] = true;

        int cnt = 0;
        int depth = 0;
        while (depth < 2) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Integer num = queue.poll();
                for (int j = 0; j < N; j++) {
                    if (!visited[j] && map[num][j] == 'Y') {
                        cnt += 1;
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
            depth += 1;
        }

        return cnt;
    }
}
