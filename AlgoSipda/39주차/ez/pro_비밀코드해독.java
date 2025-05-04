import java.util.*;

class pro_비밀코드해독 {

    static int n, answer;
    static int[][] q;
    static int[] ans;
    static Integer[] selected;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;

        selected = new Integer[5];
        combi(0, 1);

        return answer;
    }

    private static void combi(int cnt, int start) {
        if (cnt == 5) {
            check();
            return;
        }

        for (int i = start; i <= n; i++) {
            selected[cnt] = i;
            combi(cnt + 1, i + 1);
        }
    }

    private static void check() {
        Set<Integer> set = new HashSet<>(Arrays.asList(selected));

        for (int i = 0; i < ans.length; i++) {
            int cnt = 0;

            for (int j = 0; j < 5; j++) {
                if (set.contains(q[i][j])) {
                    cnt++;
                }
            }

            if (cnt != ans[i]) {
                return;
            }
        }
        answer++;
    }
}