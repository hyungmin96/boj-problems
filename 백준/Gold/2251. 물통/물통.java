import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        sol.solution();
    }
}

class Solution {

    int A, B, C;
    ArrayList<Integer> answer = new ArrayList<>();

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        bfs();
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answer.size(); i ++){
            sb.append(answer.get(i) + " ");
        }

        System.out.println(sb.toString());
    }

    public void bfs(){
        boolean[][][] v = new boolean[201][201][201];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, C });

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(v[cur[0]][cur[1]][cur[2]]) continue;
            v[cur[0]][cur[1]][cur[2]] = true;

            if(cur[0] == 0 && !answer.contains(cur[2])){
                answer.add(cur[2]);
            }
            // 물통 C에서 옮기는 경우
            if(cur[0] + cur[2] >= A){
                q.offer(new int[] { A, cur[1], cur[2] + cur[0] - A});
            }else{
                q.offer(new int[] { cur[0] + cur[2], cur[1], 0});
            }
            if(cur[1] + cur[2] >= B){
                q.offer(new int[] { cur[0], B, cur[2] + cur[1] - B});
            }else{
                q.offer(new int[] { cur[0], cur[1] + cur[2], 0});
            }

            // 물통 B에서 옮기는 경우
            if(cur[0] + cur[1] >= A){
                q.offer(new int[] { A, cur[1] + cur[0] - A, cur[2] });
            }else{
                q.offer(new int[] { cur[0] + cur[1], 0, cur[2]});
            }
            if(cur[1] + cur[2] >= C){
                q.offer(new int[] { cur[0], cur[1] + cur[2] - C, C });
            }else{
                q.offer(new int[] { cur[0], 0, cur[2] + cur[1] });
            }
            
            // 물통 A에서 옮기는 경우
            if(cur[0] + cur[1] >= B){
                q.offer(new int[] { cur[0] + cur[1] - B, B, cur[2] });
            }else{
                q.offer(new int[] { 0, cur[0] + cur[1], cur[2] });
            }
            if(cur[0] + cur[2] >= C){
                q.offer(new int[] { cur[0] + cur[2] - C, cur[1], C });
            }else{
                q.offer(new int[] { 0, cur[1], cur[2] + cur[0] });
            }
        }
    }
}

