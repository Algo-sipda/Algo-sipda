import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_다이어트 {

    static int N, min;
    static Ingredient mIngredient;
    static Ingredient[] arr;
    static boolean[] visited;
    static List<Integer> selected, answer;

    static class Ingredient {
        int n, p, f, s, u, c;
        public Ingredient(int n, int p, int f, int s, int u, int c) {
            this.n = n;
            this.p = p;
            this.f = f;
            this.s = s;
            this.u = u;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new Ingredient[N];
        visited = new boolean[N];
        min = Integer.MAX_VALUE;
        selected = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        mIngredient = new Ingredient(0, p, f, s, u, 0);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            u = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[i] = new Ingredient(i + 1, p, f, s, u, c);
        }

        dfs(0, 0, 0, 0, 0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
        for (int ans : answer) {
            System.out.print(ans + " ");
        }
    }

    private static void dfs(int cnt, int sumP, int sumF, int sumS, int sumU, int sumC) {
        if (min < sumC) {
            return;
        }
        if (sumP >= mIngredient.p && sumF >= mIngredient.f && sumS >= mIngredient.s && sumU >= mIngredient.u) {
            if (min > sumC) {
                min = sumC;
                answer = new ArrayList<>();
                answer.addAll(selected);
            }
            return;
        }
        if (cnt >= N) {
            return;
        }

        selected.add(cnt + 1);
        dfs(cnt + 1, sumP + arr[cnt].p, sumF + arr[cnt].f, sumS + arr[cnt].s, sumU + arr[cnt].u, sumC + arr[cnt].c);
        selected.remove(Integer.valueOf(cnt + 1));

        dfs(cnt + 1, sumP, sumF, sumS, sumU, sumC);
    }

}
