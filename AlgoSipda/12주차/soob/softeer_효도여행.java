import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX = 5002;
    private static final int[][] lcs = new int[MAX][MAX];

    private static int n, m, maxLCS;
    private static String string;
    private static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        string = br.readLine();
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;
        dfs(0, 0, visited);

        System.out.println(maxLCS);
        br.close();
    }

    private static void dfs(int node, int depth, boolean[] visited) {
        boolean isLeaf = true;

        for (Edge edge : graph[node]) {
            if (visited[edge.to]) continue;
            isLeaf = false;
            updateLCS(depth, edge.c);

            visited[edge.to] = true;
            dfs(edge.to, depth + 1, visited);
        }

        if (isLeaf) {
            updateMaxLCS(depth);
        }
    }

    private static void updateLCS(int depth, char c1) {
        for (int col = 0; col < string.length(); col++) {
            char c2 = string.charAt(col);
            lcs[depth + 1][col + 1] = Math.max(
                    lcs[depth][col] + (c1 == c2 ? 1 : 0),
                    Math.max(lcs[depth + 1][col], lcs[depth][col + 1])
            );
        }
    }

    private static void updateMaxLCS(int depth) {
        for (int j = 0; j < string.length(); j++) {
            maxLCS = Math.max(maxLCS, lcs[depth][j + 1]);
        }
    }

    private static class Edge {
        int to;
        char c;

        public Edge(int to, char c) {
            this.to = to;
            this.c = c;
        }
    }
}