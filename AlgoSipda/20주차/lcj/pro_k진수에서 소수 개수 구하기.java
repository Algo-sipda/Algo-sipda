class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String temp = "";
        while(n != 0) {          
            temp = n % k + temp;
            n /= k;
        }
        
        String[] array = temp.split("0");
        
        for(String arr: array) {
            if(arr.equals("")) continue;
            
            Long num = Long.parseLong(arr);
            if(isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    public boolean isPrime(Long num) {
        if(num <= 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        
        return true;
    }
}