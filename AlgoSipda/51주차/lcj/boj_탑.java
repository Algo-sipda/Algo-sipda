import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Stack<Integer> tower = new Stack<>();
		Stack<Integer> index = new Stack<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(true) {
				if(!tower.isEmpty()) {
					//스택이 비어 있지 않은 경우
					int top = tower.peek();//탑의 이전 값을 추출
					if(top > num) {//앞의 값이 현재 빌딩보다 높은 경우
						sb.append(index.peek()+" ");
						tower.push(num);
						index.push(i);
						break;
					}else {//앞의 값보다 작을 경우 어차피 가려짐
						tower.pop();
						index.pop();
					}
				}
				else {
					//스택이 비어 있는 경우
					sb.append("0 ");
					tower.push(num);
					index.push(i);
					break;
				}
			}
		}
		System.out.println(sb);
	}

}
