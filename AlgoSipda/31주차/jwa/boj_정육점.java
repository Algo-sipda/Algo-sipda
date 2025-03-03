import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] meats = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meats[i][0] = Integer.parseInt(st.nextToken());
            meats[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meats, (o1, o2) -> {
            if (o1[1] == o2[1])
                return o2[0] - o1[0];
            return o1[1] - o2[1];
        });

        int sum = meats[0][0];
        int cost = meats[0][1];
        int answer = sum >= M ? cost : Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) {
            if (meats[i - 1][1] == meats[i][1]) {
                cost += meats[i][1];
            } else {
                cost = meats[i][1];
            }

            sum += meats[i][0];

            if (sum >= M) {
                answer = Math.min(answer, cost);
            }
        }

        System.out.println(sum >= M ? answer : -1);
    }
}
