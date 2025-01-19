import java.io.*;
import java.util.*;

public class Main_20207_달력 {
	
	static int maxDay = 365;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] cntArr = new int[maxDay+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			for(int j=S;j<=E;j++) {
				cntArr[j]++;
			}
		}
		
		int total = 0;
		int curW = 0;
		int maxH = 0;
		
		for(int i=0;i<=maxDay;i++) {
			if(cntArr[i] != 0) {
				curW++;
				maxH = Math.max(maxH, cntArr[i]);
			}
			else {
				total += curW * maxH;
				curW = 0;
				maxH = 0;
			}
		}
		
		total += curW * maxH;
		System.out.println(total);

	}

}