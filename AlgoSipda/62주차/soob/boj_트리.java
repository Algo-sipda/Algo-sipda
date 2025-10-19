import java.util.*;

public class Main {

    static List<Integer>[] tree;
    static int remove;
    static Set<Integer> leafList = new HashSet<>();
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new List[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        int root = -1;
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            if (x == -1)
                root = i;
            else
                tree[x].add(i);
        }

        remove = sc.nextInt();
        visited[remove] = true;

        DFS(root);
        System.out.println(leafList.size());
    }

    public static void DFS(int n) {
        if (visited[n])
            return;
        visited[n] = true;
        if (tree[n].isEmpty() || (tree[n].size() == 1 && tree[n].get(0) == remove)) {
            leafList.add(n);
        } else {
            for (int l : tree[n]) {
                DFS(l);
            }
        }
    }
}