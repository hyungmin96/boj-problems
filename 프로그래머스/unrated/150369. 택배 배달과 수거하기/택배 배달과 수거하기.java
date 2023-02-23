import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> d = new Stack<>();
        Stack<Integer> p = new Stack<>();
        
        for(int i = 0; i < deliveries.length; i ++){
            d.push(deliveries[i]);
            p.push(pickups[i]);
        }
        
        
        while(!d.isEmpty() || !p.isEmpty()){
            int cargo = 0;
            int box = 0;
            
            while(!d.isEmpty()) if(d.peek() == 0) d.pop(); else break;
            while(!p.isEmpty()) if(p.peek() == 0) p.pop(); else break;
            
            int dist = Math.max(d.size(), p.size()) * 2;
            while(!d.isEmpty()){
                if(d.peek() + cargo <= cap)
                    cargo += d.pop();  
                else{
                    d.push(d.pop() - (cap - cargo));
                    break;
                }
            }
            while(!p.isEmpty()){
                if(p.peek() + box <= cap)
                    box += p.pop();
                else{
                    p.push(p.pop() - (cap - box));
                    break;
                }
            }
            
            answer += dist;
        }
        
        return answer;
    }
}