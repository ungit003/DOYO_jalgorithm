import java.util.Scanner;
// 달팽이
public class Boj_1913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int N = sc.nextInt();
        int target = sc.nextInt();

        int[][] matrix = new int[N][N]; 
        int ansIdx = 0, ansJdx = 0; 

        int[] di = {1, 0, -1, 0};
        int[] dj = {0, 1, 0, -1};

        int i = 0, j = 0, direction = 0;
        int num = N * N; 

        while (num > 0) {
            matrix[i][j] = num;
            if (num == target) { 
                ansIdx = i + 1;
                ansJdx = j + 1;
            }
            num--;

            int ni = i + di[direction];
            int nj = j + dj[direction];

            // 경계 밖으로 나가거나 이미 채워진 칸인 경우 방향 전환
            if (ni < 0 || ni >= N || nj < 0 || nj >= N || matrix[ni][nj] != 0) {
                direction = (direction + 1) % 4; // 방향 전환
                ni = i + di[direction];
                nj = j + dj[direction];
            }

            i = ni;
            j = nj;
        }

        // 결과 출력
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println(); // 줄바꿈
        }
        System.out.println(ansIdx + " " + ansJdx);

        sc.close();
    }
}
