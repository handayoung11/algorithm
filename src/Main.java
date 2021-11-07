import java.io.*;

public class Main {
    static int MinClickCnt, TargetCh;
    static String WorkingButtons = "", TargetChStr;

    // 백준 1107 - 리모컨
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brokenButtons = "";

        TargetCh = strToInt(TargetChStr = br.readLine());
        int brokenButtonsCnt = strToInt(br.readLine());
        if(brokenButtonsCnt != 0) brokenButtons = br.readLine();

        for (int i = 0; i < 10; i++) {
            if (!brokenButtons.contains((i + ""))) WorkingButtons += i;
        }
        MinClickCnt = Math.abs(TargetCh - 100);
        if(brokenButtonsCnt != 10 && MinClickCnt > TargetChStr.length()) {
            int dif = 0;
            while(true) {
                int nextCh = TargetCh + dif, prevCh = TargetCh - dif, len;
                if(prevCh >= 0 && (len = containWord(prevCh)) != -1) {
                    MinClickCnt = Math.min(MinClickCnt, len + dif);
                    break;
                }
                if((len = containWord(nextCh)) != -1) {
                    MinClickCnt = Math.min(MinClickCnt, len + dif);
                    break;
                }
                dif++;
            }
        }
        System.out.println(MinClickCnt);
    }

    private static int containWord(int channel) {
        int length = 0;
        do {
            if(!WorkingButtons.contains(channel % 10 + "")) return -1;
            channel /= 10;
            length++;
        } while(channel != 0);
        return length;
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
