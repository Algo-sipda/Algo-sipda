import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer = 100_001;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] now = br.readLine().toCharArray();
        char[] result = br.readLine().toCharArray();

        if(N == 2){
            if(now[0] == result[0] && now[1] == result[1]){
                System.out.println(0);
                return;
            }
            if(now[0] != result[0] && now[1] != result[1]){
                System.out.println(1);
                return;
            }
            else{
                System.out.println(-1);
                return;
            }
        }
        // 맨처음게 안바꼈을때 -> 안누르고 안누르고로 시작하거나 누르고 누르고로 시작하거나
        // 바꼈을 때 -> 누르고 안누르고 or 안누르고 누르고
        if (now[0] == result[0]) {
            calSwitchOn(2, now, result, 0);
            pushSwitch(0, now);
            pushSwitch(1, now);
            calSwitchOn(2, now, result, 2);
            pushSwitch(0, now);
            pushSwitch(1, now);
        } else {
            pushSwitch(0, now);
            calSwitchOn(2, now, result, 1);
            pushSwitch(0, now);
            pushSwitch(1, now);
            calSwitchOn(2, now, result, 1);
            pushSwitch(1, now);
        }
        answer = answer == 100_001 ? -1 : answer;
        System.out.println(answer);
    }

    static void pushSwitch(int idx, char[] now) {
        if (idx != 0)
            now[idx - 1] = now[idx - 1] == '0' ? '1' : '0';
        now[idx] = now[idx] == '0' ? '1' : '0';
        if (idx < N - 1)
            now[idx + 1] = now[idx + 1] == '0' ? '1' : '0';
    }


    static void calSwitchOn(int idx, char[] now, char[] result, int change) {
        if (idx == N - 1) {
            if (now[idx - 1] != result[idx - 1] && now[idx] != result[idx]) {
                change++;
                answer = Math.min(N, change);
            }
            if (now[idx - 1] == result[idx - 1] && now[idx] == result[idx])
                answer = Math.min(N, change);
            return;
        }
        if (now[idx - 1] == result[idx - 1]) {
            calSwitchOn(idx + 1, now, result, change);
            return;
        }

        pushSwitch(idx, now);
        calSwitchOn(idx + 1, now, result, change + 1);
        pushSwitch(idx, now);
    }
}
