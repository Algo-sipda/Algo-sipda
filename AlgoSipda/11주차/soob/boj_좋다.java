import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        int answer = 0;

        for (int k = 0; k < N; k++) {
            int target = list[k];
            int i = 0;
            int j = N - 1;
            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }

                int sum = list[i] + list[j];

                if (sum == target) {
                    answer++;
                    break;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        System.out.println(answer);
    }
}