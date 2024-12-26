import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();

        for (int t : topping) {
            addTopping(right, t);
        }

        for (int t : topping) {
            if (right.get(t) == 1) {
                right.remove(t);
            } else {
                right.put(t, right.get(t) - 1);
            }

            addTopping(left, t);

            if (left.size() == right.size()) {
                answer++;
            }
        }

        return answer;
    }

    static void addTopping(HashMap<Integer, Integer> toppings, int t) {
        if (!toppings.containsKey(t)) {
            toppings.put(t, 1);
        } else {
            toppings.put(t, toppings.get(t) + 1);
        }
    }
}
