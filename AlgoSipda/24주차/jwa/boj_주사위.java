import java.io.*;
import java.util.*;

public class Main {
    static long minSum = Long.MAX_VALUE;
    static int[] dice = new int[6];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dice.length; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;

        if (N == 1) {
            Arrays.sort(dice);
            for (int i = 0; i < dice.length - 1; i++) {
                answer += dice[i];
            }
            System.out.println(answer);
            return;
        }

        // 1면 노출
        for (int i = 0; i < dice.length; i++) {
            minSum = Math.min(minSum, dice[i]);
        }
        answer += (5L * (N - 2) * (N - 2) + 4L * (N - 2)) * minSum;

        // 2면 노출
        minSum = Long.MAX_VALUE;
        int[] twoFace = new int[2];
        combinations(2, 0, twoFace, 0);
        answer += (8L * (N - 2) + 4) * minSum;

        // 3면 노출
        minSum = Long.MAX_VALUE;
        int[] threeFace = new int[3];
        combinations(3, 0, threeFace, 0);
        answer += 4L * minSum;

        System.out.println(answer);
    }

    static void combinations(int n, int d, int[] result, int start) {
        if (d == n) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += dice[result[i]];
            }
            minSum = Math.min(minSum, sum);
            return;
        }

        for (int i = start; i < dice.length; i++) {
            boolean flag = true;
            for (int j = 0; j < d; j++) {
                if (result[j] + i == 5) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }
            result[d] = i;
            combinations(n, d + 1, result, i + 1);
        }
    }
}
