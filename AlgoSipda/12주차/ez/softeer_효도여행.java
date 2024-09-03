import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int node;
        char course;

        public Edge(int node, char course) {
            this.node = node;
            this.course = course;
        }
    }

    static String S;
    static List<Edge>[] tree;
    static int[][] dp;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        S = br.readLine();

        tree = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        dp = new int[5001][5001];
        visited = new boolean[N + 1];
        answer = 0;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            tree[a].add(new Edge(b, c));
            tree[b].add(new Edge(a, c));
        }

        dfs(0, 1);

        System.out.println(answer);
    }

    public static void dfs(int depth, int node) {
        visited[node] = true;
        boolean isLeaf = true;

        for (Edge edge : tree[node]) {
            if (!visited[edge.node]) {
                isLeaf = false;

                for (int i = 0; i < S.length(); i++) {
                    char c2 = S.charAt(i);
                    dp[depth + 1][i + 1] = Math.max(dp[depth][i] + ((edge.course == c2) ? 1 : 0),
                            Math.max(dp[depth][i + 1], dp[depth + 1][i]));
                }

                dfs(depth + 1, edge.node);
                visited[edge.node] = false;
            }
        }

        if (isLeaf) {
            for (int j = 0; j < S.length(); j++) {
                answer = Math.max(answer, dp[depth][j + 1]);
            }
        }
    }
}