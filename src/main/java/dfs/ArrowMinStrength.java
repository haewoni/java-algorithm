package dfs;

import java.util.Arrays;
import java.util.Scanner;
/*
N*N의 과녁, K개의 화살, 목표 점수 P 가 있다.
화살의 굵기 i = 1~K 이고, 각 화살을 사용하기 위해서는 B[1] ~ B[K] 의 힘이 필요하다.
과녁 중앙만 맞춘다고 가정했을때, 과녁 거리 |x1-x2|+|y1-t1| 가 굵기 i 미만일 경우 , 해당 과녁도 다 맞춘걸로 계산된다.
목표 점수 P를 정확히 얻을 수 있는 최소 힘의 합을 계산해야한다.
만약 목표 점수 P를 어떻게 해서도 얻을 수 없는 경우라면 -1 반환해야한다.

입력값 예시
3 2 8
1 1 1
1 1 1
1 1 3
1 2

출력값 예시:
3 (3,3) 과녁에 2개의 화살 모두 맞출 경우 정확히 8 점수 획득 가능
 */

public class ArrowMinStrength {
    static int N, K, P;
    static int[][] board;
    static int[] B;
    static int minPower = Integer.MAX_VALUE;
    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            // 입력 받기
            N = sc.nextInt();
            K = sc.nextInt();
            P = sc.nextInt();

            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            B = new int[K];
            for (int i = 0; i < K; i++) {
                B[i] = sc.nextInt();
            }

            // 백트래킹을 통해 최소 힘 탐색
            shootArrows(0, new boolean[N][N], 0, 0);

            // 결과 출력
            System.out.println(minPower == Integer.MAX_VALUE ? -1 : minPower);
        }

        // 백트래킹을 통해 최소 힘을 사용하여 P 점수를 만드는 조합을 찾는 함수
        static void shootArrows ( int arrowIdx, boolean[][] hit, int score, int totalPower){
            if (score == P) {
                minPower = Math.min(minPower, totalPower);
                return;
            }
            if (arrowIdx >= K) return; // 화살 다 사용했으면 종료
            if (score > P) return; // 목표 점수를 넘어서면 무의미하므로 종료

            // 현재 사용할 화살의 굵기와 힘
            int radius = B[arrowIdx];
            int power = B[arrowIdx];

            // 모든 좌표에 대해 화살을 쏠 위치 선택
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (hit[x][y]) continue; // 이미 맞춘 곳이면 패스

                    // 새롭게 화살을 맞춘 위치를 저장할 배열
                    boolean[][] newHit = new boolean[N][N];
                    for (int i = 0; i < N; i++)
                        newHit[i] = Arrays.copyOf(hit[i], N);

                    // 현재 화살을 맞춘 경우 점수 계산
                    int gainedScore = 0;
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            if (!newHit[i][j] && Math.abs(x - i) + Math.abs(y - j) < radius) {
                                newHit[i][j] = true;
                                gainedScore += board[i][j];
                            }
                        }
                    }

                    // 재귀 호출 (다음 화살 사용)
                    shootArrows(arrowIdx + 1, newHit, score + gainedScore, totalPower + power);
                }
            }
        }
}
