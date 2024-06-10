import java.util.*;

// Logic
// 1. 생성자로 diamond, iron, stone 값을 받는 것을 만들어준다.
// 2. dia > iron > stone 순서대로 정렬할 수 있도록 compareTo override해준다.
// 3. main에 들어가서 5개씩 끊어서 각각의 개수를 계산해서 list에 넣어준다.
// 4. list를 위에서 정의한 대로 정렬을 해준다.
// 5. 그 다음에 정렬된 배열에서 앞에서부터 곡괭이의 피로도가 적은 순서대로 차감하며 피로도를 계산해준다.

class Solution {
    class Minerals implements Comparable<Minerals> {
        int dia, iron, stone;
        
        public Minerals (int dia, int iron, int stone){
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
        
        @Override
        public int compareTo(Minerals o){
            if(o.dia == this.dia){
                if(o.iron == this.iron){
                    return o.stone - this.stone;
                }
                return o.iron - this.iron;
            }
            return o.dia - this.dia;
        }
        
        @Override
        public String toString() {
            return "Diamonds: " + dia + ", Iron: " + iron + ", Stone: " + stone;
        }
    }
    
    public int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};    // fatigue: 피로도
    public List<Minerals> list = new ArrayList();
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int pickCnt = picks[0]+picks[1]+picks[2];   // 곡괭이 개수
        
        for(int i=0;i<minerals.length;i+=5){
            if(pickCnt == 0) break;    // 곡괭이 개수가 0개일 경우 돌아갈 수 없음
            
            int diamond = 0;
            int iron = 0;
            int stone = 0;
            
            for(int j=i;j<i+5;j++){
                if(j==minerals.length) break;
                
                if(minerals[j].equals("diamond")){
                    diamond++;
                }
                else if(minerals[j].equals("iron")){
                    iron++;
                }else{
                    stone++;
                }
            }
            list.add(new Minerals(diamond, iron, stone));
            pickCnt--; // 한 번 작업을 완료할 때마다 곡괭이 개수 감소
        }
        
        Collections.sort(list);
        
        // fatigue 계산
        for(int i=0;i<list.size();i++){
            Minerals cur = list.get(i);
            if(picks[0]>0){
                // 다이아몬드 곡괭이가 있는 경우
                answer += (cur.dia*fatigue[0][0] + cur.iron*fatigue[0][1]+cur.stone*fatigue[0][2]);
                picks[0]--;
            }else if(picks[1]>0) {
                // 철 곡괭이 있는 경우
                answer += (cur.dia*fatigue[1][0] + cur.iron*fatigue[1][1]+cur.stone*fatigue[1][2]);
                picks[1]--;
            }else if(picks[2]>0) {
                // 돌 곡괭이 있는 경우
                answer += (cur.dia*fatigue[2][0] + cur.iron*fatigue[2][1]+cur.stone*fatigue[2][2]);
                picks[2]--;
            }
        }
        
        return answer;
    }
}