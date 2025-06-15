import java.io.*;
import java.util.*;

public class Main {
    static int[] minTree, maxTree, arr;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int size = 1;
        while (size < N) size <<= 1;
        size <<= 1;

        minTree = new int[size];
        maxTree = new int[size];

        buildMin(1, 0, N - 1);
        buildMax(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            sb.append(queryMin(1, 0, N - 1, l, r)).append(" ");
            sb.append(queryMax(1, 0, N - 1, l, r)).append("\n");
        }

        System.out.print(sb);
    }

    static void buildMin(int node, int s, int e) {
        if (s == e) {
            minTree[node] = arr[s];
            return;
        }
        int mid = (s + e) / 2;
        buildMin(node * 2, s, mid);
        buildMin(node * 2 + 1, mid + 1, e);
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
    }

    static void buildMax(int node, int s, int e) {
        if (s == e) {
            maxTree[node] = arr[s];
            return;
        }
        int mid = (s + e) / 2;
        buildMax(node * 2, s, mid);
        buildMax(node * 2 + 1, mid + 1, e);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }

    static int queryMin(int node, int s, int e, int l, int r) {
        if (r < s || e < l) return Integer.MAX_VALUE;
        if (l <= s && e <= r) return minTree[node];
        int mid = (s + e) / 2;
        return Math.min(queryMin(node * 2, s, mid, l, r),
                queryMin(node * 2 + 1, mid + 1, e, l, r));
    }

    static int queryMax(int node, int s, int e, int l, int r) {
        if (r < s || e < l) return Integer.MIN_VALUE;
        if (l <= s && e <= r) return maxTree[node];
        int mid = (s + e) / 2;
        return Math.max(queryMax(node * 2, s, mid, l, r),
                queryMax(node * 2 + 1, mid + 1, e, l, r));
    }
}
