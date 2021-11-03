import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MAX = 0;
    static int[] words;
    static int N, K;

    // 백준 1062 - 가르침
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bufferedReader.readLine());
        N = strToInt(token.nextToken());
        K = strToInt(token.nextToken());
        words = new int[N];
        int alphabet = 0, whole = 0;
        char essential[] = {'a', 'n', 't', 'i', 'c'};

        for (char c : essential) alphabet |= 1 << (c - 'a');

        for(int i = 0; i < N; i++) {
            String word = bufferedReader.readLine();
            words[i] = alphabet;
            for(int j = 4; j < word.length() - 4; j++) words[i] |= 1 << (word.charAt(j) - 'a');
            whole |= words[i];
        }

        if(K < 5) {
            System.out.println("0");
            return;
        } else if(Integer.bitCount(whole) <= K) {
            System.out.println(N + "");
            return;
        }

        dfs(alphabet, -1);
        System.out.println(MAX + "");
    }

    private static void dfs(int learnedWords, int last) {
        if(Integer.bitCount(learnedWords) == K) {
            int cnt = 0, check;
            for(int i = 0; i < N; i++) {
                check = learnedWords | words[i];
                if(check == learnedWords) cnt++;
            }
            MAX = Math.max(cnt, MAX);
            return;
        }

        for(int i = last + 1; i < 26; i++) {
            if ((learnedWords & (1 << i)) != 0) continue;
            dfs(learnedWords | 1 << i, i);
        }
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
