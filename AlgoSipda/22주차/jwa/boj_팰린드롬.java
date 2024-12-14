import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());

        int[][] palindrome = new int[N][N];

        for (int j = 0; j < N; j++) {
            for (int i = 0; i + j < N; i++) {
                if (nums[i] == nums[i + j]) {
                    if (j < 2 || palindrome[i + 1][i + j - 1] == 1) {
                        palindrome[i][i + j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;

            sb.append(palindrome[S][E]).append("\n");
        }

        System.out.println(sb);
    }
}
