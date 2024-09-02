import java.util.*;
import java.io.*;

public class Main {

    static int[] parents;

    static void init(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y)
            parents[y] = x;
        else
            parents[x] = y;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        init(n);

        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            arr[i] = x;
            if (x != 0) {
                union(i, x);
            }
        }

        for (int i = 1; i <= n; i++) {
            find(i);
        }

        Map<Integer, Integer> groupCntMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int root = parents[i];
            groupCntMap.put(root, groupCntMap.getOrDefault(root, 0) + 1);
        }

        int answer = 0;
        for (int root : groupCntMap.keySet()) {
            int edge = 0;
            for (int j = 1; j <= n; j++) {
                if (arr[j] != 0 && find(arr[j]) == root) {
                    edge++;
                }
            }
            if (edge < groupCntMap.get(root)) {
                answer += groupCntMap.get(root);
            }
        }

        System.out.println(answer);
    }
}