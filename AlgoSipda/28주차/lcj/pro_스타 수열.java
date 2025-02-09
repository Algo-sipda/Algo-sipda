class Solution {
    public int solution(int[] a) {
        int answer = -1;
        
        int[] cnt = new int[a.length];
        
        for(int i=0;i<a.length;i++) {
            cnt[a[i]]++;
        }
        
        for(int i=0;i<a.length;i++) {
            if(cnt[i] <= answer) continue;
            
            int answer2 = 0;
            
            for(int j=0;j<a.length-1;j++) {
                if(a[j] != a[j+1] && (i == a[j] || i == a[j+1])) {
                    answer2++;
                    j++;
                }
            }
            answer = Math.max(answer, answer2);
        }
        
        return answer*2;
    }
}