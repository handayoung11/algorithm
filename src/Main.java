import java.io.*;

public class Main {
    private static String words[], longestWord;
    private static boolean[][] visited = new boolean[4][4];
    private static char[][] board = new char[4][4];
    private static int score, passedwordsCnt;

    // 백준 9202 - Boggle
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int wordsCnt = strToInt(br.readLine());
        String wordsSource[] = new String[wordsCnt];
        for (int i = 0; i < wordsCnt; i++) wordsSource[i] = br.readLine();

        br.readLine();

        int boggleBoardCnt = strToInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 20 * boggleBoardCnt);
        while (boggleBoardCnt > 0) {
            for (int j = 0; j < 4; j++) board[j] = br.readLine().toCharArray();
            words = wordsSource.clone();
            score = 0;
            passedwordsCnt = 0;
            longestWord = "";
            checkBoard();
            bw.write(score + " " + longestWord + " " + passedwordsCnt + "\n");
            if (boggleBoardCnt != 1) br.readLine();
            boggleBoardCnt--;
        }
        bw.flush();
    }

    private static void checkBoard() {
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++)
                for (int i = 0; i < words.length; i++) {
                    if (words[i].charAt(0) == board[row][col] && boardContains(row, col, i, 1)) {
                        int wordLen = words[i].length();
                        if (wordLen <= 5) score += (wordLen - 1) / 2;
                        else if (wordLen == 6) score += 3;
                        else if (wordLen == 7) score += 5;
                        else score += 11;

                        if (longestWord.length() < wordLen) longestWord = words[i];
                        else if (longestWord.length() == wordLen) longestWord = longestWord.compareTo(words[i]) < 0 ? longestWord : words[i];

                        words[i] = " ";
                        passedwordsCnt++;
                    }
                }
    }

    private static boolean boardContains(int row, int col, int wordsIdx, int checkIdx) {
        if (checkIdx == words[wordsIdx].length()) return true;
        visited[row][col] = true;

        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++) {
                int nextRow = row + i, nextCol = col + j;
                if (nextRow > 3 || nextRow < 0 || nextCol > 3 || nextCol < 0) continue;
                if (!visited[nextRow][nextCol] && board[nextRow][nextCol] == words[wordsIdx].charAt(checkIdx))
                    if (boardContains(nextRow, nextCol, wordsIdx, checkIdx + 1)) {
                        visited[row][col] = false;
                        return true;
                    }
            }

        return visited[row][col] = false;
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
