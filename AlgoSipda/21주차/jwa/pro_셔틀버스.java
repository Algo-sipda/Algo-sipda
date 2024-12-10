import java.util.*;

class Solution {
    static final int FIRST_TIME = 9;
    
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 분으로 변환
        for (String time : timetable) {
            String[] times = time.split(":");
            int minutes = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            pq.add(minutes);
        }
        
        int busTime = 0;
        int capacity = m;
        int crew = -1; // crew: 버스에 마지막으로 탄 크루 시간
        
        for (int i = 0; i < n; i++) {
            busTime = FIRST_TIME * 60 + t * i;
            capacity = m;
            // 버스에 크루 태우기
            while (!pq.isEmpty() && capacity > 0) {
                crew = pq.peek();
                if (crew <= busTime) {
                    pq.poll();
                    capacity--;
                } else {
                    crew = -1; // 못 태웠으면 -1
                    break;
                }
            }
        }
        
        int hours, minutes;
        
        if (capacity == 0 && crew != -1) {
            hours = (crew - 1) / 60;
            minutes = (crew - 1) % 60;
        } else {
            hours = busTime / 60;
            minutes = busTime % 60;
        }
        
        return String.format("%02d:%02d", hours, minutes);
    }
}
