import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i ++){
            String str = br.readLine();
            for (int j = 0; j < str.length(); j ++){
                int cost = (int)Math.pow(10, str.length() - 1 - j);
                map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + cost);
            }
        }

        ArrayList<Integer> nums = new ArrayList<>();
        for(char k : map.keySet())
            nums.add(map.get(k));

        Collections.sort(nums, new Comparator<>(){
            @Override
            public int compare(Integer o1, Integer o2){ return o2 - o1; }
        });

        int answer = 0;
        int cnt = 9;
        for(int i = 0; i < nums.size(); i ++){
            answer += nums.get(i) * (cnt --);
        }

        System.out.println(answer);
    }
}