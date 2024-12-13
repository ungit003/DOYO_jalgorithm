import java.util.Scanner;

public class Main {
    static int N, M; // 방의 크기
    static int[][] map; // 방의 상태 (0: 청소되지 않음, 1: 벽, 2: 청소됨)
    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서 방향에 따른 행 변화
    static int[] dy = {0, 1, 0, -1}; // 북, 동, 남, 서 방향에 따른 열 변화
    static int cleanedCount = 0; // 청소한 칸의 개수

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        N = scanner.nextInt();
        M = scanner.nextInt();
        int x = scanner.nextInt(); // 로봇의 시작 행 위치
        int y = scanner.nextInt(); // 로봇의 시작 열 위치
        int direction = scanner.nextInt(); // 로봇이 바라보는 방향 (0: 북, 1: 동, 2: 남, 3: 서)

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        // 로봇 청소 시작
        cleanRoom(x, y, direction);

        // 결과 출력
        System.out.println(cleanedCount);
    }

    public static void cleanRoom(int x, int y, int direction) {
        while (true) {
            // 1. 현재 위치를 청소
            if (map[x][y] == 0) {
                map[x][y] = 2; // 현재 위치를 청소된 상태로 표시
                cleanedCount++;
            }

            boolean hasCleanableSpot = false;

            // 2. 현재 위치에서 주변 4칸 탐색
            for (int i = 0; i < 4; i++) {
                direction = (direction + 3) % 4; // 반시계 방향으로 회전
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                    // 청소되지 않은 빈 칸이 있다면 이동 후 다시 청소 시작
                    x = nx;
                    y = ny;
                    hasCleanableSpot = true;
                    break;
                }
            }

            if (!hasCleanableSpot) {
                // 주변에 청소할 곳이 없다면 후진 시도
                int backDirection = (direction + 2) % 4;
                int bx = x + dx[backDirection];
                int by = y + dy[backDirection];

                if (bx >= 0 && bx < N && by >= 0 && by < M && map[bx][by] != 1) {
                    // 후진 가능하면 후진
                    x = bx;
                    y = by;
                } else {
                    // 후진도 불가능하면 작동 종료
                    break;
                }
            }
        }
    }
}