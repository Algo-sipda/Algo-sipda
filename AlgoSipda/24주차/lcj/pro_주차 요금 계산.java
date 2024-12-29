import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<records.length;i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String strTime = st.nextToken();
            String[] tmp = strTime.split(":");
            int time = Integer.parseInt(tmp[0])*60 + Integer.parseInt(tmp[1]);
            String car = st.nextToken();
            String inOut = st.nextToken();
            
            map.put(car, map.getOrDefault(car, 0) + (inOut.equals("IN")? - time:time));
        }
        
        ArrayList<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        
        int[] answer = new int[map.size()];
        for(int i=0;i<keyList.size();i++) {
            answer[i] = map.get(keyList.get(i));
            if(answer[i] <= 0) {
                answer[i] += 23 * 60 + 59;
            }
            
            answer[i] -= fees[0];
            
            int fee = fees[1];
            if(answer[i] > 0) {
                fee += Math.ceil((double)answer[i] / fees[2]) * fees[3];
            }
            answer[i] = fee;
        }
        return answer;
    }
}