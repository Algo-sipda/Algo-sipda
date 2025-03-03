import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 장소의 수 (3 <= N <= 100,000)
		int N = Integer.parseInt(br.readLine());
		// 꿀 배열
		long[] honey = new long[N];
		// 왼쪽에 있는 꿀의 양 합계
		long[] honeySumLeft = new long[N];
		// 오른쪽에 있는 꿀의 양 합계
		long[] honeySumRight = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		long sum = 0, max = Long.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			honey[i] = Long.parseLong(st.nextToken());
			sum += honey[i];
			honeySumLeft[i] = sum;
			if(i != 0 && i != N-1)
				max = Math.max(max, honey[i]);
		}

		sum = 0;
		for (int i = N - 1; i >= 0; i--) {
			sum += honey[i];
			honeySumRight[i] = sum;
		}

//		System.out.println(Arrays.toString(honey));
//		System.out.println(Arrays.toString(honeySumLeft));
//		System.out.println(Arrays.toString(honeySumRight));

		long sum1, sum2 = honeySumRight[2] * 2, sum3 = honeySumLeft[N - 3] * 2;

		// 벌1의 위치가 0, 벌2의 위치가 N-1일때
		sum1 = sum - honey[0] - honey[N - 1] + max;
		
		// 벌1의 위치가 0일때 오른쪽의 벌2 위치 탐색
		for (int i = 2; i < N - 1; i++) {
			sum2 = Math.max(sum2, honeySumRight[i + 1] * 2 + honeySumRight[1] - honeySumRight[i]);
		}
		// 벌1의 위치가 N-1일때 왼쪽에 벌 2위치 탐색
		for (int i = N - 3; i > 1; i--) {
			sum3 = Math.max(sum3, honeySumLeft[i - 1] * 2 + honeySumLeft[N - 2] - honeySumLeft[i]);
		}
		
		long answer = Math.max(sum1, sum2);
		answer = Math.max(answer, sum3);
		
		System.out.println(answer);
	}

}