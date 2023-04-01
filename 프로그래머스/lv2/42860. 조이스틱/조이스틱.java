import java.util.*;
    public class Solution {

        int answer = 0, move = 0;
        boolean[] check;
        ArrayList<Integer> list = new ArrayList<>();
        String n1 = "";
        public int solution(String name) {
            n1 = name;
            list.add(0);
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (c != 'A') {
                    int idx = (int) (c - 'A');
                    list.add(i);
                    answer += Math.min(idx, 26 - idx);
                }
            }

            if(list.size() >= 1){
                move = 987654321;
                Collections.sort(list);
                check = new boolean[list.size()];
                check[0] = true;
                dfs(list.size(), 0, 0, 0, 0);    
            }
            return answer + move;
        }

        public void dfs(int n, int depth, int pos, int idx, int cnt) {
            if (n - 1 == depth) {
                move = Math.min(move, cnt);
                return;
            }

            int left_move_idx = findLeft(n, idx);
            if (left_move_idx != -1) {
                check[left_move_idx] = true;
                int left_pos = list.get(left_move_idx);
                int dist = Math.min(Math.abs(n1.length() - Math.abs(left_pos - pos)), Math.abs(pos - left_pos));

                dfs(n, depth + 1, list.get(left_move_idx), left_move_idx, cnt + dist);
                check[left_move_idx] = false;
            }

            int right_move_idx = findRight(n, idx);
            if (right_move_idx != -1) {
                check[right_move_idx] = true;
                int right_pos = list.get(right_move_idx);
                int dist = Math.min(Math.abs(n1.length() - Math.abs(right_pos - pos)), Math.abs(pos - right_pos));

                dfs(n, depth + 1, list.get(right_move_idx), right_move_idx, cnt + dist);
                check[right_move_idx] = false;
            }
        }

        public int findLeft(int n, int cur) {
            for (int i = n - 1; i >= 0; i--) {
                if (cur - 1 < 0)
                    cur = n - 1;
                else
                    cur--;
                if (!check[cur]) {
                    return cur;
                }
            }
            return -1;
        }

        public int findRight(int n, int cur) {
            for (int i = 0; i < n; i++) {
                if (cur + 1 >= n)
                    cur = 0;
                else
                    cur++;
                if (!check[cur]) {
                    return cur;
                }
            }
            return -1;
        }
}