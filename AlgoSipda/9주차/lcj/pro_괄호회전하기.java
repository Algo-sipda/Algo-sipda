import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int i=0;i<s.length();i++) {
            char[] arr = new char[s.length()];
            for(int j=0;j<s.length();j++) {
                arr[j] = s.charAt((i+j)%s.length());
            }
            
            // 이것이 올바른 괄호 문자열인지 확인
            if(isPossibleBracket(arr, s.length())) answer++;
        }
        
        return answer;
    }
    
    public boolean isPossibleBracket(char[] arr, int len) {   
        Stack<Character> st = new Stack<>();
        
        for(int i=0;i<arr.length;i++) {
            char cur = arr[i];
            
            if(cur == '(' || cur == '{' || cur == '[') {
                st.push(cur);
            } else {
                if(st.isEmpty()) {
                    return false;
                }
                else if(st.peek() == '(' && cur == ')') {
                    st.pop();
                }
                
                else if(st.peek() == '{' && cur == '}') {
                    st.pop();
                }
                
                else if(st.peek() == '[' && cur == ']') {
                    st.pop();
                }
                else {
                    st.push(cur);
                }
            }
        }
        
        if(st.isEmpty()) return true;
        
        return false;
    }
}