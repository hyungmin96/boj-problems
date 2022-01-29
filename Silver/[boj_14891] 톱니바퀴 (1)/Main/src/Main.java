import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gears = new String[4];
        // N극 : 0, S극 : 1
        for(int i = 0; i < 4; i ++){
            gears[i] = br.readLine();
        }
        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> arrList = new ArrayList<>();
        // 1 : 시계, -1 : 반 시계
        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arrList.add(new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) });
        }

        System.out.println(solution(gears, n, arrList));
    }

    public static int solution(String[] gears, int n, ArrayList<int[]> arrList){
        for(int[] spin_info : arrList){
            boolean[] gear_spin = new boolean[4];
            gear_spin[spin_info[0]] = true;

            for(int i = spin_info[0] - 1; i >= 0; i --){
                if(gear_spin[i + 1] && gears[i].charAt(2) != gears[i + 1].charAt(6)){
                    gear_spin[i] = true;
                }
            }

            for(int i = spin_info[0] + 1; i < 4; i ++){
                if(gear_spin[i - 1] && gears[i].charAt(6) != gears[i - 1].charAt(2)){
                    gear_spin[i] = true;
                }
            }

            gears[spin_info[0]] = spin(gears[spin_info[0]], spin_info[1]);
            for(int i = 0; i < gear_spin.length; i ++){
                if(i != spin_info[0] && gear_spin[i]){
                    int d = (spin_info[0] - 2 == i || spin_info[0] + 2 == i) ? spin_info[1] : (spin_info[1] * -1);
                    gears[i] = spin(gears[i], d);
                }
            }
        }
        return getScore(gears);
    }

    public static String spin(String str, int type){
        if(type == -1) 
            return str.substring(1) + str.charAt(0);
        else 
            return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
    }

    public static int getScore(String[] gears){
        int result = 0;
        for(int i = 0; i < gears.length; i ++){
            if (gears[i].charAt(0) == '1') {
                switch (i) {
                    case 0:
                        result += 1;
                        break;
                    case 1:
                        result += 2;
                        break;
                    case 2:
                        result += 4;
                        break;
                    case 3:
                        result += 8;
                        break;
                }
            }
        }
        return result;
    }
}
