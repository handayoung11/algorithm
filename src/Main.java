import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static char board[][] = new char[5][5];
    static ArrayList<Point> pts = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    // 백준 1035 - 조각 움직이기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == '*')
                    pts.add(new Point(j, i));
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Set<Point> possiblePts = new HashSet<>();
                //0,0에서부터 5,5까지 보드 전체 좌표를 한 번씩 기준점으로 삼아본다
                addPossiblePoint(new Point(j, i), possiblePts);
                if (possiblePts.containsAll(pts)) {
                    System.out.println(0);
                    return;
                }
                dfs(0, possiblePts);
                possiblePts.clear();
            }
        }

        System.out.println(ans);
    }

    static void dfs(int distance, Set<Point> possiblePts) {
        if (pts.size() == 0) {
            ans = Math.min(ans, distance);
            return;
        }

        for (Point p : pts) {
            // 이동가능좌표(pp)가 이미 별(p)인 경우 해당 좌표를 제외하고, 다음 좌표로 넘어간다
            if (possiblePts.contains(p)) {
                pts.remove(p);
                dfs(distance, possiblePts);
                pts.add(p);
                return;
            }
        }

        // 이동가능좌표들(pp)과 이동해야할 별 좌표(p)들 사이의 최단 거리 좌표들을 구한다
        int minDistance = Integer.MAX_VALUE;
        ArrayList<Point[]> closest = new ArrayList<>();
        for (Point pp : possiblePts) {
            for (Point p : pts) {
                int d = (int) (Math.abs(pp.getX() - p.getX()) + Math.abs(pp.getY() - p.getY()));
                if (d < minDistance) {
                    closest.clear();
                    closest.add(new Point[] {p, pp});
                    minDistance = d;
                } else if (d == minDistance) {
                    closest.add(new Point[] {p, pp});
                }
            }
        }

        distance += minDistance;
        if (distance >= ans) {
            return;
        } else if (pts.size() == 1) {
            //별 좌표들이(pts) 하나만 남은 경우 최단 거리는 동일하므로 경우의 수를 계산하지 않도록 return한다
            Point remove = pts.remove(0);
            dfs(distance, possiblePts);
            pts.add(remove);
            return;
        }

        //최단거리 좌표들을 히나하나 확인한다
        boolean isStar;
        for (Point[] c : closest) {
            int y = (int) c[0].getY();
            int x = (int) c[0].getX();
            if (isStar = isStarPoint(c[0])) {
                /* '.'으로 바꿔주지 않으면 calPossiblePts에서 해당 좌표를 '*'로 인식한다
                * 해당 '*'은 최단거리의 이동가능좌표(c[1])로 이동하기때문에 더이상 '*'이 아니다.*/
                board[y][x] = '.';
            }
            Set<Point> copy = new HashSet<>();
            copy.addAll(possiblePts);
            pts.remove(c[0]);
            calPossiblePts(c[1], copy);
            dfs(distance, copy);
            pts.add(c[0]);
            if (isStar) {
                board[y][x] = '*';
            }
        }
    }

    /**
     * 입력받은 좌표를 기준으로 이동가능좌표를 설정한다
     */
    static void addPossiblePoint(Point p, Set<Point> possiblePts) {
        if (isStarPoint(p)) {
            calPossiblePts(p, possiblePts);
        } else {
            possiblePts.add(p);
        }
    }

    /**
     * 입력받은 좌표의 상하좌우를 이동가능좌표로 추가해준다.
     */
    static void calPossiblePts(Point p, Set<Point> possiblePts) {
        possiblePts.add(p);

        int x = (int) p.getX();
        int y = (int) p.getY();
        int possibleXY[] = new int[]{x - 1, x + 1, y - 1, y + 1};

        for (int i = 0; i < 4; i++) {
            if (possibleXY[i] >= 0 && possibleXY[i] < 5) {
                Point possible;
                if (i <= 1) {
                    possible = new Point(possibleXY[i], y);
                } else {
                    possible = new Point(x, possibleXY[i]);
                }

                //이동가능좌표가 이미 별인 경우 해당 포인트를 기준으로 가능지점 추가
                if (isStarPoint(possible) && !possiblePts.contains(possible)) {
                    calPossiblePts(possible, possiblePts);
                }

                possiblePts.add(possible);
            }
        }
    }

    /**
     * 입력받은 좌표가 별인지 아닌지 체크한다.
     */
    static boolean isStarPoint(Point p) {
        return board[(int) p.getY()][(int) p.getX()] == '*';
    }
}
