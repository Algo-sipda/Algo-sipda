import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_꿀따기 {

    static int N, max;
    static int[] honey, front, back;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        honey = new int[N];
        front = new int[N];
        back = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                front[i] = honey[i];
                continue;
            }
            front[i] += front[i - 1] + honey[i];
        }

        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1) {
                back[i] = honey[i];
                continue;
            }
            back[i] += back[i + 1] + honey[i];
        }

        beeBeeHoney();
        beeHoneyBee();
        honeyBeeBee();

        System.out.println(max);
    }

    private static void beeBeeHoney() {
        int bee1 = 0;
        int hive = N - 1;

        for (int bee2 = 1; bee2 < N - 1; bee2++) {
            int beeSum1 = front[hive] - honey[bee1] - honey[bee2];
            int beeSum2 = front[hive] - front[bee2];
            max = Math.max(max, beeSum1 + beeSum2);
        }
    }

    private static void beeHoneyBee() {
        int bee1 = 0;
        int bee2 = N - 1;

        for (int hive = 1; hive < N - 1; hive++) {
            int beeSum1 = front[hive] - front[bee1];
            int beeSum2 = back[hive] - back[bee2];
            max = Math.max(max, beeSum1 + beeSum2);
        }
    }

    private static void honeyBeeBee() {
        int hive = 0;
        int bee2 = N - 1;

        for (int bee1 = 1; bee1 < N - 1; bee1++) {
            int beeSum1 = back[hive] - back[bee1];
            int beeSum2 = back[hive] - honey[bee1] - honey[bee2];
            max = Math.max(max, beeSum1 + beeSum2);
        }
    }
}
