def check(row, col, num):
    # 같은 행에 중복된 숫자가 없는지 확인
    for x in range(9):
        if sudoku_board[row][x] == num:
            return False
    
    # 같은 열에 중복된 숫자가 없는지 확인
    for x in range(9):
        if sudoku_board[x][col] == num:
            return False
    
    # 3x3 사각형 내에 중복된 숫자가 없는지 확인
    start_row, start_col = 3 * (row // 3), 3 * (col // 3)
    for i in range(3):
        for j in range(3):
            if sudoku_board[i + start_row][j + start_col] == num:
                return False
    return True

def solve_sudoku(board):
    for row in range(9):
        for col in range(9):
            if board[row][col] == 0:
                for num in range(1, 10):
                    if check(row, col, num):
                        board[row][col] = num
                        if solve_sudoku(board):
                            return True
                        board[row][col] = 0
                return False
    return True

# 스도쿠 보드 입력 받기
sudoku_board = []
for _ in range(9):
    row = list(map(int, input().strip()))
    sudoku_board.append(row)

solve_sudoku(sudoku_board)

# 결과 출력
for row in sudoku_board:
    print(''.join(map(str, row)))