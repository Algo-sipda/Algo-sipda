import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> ascMap = new HashMap<>();
        Map<Integer, List<Integer>> descMap = new HashMap<>();
        for (int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ascMap.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            descMap.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            Set<Integer> set = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int size = queue.size();
                set.addAll(queue);
                for (int j = 0; j < size; j++) {
                    int x = queue.poll();
                    if (ascMap.containsKey(x)) {
                        for (int next : ascMap.get(x)) {
                            if (visited[next])
                                continue;
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }

            queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int size = queue.size();
                set.addAll(queue);
                for (int j = 0; j < size; j++) {
                    int x = queue.poll();
                    if (descMap.containsKey(x)) {
                        for (int next : descMap.get(x)) {
                            if (visited[next])
                                continue;
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }

            System.out.println(N - set.size());
        }

    }
}
