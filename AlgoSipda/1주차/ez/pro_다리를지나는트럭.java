import java.util.*;

/*
    1. 다리에 트럭이 없는 경우(시작)
        - queue.add(0)
    2. 다리에 꽉 차면(queue full)
        - queue.poll
    3. 무게를 초과하지 않을 경우
        - queue에 트럭 넣기
    4. 무게를 초과할 경우
        - queue.add(0)
 */

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int truckIdx = 0;
        int sum = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        while (truckIdx < truck_weights.length) {
            if (queue.isEmpty()) {
                queue.add(0);
            }

            if (queue.size() >= bridge_length) {
                sum -= queue.poll();
            }
            if (sum + truck_weights[truckIdx] <= weight) {
                queue.add(truck_weights[truckIdx]);
                sum += truck_weights[truckIdx++];
            } else {
                queue.add(0);
            }
            answer++;
        }

        answer += bridge_length;

        return answer;
    }
}