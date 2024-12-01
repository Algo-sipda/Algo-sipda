import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String change = Integer.toString(n, k);
        String[] arr = change.split("0");

        for(String a: arr) {
            if(a.equals("")) continue;

            long num = Long.parseLong(a);
            if(isPrime(num)) answer++;
        }

        return answer;
    }

    public boolean isPrime(long num) {
        if(num < 2) return false;

        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}