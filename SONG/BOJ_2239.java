import java.io.*;

public class Main {

    static int[][] maps = new int[9][9];
    static int cnt =0;  //빈칸 수
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        //스도쿠 판 입력받기
        for(int i=0;i<9;i++){
            String st = in.readLine();
            for(int j=0;j<9;j++){
                int n = st.charAt(j)-'0';
                if (n !=0){
                    cnt++;
                }
                maps[i][j]=n;
            }
        }
        Sudoku(0,0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(maps[i][j]);
            }
            System.out.println();
        }

    }
    static boolean Sudoku(int x,int y){
        if (x==9){ // 종료 조건
            return true;
        }
        if (maps[x][y] !=0){ // 현재 값이 빈칸이 아니면 다음칸으로
            return Sudoku(y == 8 ? x+1 : x ,(y+1)%9);
        }
        int[] num_list;
        num_list = find_numbers(x, y); // 넣을 수 있는 숫자 리스트 받기
        if (num_list[0] == 0) { // 넣을 수 있는 숫자가 없으면 잘못된 케이스
            return false;
        }
        for (int k = 1; k < num_list.length; k++) {
            if (num_list[k]==1){
                maps[x][y] = k;
                if (Sudoku(y == 8 ? x + 1 : x, (y + 1) % 9)) { // 하나의 경우를 찾으면 재귀를 종료해야함
                    return true;
                }
                maps[x][y]=0; // 원상복구 시키기
            }
        }
        return false;
    }

    static int[] find_numbers(int i, int j){
        int[] poss_num = {9,1,1,1,1,1,1,1,1,1}; //현재 삽입가능한 숫자 초기화 1:사용가능 2: 불가능
        for(int x=0; x<9 ;x++){ //가로 세로 숫자 탐색
            if (maps[i][x] !=0 && poss_num[maps[i][x]]==1){
                poss_num[maps[i][x]] = 0;
                poss_num[0]--;
            }
            if ( maps[x][j] !=0 && poss_num[maps[x][j]] ==1){
                poss_num[maps[x][j]] = 0;
                poss_num[0]--;
            }
        }
        //작은 직사각형 숫자탐색
        int row= (i/3)*3;
        int col= (j/3)*3;
        for(int x=row; x<row+3;x++){
            for(int y=col;y<col+3;y++){
                if (maps[x][y] !=0 && poss_num[maps[x][y]] ==1){
                    poss_num[maps[x][y]] =0;
                    poss_num[0]--;
                }
            }
        }
        return poss_num;
    }


}