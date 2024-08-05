class Solution {
    public int solution(int[] a) {
        if(a.length == 1) return 1;
        if(a.length == 2) return 2;
        
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        
        left[0] = a[0];
        right[a.length-1] = a[a.length-1];
        
        int min = a[0];
        for(int i=1;i<a.length;i++) {
            left[i] = min;
            min = Math.min(min, a[i]);
        }
        
        min = a[a.length-1];
        for(int i=a.length-2;i>0;i--) {
            right[i] = min;
            min = Math.min(min, a[i]);
        }
        
        int answer = 2;
        for(int i=1;i<a.length-1;i++) {
            if(left[i] < a[i] && right[i] < a[i]) continue;
            answer++;
        }
        
        return answer;
    }
}