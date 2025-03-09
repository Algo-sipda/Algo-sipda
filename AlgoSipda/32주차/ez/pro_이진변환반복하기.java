class pro_이진변환반복하기 {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        int cnt = 0;
        while(true) {
            if(s.equals("1")) break;
            if(s.contains("0")) {
                int len = s.length();
                s = s.replaceAll("0", "");
                answer[1] += (len - s.length());
            }
            s = Integer.toBinaryString(s.length());
            answer[0]++;
        }
        return answer;
    }
}
