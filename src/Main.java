import java.io.*;

public class Main {

    // 백준 1436 - 영화감독 숌
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = strToInt(bufferedReader.readLine()), seriesNum = 666, cnt = 0;

        while(true) {
            int num = seriesNum;
            while(num >= 666) {
                // 세 수가 연속적으로 6인 경우 cnt 증가
                if(num % 10 == 6 && num / 10 % 10 == 6 && num / 100 % 10 == 6) {
                    cnt++;
                    break;
                }
                num /= 10;
            }

            if(cnt == n) {
                System.out.println(seriesNum);
                break;
            }

            seriesNum += 1;
        }
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
