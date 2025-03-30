import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer s = new StringBuffer(br.readLine());
		StringBuffer t = new StringBuffer(br.readLine());
		
		while(s.length() < t.length()) {
			if(t.charAt(t.length()-1)== 'A') {
				t.deleteCharAt(t.length()-1);
			}else if(t.charAt(t.length()-1)=='B') {
				t.deleteCharAt(t.length()-1);
				t.reverse();
			}
		}
		
		int answer = 0;
		
		if((s.toString()).equals(t.toString())) {
			answer = 1;
		}
		System.out.println(answer);
	}
}