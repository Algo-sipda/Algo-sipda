class Solution {
    public int solution(String s) {
        int minLength = s.length();

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            String compressed = compressString(s, unit);
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength;
    }

    private String compressString(String s, int unit) {
        StringBuilder result = new StringBuilder();
        String prev = s.substring(0, unit);
        int count = 1;

        for (int i = unit; i <= s.length(); i += unit) {
            String next;
            if (i + unit <= s.length()) {
                next = s.substring(i, i + unit);
            } else {
                next = s.substring(i);
            }

            if (prev.equals(next)) {
                count++;
            } else {
                if (count > 1) {
                    result.append(count);
                }
                result.append(prev);
                prev = next;
                count = 1;
            }
        }

        if (count > 1) {
            result.append(count);
        }
        result.append(prev);

        return result.toString();
    }
}