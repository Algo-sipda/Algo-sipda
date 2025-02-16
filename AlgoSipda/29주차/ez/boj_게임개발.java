import java.util.*;
import java.io.*;

/*
다시 풀기
 */
public class boj_게임개발 {

    static class Building implements Comparable<Building> {
        int num;
        int time;

        Building(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Building arg0) {
            return time - arg0.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }

        int[] degree = new int[N + 1];
        Building[] buildings = new Building[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            buildings[i] = new Building(i, time);

            while (true) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) {
                    break;
                }

                a.get(num).add(i);

                degree[i]++;
            }
        }

        String ans = topologicalSort(a, degree, buildings, N);

        System.out.println(ans);
    }

    public static String topologicalSort(ArrayList<ArrayList<Integer>> a, int[] degree, Building[] buildings, int N) {
        PriorityQueue<Building> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.offer(buildings[i]);
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll().num;

            for (int next : a.get(now)) {
                degree[next]--;

                if (degree[next] == 0) {
                    buildings[next].time += buildings[now].time;
                    pq.offer(new Building(next, buildings[next].time));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(buildings[i].time + "\n");
        }

        return sb.toString();
    }
}