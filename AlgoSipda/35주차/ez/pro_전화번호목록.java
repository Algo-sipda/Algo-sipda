import java.util.*;
class pro_전화번호목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Map<String,Integer> map = new HashMap<>();

        for (String number : phone_book) {
            map.put(number, 0);
        }

        for (String key : map.keySet()) {
            for (int j = 1; j < key.length(); j++) {
                if (map.containsKey(key.substring(0, j))) {
                    answer = false;
                }
            }
        }
        return answer;
    }
}