import java.io.*;
import java.util.*;

public class codetree_LED전구버튼 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        char[] status = new char[N];
        for (int i = 0; i < N; i++) {
            status[i] = br.readLine().charAt(0);
        }
        ArrayList<String> history = new ArrayList<>();
        history.add(String.valueOf(status));
        HashMap<String, Integer> statusIdx = new HashMap<>();
        int startIdx = -1, cycle = 0;
        for (int time = 1; time <= B; time++) {
            char[] temp = Arrays.copyOf(status, N);
            for (int i = 0; i < N; i++) {
                // 왼쪽이 켜져있으면 토글
                if (status[(i - 1 + N) % N] == '1') {
                    temp[i] = temp[i] == '0' ? '1' : '0';
                }
            }
            status = Arrays.copyOf(temp, N);
            String statusStr = String.valueOf(status);
            // 주기 찾음
            if (statusIdx.containsKey(statusStr)) {
                startIdx = statusIdx.get(statusStr);
                cycle = time - startIdx;
                break;
            }
            history.add(statusStr);
            statusIdx.put(statusStr, time);
        }
        String answer;
        if (startIdx == -1) {
            answer = history.get((int) B);
        } else {
            answer = history.get((int) (startIdx + ((B - startIdx) % cycle)));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer.charAt(i));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

// 초기 세팅
// 배열에 전구 상태 저장
// 전구 상태를 키로, 번째를 값으로 하는 해시맵
// 전구 상태 변경 될 때마다 해시맵에 존재하는지 확인 (주기 찾기)
// 존재하면 해당 값이 주기 시작 인덱스가 되고, 현재 인덱스에서 그걸 빼면 주기가 됨
// ((B - 주기시작idx) % 주기)에 해당하는 상태가 답
