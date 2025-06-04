import java.util.*;
class Solution {
    
    int N, answer = 0;
    List<List<Integer>> list;
    
    public int solution(int n, int[][] lighthouse) {
        N = n;
        list = new ArrayList<>();
        
        for(int i=0;i<=N;i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0;i<lighthouse.length;i++) {
            list.get(lighthouse[i][0]).add(lighthouse[i][1]);
            list.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }
        
        dfs(1, 0);
        
        return answer;
    }
    
    public int dfs(int cur, int before) {
        if(list.get(cur).size() == 1 && list.get(cur).get(0) == before) return 1;
        
        int tmp = 0;
        for(int i=0;i<list.get(cur).size();i++) {
            int next = list.get(cur).get(i);
            
            if(next == before) continue;
            
            tmp += dfs(next, cur);
        }
        
        if(tmp == 0) return 1;
        
        answer++;
        return 0;
    }
}