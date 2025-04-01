import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Integer;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ArrayList;

// 1158 - 요세푸스 문제
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("<");

        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        int gap = -1;

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(i + 1);
        }


        while (arr.size() != 1) {
            gap += K;
            gap = gap % arr.size();
            sb.append(arr.get(gap)).append(", ");
            arr.remove(gap--);
        }
        sb.append(arr.get(0)).append(">");
        System.out.println(sb);
    }
}