import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;
    static Map<String, Integer> nameToIndex;
    static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[F * 2];
            size = new int[F * 2];
            nameToIndex = new HashMap<>();
            index = 0;

            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();

                if (!nameToIndex.containsKey(A)) {
                    nameToIndex.put(A, index);
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }
                if (!nameToIndex.containsKey(B)) {
                    nameToIndex.put(B, index);
                    parent[index] = index;
                    size[index] = 1;
                    index++;
                }

                System.out.println(union(nameToIndex.get(A), nameToIndex.get(B)));
            }
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        return size[rootA];
    }
}
