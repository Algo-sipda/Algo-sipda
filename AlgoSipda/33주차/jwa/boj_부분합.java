import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 100_000;
        int left = 0, right = 0;
        int sum = 0;

        while (right < N) {
            sum += nums[right];

            if (sum >= S) {
                answer = Math.min(answer, right - left + 1);

                while (sum - nums[left] >= S) {
                    sum -= nums[left++];
                    answer = Math.min(answer, right - left + 1);
                }
            }

            right++;
        }

        System.out.println(answer == 100_000 ? 0 : answer);
    }
}
