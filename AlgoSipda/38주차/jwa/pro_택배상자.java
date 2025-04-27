import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 1;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < order.length; i++) {
            while (order[i] > idx) {
                deque.addFirst(idx++);
            }

            if (order[i] == idx) {
                answer++;
                idx++;
            } else if (!deque.isEmpty() && order[i] == deque.peek()) {
                deque.poll();
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }
}
