import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        long n = Integer.parseInt(br.readLine());
        long[] arr = new long[6];
        
        st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long scene[] = new long[3];
		
		scene[0] = (n-2)*(n-2) + (4*(n-2)*(n-1)) ; // 한면만 보이는 갯수
		scene[1] = (4*(n-2)) + (4*(n-1)); 		// 두면만 보이는 갯수 
		scene[2] = 4;								// 세면만 보이는 갯수
		

		long num = 0;
		long sum = 0;
		long min;
		
		if (n==1) {
			Arrays.sort(arr);
			min = arr[0];
			for (int i = 0; i < arr.length-1; i++) {
				sum += arr[i];
			}
		} else {
			min = arr[0];
			for (int i = 0; i < arr.length; i++) {
				min = Math.min(min, arr[i]);
			}
			
			sum += min*scene[0];
			
			min = Long.MAX_VALUE;
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = i+1; j < arr.length; j++) {
					if (i+j != 5) {
						min = Math.min(min, arr[i]+arr[j]);
					}
				}
			}
			
			sum+= min*scene[1]; 
			
			for (int i = 0; i < 3; i++) {
				num += Math.min(arr[i], arr[5-i]);
			}
			sum+= num*scene[2];
		}
		
		System.out.println(sum);
    }
}