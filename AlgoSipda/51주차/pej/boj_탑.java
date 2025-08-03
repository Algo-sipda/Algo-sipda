/*
[BOJ] 탑
https://www.acmicpc.net/problem/2493
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] idx = new int[N];
        // 왼쪽에서 자신보다 큰 탑 후보만 남기기
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++){
            if(stack.isEmpty()) {
                stack.add(i);
            }

            if(heights[stack.peek()] > heights[i]) { // 현재 탑 i는 stack.peek()에게 신호를 받을 수 있음
                idx[i] = stack.peek() + 1;
            }else {
                while(true) { // 스택에서 작거나 같은 높이의 탑은 모두 스택에서 제거 
                    if(stack.isEmpty() || heights[stack.peek()] > heights[i]){
                        break;
                    }
                    stack.pop();
                }
                if(!stack.isEmpty()) {
                    idx[i] = stack.peek() + 1;
                }
            }
            stack.push(i);

        }
        for(int i = 0; i < N; i++){
            System.out.print(idx[i] + " ");
        }
        System.out.println();
    }
}
/*
스택 사용하는 알고리즘
> 이전/다음 중에서 특정 조건을 만족하는 요소를 찾는가? (단조 스택)

1. 스택이 어떤 상태를 유지하는 지 정의 
    -> 오름차순 / 내림차순 유지 
2. 스택에 무엇을 담을 지 결정 (값/인덱스/함께)
3. Pop을 언제 할지 정하기 
    -> 현재 값과 비교해서 쓸모가 없어진 값을 pop한다 
4. 결과를 언제 저장할지 결정
    -> pop하기 전에 결과 저장
    -> pop후 남은 스택에서 결과 확인 
 */