// [BOJ] 소수의 연속합
// https://www.acmicpc.net/problem/1644

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
	static boolean[] prime;
	static int N;
	public static void main(String[] args)throws Exception{
		List<Integer> arr = new ArrayList<>();

		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prime = new boolean[N+1];
		
		prime[0] = prime[1] = true;

		for(int i = 2; i <= N; i++) {
			if(!prime[i]) {
				arr.add(i);
				for(int j = i * 2; j <= N; j += i) {
					prime[j] = true;
				}
			}
		}
	
		int r = 0, l = 0; // 투 포인터
		int cnt = 0;
		int sum = 0;

		int size = arr.size();
		while(r < size) {
			if(sum > N) {
				sum -= arr.get(l++);
			}else if(sum < N) {
				sum += arr.get(r++);
			}else {
				cnt += 1;
				sum -= arr.get(l++);
			}
		}
		if(size > 0 && arr.get(--r) == N) {
			cnt++;
		}
		System.out.println(cnt);
		
	}	

}
 