import java.util.*;
import java.io.*;

public class boj_용액 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) <= min) {
                min = Math.abs(sum);
                answer[0] = arr[left];
                answer[1] = arr[right];
            }
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
