package AA; 

import java.util.ArrayList;

public class P002 {

    public String sol (String data){
        int [][] tablero = new int[9][9];
        Boolean [][] tablero_correcto = new Boolean [9][9];
        String[] line = data.split("\n");
        for(int i=0; i<line.length; ++i){
            String s = line[i];
            for (int j=0;j<line[i].length();++j){
                int num = Character.getNumericValue(s.charAt(j));
                tablero[i][j] = num;
                tablero_correcto[i][j] = false;
                if (num!=0) tablero_correcto[i][j] = true;
            }
        }
        int k = 0; 
        int i = 0, j=0, c=0, n=0;
        int dir = 1; 
        while (k < 81){
            i = k/9;
            j = k - i*9;
            c = i/3; 
            c = c*3; 
            c = c+j/3; 
            if (!tablero_correcto[i][j]){
                if (tablero[i][j]==9) {
                    k--;
                    dir = -1; 
                    tablero[i][j]=0;
                }
                else {
                    n = ++tablero[i][j];
                    Boolean correct = true; 
                    for (int aux = 0; aux <9; aux++){
                        if(aux != i && tablero[aux][j]==n) correct=false;
                        if(aux != j && tablero[i][aux]==n) correct=false;
                    }
                    int i1 = (c/3)*3;
                    int j1 = (c-i1)*3;
                    for (int auxi=i1;auxi<i1+3;auxi++){
                        for (int auxj=j1;auxj<j1+3;auxj++){
                            if(!(auxi==i && auxj==j) && tablero[auxi][auxj]==n) correct = false;
                        }
                    }
                    if (correct){
                        k++;
                        dir = 1;
                    }
                }
            }
            else k = k+dir; 
        }
        String solution = "";
        for (i = 0; i<9; i++){
            for (j = 0; j<9; j++){
                solution += tablero[i][j];
            }
            solution += "\n";
        }
        return solution;
    }

    public static void main(String[] args) {
        String data = "009005070\n081600000\n500001008\n050100000\n010807040\n000004010\n700400001\n000009320\n060200700";
        // String data =   "007006500\n000020800\n068053400\n830000074\n004308000\n000500382\n001000009\n350604008\n082009005";
        P002 problem = new P002();
        System.out.print(problem.sol(data));
    }
}