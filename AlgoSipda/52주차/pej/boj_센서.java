import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] sensors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        sensors = new int[N];
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        if (K >= N) {
            System.out.println(0);
            return;
        }

        Arrays.sort(sensors);

        // 센서 간 거리 계산
        int[] dist = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(dist);

        int sum = 0;
        for (int i = 0; i < N - K; i++) { // 작은 것들 합산
            sum += dist[i];
        }

        System.out.println(sum);
    }
}
