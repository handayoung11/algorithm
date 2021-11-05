import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int cnt, num[], round = 1;
    static StringBuilder matchList = new StringBuilder();

    // 백준 1057 - 토너먼트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        cnt = strToInt(token.nextToken());
        num = new int[]{strToInt(token.nextToken()), strToInt(token.nextToken())};

        while(matchList.length() < cnt) matchList.insert(0, 0);
        matchList.replace(num[0] - 1, num[0], "1");
        matchList.replace(num[1] - 1, num[1], "1");
        match();

        System.out.println(round);
    }

    static void match() {
        StringBuilder nextMatchList = new StringBuilder();
        for(int i = 0; i < cnt - 1; i+=2) {
            int p = ((matchList.charAt(i) - '0') + (matchList.charAt(i + 1) - '0'));
            if(p == 2) return;
            nextMatchList.append(p);
        }
        if(cnt != nextMatchList.length()) nextMatchList.append(matchList.charAt(cnt - 1));
        matchList = nextMatchList;

        if(cnt <= 1) {
            round = -1;
            return;
        }

        cnt = (cnt + 1) / 2;
        round++;
        match();
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
