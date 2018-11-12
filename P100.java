package AA;

import java.util.ArrayList;

public class P100{

    public ArrayList<Integer> bestSolution(String data){
        ArrayList<Integer>solution = new ArrayList<>();
        String[] parts = data.split("\\p{Space}+");
        if (parts.length < 3) return solution;
        int N = Integer.parseInt(parts[0]);
        int L = Integer.parseInt(parts[1]);
        ArrayList<Integer>days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }
        int M = days.size();
        int[][] almacen = new int[N][M];
        int pick=0, dont_pick=0;
        for (int i=0;i<N;i++) almacen[i][M-1] = days.get(M-1);
        for (int j=M-2;j>=0;j--){
            for (int i=N-1;i>=0;i--){
                if (j+L >= M || i==0) pick = days.get(j);
                else pick = days.get(j) + almacen[i-1][j+L];
                dont_pick = almacen[i][j+1];
                almacen[i][j] = Math.max(pick, dont_pick);
            }
        }
        int i=N-1, j=0;
        while (true){
            if (j+1 < M && almacen[i][j] == almacen[i][j+1]){
                j = j+1;
            } else {
                solution.add(j+1);
                i = i-1; 
                j = j+L;                
            }
            if (i<0 || j>=M) break;
        }
        return solution;
    }

    public static void main(String[] args) {
        String data = "3 2 1 2 4 7 3";
        P100 problem = new P100();
        ArrayList<Integer>solution = problem.bestSolution(data);
        for(Integer x : solution){
            System.out.print(x + " ");
        }
    }

}