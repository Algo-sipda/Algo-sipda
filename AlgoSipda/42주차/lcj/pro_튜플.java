import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String s) {
        ArrayList<Integer> answer = new ArrayList<>();
  
        String[] arr = s.substring(2, s.length()-2).replace("},{", "-").split("-");
        
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        
        for(String a : arr) {
            String[] str = a.split(",");
            
            for(int i=0;i<str.length;i++) {
                int n = Integer.parseInt(str[i]);
                
                if(!answer.contains(n)) answer.add(n);
            }
        }
        
        return answer;
    }
}