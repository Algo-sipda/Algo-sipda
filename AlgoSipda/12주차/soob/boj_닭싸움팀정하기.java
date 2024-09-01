import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] enemies = new ArrayList[N + 1];
        ArrayList<Integer>[] friends = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            enemies[i] = new ArrayList<>();
            friends[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String relationType = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (relationType.equals("E")) {
                addEnemy(enemies, a, b);
            } else {
                addFriend(friends, a, b);
            }
        }

        connectIndirectFriends(friends, enemies, N);
        unionAllFriends(friends, N);

        int numberOfTeams = countDistinctTeams(N);
        System.out.println(numberOfTeams);
    }

    private static void addEnemy(ArrayList<Integer>[] enemies, int a, int b) {
        enemies[a].add(b);
        enemies[b].add(a);
    }

    private static void addFriend(ArrayList<Integer>[] friends, int a, int b) {
        friends[a].add(b);
        friends[b].add(a);
    }

    private static void connectIndirectFriends(ArrayList<Integer>[] friends, ArrayList<Integer>[] enemies, int N) {
        for (int i = 1; i <= N; i++) {
            for (int enemy : enemies[i]) {
                for (int indirectFriend : enemies[enemy]) {
                    if (i != indirectFriend) {
                        friends[i].add(indirectFriend);
                        friends[indirectFriend].add(i);
                    }
                }
            }
        }
    }

    private static void unionAllFriends(ArrayList<Integer>[] friends, int N) {
        for (int i = 1; i <= N; i++) {
            for (int friend : friends[i]) {
                union(i, friend);
            }
        }
    }

    private static int countDistinctTeams(int N) {
        HashSet<Integer> distinctTeams = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            distinctTeams.add(find(i));
        }
        return distinctTeams.size();
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}