import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int top = 0; top < n; top++) {
            int[] temp = new int[m];
            for (int bottom = top; bottom < n; bottom++) {
                for (int col = 0; col < m; col++) {
                    temp[col] += map[bottom][col];
                }

                int sum = temp[0];
                int maxSum = temp[0];
                for (int k = 1; k < m; k++) {
                    sum = Math.max(temp[k], sum + temp[k]);
                    maxSum = Math.max(maxSum, sum);
                }

                answer = Math.max(answer, maxSum);
            }
        }

        System.out.println(answer);
    }
}
