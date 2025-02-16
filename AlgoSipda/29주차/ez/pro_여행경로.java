import java.util.*;
class pro_여행경로 {

    static List<String> allRoute = new ArrayList<>();
    static boolean[] visited;

    public String[] solution(String[][] tickets) {
        String[] answer;

        visited = new boolean[tickets.length];

        dfs(0, tickets.length, tickets, "ICN", new StringBuilder("ICN "));

        Collections.sort(allRoute);

        return allRoute.get(0).split(" ");
    }

    public static void dfs(int cnt, int n, String[][] tickets, String from, StringBuilder sb) {
        if (cnt == n) {
            allRoute.add(sb.toString());
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(from)) {
                visited[i] = true;
                dfs(cnt + 1, n, tickets, tickets[i][1], sb.append(tickets[i][1] + " "));
                visited[i] = false;
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }

}
