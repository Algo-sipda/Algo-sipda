class Solution {
    int max = -1;
    int[] arr;
    int[] answer = {-1};
    
    public int[] solution(int n, int[] info) {
        
        arr = new int[11];
        dfs(info, 0, 0, n);
        
        return answer;
    }
    
    public void dfs(int[] info, int idx, int cnt, int N) {
        if(idx == 11) {
            if(cnt == N) {
                int apeachScore = 0;
                int lionScore = 0;
                for(int i=0;i<11;i++) {
                    if(info[i] == 0 && arr[i] == 0) continue;
                    if(info[i] >= arr[i]) apeachScore += (10-i);
                    else lionScore += (10-i);
                }
                
                if(lionScore > apeachScore) {
                    if(lionScore - apeachScore > max) {
                        max = lionScore - apeachScore;
                        answer = arr.clone();
                    }
                    else if((lionScore - apeachScore) == max) {
                        for(int i=10;i>=0;i--) {
                            if(answer[i] < arr[i]){
                                answer = arr.clone();
                                return;
                            }
                            else if(answer[i] > arr[i]) return;
                        }
                    }
                }
            }
            return;
        }
        
        if(info[idx] == 0) {    // 둘다 0으로 점수 받기
            dfs(info, idx+1, cnt, N);
        }
        if((cnt+1)+info[idx] <= N) {    // 어피치한테 이기는 경우
            arr[idx] = info[idx]+1;
            dfs(info, idx+1, cnt+1+info[idx], N);
            arr[idx] = 0;
        }
        if(info[idx] != 0) {    // 어피치한테 지는 경우
            for(int i=0;i<=info[idx];i++) {
                arr[idx] = i;
                dfs(info, idx+1, cnt+i, N);
                arr[idx] = 0;
            }
        }
    }
}