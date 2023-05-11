import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.lang.Integer.*;

// 백준 1090 - 체커
public class Main {

    static int N;
    static List<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        int dis[] = new int[N];

        for (int i = 0; i < N; i++) {
            String[] xy = br.readLine().split(" ");
            points.add(new Point(parseInt(xy[0]), parseInt(xy[1])));
            dis[i] = MAX_VALUE;
        }

        //복잡도는 O(n * n * 2n), 축약: O(N^3)
        //모든 x와 y를 조합해서 좌표를 만든다
        for (int j = 0; j < N; j++) {
            int x = points.get(j).x;
            for (int k = 0; k < N; k++) {
                int y = points.get(k).y;
                List<Integer> distances = new ArrayList<>();

                //입력받은 모든 포인트들로부터 조합 좌표까지의 거리를 구한다
                for (int c = 0; c < N; c++) {
                    Point p = points.get(c);
                    distances.add((int) (Math.abs(p.getX() - x) + Math.abs(p.getY() - y)));
                }

                //오름차순 정렬
                Collections.sort(distances);

                int dist = 0;
                for (int i = 0; i < N; i++) {
                    //정렬된 거리 정보를 통해 i개의 체커들이 조합된 좌표로 모일 때의 최소 거리를 구한다.
                    dist += distances.get(i);
                    //최소 거리가 기존 최소 거리보다 작다면 갱신
                    dis[i] = Math.min(dist, dis[i]);
                }
            }
        }

        for (int d : dis) {
            System.out.print(d + " ");
        }
    }
}