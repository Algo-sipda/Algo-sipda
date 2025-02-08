import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int r = relation.length;
        int c = relation[0].length;
        HashSet<Integer> candidate = new HashSet<>();

        // 비트마스킹
        Top: for (int i = 1; i < Math.pow(2, c); i++) {
            for (int candi : candidate) {
                if ((i & candi) == candi) {
                    continue Top;
                }
            }

            HashSet<String> rows = new HashSet<>();

            for (String[] row : relation) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < c; j++) {
                    if (((1 << j) & i) != 0) {
                        sb.append(row[j]);
                    }
                }
                rows.add(sb.toString());
            }

            if (rows.size() == r) {
                candidate.add(i);
            }
        }

        return candidate.size();
    }
}
