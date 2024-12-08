import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String time: timetable) {
            int minutes = Integer.parseInt(time.substring(0, 2))*60 + Integer.parseInt(time.substring(3));
            pq.add(minutes);
        }
        
        int startTime = 9*60; // 9시 시작
        int endTime = 0;
        
        int totalCnt = 0;
        for(int i=0;i<n;i++) {
            totalCnt = 0;
            while(!pq.isEmpty()) {
                int curTime = pq.peek();
                
                if(curTime <= startTime && totalCnt < m) {
                    pq.poll();
                    totalCnt++;
                }
                else {
                    break;
                }
                
                endTime = curTime - 1;
            }
            startTime += t;
        }
        
        if(totalCnt < m) {
            endTime = startTime - t;
        }
        
        String hour = String.format("%02d", endTime/60);
        String minute = String.format("%02d", endTime%60);
        
        return hour+":"+minute;
    }
}