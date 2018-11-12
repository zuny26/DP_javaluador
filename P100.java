package AA;

import java.util.ArrayList;

public class P100{

    public ArrayList<Integer> bestSolution(String data){
        ArrayList<Integer>solution = new ArrayList<>();
        String[] parts = data.split("\\p{Space}+");
        int M = parts.length - 2;
        if (M==0) return solution;
        int N = Integer.parseInt(parts[0]);
        int L = Integer.parseInt(parts[1]);
        int[] days = new int[M];
        for (int i = 0; i<M; ++i) days[i] = Integer.parseInt(parts[i+2]);
        int[][] almacen = new int[N][M];
        int pick=0, dont_pick=0;
        for (int i=0;i<N;i++) almacen[i][M-1] = days[M-1];
        for (int j=M-2;j>=0;--j){
            for (int i=0;i<N;++i){
                if (j+L >= M || i==N-1) pick = days[j];
                else pick = days[j] + almacen[i+1][j+L];
                dont_pick = almacen[i][j+1];
                almacen[i][j] = Math.max(pick, dont_pick);
            }
        }
        int i=0, j=0;
        while (true){
            if (j+1 < M && almacen[i][j] == almacen[i][j+1]){
                ++j;
            } else {
                solution.add(j+1);
                ++i;
                j = j+L;                
            }
            if (i>=N || j>=M) break;
        }
        return solution;
    }

}