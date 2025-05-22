import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_고층건물 {

    static int N;
    static int[] buildings, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        answer = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            answer[i]++;
            answer[i + 1]++;
            double cur = buildings[i + 1] - buildings[i];
            for (int j = i + 2; j < N; j++) {
                double next = calc(i, j);
                if (next > cur) {
                    cur = next;
                    answer[i]++;
                    answer[j]++;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, answer[i]);
        }
        System.out.println(max);
    }

    private static double calc(int i, int j) {
        return (double) (buildings[j] - buildings[i]) / (j - i);
    }
}
