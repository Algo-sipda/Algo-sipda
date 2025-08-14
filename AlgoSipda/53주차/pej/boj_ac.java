// [BOJ] AC
// https://www.acmicpc.net/problem/5430
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			String command = br.readLine();
			int numCnt = Integer.parseInt(br.readLine());
			String arr = br.readLine(); // [x1, ... , xn]
			boolean isError = false;
			boolean isReversed = false;
			List<Integer> origin = parse(arr);
			for(int i = 0; i < command.length(); i++) {
				if(command.charAt(i) =='R') {
					isReversed = !isReversed;
				}else {
					if(origin.size()==0) {
						isError = true;
						break;
					}
					if(!isReversed) {
						origin.remove(0);						
					}else {
						origin.remove(origin.size()-1);
					}
				}
			}
			if(isError) {
				sb.append("error\n"); 
			}else {
				sb.append("[");
				if(isReversed) {
					for(int i = origin.size()-1 ; i >= 0; i--) {
						sb.append(origin.get(i));
						if(i != 0) {
							sb.append(","); 
						}
					}

				}else {
					for(int i = 0 ; i < origin.size(); i++) {
						sb.append(origin.get(i));
						if(i != origin.size() - 1) {
							sb.append(","); 
						}
					}					
				}
				sb.append("]\n");
			}
		}
		System.out.print(sb);
	}
	static List<Integer> parse(String arr){
		List<Integer> temp = new ArrayList<>();
		arr = arr.replace("[","");
		arr = arr.replace("]", "");
		if(arr.length() > 0) {
			String[] newArr = arr.split(",");		
			for(int i = 0; i < newArr.length; i++) {
				temp.add(Integer.parseInt(newArr[i]));
			}			
		}
		return temp;
	}
	
}
