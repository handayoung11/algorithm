import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

// 백준 1047 - 울타리
public class Main {

    static Tree[] trees;
    static int[] x, y;
    static int ans;
    static int N;
    static Rectangle r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        trees = new Tree[N];
        x = new int[N];
        y = new int[N];
        ans = N - 1;

        for (int i = 0; i < N; i++) {
            String s[] = br.readLine().split(" ");
            trees[i] = new Tree(parseInt(s[0]), parseInt(s[1]), parseInt(s[2]));
            x[i] = trees[i].x;
            y[i] = trees[i].y;
        }
        Arrays.sort(trees, Comparator.comparingInt((Tree t) -> t.len).reversed());
        Arrays.sort(x);
        Arrays.sort(y);

        //x좌표와 y좌표를 조합해서 모든 경우의 사각형을 만든다
        int minX, minY, maxX, maxY;
        for (int a = 0; a < N - 1; a++) {
            minX = x[a];
            for (int b = a + 1; b < N; b++) {
                maxX = x[b];
                for (int c = 0; c < N - 1; c++) {
                    minY = y[c];
                    for (int d = c + 1; d < N; d++) {
                        maxY = y[d];
                        r = new Rectangle(minX, minY, maxX - minX, maxY - minY);
                        cutDown();
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void cutDown() {
        int cutted = 0;
        int len = 0;

        Queue<Integer> contained = new LinkedList<>();
        for (Tree t : trees) {
            // 사각형 안에 포함되는 경우
            if (contained(t)) {
                contained.add(t.len);
            } else {
                // 포함되지 않는 경우, 나무를 자르고 길이를 추가한다
                cutted++;
                if (cutted == ans) {
                    return;
                }
                len += t.len;
            }
        }

        // 바깥쪽의 나무를 잘라서 둘레를 충족시킨 경우
        int perimeter = (r.width + r.height) * 2;
        if (perimeter <= len) {
            ans = cutted;
            return;
        }

        // 그렇지 못한 경우 안쪽의 나무들도 잘라서 둘레를 충족시킨다
        Integer l;
        while ((l = contained.poll()) != null) {
            cutted++;
            if (cutted == ans) {
                return;
            }
            len += l;
            if (perimeter <= len) {
                ans = cutted;
                return;
            }
        }
    }

    static boolean contained(Tree t) {
        return r.x <= t.x && r.x + r.width >= t.x && r.y <= t.y && r.y + r.height >= t.y;
    }
}

class Tree {
    int x;
    int y;
    int len;

    public Tree(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }
}