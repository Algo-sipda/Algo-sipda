import java.io.*;
import java.util.*;

public class Main {
    static int MAX_HEIGHT = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] currentArrows = new int[MAX_HEIGHT + 1];
        int answer = 0;

        for (int height : heights) {
            if (currentArrows[height] > 0) {
                currentArrows[height]--;
            } else {
                answer++;
            }
            currentArrows[height - 1]++;
        }

        System.out.println(answer);
    }
}
