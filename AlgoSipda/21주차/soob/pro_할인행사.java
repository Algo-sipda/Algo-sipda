import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {        
        int totalCnt = 0;
        
        for(int n : number){
            totalCnt += n;
        }
        
        int answer = discount.length - totalCnt + 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < totalCnt; i++){
            if(map.get(discount[i]) == null){
                map.put(discount[i], 1);
            }else{
                map.put(discount[i], map.get(discount[i]) + 1);
            }
        }
        
        for(int i = 0; i <= discount.length - totalCnt; i++){
            if(i != 0){
                map.put(discount[i-1], map.get(discount[i-1]) - 1);
                if(map.get(discount[i+totalCnt-1]) == null){
                    map.put(discount[i+totalCnt-1], 1);
                }else{
                    map.put(discount[i+totalCnt-1], map.get(discount[i+totalCnt-1])+ 1);
                }   
            }
                                
            for(int k = 0; k < number.length; k++){
                if(map.get(want[k]) == null){   // 찾는게 배열에 없거나
                    answer--;
                    break;
                }
                if(map.get(want[k]) != number[k]){  // 수량이 안맞거나
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}