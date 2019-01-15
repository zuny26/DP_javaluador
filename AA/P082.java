package AA;

import java.util.ArrayList;

public class P082{

    private int N;
    private int tasks[][];
    private int almacen[][];

    public int best(String[] data){
        N = data[0].split("\\p{Space}+").length; // number of tasks
        tasks = new int [N][3];
        for (int i=0;i<3;i++){
            String[] aux = data[i].split("\\p{Space}+");
            for (int j=0;j<N;j++){
                tasks[j][i] = Integer.parseInt(aux[j]);
            }
        }
        int M = tasks[N-1][2];
        int coger=0, no_coger=0;
        boolean posible = true;
        almacen = new int[2][M+1];
        for (int j=0;j<=M && posible;++j){
            posible = tasks[N-1][2]-j>=tasks[N-1][0];
            if(posible) almacen[1][j] = tasks[N-1][1];
        }
        for (int i=N-2; i>=0; --i){
            posible =true;
            for (int j=0;j<=M;++j){
                if (posible) posible = tasks[i][2]-j >= tasks[i][0];
                no_coger = almacen[1][j];
                coger = (posible) ? tasks[i][1] + almacen[1][j+tasks[i][0]] : 0;
                almacen[0][j] = Math.max(coger, no_coger);
            }
            for (int j=0;j<=M;++j){
                almacen[1][j] = almacen[0][j];
            }
        }
        return almacen[0][0];
    }

    public static void main(String[] args) {
        String[] data = {"8 2 10", "35 25 75", "9 10 10"};
        // String[] data = {"7 16 19 17 28 6 22 14 12 12", "26 9 94 14 1 15 49 96 6 27", "2 9 10 16 36 53 69 85 86 86"};
        // String[] data = {"15 16 14", "40 41 25", "25 25 29"};
        P082 problem = new P082();
        System.out.println(problem.best(data));   
    }
}