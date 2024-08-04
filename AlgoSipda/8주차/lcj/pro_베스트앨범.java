import java.util.*;

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        HashMap<String, Integer> hm = new HashMap<>();
        List<Integer> answer = new LinkedList<>();
        
        // 총 합을 기준으로 genres 총 개수 순위 나열
        for(int i=0;i<genres.length;i++) {
            hm.put(genres[i], hm.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> list = new LinkedList<>(hm.keySet());
        Collections.sort(list, (o1, o2) -> {
            return hm.get(o2) - hm.get(o1);
        });
        
        // 각각의 genres의 정렬
        for(String curGenre: list) {
            HashMap<Integer, Integer> genrePlays = new HashMap<>();
            for(int i=0;i<genres.length;i++) {
                if(genres[i].equals(curGenre)) {
                    genrePlays.put(i, plays[i]);
                }
            }
            
            // hashmap에 저장한 부분 list로 정렬하기
            List<Integer> playList = new LinkedList<>(genrePlays.keySet());
            Collections.sort(playList, (o1, o2) -> {
                return genrePlays.get(o2) - genrePlays.get(o1);
            });
            
            for(int i=0;i<playList.size();i++) {
                if(i == 2) break;
                answer.add(playList.get(i));
            }
        }
        
        return answer;
    }
}

