class pro_문자열압축 {
    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i < s.length() / 2 + 1; i++) {
            StringBuilder sb = new StringBuilder();
            int cnt = 1;
            String cur = "";
            String next = "";
            for (int j = 0; j < s.length() - i; j += i) {
                cur = s.substring(j, j+i);
                next = s.substring(j+i, Math.min(s.length(), j+i+i));

                if (cur.equals(next)) {
                    cnt++;
                } else {
                    if(cnt != 1) {
                        sb.append(cnt+cur);
                        cnt = 1;
                    } else {
                        sb.append(cur);
                    }
                }
            }
            if (cnt != 1) {
                sb.append(cnt+cur);
            } else {
                sb.append(next);
            }
            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}