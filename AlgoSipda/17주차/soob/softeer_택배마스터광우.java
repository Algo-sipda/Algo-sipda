import java.util.*;
import java.io.*;

public class Main {
    private static final boolean[] visited = new boolean[11];
    private static final int[] matrix = new int[11];
    private static int minSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] railData = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            railData[i] = Integer.parseInt(st.nextToken());
        }

        findMinimumSum(0, n, m, k, railData);

        bw.write(String.valueOf(minSum));
        bw.flush();
    }

    private static void findMinimumSum(int count, int n, int m, int k, int[] railData) {
        if (count == n) {
            calculateSum(n, m, k);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                matrix[count] = railData[i];
                findMinimumSum(count + 1, n, m, k, railData);
                visited[i] = false;
            }
        }
    }

    private static void calculateSum(int n, int m, int k) {
        int idx = 0;
        int bucket = 0;
        int totalSum = 0;

        for (int work = 0; work < k; work++) {
            while (matrix[idx] + bucket <= m) {
                bucket += matrix[idx];
                idx = (idx + 1) % n;
            }
            totalSum += bucket;
            bucket = 0;
        }

        minSum = Math.min(totalSum, minSum);
    }
}
