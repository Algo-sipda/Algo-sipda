import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[] rails;
    static boolean[] visited;
    static int minWeight = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        rails = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            rails[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, new int[n]);
        System.out.println(minWeight);
    }

    static void dfs(int count, int[] result) {
        if(count == n) {
            int w = getWeight(result);
            minWeight = Math.min(w, minWeight);
            return;
        }
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[count] = rails[i];
                dfs(count+1, result);
                visited[i] = false;
            }
        }
    }

    static int getWeight(int[] result) {
        int r = 0;
        int idx = 0;
        for(int i = 0; i < k; i++) {
            int weight = 0;
            while(weight + result[idx] <= m) {
                weight += result[idx];
                idx = (idx + 1 == n) ? 0 : idx+1;
            }
            r += weight;
        }
        return r;
    }
}
