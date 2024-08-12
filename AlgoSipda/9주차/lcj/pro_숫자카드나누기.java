import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        // 최소 공배수
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        for(int i=1;i<arrayA.length;i++) {
            gcdA = gcd(arrayA[i], gcdA);
            gcdB = gcd(arrayB[i], gcdB);
        }
        
        if(notDivide(arrayB, gcdA)) {
            if(answer < gcdA) answer = gcdA;
        }
        
        if(notDivide(arrayA, gcdB)) {
            if(answer < gcdB) answer = gcdB;
        }
        
        return answer;
    }
    
    public int gcd(int a, int b) {
        if(a%b == 0) return b;
        return gcd(b, a%b);
    }
    
    public boolean notDivide(int[] array, int num) {
        for(int a: array) {
            if(a % num == 0) return false;
        }
        return true;
    }
}