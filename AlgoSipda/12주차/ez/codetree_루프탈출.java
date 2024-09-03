import java.util.*;
import java.io.*;

public class Main {

    static int[] parents;

    public static void makeSet(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    public static int findSet(int v) {
        if (v == parents[v]) {
            return v;
        }

        return parents[v] = findSet(parents[v]); 	// root
    }

    public static void union(int u, int v) {
        int uRoot = findSet(u);
        int vRoot = findSet(v);
        if (uRoot < vRoot)
            parents[vRoot] = uRoot;
        else
            parents[uRoot] = vRoot;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        makeSet(n);

        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            arr[i] = x;
            if (x != 0) {
                union(i, x);
            }
        }

        for (int i = 1; i <= n; i++) {
            findSet(i);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int root = parents[i];
            map.put(root, map.getOrDefault(root, 0) + 1);
        }

        int answer = 0;
        for (int root : map.keySet()) {
            int edge = 0;
            for (int j = 1; j <= n; j++) {
                if (arr[j] != 0 && findSet(arr[j]) == root) {
                    edge++;
                }
            }
            if (edge < map.get(root)) {
                answer += map.get(root);
            }
        }

        System.out.println(answer);
    }
}