import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long N = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        int max = 0, sum = 0, min = 51;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, dice[i]);
            min = Math.min(min, dice[i]);
            sum += dice[i];
        }

        long answer = 0;
        if (N == 1) {
            answer = sum - max;
        } else if (N == 2) {
            int[] minArr = new int[3];
            for (int i = 0; i < 3; i++) {
                minArr[i] = Math.min(dice[i], dice[5 - i]);
            }
            Arrays.sort(minArr);
            answer += (minArr[0] + minArr[1]) * 4;
            for (int i = 0; i < 3; i++) {
                answer += Math.min(dice[i], dice[5 - i]) * 4;
            }
        } else {
            long[] visibleSideArr = new long[4];
            visibleSideArr[1] = (N - 2) * (N - 2) * 5 + (N - 2) * 4;
            visibleSideArr[2] = (N - 2) * 8 + 4;
            visibleSideArr[3] = 4;
            answer += min * visibleSideArr[1];
            int[] minArr = new int[3];
            for (int i = 0; i < 3; i++) {
                minArr[i] = Math.min(dice[i], dice[5 - i]);
            }
            Arrays.sort(minArr);
            answer += (minArr[0] + minArr[1]) * visibleSideArr[2];
            int minSum = 0;
            for (int i : minArr) {
                minSum += i;
            }
            answer += minSum * visibleSideArr[3];
        }

        System.out.println(answer);
    }
}