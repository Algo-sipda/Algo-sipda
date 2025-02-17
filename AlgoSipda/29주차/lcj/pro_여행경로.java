import java.util.*;

class Solution {
    
    boolean[] visited;
    ArrayList<String> results;
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length+1];
        results = new ArrayList<>();
        
        dfs(tickets, "ICN", 1, "ICN");
        
        Collections.sort(results);
        return results.get(0).split(" ");
    }
    
    public void dfs(String[][] tickets, String start, int depth, String path) {
        if(depth == tickets.length+1) {
            results.add(path);
            return;
        }
        
        for(int i=0;i<tickets.length;i++) {
            if(tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(tickets, tickets[i][1],depth+1,path + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}