
class pro_전력망을둘로나누기 {

    static int[] parents;
    static int N;

    private static void makeSet() {
        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int v) {
        if (parents[v] == v) {
            return v;
        }

        return parents[v] = findSet(parents[v]);
    }

    private static void union(int u, int v) {
        int uRoot = findSet(u);
        int vRoot = findSet(v);

        if (uRoot == vRoot) {
            return;
        }

        if (uRoot > vRoot) {
            parents[uRoot] = vRoot;
        } else {
            parents[vRoot] = uRoot;
        }
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        N = n;

        for (int i = 0; i < wires.length; i++) {
            makeSet();
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                union(wires[j][0], wires[j][1]);
            }
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                union(wires[j][0], wires[j][1]);
            }
            int[] cnt = new int[2];
            int first = 0;
            for (int j = 1; j < parents.length; j++) {
                if (first == 0) {
                    first = parents[j];
                }
                if (parents[j] == first) {
                    cnt[0]++;
                } else {
                    cnt[1]++;
                }
            }
            answer = Math.min(answer, Math.abs(cnt[0] - cnt[1]));
        }
        return answer;
    }
}