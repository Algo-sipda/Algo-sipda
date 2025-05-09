// [PRO]  유연근무제 https://school.programmers.co.kr/learn/courses/30/lessons/388351
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        // 주중 : 출근 희망 시각 + 10분 
        // 주말 : 신경 X 
        // 시각 : 시 * 100 + 분 
        for(int s = 0; s < schedules.length; s++) {
            int schedule = schedules[s];
            boolean isOnTime = true;
            for(int t = 0; t < timelogs[s].length; t++) {
                int timelog = timelogs[s][t];
                if(isWeekend((startday + t)% 7)){
                    continue;
                }
                
                if(!checkOnTime(schedule, timelog)) {
                    isOnTime = false;
                    break;
                }
            }
            if(isOnTime){
                answer += 1;
            }
        }
        return answer;
    }
    
    static boolean checkOnTime(int schedule, int timelog) {
        int h2 = schedule / 100;
        int m2 = (schedule + 10) - h2 * 100;
        if(m2 >= 60){
            int add = m2 / 60;
            m2 = m2 % 60;
            h2 += add;
        }

        int h1 = timelog / 100;
        int m1 = timelog - h1 * 100;
        if(m1 >= 60){
            int add = m1 / 60;
            m1 = m1 % 60;
            h1 += add;
        }
        return h2 * 60 + m2 >= h1 * 60 + m1 ;
    }
    static boolean isWeekend(int day) {
        return day == 6 || day == 0;
    }
}