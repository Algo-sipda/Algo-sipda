import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		long[] solution = new long[N];
		long[] answer = new long[3];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			solution[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(solution);
		long diff = Long.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			int left = i+1;
			int right = N-1;
			
			while(left < right) {
				long sum = solution[i] + solution[left] + solution[right];
				
				long curDiff = Math.abs(sum);
				
				if(curDiff<diff) {
					diff = curDiff;
					answer[0] = solution[i];
					answer[1] = solution[left];
					answer[2] = solution[right];
				}
				
				if(sum > 0) right--;
				else left++;
			}
		}
		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}
}