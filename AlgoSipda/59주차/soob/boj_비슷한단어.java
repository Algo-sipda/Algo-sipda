import java.io.*;
import java.util.*;

public class Main{
    static class Node {
        String s;
        int idx;

        Node(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }
    }

    static int lcp(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] original = new String[N];
        for (int i = 0; i < N; i++) {
            original[i] = br.readLine();
        }

        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(original[i], i);
        }
        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.s.compareTo(b.s);
            }
        });

        int[] adj = new int[Math.max(0, N - 1)];
        int L = 0;
        for (int i = 0; i + 1 < N; i++) {
            adj[i] = lcp(arr[i].s, arr[i + 1].s);
            if (adj[i] > L)
                L = adj[i];
        }

        int bestI = N + 1, bestJ = N + 1;
        int i = 0;
        while (i < N) {
            int j = i;
            while (j + 1 < N && adj[j] >= L)
                j++;
            if (j > i) {
                int m1 = N + 1, m2 = N + 1;
                for (int k = i; k <= j; k++) {
                    int idx = arr[k].idx;
                    if (idx < m1) {
                        m2 = m1;
                        m1 = idx;
                    } else if (idx < m2)
                        m2 = idx;
                }
                if (m1 < bestI || (m1 == bestI && m2 < bestJ)) {
                    bestI = m1;
                    bestJ = m2;
                }
            }
            i = j + 1;
        }

        if (L == 0) {
            int m1 = 0, m2 = 1;
            if (m2 < bestJ || bestI == N + 1) {
                bestI = m1;
                bestJ = m2;
            }
        }

        if (bestI > bestJ) {
            int t = bestI;
            bestI = bestJ;
            bestJ = t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(original[bestI]).append('\n').append(original[bestJ]).append('\n');
        System.out.print(sb.toString());
    }
}
