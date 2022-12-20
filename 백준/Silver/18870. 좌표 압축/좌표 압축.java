import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int idx, num;
        public Pair(int idx, int num) { this.idx = idx; this.num = num; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] answer = new int[n];
        Pair[] nums = new Pair[n];
        for(int i = 0; i < n; i ++)
            nums[i] = new Pair(i, Integer.parseInt(st.nextToken()));

        Arrays.sort(nums, new Comparator<>(){
            @Override
            public int compare(Pair p1, Pair p2){
                return p1.num - p2.num;
            }
        });

        int prev = Integer.MIN_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i ++){
            if(prev != nums[i].num)
                answer[nums[i].idx] = list.size();
            else
                answer[nums[i].idx] = list.size() - 1;

            if(prev == Integer.MIN_VALUE || prev != nums[i].num){
                list.add(nums[i].num);
                prev = nums[i].num;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answer.length; i ++)
            sb.append(answer[i] + " ");
        System.out.println(sb.toString());
    }
}