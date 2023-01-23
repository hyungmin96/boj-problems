import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[100_000_010];
        StringBuilder sb = new StringBuilder();

        for(int n : getPirmeNumbers(b)){
            if(n < a) continue;
            if(isPalindrome(n))
                sb.append(n + "\n");
        }
        sb.append(-1);
        System.out.println(sb.toString());
    }

    public static ArrayList<Integer> getPirmeNumbers(int limit) {
        ArrayList<Integer> pn = new ArrayList<>();
        boolean[] isPn = new boolean[limit+1];
        int sqrtN = (int)Math.sqrt(limit);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (isPn[i]) continue;
            int base = i + i;
            while (base <= limit) {
                isPn[base] = true;
                base+=i;
            }
        }
        pn.add(2);
        for (int i = 3; i <= limit; i+=2) {
            if (!isPn[i]) pn.add(i);
        }
        return pn;
    }

    private static boolean isPalindrome(int num) {
        int base = 1;
        int len = 0;
        while (num >= base) {
            base *= 10;
            len++;
        }
        int low = 10;
        int high = base/10;
        for (int i = 0; i < len/2; i++, low*=10, high/=10)
            if ((num%low)/(low/10) != (num/high)%10) return false;
        return true;
    }
}