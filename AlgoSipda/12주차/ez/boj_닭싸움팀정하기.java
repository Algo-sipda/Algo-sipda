import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_닭싸움팀정하기 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] enemyList = new ArrayList[N + 1];
        ArrayList<Integer>[] friendList = new ArrayList[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            friendList[i] = new ArrayList<>();
            enemyList[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("E")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                enemyList[a].add(b);
                enemyList[b].add(a);
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                friendList[a].add(b);
                friendList[b].add(a);
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < enemyList[i].size(); j++) {
                int n = enemyList[i].get(j);
                for (int k = 0; k<enemyList[n].size(); k++) {
                    if (i == enemyList[n].get(k)) continue;
                    friendList[i].add(enemyList[n].get(k));
                    friendList[enemyList[n].get(k)].add(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < friendList[i].size(); j++) {
                union(i, friendList[i].get(j));
            }
        }
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            hs.add(parent[i]);
        }
        System.out.println(hs.size());
    }

    public static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }
}