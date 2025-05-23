// [leetcode] https://leetcode.com/problems/boats-to-save-people

import java.util.*;
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int cnt = 0;
        int i = 0, j = people.length - 1;
        while(i <= j){
            if(people[i] + people[j] > limit){
                j--;
            }else{
                i++;
                j--;
            }
            cnt += 1;

       }
        return cnt;
    }
}