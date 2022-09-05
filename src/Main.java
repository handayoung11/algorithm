import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int ROW, COLUMN;
    static char[][] BUILDING;
    static char[][] ROOMS;
    static boolean isElgnatcer = true;

    // 백준 17860 - Square Rooms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(str.nextToken());
        COLUMN = Integer.parseInt(str.nextToken());
        BUILDING = new char[ROW][COLUMN];
        ROOMS = new char[ROW][COLUMN];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                int read = br.read();
                if (read == 10) {
                    j--;
                    continue;
                }
                BUILDING[i][j] = (char) read;
            }
        }

        dfs(0, 0, 'A');

        if(isElgnatcer) {
            System.out.println("elgnatcer");
        }
    }

    static void dfs(int x, int y, char label) {
        char nextLabel = label;
        int size = Math.min(COLUMN - x, ROW - y);
        loop:
        while(size >= 1) {
            int treasures = 0, rocks = 0;
            int maxColumn = x + size, maxRow = y + size;
            for (int i = y; i < maxRow; i++) {
                for (int j = x; j < maxColumn; j++) {
                    if (ROOMS[i][j] != '0' && ROOMS[i][j] != '\u0000') {
                        size = j == x ? 1 : j - x;
                        continue loop;
                    }
                    if (BUILDING[i][j] == '$') {
                        treasures++;
                    }
                    if (BUILDING[i][j] == '#') {
                        rocks++;
                    }
                    if (treasures + rocks > 1) {
                        size--;
                        continue loop;
                    }
                }
            }

            if (treasures + rocks == 0) {
                return;
            } else {
                for (int i = y; i < maxRow; i++) {
                    for (int j = x; j < maxColumn; j++) {
                        if (rocks == 1) {
                            ROOMS[i][j] = '#';
                        } else {
                            nextLabel = label == 'Z' ? 'a' : (char) (label + 1);
                            ROOMS[i][j] = i < maxRow && j < maxColumn ? label : '0';
                        }
                    }
                }
            }

//            System.out.println("(x + \",\" + y) = " + (x + "," + y));
//            System.out.println("size = " + size);

            int nextX = x + size;
            int nextY = y;

            if (nextX >= COLUMN) {
                nextX = 0;
                nextY++;
            }

            loop2:
            for (; nextY < ROW; nextY++) {
                for (; nextX < COLUMN; nextX++) {
                    if (ROOMS[nextY][nextX] == '0' || ROOMS[nextY][nextX] == '\u0000') {
                        break loop2;
                    }
                }
                nextX = 0;
            }
            if (nextY == ROW) {
                print();
                System.exit(0);
                return;
            }
            dfs(nextX, nextY, nextLabel);
            for (int i = y; i < maxRow; i++) {
                for (int j = x; j < maxColumn; j++) {
                    ROOMS[i][j] = '0';
                }
            }
            size--;
        }
    }

    static void print() {
        isElgnatcer = true;
        for (int c = 0; c < ROW; c++) {
            for (int d = 0; d < COLUMN; d++) {
                System.out.print(ROOMS[c][d]);
            }
            System.out.println();
        }
    }
}
