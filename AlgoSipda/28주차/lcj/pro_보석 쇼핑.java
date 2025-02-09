import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> jewelKind = new HashSet<>();
        Map<String, Integer> jewelCnt = new HashMap<>();
        
        for(int i=0;i<gems.length;i++) {
            jewelKind.add(gems[i]);
        }
        
        int start = 0;
        int tmpStart = 0;
        int len = gems.length;
        
        Queue<String> q = new LinkedList<>();
        
        for(int i=0;i<gems.length;i++) {
            jewelCnt.put(gems[i], jewelCnt.getOrDefault(gems[i], 0) + 1);
            
            q.add(gems[i]);
            
            while(true) {
                String cur = q.peek();
                
                if(jewelCnt.get(cur) > 1) {
                    jewelCnt.put(cur, jewelCnt.get(cur)-1);
                    q.poll();
                    tmpStart++;
                }
                else {
                    break;
                }
            }
            
            if(jewelCnt.size() == jewelKind.size()) {
                if(len > q.size()) {
                    len = q.size();
                    start = tmpStart;
                }
            }
        }
        
        return new int[] {start+1, start+len};
    }
}