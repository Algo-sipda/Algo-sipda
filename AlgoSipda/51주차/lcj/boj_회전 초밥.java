import java.io.*;
import java.util.*;

public class Main {
	
	static int N, d, k, c;
	static int[] table, sushi;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		table = new int[N];//arr -> table
		sushi = new int[d+1];// eat -> sushi
		
		for(int i=0;i<N;i++) {
			table[i] = Integer.parseInt(br.readLine());
		}
		
		possibleSushi();
		System.out.println(max);
	}
	
	public static void possibleSushi() {
		int cnt = 1;
		sushi[c] = 3001;
		
		for(int i=0;i<k;i++) {			
			if(sushi[table[i]]==0) cnt++;
			
			sushi[table[i]]++;
		}
		
		max = cnt;
		
		for(int i=0;i<N;i++) {
			int end = (i+k)%N;
			
			sushi[table[i]]--;
			
			if(sushi[table[i]]==0) cnt--;
			if(sushi[table[end]]==0) cnt++;
			
			sushi[table[end]]++;
			
			max = Math.max(max, cnt);
		}
	}
}