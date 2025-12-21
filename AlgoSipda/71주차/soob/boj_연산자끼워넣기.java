import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // 수의 개수
    static int[] a; // 수열
    static int[] op; // 연산자 개수: +, -, *, /
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        a = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, a[0]);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n').append(min);
        System.out.print(sb);
    }

    static void dfs(int idx, int cur) {
        if (idx == N) {
            if (cur > max)
                max = cur;
            if (cur < min)
                min = cur;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0)
                continue;

            op[i]--;
            int next = calc(cur, a[idx], i);
            dfs(idx + 1, next);
            op[i]++;
        }
    }

    static int calc(int left, int right, int type) {
        if (type == 0)
            return left + right;
        if (type == 1)
            return left - right;
        if (type == 2)
            return left * right;
        return left / right;
    }
}
