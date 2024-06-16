import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++)
            queue.add(0);

        int totalWeight = 0;
        int passCnt = 0;
        int inCnt = 0;
        int time = 0;

        while (true) {

            int outWeight = queue.poll();
            if (outWeight != 0) {
                totalWeight -= outWeight;
                passCnt++;
            }

            if (passCnt == truck_weights.length)
                break;

            if (inCnt < truck_weights.length) {
                if (totalWeight + truck_weights[inCnt] <= weight) {
                    queue.add(truck_weights[inCnt]);
                    totalWeight += truck_weights[inCnt];
                    inCnt++;
                } else
                    queue.add(0);
            } else
                queue.add(0);

            time++;
        }

        return time + 1;
    }
}