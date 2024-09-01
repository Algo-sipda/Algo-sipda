import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int to;
        String s;

        public Point(int to, String s) {
            this.to = to;
            this.s = s;
        }
    }

    static int N, M;
    static String S;
    static ArrayList<Point>[] list;
    static int[][] dp;
    static int answer;

    static final int MAX = 5001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = br.readLine();

        list = new ArrayList[N+1];
        for(int i=1; i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            list[u].add(new Point(v, c));
            list[v].add(new Point(u, c));
        }

        boolean[] visited = new boolean[N+1];
        dp = new int[MAX][MAX];
        visited[1] = true;
        dfs(1, 0, visited);

        System.out.println(answer);
    }

    public static void dfs(int node, int depth, boolean[] visited) {
        boolean isLeaf = true;

        for(int i=0;i<list[node].size();i++) {
            Point next = list[node].get(i);

            if(visited[next.to]) continue;
            isLeaf = false;

            for(int j=0;j<S.length();j++) {
                String str = S.substring(j, j+1);

                dp[depth+1][j+1] = Math.max(dp[depth][j] + ((next.s.equals(str)) ?  1 : 0), Math.max(dp[depth+1][j], dp[depth][j+1]));
            }
            visited[next.to] = true;
            dfs(next.to, depth+1, visited);
        }

        if(isLeaf) {
            for(int j=0;j<S.length();j++) {
                answer = Math.max(answer, dp[depth][j+1]);
            }
        }
    }
}
