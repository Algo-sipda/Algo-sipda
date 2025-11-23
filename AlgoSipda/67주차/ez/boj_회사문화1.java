import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_회사문화1 {

    static int N, M;
    static int[] comp;
    static List<Person>[] adjList;

    static class Person {
        int num, weight;
        public Person(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int up = Integer.parseInt(st.nextToken());
            if (up == -1) continue;
            adjList[up].add(new Person(i + 1, 0));
        }

        comp = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            comp[num] += weight;
        }

        dfs(1);

        for (int i = 1; i < comp.length; i++) {
            System.out.print(comp[i] + " ");
        }
    }

    private static void dfs(int num) {
        for (Person next : adjList[num]) {
            comp[next.num] += comp[num];
            dfs(next.num);
        }
    }
}