import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        int T = Integer.parseInt(in.readLine());

        for(int i=0;i<T;i++){
            String commends = in.readLine();
            int N = Integer.parseInt(in.readLine()); //배열 수 세기
            String array_input = in.readLine();

            // 배열 빼는 횟수 세기
            int D_cnt=0;
            for(int j=0;j<commends.length();j++){
                char c = commends.charAt(j);
                if (c=='D'){
                    D_cnt++;
                }
            }
            if (D_cnt>N){
                System.out.println("error");
                continue;
            }
            array_input = array_input.substring(1,array_input.length()-1); // 대괄호 제거해주기
            String[] arrays= array_input.split(","); // ,제거하여 배열에 삽입

            boolean state =true; //false이면 뒤집힌 상태
            int start =0; // 배열의 앞
            int end = arrays.length; //배열의 뒤

            for(int j=0;j<commends.length();j++){
                if(commends.charAt(j)=='R'){ // R이면 뒤집기
                    state = !state; //뒤집기
                } else{
                    if (state){
                        start++; // 앞에꺼 빼기
                    }
                    else{
                        end--; //뒤에꺼 빼기
                    }
                }
            }
            String[] result = new String[end-start];
            if (state){
                for (int j = 0; j < result.length; j++) {
                    result[j]=arrays[start];
                    start++;
                }
            }else{
                end--;
                for (int j = 0; j < result.length; j++) {
                    result[j]=arrays[end];
                    end--;
                }
            }
            System.out.print('[');
            for(int j=0;j<result.length;j++){
                if(j==result.length-1){
                    System.out.print(result[j]);
                }else{
                    System.out.print(result[j]+',');
                }
            }
            System.out.print(']');
            System.out.println();
        }
    }
}