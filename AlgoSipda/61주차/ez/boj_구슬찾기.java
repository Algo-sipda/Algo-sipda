import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_구슬찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<ArrayList<Integer>> parents = new ArrayList<>();
        List<ArrayList<Integer>> child = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            parents.add(new ArrayList<>());
            child.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            parents.get(to).add(from);
            child.get(from).add(to);
        }

        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;

            int up = dfs(i, parents, visited, 0);
            if (up > N / 2) {
                ans++;
                continue;
            }
            int down = dfs(i, child, visited, 0);
            if (down > N / 2) {
                ans++;
                continue;
            }
        }
        System.out.println(ans);
    }

    private static int dfs (int cur, List<ArrayList<Integer>> list, boolean[] visited, int cnt) {
        for (int i = 0; i < list.get(cur).size(); i++) {
            if (!visited[list.get(cur).get(i)]) {
                visited[list.get(cur).get(i)] = true;
                cnt += dfs(list.get(cur).get(i), list, visited, 1);
            }
        }
        return cnt;
    }
}
