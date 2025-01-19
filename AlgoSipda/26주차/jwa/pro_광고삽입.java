import java.util.*;

class Solution {    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convertToTime(play_time);
        int advTime = convertToTime(adv_time);
        long[] prefix = new long[playTime + 2];
        
        for (String log : logs) {
            String[] logTime = log.split("-");
            int start = convertToTime(logTime[0]);
            int end = convertToTime(logTime[1]);
            
            prefix[start + 1]++;
            prefix[end + 1]--;
        }
        
        for (int i = 1; i <= playTime; i++) {
            prefix[i] += prefix[i - 1];
        }
        
        for (int i = 1; i <= playTime; i++) {
            prefix[i] += prefix[i - 1];
        }
        
        long maxSum = prefix[advTime];
        int startTime = 0;
        
        for (int i = 1; i + advTime <= playTime; i++) {
            long curr = prefix[i + advTime] - prefix[i];
            if (maxSum < curr) {
                maxSum = curr;
                startTime = i;
            }
        }
        
        return String.format("%02d:%02d:%02d", startTime / 3600, startTime % 3600 / 60, startTime % 60);
    }
    
    public int convertToTime(String time) {
        int[] t = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return t[0] * 60 * 60 + t[1] * 60 + t[2];
    }
}
