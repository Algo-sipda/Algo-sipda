import java.util.*;

class Solution {
    class Plan implements Comparable<Plan> {
        String name;
        int startTime;
        int playTime;
        
        public Plan(String name, int startTime, int playTime) {
            this.name = name;
            this.startTime = startTime;
            this.playTime = playTime;
        }
        
        public Plan(String name, int playTime) {
            this.name = name;
            this.playTime = playTime;
        }
        
        @Override
        public int compareTo(Plan o) {
            return this.startTime - o.startTime;
        }
    }
    
    public List<String> solution(String[][] plans) {
        List<String> answer = new LinkedList<>();
        
        PriorityQueue<Plan> pq = new PriorityQueue<>();
        
        for(int i=0;i<plans.length;i++) {
            String name = plans[i][0];
            
            String[] startTimeStr = plans[i][1].split(":");
            int h = Integer.parseInt(startTimeStr[0]);
            int m = Integer.parseInt(startTimeStr[1]);
            int startTime = h*60 + m;
            
            int playTime = Integer.parseInt(plans[i][2]);
            pq.add(new Plan(name, startTime, playTime));
        }
        
        // 남아있는 과제 -> 가장 최근에 멈춘 과제부터 시작해야 하므로 -> Stack
        Stack<Plan> remainPlans = new Stack<>();
        
        while(!pq.isEmpty()){
            Plan cur = pq.poll();
            
            int currentTime = cur.startTime;
            
            if(!pq.isEmpty()){
                Plan next = pq.peek();
                
                if(currentTime + cur.playTime < next.startTime) {
                    answer.add(cur.name);
                    currentTime += cur.playTime;
                    
                    while(!remainPlans.isEmpty()) {
                        Plan remainPlan = remainPlans.pop();
                        
                        if(currentTime + remainPlan.playTime <= next.startTime) {
                            currentTime += remainPlan.playTime;
                            answer.add(remainPlan.name);
                            continue;
                        }
                        else {
                            int time = remainPlan.playTime - (next.startTime - currentTime);
                            remainPlans.add(new Plan(remainPlan.name, time));
                            break;
                        }
                    }
                }
                else if(currentTime + cur.playTime == next.startTime){
                    answer.add(cur.name);
                    continue;
                }else {
                    int time = next.startTime - currentTime;
                    remainPlans.add(new Plan(cur.name, cur.playTime - time));
                }
            }
            else {
                // 다음에 존재하는 게 없을 경우
                if(remainPlans.isEmpty()) {
                    currentTime += cur.playTime;
                    answer.add(cur.name);
                }
                else {
                    answer.add(cur.name);
                    
                    while(!remainPlans.isEmpty()) {
                        Plan remain = remainPlans.pop();
                        answer.add(remain.name);
                    }
                }
            }
        }
        
        return answer;
    }
}