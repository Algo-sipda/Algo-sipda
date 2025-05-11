import java.util.*;

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> dictSet = new HashSet<>(dictionary);
        StringTokenizer st = new StringTokenizer(sentence);
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            String replacement = s;

            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                if (dictSet.contains(prefix)) {
                    replacement = prefix;
                    break;
                }
            }

            sb.append(replacement).append(" ");
        }

        return sb.toString().trim();
    }
}