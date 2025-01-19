import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> calendar = new HashMap<>();

        for (int i = 1; i <= 366; i++) {
            calendar.put(i, 0);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int j = S; j <= E; j++) {
                calendar.put(j, calendar.get(j) + 1);
            }
        }

        int answer = 0;
        int start = 0;
        int maxPlan = 0;

        for (int i = 1; i <= 366; i++) {
            if (calendar.get(i) == 0) {
                answer += maxPlan * (i - start);
                start = 0;
                maxPlan = 0;
                continue;
            }

            if (start == 0) {
                start = i;
            }

            maxPlan = Math.max(maxPlan, calendar.get(i));
        }

        System.out.println(answer);
    }
}
