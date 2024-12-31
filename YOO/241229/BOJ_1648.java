import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 집의 개수
        int M = Integer.parseInt(input[1]); // 길의 개수

        // 길 정보를 저장할 리스트
        List<int[]> roads = new ArrayList<>();

        // 길 정보 입력
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            roads.add(new int[]{a, b, c});
        }

        // 길을 비용 순으로 정렬
        Collections.sort(roads, (a, b) -> a[2] - b[2]);

        int totalCost = 0;
        int connectedCities = 0;
        int lastRoadCost = 0;

        // 가장 저렴한 길부터 선택
        for (int[] road : roads) {
            totalCost += road[2];
            connectedCities++;
            lastRoadCost = road[2];

            // 모든 도시가 연결되면 중단
            if (connectedCities == N - 1) break;
        }

        // 마지막 길을 제거하여 두 마을로 분리
        System.out.println(totalCost - lastRoadCost);
    }
}
