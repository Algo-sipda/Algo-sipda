import java.io.*;
import java.util.*;

public class Main {
    static int MOD = 1000;

    public static int[][] prodMatrix(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                for (int k = 0; k < n; k++) {
                    result[y][x] = (result[y][x] + a[y][k] * b[k][x]) % MOD;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int[][] matrix = new int[n][n];
        Map<Long, int[][]> map = new HashMap<>();

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                matrix[y][x] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        map.put(1L, matrix);

        long pow = 1;
        while (pow * 2 <= b) {
            int[][] prev = map.get(pow);
            map.put(pow * 2, prodMatrix(prev, prev));
            pow *= 2;
        }

        int[][] answer = identityMatrix(n);
        for (long key : map.keySet()) {
            if ((b & key) != 0) {
                answer = prodMatrix(answer, map.get(key));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                sb.append(answer[y][x]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static int[][] identityMatrix(int n) {
        int[][] id = new int[n][n];
        for (int i = 0; i < n; i++) id[i][i] = 1;
        return id;
    }
}
