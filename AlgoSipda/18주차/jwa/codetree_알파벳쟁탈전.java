import java.io.*;
import java.util.*;

public class codetree_알파벳쟁탈전 {
    static class Condition {
        char c;
        int a, b;

        Condition(char c, int a, int b) {
            this.c = c;
            this.a = a;
            this.b = b;
        }
    }

    static int N, K;
    static Condition[] conditions;
    static char[] result;
    static char[] alphabets = {'A', 'B', 'C'};
    static int answer = 0;

    static void dfs(int idx) {
        for (Condition condition : conditions) {
            if (idx < condition.a || idx < condition.b) {
                continue;
            }
            if (condition.c == 'S' && result[condition.a - 1] != result[condition.b - 1]) {
                return;
            } else if (condition.c == 'D' && result[condition.a - 1] == result[condition.b - 1]) {
                return;
            }
        }

        if (idx == N) {
            answer++;
            return;
        }

        for (char alpha : alphabets) {
            result[idx] = alpha;
            dfs(idx + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        result = new char[N];
        conditions = new Condition[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            conditions[i] = new Condition(c, a, b);
        }
        dfs(0);
        System.out.println(answer);
    }
}
