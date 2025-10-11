import java.io.*;

/**
 * G킬로그램 : 현재 몸무게의 제곱 - 성원이가 기억하고 있던 몸무게 제곱
 * @author lcj52
 *
 */

public class Main_1484_다이어트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int G = Integer.parseInt(br.readLine());
		
		int start = 1;
		int end = 2;
		boolean flag = false;
		
		while(end < 100_000) {
			int memoryWeight = start*start;
			int curWeight = end*end;
			
			if(curWeight - memoryWeight == G) {
				sb.append(end).append("\n");
				if(!flag) flag = true;
			}
			
			if(curWeight - memoryWeight >= G) {
				start++;
			}
			else {
				end++;
			}
		}
		
		if(!flag)
			System.out.println(-1);
		else
			System.out.print(sb);
	}

}