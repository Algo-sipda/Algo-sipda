import java.io.*;
import java.util.*;

public class Main_7983_내일할거야 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> {return o2[1]-o1[1];});
		
		int endPoint = arr[0][1];
		
		for(int i=0;i<n;i++) {
			if(arr[i][1] <= endPoint) {
				endPoint = arr[i][1] - arr[i][0];
			}
			else {
				endPoint -= arr[i][0];
			}
		}
		
		System.out.println(endPoint);
	}

}