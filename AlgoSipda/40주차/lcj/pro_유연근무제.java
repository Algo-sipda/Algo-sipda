class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int[] endTimes = new int[schedules.length];
        
        for(int i=0;i<schedules.length;i++) {
            int hour = schedules[i] / 100;
            int minute = schedules[i] % 100;
            endTimes[i] = (hour * 100 + minute + 10);
            
            if(endTimes[i] % 100 >= 60) endTimes[i] += 40;
        }
        
        for(int i=0;i<timelogs.length;i++) {
            boolean flag = true;
            
            for(int j=0;j<7;j++) {
                int actualDay = (startday + j - 1) % 7;
                
                if(actualDay==5 || actualDay == 6) continue;
                
                if(timelogs[i][j] > endTimes[i]) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) answer++;
        }
        return answer;
    }
}