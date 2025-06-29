import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        HashMap<Character, Integer> termsMap = new HashMap<>();
        HashMap<Integer, HashMap<Character, String>> privaciesMap = new HashMap<>();
        
        termsMap = setTermsToMap(terms);
        privaciesMap = setPrivaciesToMap(privacies);
        
        int[] answer = getAnswer(today, termsMap, privaciesMap);
        return answer;
    }
    
    public int[] getAnswer(String today, HashMap<Character, Integer> termsMap, HashMap<Integer, HashMap<Character, String>> privaciesMap){
        ArrayList<Integer> answer = new ArrayList<>();
        int todayToDay = convertDay(today);
        
        for(int num : privaciesMap.keySet()){
            HashMap<Character, String> tmp = new HashMap<>();
            tmp = privaciesMap.get(num);
            
            for(char m : tmp.keySet()){
                String agree = tmp.get(m);
                int agreement = convertDay(agree);
                
                if((agreement + (termsMap.get(m) * 28)) - todayToDay <= 0){
                    answer.add(num);
                }
            }
        }
        
        int[] ret = new int[answer.size()];
        for(int i = 0; i < answer.size(); i ++){
            ret[i] = answer.get(i);
        }
        return ret;
    }
    
    public int convertDay(String s){
        s = s.replace(".", " ");
        String[] arr = s.split(" ");
        
        int y = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int d = Integer.parseInt(arr[2]);
                
        return (y * 12 * 28) + (m * 28) + d;
    }
    
    public HashMap<Integer, HashMap<Character, String>> setPrivaciesToMap(String[] privacies){
        HashMap<Integer, HashMap<Character, String>> privaciesMap = new HashMap<>();
        for(String s : privacies){
            HashMap<Character, String> info = new HashMap<>();
            
            String[] arr = s.split(" ");
            char menu = arr[1].charAt(0);
            String expireDate = arr[0];
            info.put(menu, expireDate);
            
            privaciesMap.put(privaciesMap.size() + 1, info);
        }
        return privaciesMap;
    }
    
    public HashMap<Character, Integer> setTermsToMap(String[] terms){
        HashMap<Character, Integer> termsMap = new HashMap<>();
        for(String s : terms){
            String[] arr = s.split(" ");
            char menu = arr[0].charAt(0);
            int nums = Integer.parseInt(arr[1]);
            termsMap.put(menu, nums);
        }
        return termsMap;
    }
}

// 모든 달 28일
// 약관이 12개월이고 1.5에 동의했다면 1.4에 폐기되어야함
// 폐기해야할 개인정보의 번호를 오름차순으로 리턴