import java.util.*;

class leetcode_replaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        Set<String> set = new HashSet<>(dictionary);

        for (String word : words) {
            sb.append(getWords(word, set) + " ");
        }

        return sb.delete(sb.toString().length() - 1, sb.toString().length()).toString();
    }

    private String getWords(String word, Set<String> dictionary) {
        for (int i = 1; i < word.length(); i++) {
            String sub = word.substring(0, i);
            if (dictionary.contains(sub)) {
                return sub;
            }
        }
        return word;
    }
}
