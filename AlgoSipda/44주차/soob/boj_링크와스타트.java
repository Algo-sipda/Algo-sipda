import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] bonus;
    static int N;
    static long answer = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bonus = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                bonus[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> aGroup = new ArrayList<>();
        aGroup.add(0);
        List<Integer> bGroup = new ArrayList<>();
        for (int b = 1; b < N; b++) {
            dfs(1, aGroup, bGroup, b, 0, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int idx, List<Integer> aGroup, List<Integer> bGroup, int fixedB, int aSum, int bSum) {
        if (idx == N) {
            answer = Math.min(answer, Math.abs(aSum - bSum));
            return;
        }

        if (idx != fixedB) {
            int added = calcIncrement(aGroup, idx);
            aGroup.add(idx);
            dfs(idx + 1, aGroup, bGroup, fixedB, aSum + added, bSum);
            aGroup.remove(aGroup.size() - 1);
        }

        int added = calcIncrement(bGroup, idx);
        bGroup.add(idx);
        dfs(idx + 1, aGroup, bGroup, fixedB, aSum, bSum + added);
        bGroup.remove(bGroup.size() - 1);
    }

    static int calcIncrement(List<Integer> group, int idx) {
        int sum = 0;
        for (int n : group) {
            sum += bonus[n][idx] + bonus[idx][n];
        }
        return sum;
    }
}
