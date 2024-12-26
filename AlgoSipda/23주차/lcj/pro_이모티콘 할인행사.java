class Solution {
    
    int[] discount = {10, 20, 30, 40};
    int maxSubscribe = 0, maxCost = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] arr = new int[emoticons.length];
        
        combi(0, arr, users, emoticons);
        
        return new int[]{maxSubscribe, maxCost};
    }
    
    public void combi(int idx, int[] arr, int[][] users, int[] emoticons) {
        if(idx == arr.length) {
            calc(arr, users, emoticons);
            return;
        }
        
        for(int i=10;i<=40;i+= 10) {
            arr[idx] = i;
            combi(idx+1, arr, users, emoticons);
        }
    }
    
    public void calc(int[] arr, int[][] users, int[] emoticons) {
        int cnt = 0;
        int total = 0;
        
        for(int[] user: users) {
            int discount = user[0];
            int price = user[1];
            int sum = 0;
            
            for(int i=0;i<arr.length;i++) {
                if(arr[i] >= discount) {
                    sum += (100-arr[i]) * emoticons[i] / 100;
                }
            }
            
            if(sum >= price) cnt++;
            else total += sum;
        }
        
        if(cnt > maxSubscribe) {
            maxSubscribe = cnt;
            maxCost = total;
        }
        else if(cnt == maxSubscribe) {
            if(maxCost < total) {
                maxCost = total;
            }
        }
    }
}