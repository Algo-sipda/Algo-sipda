 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);

		int answer = 0;
		int minusIdx = 0;
		// 음수쪽 먼저 계산
		while(minusIdx < N && arr[minusIdx]<1) {
			if(minusIdx + 1 < N && arr[minusIdx+1] < 1) {// 곱이 양수
				answer += (arr[minusIdx] * arr[minusIdx+1]);
				minusIdx+= 2;
			}else {// 0이거나 음수가 1개 남은 경우 그냥 더하기
				answer += arr[minusIdx];
				minusIdx++;
			}
		}
		
		int plusIdx = N-1;
		while(plusIdx >= minusIdx && arr[plusIdx] > 0) {
			if(plusIdx - 1 >= minusIdx && arr[plusIdx-1]>1) {
				answer += (arr[plusIdx]*arr[plusIdx-1]);
				plusIdx -= 2;
			}else {
				answer += arr[plusIdx];
				plusIdx--;
			}
		}
		System.out.println(answer);
	}

}
