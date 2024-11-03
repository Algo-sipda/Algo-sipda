import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        
        int x = 1;
        List<Long> plusList = new ArrayList<>();
        List<Long> minusList = new ArrayList<>();
        
        long sum = 0;
        int multiply = 1;
        for(int s : sequence){
            sum += s * multiply;
            multiply *= -1;
            if(sum >= 0)
                plusList.add(sum);
            else
                minusList.add(sum);
        }
        
        long answer = 0;
        
        if(plusList.isEmpty()){
            if(minusList.size() != 1)
                answer = Math.abs(Collections.min(minusList));
            else
                answer = -minusList.get(0);
        }
        
        if(minusList.isEmpty()){
            if(plusList.size() != 1)
                answer = Math.abs(Collections.max(plusList));
            else
                answer = plusList.get(0);
        }
        
        if(!plusList.isEmpty() && !minusList.isEmpty())
            answer = Math.abs(Collections.max(plusList)) + Math.abs(Collections.min(minusList));
        
        return answer;
    }
}