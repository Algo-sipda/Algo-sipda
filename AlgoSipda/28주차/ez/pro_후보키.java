import java.util.*;

class pro_후보키 {

    List<String> keys;

    public int solution(String[][] relation) {
        List<Integer> list = new ArrayList<>();
        keys = new ArrayList<>();

        combi(0, list, relation);

        return keys.size();
    }

    private void combi(int cnt, List<Integer> list, String[][] relation) {

        keyCheck(list, relation);

        if (cnt == relation[0].length) {
            return;
        }

        for (int i = cnt; i < relation[0].length; i++) {
            list.add(i);
            combi(i + 1, list, relation);
            list.remove(list.size() - 1);
        }
    }

    private void keyCheck(List<Integer> list, String[][] relation) {
        String str = "";
        for (int item : list) {
            str += item;
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            String s = "";
            for (int idx : list) {
                s += relation[i][idx];
            }

            if (map.containsKey(s)) {
                return;
            }
            map.put(s, 0);
        }

        for (String key : keys) {
            int cnt = 0;
            for (int i = 0; i < str.length(); i++) {
                String temp = str.charAt(i) + "";
                if (key.contains(temp)) {
                    cnt++;
                }
            }
            if (cnt == key.length()) {
                return;
            }
        }

        keys.add(str);
        return;
    }
}