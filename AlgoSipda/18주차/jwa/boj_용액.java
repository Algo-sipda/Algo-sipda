import java.io.*;
import java.util.*;

public class boj_용액 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = N - 1;
        int minValue = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (Math.abs(sum) < minValue) {
                minValue = Math.abs(sum);
                answer[0] = left;
                answer[1] = right;
                if (sum == 0) {
                    break;
                }
            }
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.printf("%d %d%n", numbers[answer[0]], numbers[answer[1]]);
    }
}
