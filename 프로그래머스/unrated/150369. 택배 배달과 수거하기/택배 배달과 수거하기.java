import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> delivery = new Stack<>();
        Stack<Integer> pickup = new Stack<>();
        
        for(int i = 0; i < deliveries.length; i ++){
            delivery.push(deliveries[i]);
        }
        
        for(int i = 0; i < pickups.length; i ++){
            pickup.push(pickups[i]);
        }
        
        while(!(delivery.isEmpty() && pickup.isEmpty())){
            int dist = 0;
            while(!delivery.isEmpty() && delivery.peek() == 0) delivery.pop();
            dist = Math.max(dist, delivery.size());
            
            int tmp = cap;
            while(!delivery.isEmpty() && tmp > 0){
                int cur = delivery.pop();
                if(tmp - cur < 0){
                    delivery.push(cur - tmp);
                    break;
                }else{
                    tmp -= cur;
                }
            }
            
            tmp = cap;
            while(!pickup.isEmpty() && pickup.peek() == 0) pickup.pop();
            dist = Math.max(dist, pickup.size());
            while(!pickup.isEmpty() && tmp > 0){
                int cur = pickup.pop();
                if(tmp - cur < 0){
                    pickup.push(cur - tmp);
                    break;
                }else{
                    tmp -= cur;
                }
            }
            answer += dist;
        }
        
        return answer * 2;
    }
}