import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prev = new int[100001];
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(N);
        visited[N] = true;
        prev[N] = -1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == K)
                break;

            int[] nextArr = {cur - 1, cur + 1, cur * 2};
            for (int next : nextArr) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    prev[next] = cur;
                    queue.add(next);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int i = K; i != -1; i = prev[i])
            path.add(i);

        Collections.reverse(path);

        System.out.println(path.size() - 1);
        for (int p : path)
            System.out.print(p + " ");
    }
}
