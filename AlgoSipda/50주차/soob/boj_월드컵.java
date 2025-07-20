import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] match = new int[15][2];
    static int[][] result = new int[6][3];
    static boolean possible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for (int i = 0; i < 5; i++){
            for (int j = i + 1; j < 6; j++){
                match[idx][0] = i; match[idx++][1] = j;
            }
        }

        for (int t = 0; t < 4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 3; j++){
                    result[i][j] = Integer.parseInt(st.nextToken());
                }
            }
                

            for (int i = 0; i < 6; i++)
                sum += result[i][0] + result[i][1] + result[i][2];

            possible = false;
            if (sum == 30)
                dfs(0);

            sb.append(possible ? 1 : 0).append(" ");
        }

        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (possible) return;
        if (depth == 15) {
            possible = true;
            return;
        }

        int t1 = match[depth][0];
        int t2 = match[depth][1];

        if (result[t1][0] > 0 && result[t2][2] > 0) {
            result[t1][0]--;
            result[t2][2]--;
            dfs(depth + 1);
            result[t1][0]++;
            result[t2][2]++;
        }

        if (result[t1][1] > 0 && result[t2][1] > 0) {
            result[t1][1]--;
            result[t2][1]--;
            dfs(depth + 1);
            result[t1][1]++;
            result[t2][1]++;
        }

        if (result[t1][2] > 0 && result[t2][0] > 0) {
            result[t1][2]--;
            result[t2][0]--;
            dfs(depth + 1);
            result[t1][2]++;
            result[t2][0]++;
        }
    }
}