import java.io.*;
import java.util.*;

/**
 * 용액 : -100_000_000 ~ 100_000_000
 * => 두 용액을 혼합하여 0에 가장 가까운 용액
 * => 최대값 : 200_000_000, 최소값 : -200_000_000(-2e8 ~ 2e8) => int로 가능
 * 
 * 모든 쌍 (i, j)에 대해서 a[i]+a[j]로 계산하면 -> O(N^2) -> 10^10 -> 시간초과
 * => 풀이 방법 : 투 포인터
 * @author lcj52
 *
 */

public class Main_14921_용액합성하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		int left = 0;
		int right = N-1;
		
		int sum = Integer.MAX_VALUE;
		while(left < right) {
			int curSum = arr[left] + arr[right];
			
			if(Math.abs(curSum) < Math.abs(sum)) {
				sum = curSum;
			}
			
			if(curSum > 0) {
				right--;
			}
			else if(curSum < 0) {
				left++;
			}
			else {
				sum = 0;
				break;
			}
		}
		System.out.println(sum);
	}

}