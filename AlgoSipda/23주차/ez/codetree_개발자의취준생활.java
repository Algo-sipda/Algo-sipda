import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long res;
    static int[] cost, company;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N];
        company = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cost);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            company[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        res = 1;
        for (int i = N - 1; i >= 0; i--) {
            int cnt = 0;
            int lastIdx = 0;
            for (int j = 0; j < N; j++) {
                if (!visited[j] && cost[i] <= company[j]) {
                    cnt++;
                    lastIdx = j;
                }
            }
            visited[lastIdx] = true;
            res *= cnt;
        }

        System.out.println(res);
    }
}