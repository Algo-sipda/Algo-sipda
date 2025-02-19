import java.io.*;
import java.util.*;

public class Main {
    static int buildingCount;
    static int[] inDegree, buildTime, maxBuildTime;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        buildingCount = Integer.parseInt(br.readLine());

        inDegree = new int[buildingCount + 1];
        buildTime = new int[buildingCount + 1];
        maxBuildTime = new int[buildingCount + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= buildingCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= buildingCount; i++) {
            st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int prerequisite = Integer.parseInt(st.nextToken());

                if (prerequisite == -1)
                    break;

                graph.get(prerequisite).add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= buildingCount; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                maxBuildTime[next] =
                        Math.max(maxBuildTime[next], maxBuildTime[curr] + buildTime[curr]);

                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= buildingCount; i++) {
            sb.append(maxBuildTime[i] + buildTime[i]).append("\n");
        }

        System.out.println(sb);
    }

}
