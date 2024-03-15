import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Pair{
        int idx;
        String s;
        public Pair(int idx, String s){
            this.idx = idx;
            this.s = s;
        }
    }

    int N;
    Pair[] words;
    String[] origin;
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new Pair[N];
        origin = new String[N];
        for(int i = 0; i < N; i ++){
            words[i] = new Pair(i, br.readLine());
            origin[i] = words[i].s;
        }

        int len = 0, l = 0, r = 100;
        while(l <= r){
            int mid = (r + l) / 2;
            if(check(mid)){
                len = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        
        ArrayList<int[]> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i ++){
            if(words[i].s.length() < len){
                continue;
            }
            String s = words[i].s.substring(0, len);
            if(map.containsKey(s)){
                int n1 = words[i].idx;
                int n2 = map.get(s);

                list.add(new int[] { Math.min(n1, n2), Math.max(n1, n2) });
                map.put(s, Math.min(map.get(s), words[i].idx));
            }
            map.put(s, words[i].idx);
        }
        Collections.sort(list, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        System.out.println(origin[list.get(0)[0]]);
        System.out.println(origin[list.get(0)[1]]);
    }

    public boolean check(int mid){
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < N; i ++){
            if(words[i].s.length() < mid){
                continue;
            }

            String s = words[i].s.substring(0, mid);
            if(set.contains(s)){
                return true;
            }
            set.add(s);
        }
        return false;
    }
}


// 문자열의 수 N <= 20,000
// 문자열의 길이 <= 200

// 가장 비슷한(서로 다른 2개의 문자열의 공통된 접두사의 길이가 가장 긴) 문자열 S와 T를 출력
// (S, T)의 순서쌍이 여러개인 경우 S가 먼저 입력된 경우, 이러한 경우가 여러개이면 T가 입력된 순서가 빠른 순서쌍을 출력

// 1. 최대 20,000개의 문자열 중 공통된 접두사의 길이가 가장 긴 문자열 탐색방법
//  - O(log n) | O(n) | O(n log n) 의 복잡도를 가진 알고리즘으로 탐색해야함
//  - 문자열을 정렬할 경우
//   : ab
//   : acaabcde
//   : acb
// answer : 'ac'
