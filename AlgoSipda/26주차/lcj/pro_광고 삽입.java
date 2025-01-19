import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int playTime = changeIntTime(play_time);
        int advTime = changeIntTime(adv_time);
        
        // 99:59:59는 99*60*60 + 59*60 + 59 = 359999 => 360000 
        int[] times = new int[360000];
        
        for(String log: logs) {
            String[] splitLog = log.split("-");
            int startTime = changeIntTime(splitLog[0]);
            int endTime = changeIntTime(splitLog[1]);
            
            for(int i=startTime;i<endTime;i++) {
                times[i]++;
            }
        }
        
        long totalTime = 0;
        for(int i=0;i<advTime;i++) {
            totalTime += times[i];
        }
        
        long maxTotalTime = totalTime;
        int maxIdxTime = 0;
        for(int i=advTime;i<playTime;i++) {
            totalTime += times[i] - times[i-advTime];
            
            if(totalTime > maxTotalTime) {
                maxTotalTime = totalTime;
                maxIdxTime = i-advTime + 1;
            }
        }
        
        int H = maxIdxTime / 3600;
        int M = (maxIdxTime - H * 3600) / 60;
        int S = maxIdxTime - 3600 * H - 60 * M;
        
        StringBuilder sb = new StringBuilder();
        
        if(H<10) sb.append("0");
        sb.append(H).append(":");
        
        if(M<10) sb.append("0");
        sb.append(M).append(":");
        
        if(S<10) sb.append("0");
        sb.append(S);
        
        return sb.toString();
    }
    
    public int changeIntTime(String time) {
        // 02:03:55
        String[] timeArr = time.split(":");
        int total = Integer.parseInt(timeArr[0])*60*60 
                    + Integer.parseInt(timeArr[1])*60 
                    + Integer.parseInt(timeArr[2]);
        return total;
    }
}