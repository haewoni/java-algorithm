package main.java.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class Picture2 {
    static int pictureCount = 0;
    static int totalArea = 0;

    public static int[] solution(int[][] paper){
       
        int height = paper.length; // 높이&x
        int width = paper[0].length; // 가로&y
        boolean[][] visited = new boolean[height][width];
        
        // 위아래 좌우 확인용
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                // 시작점 찾기, 방문 체크
                if(paper[i][j]==1 && !visited[i][j]){
                    pictureCount++; // 그림 수 세기
                    bfs(paper, height, width, i, j, dx, dy, visited);
                    }
            }
        }

        return new int[]{pictureCount,totalArea};
    }

    public static void bfs(int[][] paper, int h, int w, int x, int y, int[] dx, int[] dy, boolean[][] visited){
        Queue<int[]> queue = new LinkedList<>();  //BFS 자료구조
        queue.add(new int[]{x,y}); // 큐에 현재 좌표 추가

        visited[x][y] = true;
        int area = 1; // 최소 넓이

        //BFS 자료구조
        while(!queue.isEmpty()){
            int[] current = queue.poll(); // 현재꺼
            int bx =  current[0];
            int by = current[1];

            for(int i=0;i<4;i++){
                int cx = bx + dx[i];
                int cy = by + dy[i];

                if(cx>=0 && cy>=0 && cx <h && cy<w ){
                    if(paper[cx][cy] == 1 && !visited[cx][cy]) {
                        visited[cx][cy] = true;
                        queue.add(new int[]{cx,cy});
                        area++;
                    }
                }
            }


        }
        totalArea += area;

    }

}
