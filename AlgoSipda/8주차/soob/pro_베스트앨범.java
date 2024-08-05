import java.util.*;

class Solution {
    
    class Song implements Comparable<Song>{
        int idx;
        int plays;
        
        public Song(int idx, int plays){
            this.idx = idx;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song S){
            return S.plays - this.plays;
        }
    }
    
    class Genre implements Comparable<Genre>{
        int playCnt;
        String name;
        ArrayList<Song> songs;
        
        public Genre(String name, int playCnt){
            this.name = name;
            this.playCnt = playCnt;
            songs = new ArrayList<>();
        }
        
        public void putSong(int idx, int plays){
            Song song = new Song(idx, plays);
            this.songs.add(song);
        }
        
        @Override
        public int compareTo(Genre G){
            return G.playCnt - this.playCnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        
        Map<String, Integer> songCnt = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){     //장르별 재생 수 합산
            if(songCnt.get(genres[i]) == null)
                songCnt.put(genres[i], plays[i]);
            else
                songCnt.put(genres[i], songCnt.get(genres[i]) + plays[i]);
        }
                
        ArrayList<Genre> genreCnt = new ArrayList<>();  // 정렬하기 위해 ArrayList로 변환
        for(String k : songCnt.keySet()){
            Genre g = new Genre(k, songCnt.get(k));
            
            genreCnt.add(g);
            
            for(int i = 0; i < plays.length; i++){
                if(k.equals(genres[i])){
                    g.putSong(i, plays[i]);
                }
            }
            

        }
        
        for(Genre g : genreCnt){
            Collections.sort(g.songs);
        }
                
        Collections.sort(genreCnt);     // 정렬
        
        int cnt = 0;
        ArrayList<Integer> answerArray = new ArrayList<>();
        for(Genre g: genreCnt){
            for(int i = 0; i < 2; i++){
                if(i == g.songs.size())
                    break;
                answerArray.add(g.songs.get(i).idx);
            }
        }
        
        answer = new int[answerArray.size()];
        cnt = 0;
        for(int i : answerArray){
            answer[cnt++] = i;
        }
        
        return answer;
    }
}