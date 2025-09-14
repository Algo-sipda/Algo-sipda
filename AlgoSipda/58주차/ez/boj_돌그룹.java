import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_돌그룹 {
    public static int A, B, C;
    public static boolean[][] visited;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if ((A + B + C) % 3 != 0) { //3개의 합이 3으로 나눠지지 않는경우 돌의 개수를 같게 만들 수 없습니다. 3개가 모두 같아지려면 3의 배수여야합니다.
            System.out.println("0");
            return ;
        }

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[2001][2001];
        queue.offer(new int[] {A, B, C});
        visited[A][B] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if (a == b && b == c) {
                answer = 1;
                return ;
            }

            if (a != b) {
                int na = a > b ? a - b : a + a;
                int nb = a > b ? b + b : b - a;

                if (visited[na][nb] == false) {
                    queue.offer(new int[] {na, nb, c});
                    visited[na][nb] = true;
                }
            }

            if(b != c) {
                int nb = b > c ? b - c : b + b;
                int nc = b > c ? c + c : c - b;
                if (visited[nb][nc] == false) {
                    queue.offer(new int[]{a, nb, nc});
                    visited[nb][nc] = true;
                }
            }

            if(a != c) {
                int na = a > c ? a - c : a + a;
                int nc = a > c ? c + c : c - a;
                if (visited[na][nc] == false) {
                    queue.offer(new int[] {na, b, nc});
                    visited[na][nc] = true;
                }
            }

        }

    }
}
