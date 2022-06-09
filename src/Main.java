import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int totalX, totalY, target, vx, vy; //X값 총합,Y값 총합
    static int coor[][]; //좌표 배열
    static double min;

    // 백준 1007 - 벡터 매칭
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            target = n / 2;
            //coor 구조 = [[x: 0, y: 1], [0, 2]]
            coor = new int[n][2];
            totalX = 0;
            totalY = 0;
            min = Double.POSITIVE_INFINITY;

            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                coor[j] = new int[]{x + x, y + y};
                totalX += x;
                totalY += y;
            }

            for (int j = 0; j < coor.length; j++) {
                vx = totalX;
                vy = totalY;
                if (coor.length - j >= target) {
                    getMinVec(j, 1);
                }
            }
            System.out.println(Math.sqrt(min));
        }
    }

    private static void getMinVec(int idx, int depth) {
        vx -= coor[idx][0];
        vy -= coor[idx][1];

        if (depth >= target) {
            min = Math.min((long)vx * vx + (long)vy * vy, min);
        }
        else {
            ++depth;
            for (int i = idx + 1; i < coor.length; i++) {
                if (coor.length - i >= target - depth + 1) {
                    getMinVec(i, depth);
                }
            }
        }
        vx += coor[idx][0];
        vy += coor[idx][1];
    }
}