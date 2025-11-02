import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int INF = 99999999; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] currentArr1 = new int[N];//첫번째 전구를 키는 경우
		int[] currentArr2 = new int[N];//첫번째 전구를 키지 않는 경우
		int[] result = new int[N];
		
		String str = br.readLine();
		for(int i=0;i<N;i++) {
			int num = str.charAt(i)-'0';
			currentArr1[i] = currentArr2[i] = num;
		}

		str = br.readLine();
		for(int i=0;i<N;i++) {
			int num = str.charAt(i)-'0';
			result[i] = num;
		}
		
		//스위치를 켜는 경우 - 앞에 켰을 때는 두개만 적용
		currentArr1[0] = currentArr1[0]==0?1:0;
		currentArr1[1] = currentArr1[1]==0?1:0;
		
		int cR1 = 1, cR2 = 0;
		for(int i=1;i<N;i++) {
			//전구 스위치 켠 경우
			if(currentArr1[i-1] != result[i-1]) {
				currentArr1[i-1] = currentArr1[i-1]==0?1:0;
				currentArr1[i] = currentArr1[i]==0?1:0;
				cR1++;
				if(i != N-1)
					currentArr1[i+1] = currentArr1[i+1]==0?1:0;
			}
			//전구 스위치 끈 경우
			if(currentArr2[i-1] != result[i-1]) {
				currentArr2[i-1] = currentArr2[i-1]==0?1:0;
				currentArr2[i] = currentArr2[i]==0?1:0;
				cR2++;
				if(i != N-1)
					currentArr2[i+1] = currentArr2[i+1]==0?1:0;
			}
		}
		
		if(currentArr1[N-1] != result[N-1]) cR1 = INF;
		if(currentArr2[N-1] != result[N-1]) cR2 = INF;
		
		if(cR1 == INF && cR2 == INF) {
			System.out.println(-1);
		}else {
			System.out.println(Math.min(cR1, cR2));
		}
	}
}
