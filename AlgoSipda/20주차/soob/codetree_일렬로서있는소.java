import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, answer = 0;
    static int[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cows);
        dfs(0, new int[3], 0);

        System.out.println(answer);
    }

    public static void dfs(int idx, int[] cowArr, int cnt) {
        if (cnt == 3) {
            int x = cowArr[0];
            int y = cowArr[1];
            int z = cowArr[2];
            if (y - x <= z - y && z - y <= 2 * (y - x))
                answer++;
            return;
        }

        for (int i = idx; i < N; i++) {
            cowArr[cnt] = cows[i];
            dfs(i + 1, cowArr, cnt + 1);
        }
    }
}
