import java.util.*;
import java.io.*;

public class Main {
    static class State {
        int x;
        int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, L, K;
    static ArrayList<State> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new State(x, y));
        }

        int res = 0;
        for (State a : list) {
            for (State b : list) {
                res = Math.max(res, isFound(a.x, b.y));
            }
        }

        System.out.println(K - res);
    }

    private static int isFound(int i, int j) {
        int cnt = 0;
        for (State s : list) {
            if (s.x >= i && s.x <= i + L && s.y >= j && s.y <= j + L) {
                cnt++;
            }
        }

        return cnt;
    }

}