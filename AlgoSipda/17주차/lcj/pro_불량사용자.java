import java.util.*;

class Solution {
    private HashSet<String> banUserIdx;
    
    public int solution(String[] user_id, String[] banned_id) {
	    	banUserIdx = new HashSet<String>(); // 중복제거
	    	boolean visited[] = new boolean[user_id.length];
	    	dfs(user_id, banned_id, 0, visited); // 경우의 수 찾기
        
            int answer = banUserIdx.size();
	        return answer;
    }
    
    private void dfs(String[] user_id, String[] banned_id, int banIdx, boolean[] visited){
            if(banIdx == banned_id.length) { // ban된 id 모두 선택
				StringBuilder userIdxs = new StringBuilder();
				for(int i = 0; i < user_id.length; i++) {
					if(visited[i]) {
						userIdxs.append(i); // 모든 인덱스를 이어붙이자
					}
				}
				banUserIdx.add(userIdxs.toString()); // 중복된 값 없는 모든 인덱스
				return ;
			}
        
            for(int i = 0; i < user_id.length; i++) {
				if(visited[i]) continue;
				boolean flag = false;
                // 유저와 밴 된 유저의 길이가 같은 경우만 체크
				if(user_id[i].length() == banned_id[banIdx].length()) {
					for(int s = 0; s < user_id[i].length(); s++) {
						if(banned_id[banIdx].charAt(s) == '*') continue;
                       // 하나라도 다른 글자가 포함되어 있다면 멈추고 다음 유저로
						if(user_id[i].charAt(s) != banned_id[banIdx].charAt(s)) {
							flag = true;
							break;
						}
					}
					if(!flag) { // 모든 글자가 맞았다
						visited[i] = true;
						dfs(user_id, banned_id, banIdx+1, visited); // 다음 탐색
						visited[i] = false;
					}
				}
			}
    }
}