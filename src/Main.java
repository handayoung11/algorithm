import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {
    static int N, M, ans = Integer.MAX_VALUE, targetNum, curNum, time;
    static int[][] statuses, cur;
    static boolean isChanged;
    static ArrayList<int[]> virus = new ArrayList<>();

    // 백준 17142 - 연구소3
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        statuses = new int[N][N];
        cur = new int[N][N];
        targetNum = N * N;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                statuses[i][j] = parseInt(st.nextToken());
                if (statuses[i][j] == 1 || statuses[i][j] == 2) {
                    targetNum--;
                    if (statuses[i][j] == 2) {
                        if (virus.size() % 5 == 0) {
                            virus.add(new int[]{i, j});
                        } else {
                            virus.add(0, new int[]{i, j});
                        }
                    }
                }
            }
        }

        if (targetNum == 0) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(int active, int si) {
        if (active == M) {
            curNum = 0;
            cur = new int[N][N];
            time = 0;
            separate();
            return;
        }
        for (int i = si; i < virus.size(); i++) {
            int[] coor = virus.get(i);
            statuses[coor[0]][coor[1]] = -1;
            dfs(++active, i + 1);
            --active;
            statuses[coor[0]][coor[1]] = 2;
        }
    }

    private static void separate() {
        while (true) {
            isChanged = false;
            time++;
            if (time >= ans) {
                return;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (time == 1 && statuses[i][j] == -1) {
                        cur[i][j] = 1;
                    }
                    if (cur[i][j] == time) {
                        separateFrom(i, j);
                    }
                }
            }


            if (!isChanged) {
                return;
            } else if (curNum == targetNum) {
                ans = Math.min(time, ans);
                return;
            }
        }
    }

    private static void separateFrom(int i, int j) {
        separateTo(i - 1, j);
        separateTo(i + 1, j);
        separateTo(i, j - 1);
        separateTo(i, j + 1);
    }

    private static void separateTo(int i, int j) {
        try {
            if ((statuses[i][j] == 0 || statuses[i][j] == 2) && cur[i][j] == 0) {
                if (statuses[i][j] == 0)
                    curNum++;
                cur[i][j] = time + 1;
                isChanged = true;
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }
}
