import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        
        int sum = 0;    // 다리를 건너는 트럭 무게의 합
        int time = 0;
        
        for(int i=0;i<truck_weights.length;i++){
            int curTruck = truck_weights[i];
            
            while(true){
                // 큐가 비어있다면 큐에 추가
                if(q.isEmpty()){
                    q.add(curTruck);
                    sum += curTruck;
                    time++;
                    break;
                }
                // 큐가 비어있지 않다면
                else {
                    //큐의 사이즈와 다리의 길이가 같다면 큐에서 큐에서 처음 값을 빼고 무게 합
                    if(q.size() == bridge_length){
                        sum -= q.poll();
                    }
                    //다음 트럭이 최대 무게 이내
                    else if(sum + curTruck <= weight) {
                        q.add(curTruck);
                        sum += curTruck;
                        time++;
                        break;
                    }
			        //다음 트럭이 최대 무게 초과 
                    else{
                        q.add(0);
                        time++;
                    }
                }
            }
        }
        
        return time + bridge_length;
    }
}