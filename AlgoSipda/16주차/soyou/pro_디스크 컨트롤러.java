import java.util.*;
import java.io.*;

class Solution {

    public class Job implements Comparable<Job> {

        int requestTime;
        int duration;

        public Job(int requestTime, int duration) {
            this.requestTime = requestTime;
            this.duration = duration;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.duration, o.duration);
        }

    }

    public int solution(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue<>();

        int idx = 0, jobIdx = 0;
        int currentTime = 0;
        int totalTime = 0;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        while(jobIdx < jobs.length) {

            while(idx < jobs.length && jobs[idx][0] <= currentTime) {
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if(pq.isEmpty()) {
                currentTime = jobs[idx][0];
                continue;
            }

            Job cur = pq.poll();
            currentTime += cur.duration;
            totalTime += currentTime - cur.requestTime;
            jobIdx++;
        }

        return totalTime / jobs.length;
    }
}