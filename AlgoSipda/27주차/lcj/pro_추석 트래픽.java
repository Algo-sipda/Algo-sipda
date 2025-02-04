import java.util.*;

class Time {
    int start, end;
    
    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public int solution(String[] lines) {
        List<Time> traffic = new ArrayList<>();
        
        for(int i=0;i<lines.length;i++) {
            int hour = Integer.parseInt(lines[i].substring(11,13));
            int minute = Integer.parseInt(lines[i].substring(14,16));
            double second = Double.parseDouble(lines[i].substring(17,23));
            int end = (int)((hour*3600 + minute*60)*1000 + second*1000);
            
            int processingTime = (int)(Double.parseDouble(lines[i].substring(24, lines[i].length() - 1)) * 1000);
            int start = end - processingTime + 1;
            
            traffic.add(new Time(start, end));
        }
        
        int max = 1;
        int cnt = 0;
        
        for(int i=0;i<traffic.size();i++) {
            cnt = 1;
            for(int j=i+1;j<traffic.size();j++) {
                if(traffic.get(i).end + 1000 > traffic.get(j).start) cnt++;
            }
            max = Math.max(cnt, max);
        }
        
        return max;
    }
}