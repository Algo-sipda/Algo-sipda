class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();

        int minMove = n - 1;

        for(int i = 0; i < n; i++){
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            int index = i + 1;
            while(index < n && name.charAt(index) == 'A'){
                index++;
            }
            minMove = Math.min(minMove, i * 2 + n - index);
            minMove = Math.min(minMove, (n - index) * 2 + i);
        }
        return answer + minMove;
    }
}
