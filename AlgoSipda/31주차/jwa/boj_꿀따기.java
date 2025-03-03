import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] honey = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefixSum = new int[N];
        prefixSum[0] = honey[0];

        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + honey[i];
        }

        int answer = 0;

        // 벌 벌 통
        for (int i = 1; i < N - 1; i++) {
            answer = Math.max(answer, prefixSum[N - 1] * 2 - honey[0] - honey[i] - prefixSum[i]);
        }

        // 벌 통 벌
        for (int i = 1; i < N - 1; i++) {
            answer = Math.max(answer,
                    prefixSum[i] - honey[0] + prefixSum[N - 2] - prefixSum[i - 1]);
        }

        // 통 벌 벌
        for (int i = 1; i < N - 1; i++) {
            answer = Math.max(answer, prefixSum[N - 2] - honey[i] + prefixSum[i - 1]);
        }

        System.out.println(answer);
    }
}
