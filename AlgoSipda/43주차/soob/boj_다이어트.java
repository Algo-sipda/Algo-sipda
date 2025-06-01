import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int mp, mf, ms, mv, totalCost = Integer.MAX_VALUE;
    static List<Integer> totalCostList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 식재료의 개수
        List<int[]> ingredients = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());  // 단백질
        mf = Integer.parseInt(st.nextToken());  // 지방
        ms = Integer.parseInt(st.nextToken());  // 탄수화물
        mv = Integer.parseInt(st.nextToken());  // 비타민
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ingredients.add(new int[]{p, f, s, v, c});
        }

        boolean[] used = new boolean[ingredients.size()];
        dfs(0, 0, 0, 0, 0, 0, ingredients, used);

        totalCost = totalCost == Integer.MAX_VALUE ? -1 : totalCost;
        System.out.println(totalCost);
        for(int cost : totalCostList){
            System.out.print(cost + " ");
        }
    }

    public static void dfs(int idx, int p, int f, int s, int v, int cost, List<int[]> ingredients, boolean[] used) {
        if (p >= mp && f >= mf && s >= ms && v >= mv) {
            if (totalCost > cost) {
                totalCost = cost;
                totalCostList.clear();
                for (int i = 0; i < used.length; i++) {
                    if (used[i])
                        totalCostList.add(i + 1);
                }
            }
            return;
        }

        if (idx >= ingredients.size())
            return;

        int[] nowI = ingredients.get(idx);
        used[idx] = true;
        dfs(idx + 1, p + nowI[0], f + nowI[1], s + nowI[2], v + nowI[3], cost + nowI[4], ingredients, used);
        used[idx] = false;
        dfs(idx + 1, p, f, s, v, cost, ingredients, used);
    }
}
