import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    public class Price{
        int n, p;
        public Price(int n, int p){
            this.n = n;
            this.p = p;
        }
    }

    int N;
    int[] map;
    Price[] p;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new Price[N];
        map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i ++){
            int price = Integer.parseInt(st.nextToken());
            p[i] = new Price(i, price);
            map[i] = price;
        }

        int asset = Integer.parseInt(br.readLine());
        solve(asset);
    }

    public void solve(int asset){
        // 가격이 가장 싸면서 큰 숫자순으로 정렬
        Arrays.sort(p, new Comparator<Price>(){
            @Override
            public int compare(Price p1, Price p2){
                if(p1.p != p2.p){
                    return p1.p - p2.p;
                }
                return p2.n - p1.n;
            }
        });
        
        // 가장 저렴한 가격의 숫자로 최대 자리 수 채우기
        ArrayList<Integer> list = new ArrayList<>();
        while(asset >= p[0].p){
            if(list.size() == 0 && p[0].n == 0){
                if(1 < N && p[1].p <= asset){
                    list.add(p[1].n);
                    asset -= p[1].p;
                }else{
                    asset = 0;
                    break;
                }
            }else{
                list.add(p[0].n);
                asset -= p[0].p;
            }
        }

        int[] nums = new int[list.size()];
        for(int i = 0; i < list.size(); i ++){
            nums[i] = list.get(i);
        }

        int rest = asset;
        for(int idx = 0; idx < nums.length && rest > 0; idx ++){
            // 현재 남은 가격
            // 현재 idx 자리 숫자의 가격
            int idx_price = map[nums[idx]];
            rest += idx_price;
            // 현재 자리에서 구매가능한 가장 큰 값
            int max = -1, selected_price = 0;
            for(int i = 0; i < N; i ++){
                if(max < p[i].n && rest >= p[i].p){
                    max = p[i].n;
                    selected_price = p[i].p;
                }
            }
            nums[idx] = max;
            rest -= selected_price;
        }

        if(nums.length == 0){
            System.out.println(0);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int n : nums){
            sb.append(n);
        }
        System.out.println(sb.toString());
    }
}