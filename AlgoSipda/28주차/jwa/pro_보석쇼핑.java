import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> counter = new HashMap<>();
        int types = new HashSet<>(Arrays.asList(gems)).size();
        int left = 0, right = 0;
        counter.put(gems[0], 1);
        int[] answer = {1, gems.length};

        while (right < gems.length - 1) {
            while (counter.size() < types && right < gems.length - 1) {
                counter.put(gems[++right], counter.getOrDefault(gems[right], 0) + 1);
            }

            while (counter.size() == types) {
                if (right - left < answer[1] - answer[0]) {
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                counter.put(gems[left], counter.get(gems[left]) - 1);

                if (counter.get(gems[left]) == 0) {
                    counter.remove(gems[left]);
                }

                left++;
            }
        }

        return answer;
    }
}
