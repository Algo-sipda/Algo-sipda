import java.util.*;

class leetcode_substring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        int wordLen = words[0].length();
        int wordCnt = words.length;

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int len = words.length * words[0].length();
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> subMap = new HashMap<>();
            int start = i;
            int cnt = 0;
            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String cur = s.substring(j, j + wordLen);
                if (map.containsKey(cur)) {
                    subMap.put(cur, subMap.getOrDefault(cur, 0) + 1);
                    cnt++;

                    while (subMap.get(cur) > map.get(cur)) {
                        String word = s.substring(start, start + wordLen);
                        subMap.put(word, subMap.get(word) - 1);
                        start += wordLen;
                        cnt--;
                    }

                    if (cnt == wordCnt) {
                        answer.add(start);
                    }
                } else {
                    cnt = 0;
                    start = j + wordLen;
                    subMap.clear();
                }
            }

        }
        return answer;
    }
}