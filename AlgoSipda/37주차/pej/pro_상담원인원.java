// https://school.programmers.co.kr/learn/courses/30/lessons/214288

import java.util.*;
class Solution {
    static List<Job>[] jobsForType;
    public int solution(int K, int N, int[][] reqs) {
        int answer = 0;
        
        jobsForType = new ArrayList[K+1];
        for(int i = 0; i <= K; i++){
            jobsForType[i] = new ArrayList<>();
        }
        
        for(int[] req : reqs) {
            int a = req[0];
            int b = req[1];
            int type = req[2];
            jobsForType[type].add(new Job(a, a + b));
        }
        int[][] waitTimeForEach = new int[K+1][N+1];
        for(int k = 1; k <= K; k++) { // k번째 상담 타입에 
            if(jobsForType[k].size() == 0)continue;
            for(int cnt = 1; cnt <= N - K + 1; cnt++) { // 상담원을 cnt 명 배치했을 때 
                int waitTime = calculate(jobsForType[k], cnt); // 기다리는 시간을 계산          
                waitTimeForEach[k][cnt] = waitTime;
            }        
        }
        
        // 상담원들 한명씩 배치
        int[] current = new int[K+1];
        for(int k = 1; k <= K; k++){
            current[k] = 1;
        }
        
        // 한 명씩 배치하고 남은 상담사 수 
        int remain = N - K ; 
        while(remain-- > 0) { // -> 그리디 적인 사고 : 대기 시간이 가장 많이 줄어든 곳에 상담사 배치 
            int maxReduceTime = 0; // 상담사 수가 증가했을 떄 대기 시간이 가장 많이 줄어든 시간
            int correspondingType = 0; // 해당 타입의 번호
            for(int type = 1; type <= K; type++){
                int c = current[type]; // type에 현재 배치된 상담사 수 
                int wait = waitTimeForEach[type][c]; // 기다리는 시간
                int next = waitTimeForEach[type][c + 1]; // 상담사를 한 명 더 배치했을 때 기다리는 시간
                int reduce = Math.abs(wait - next); // 얼마다 급격히(?) 줄어들었는지 확인
                
                if(reduce >= maxReduceTime) { // 최대로 급격히 줄어든 기다리는 시간 확인 
                    maxReduceTime = reduce;
                    correspondingType = type;
                }
            }
            if(maxReduceTime == 0)break;
            current[correspondingType]++; // 가장 기다리는 시간이 많이 줄은 곳에 상담사 배치 
        }
        
        // 상담원 배치가 끝나고 계산
        for(int type = 1; type <= K; type ++){
            if(jobsForType[type].size() == 0)continue;
            int cnt = current[type];
            answer += waitTimeForEach[type][cnt];
        }
        return answer;
    }
    static int calculate(List<Job> jobsForType, int cnt){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int wait = 0;
        for(Job job : jobsForType) {
            if(pq.isEmpty() || pq.size() < cnt){ 
                pq.add(job.end);// 상담사를 최대 상담사 수 만큼 먼저 배치한다 
            }else {
                int early = pq.poll(); // 가장 빨리 끝난 상담사가 언제 끝났는지 확인한다 
                if(job.start < early){ // 기다려야 했다면
                    wait += (early - job.start); // 기다린 시간 추가 
                    pq.add(early + (job.end - job.start)); // 다음 종료 시각 추가 
                }else {
                    pq.add(job.end);
                }
            }
        }
        return wait;
    }
    
    static class Job implements Comparable<Job>{
        int start, end;
        Job(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Job o){
            return this.end - o.end;
        }
    }
}