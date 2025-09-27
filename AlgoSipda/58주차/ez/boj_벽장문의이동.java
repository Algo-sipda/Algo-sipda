import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_벽장문의이동 {

    static int N, len, result;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int one = Integer.parseInt(st.nextToken());
        int two = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(br.readLine());
        num = new int[len];
        result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        dfs(one, two, 0, 0);

        System.out.println(result);
    }

    private static void dfs(int one, int two, int idx, int cnt) {
        if (cnt > result) {
            return;
        }

        if (idx == len) {
            result = Math.min(result, cnt);
            return;
        }

        dfs(num[idx], two, idx + 1, cnt + Math.abs(one - num[idx]));

        dfs(one, num[idx], idx + 1, cnt + Math.abs(two - num[idx]));

    }
}
