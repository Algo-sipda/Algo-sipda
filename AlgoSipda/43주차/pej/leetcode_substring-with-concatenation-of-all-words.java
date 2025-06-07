// [LEETCODE] Substring with concatenation of All Words 
// https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
// 시간 초과 > HashMap + sliding window 활용  // 단어 n개가 하나의 슬라이드로 offset은 0부터 단어 길이 -1 까지 확인
class Solution {
    static int LEN_S;
    static int WORD_SIZE;
    static int REPEAT;
    public List<Integer> findSubstring(String s, String[] words) {
        LEN_S = s.length();
        WORD_SIZE = words[0].length();
        REPEAT = words.length;

        Map<String, Integer> dictionary = new HashMap<>();
        for(String w : words) {
            dictionary.put(w, dictionary.getOrDefault(w, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
     
        // [단어1, 단어2 .. 단어REPEAT], .. 단어1 시작점 == offset 
        for(int offset = 0; offset < WORD_SIZE; offset += 1) {
            int wSize = 0;
            int start = offset; 
            Map<String, Integer> temp = new HashMap<>();
            for(int curIdx = offset; curIdx <= LEN_S - WORD_SIZE; curIdx += WORD_SIZE) {
                String word = s.substring(curIdx, curIdx + WORD_SIZE);
                temp.put(word, temp.getOrDefault(word, 0) + 1);
                wSize += 1;
                if(wSize == REPEAT) {
                    if(temp.equals(dictionary)) {
                        answer.add(start);
                    }
                    String remove = s.substring(start, start + WORD_SIZE);
                    temp.computeIfPresent(remove, (a, b) -> (b > 1) ? b - 1 : null);
                    wSize -= 1;
                    start += WORD_SIZE;
                }
            }
        } 
        return answer;
    }
}