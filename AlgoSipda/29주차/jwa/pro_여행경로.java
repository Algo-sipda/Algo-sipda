import java.util.*;

class Solution {
    static final String START = "ICN";
    static int N;
    static HashMap<String, List<Ticket>> graph;
    static boolean[] visited;
    static List<String> answer;
    static boolean finish = false;

    static class Ticket implements Comparable<Ticket> {
        String dest;
        int idx;

        Ticket(String dest, int idx) {
            this.dest = dest;
            this.idx = idx;
        }

        @Override
        public int compareTo(Ticket o) {
            return this.dest.compareTo(o.dest);
        }
    }

    public String[] solution(String[][] tickets) {
        N = tickets.length;
        answer = new ArrayList<>();
        graph = new HashMap<>();
        visited = new boolean[tickets.length];

        for (int i = 0; i < tickets.length; i++) {
            if (!graph.containsKey(tickets[i][0])) {
                graph.put(tickets[i][0], new ArrayList<>());
            }

            graph.get(tickets[i][0]).add(new Ticket(tickets[i][1], i));
        }

        for (String k : graph.keySet()) {
            Collections.sort(graph.get(k));
        }

        answer.add(START);
        dfs(START);

        return answer.toArray(new String[0]);
    }

    static void dfs(String start) {
        if (answer.size() == N + 1) {
            finish = true;
            return;
        }

        if (!graph.containsKey(start)) {
            return;
        }

        for (Ticket next : graph.get(start)) {
            if (visited[next.idx])
                continue;

            visited[next.idx] = true;
            answer.add(next.dest);
            dfs(next.dest);

            if (finish)
                return;

            answer.remove(answer.size() - 1);
            visited[next.idx] = false;
        }
    }
}
