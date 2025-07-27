import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Stack<Character> st = new Stack<>();
		boolean flag = true;
		int answer = 0;
		int cnt = 1;
		
		for(int i=0;i<str.length();i++) {
			char cur = str.charAt(i);
			
			if(cur == '(') {
				st.push(cur);
				cnt*=2;
			}else if(cur =='[') {
				st.push(cur);
				cnt *=3;
			}else {
				if(cur==')') {
					if(st.isEmpty() || st.peek() != '(') {
						flag = false;
						break;
					}
					if(str.charAt(i-1) == '(') {
						answer += cnt;
					}
					st.pop();
					cnt /= 2;
				}else if(cur == ']') {
					if(st.isEmpty() || st.peek() != '[') {
						flag = false;
						break;
					}
					if(str.charAt(i-1) == '[') {
						answer += cnt;
					}
					st.pop();
					cnt /= 3;
				}else {
					flag = false;
					break;
				}
			}
		}
		if(!flag || !st.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}

}