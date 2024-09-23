import java.util.*;

class Solution {
    
    class Point{
        int num, c;
        
        public Point(int num, int c){
            this.num = num;
            this.c = c;
        }
    }
    
    public int[] solution(int e, int[] starts) {
        
        Point[] dp = new Point[e+1];
        
        for(int i=0; i<=e; i++){
            dp[i] = new Point(i, 0);
        }
        
        for(int i=1; i<=e; i++){ 
            for(int j=i; j<=e; j+=i){
                dp[j].c++;
            }
        }
        
        Arrays.sort(dp, new Comparator<Point>(){ // 약수가 큰 개수대로
            @Override
            public int compare(Point o1, Point o2){
                if(o1.c < o2.c) return 1;
                else if(o1.c > o2.c){
                    return -1;
                }
                else {
                    if(o1.num > o2.num) return 1;
                    else if(o1.num < o2.num) return -1;
                    else return 0;
                }
            }
        });          
        
        int[] answer = new int[starts.length];
        
        for(int i=0; i<answer.length; i++){
            for(int j=0; j<=e; j++){
                if(starts[i] <= dp[j].num){
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }
        
        return answer;
    }
}