import java.util.*;

class Solution {
    String[] arr = {"A", "E", "I", "O", "U"};
    List<String> list = new LinkedList<>();
    
    public int solution(String word) {
        dfs("", 0);
        
        int answer = 0;
        int idx = 0;
        while(idx != list.size()) {
            if(list.get(idx).equals(word)) {
                answer = idx;
            }
            idx++;
        }
        return answer;
    }
    
    public void dfs(String str, int depth) {
        list.add(str);
        if(depth == 5) {
            return;
        }
        for(int i=0;i<5;i++) {
            dfs(str + arr[i], depth+1);
        }
    }
}