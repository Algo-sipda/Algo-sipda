import java.util.*;

class Solution {
    
    static String[][] shape;
    static int startX, startY, targetX, targetY, answer, total;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        shape = new String[52][52];
        startX = characterX; startY = characterY; targetX = itemX; targetY = itemY; 
        answer = total = 0;

        for(int i = 0; i < 52; i++) Arrays.fill(shape[i],"");

        for(int[] xy : rectangle){
            int leftX = xy[0], rightX = xy[2], leftY = xy[1], rightY = xy[3];

            shape[leftX][leftY] = "LDX";
            shape[rightX][leftY] = "RDX";
            shape[leftX][rightY] = "LUX";
            shape[rightX][rightY] = "RUX";

            for(int x=leftX+1; x<rightX; x++){// 상(U), 하(D)
                shape[x][rightY] += "U";
                shape[x][leftY] += "D";
            }

            for(int y=leftY+1; y<rightY; y++){// 좌(L), 우(R)
                shape[leftX][y] += "L";
                shape[rightX][y] += "R";
            }
        }

        followLine(characterX, characterY);

        return Math.min(answer, total-answer);
    }

    public void followLine(int x, int y){
        String location = shape[x][y];
        
        if(location.equals("RU") || location.equals("UR")
           || location.equals("LUX") || location.equals("U"))
            x++;
        if(location.equals("LD") || location.equals("DL") 
           || location.equals("RDX") || location.equals("D"))
            x--;
        if(location.equals("LU") || location.equals("UL") 
           || location.equals("LDX") || location.equals("L"))
            y++;
        if(location.equals("RD") || location.equals("DR") 
           || location.equals("RUX") || location.equals("R"))  
            y--;
        
        total++;
        
        if(x == targetX && y == targetY)
            answer = total;
        
        if(x == startX && y == startY)
            return;
        
        followLine(x, y);
    }
}