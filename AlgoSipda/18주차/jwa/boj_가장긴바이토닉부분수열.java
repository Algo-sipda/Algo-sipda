import java.io.*;
import java.util.*;

public class boj_가장긴바이토닉부분수열 {
    static int N;
    static int[] numbers;

    static void checkNumbers(int i, int j, int[] result) {
        if (numbers[j] >= numbers[i])
            return;
        result[i] = Math.max(result[i], result[j] + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[] increment = new int[N];
        int[] decrement = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                checkNumbers(i, j, increment);
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                checkNumbers(i, j, decrement);
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, increment[i] + decrement[i] + 1);
        }
        System.out.println(answer);
    }
}
