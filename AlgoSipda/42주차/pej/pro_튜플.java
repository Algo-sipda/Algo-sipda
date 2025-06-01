// [PRO] 튜플 https://school.programmers.co.kr/learn/courses/30/lessons/64065
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        // 앞 {{ 제거, 뒤 }} 제거
        // },{
        s = s.substring(2, s.length() - 2);
        String[] groups = s.split("\\},\\{");
        
        List<String[]> all = new ArrayList<>();
        for(String group : groups) {
            all.add(group.split(","));
        }
        all.sort(Comparator.comparingInt(x -> x.length));
        
        List<Integer> result = new ArrayList<>();
        Set<Integer>  visited = new HashSet<>();
        for(String[] group : all) {
            for(String item : group){
                int num = Integer.parseInt(item);
                if(!visited.contains(num)){
                    visited.add(num);
                    result.add(num);
                }
            }
        }
        
    
  
        return result.stream().mapToInt(i -> i).toArray();
    }
}