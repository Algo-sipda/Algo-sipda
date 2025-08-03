import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_센서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensor);

        int[] d = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            d[i] = sensor[i + 1] - sensor[i];
        }

        Arrays.sort(d);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += d[i];
        }

        System.out.println(sum);
    }
}