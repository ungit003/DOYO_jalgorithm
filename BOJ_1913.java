import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // 표의 크기
        int target = scanner.nextInt();  // 찾을 숫자
        
        int[][] board = new int[n][n];  // 2차원 배열로 표 생성
        int num = n * n;  // 시작 숫자 (가장 큰 숫자부터 시작)
        
        int row = 0, col = 0;  // 현재 위치
        int direction = 0;  // 이동 방향 (0: 아래, 1: 오른쪽, 2: 위, 3: 왼쪽)
        
        // 이동 방향에 따른 행과 열의 변화
        int[] dr = {1, 0, -1, 0};  // 행 변화
        int[] dc = {0, 1, 0, -1};  // 열 변화
        
        while (num > 0) {
            board[row][col] = num--;  // 현재 위치에 숫자 채우기
            
            // 다음 위치 계산
            int nextRow = row + dr[direction];
            int nextCol = col + dc[direction];
            
            // 다음 위치가 범위를 벗어나거나 이미 숫자가 채워져 있다면 방향 전환
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || board[nextRow][nextCol] != 0) {
                direction = (direction + 1) % 4;  // 다음 방향으로 전환
                nextRow = row + dr[direction];
                nextCol = col + dc[direction];
            }
            
            row = nextRow;
            col = nextCol;
        }
        
        // 결과 출력
        int targetRow = 0, targetCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
                if (board[i][j] == target) {
                    targetRow = i + 1;
                    targetCol = j + 1;
                }
            }
            System.out.println();
        }
        System.out.println(targetRow + " " + targetCol);
        
        scanner.close();
    }
}