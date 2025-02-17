import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] timeArr;
    static int[] dp;
    static List<Integer>[] preList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        timeArr = new int[N + 1];
        dp = new int[N + 1];
        preList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            preList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeArr[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;
                preList[i].add(pre);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dfs(i)).append("\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int building) {
        if (dp[building] != 0) return dp[building];

        int maxPreTime = 0;
        for (int pre : preList[building]) {
            maxPreTime = Math.max(maxPreTime, dfs(pre));
        }

        dp[building] = timeArr[building] + maxPreTime;
        return dp[building];
    }
}