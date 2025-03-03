import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_정육점 {

    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        int sum = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arr[i][0] = weight;
            arr[i][1] = price;
            sum += weight;
        }

        if (sum < M) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });

        int min = Integer.MAX_VALUE;
        int priceSum = 0;
        int weightSum = 0;
        int before = 0;

        for (int i = 0; i < arr.length; i++) {
            weightSum += arr[i][0];

            if (before != arr[i][1]) {
                before = arr[i][1];
                priceSum = before;
            } else {
                priceSum += arr[i][1];
            }

            if (weightSum >= M) {
                min = Math.min(min, priceSum);
            }
        }

        System.out.println(min);
    }
}
