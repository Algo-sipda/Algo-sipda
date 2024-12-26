import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> heights = new ArrayDeque<>();
        int answer = 0;
        for (int i = 0; i <= n; i++) {
            int y;
            if (i < n) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
            } else {
                y = 0;
            }
            while (!heights.isEmpty() && heights.peek() > y) {
                int prev = heights.peek();
                answer++;
                while (!heights.isEmpty() && prev == heights.peek()) {
                    prev = heights.pop();
                }
            }
            if (y > 0) {
                heights.push(y);
            }
        }

        System.out.println(answer);
    }
}
