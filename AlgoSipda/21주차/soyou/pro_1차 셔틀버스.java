import java.util.*;
import java.io.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(String tt: timetable) {
            String[] time = tt.split(":");
            pq.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }

        int current = 540;
        int lastTime = 0;
        int passenger = 0;
        int answer = 0;

        for(int i = 0; i < n; i++) {
            passenger = 0;

            while(!pq.isEmpty() && pq.peek() <= current) {
                passenger++;
                lastTime = pq.poll();
                if (passenger == m) break;
            }
            current += t;
        }

        if(passenger == m) answer = lastTime - 1;
        else answer = current - t;

        return String.format("%02d", answer / 60) + ":" + String.format("%02d", answer % 60);
    }
}