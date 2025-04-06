import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arrows = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (arrows[height] > 0) {
                arrows[height]--;
            } else {
                result++;
            }
            arrows[height - 1]++;
        }

        System.out.println(result);
    }
}
