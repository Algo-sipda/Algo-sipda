import java.util.*;
import java.io.*;

public class codetree_개발자들의거리두기 {

    static class State implements Comparable<State> {
        int location, infection;

        public State(int location, int infection) {
            this.location = location;
            this.infection = infection;
        }

        @Override
        public int compareTo(State o) {
            return this.location - o.location;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<State> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        int dis = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            State p = list.get(i);
            if (p.infection == 0) {
                if (i != 0) {
                    if (list.get(i - 1).infection == 1)
                        dis = Math.min(dis, p.location - list.get(i - 1).location - 1);
                }
                if (i != list.size() - 1) {
                    if (list.get(i + 1).infection == 1)
                        dis = Math.min(dis, list.get(i + 1).location - p.location - 1);
                }
            }
        }

        int min = Integer.MIN_VALUE;
        int answer = 0;
        for (State p : list) {
            if (p.infection == 0)
                continue;
            if (min + dis < p.location) {
                answer++;
            }
            min = p.location;
        }

        System.out.println(answer);
    }
}