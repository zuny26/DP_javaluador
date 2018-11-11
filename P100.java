import java.util.ArrayList;

public class P100{

    public ArrayList<Integer> bestSolution(String data){
        String[] parts = data.split("\\p{Space}+");
        int N = Integer.parseInt(parts[0]);
        int L = Integer.parseInt(parts[1]);
        ArrayList<Integer>days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }
        int M = days.size();
        int[][] almacen = new int[N][M];
        ArrayList<Integer>solution = new ArrayList<>();
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
            if (almacen[i][j] == almacen[i][j+1]){
                j = j+1;
            } else {
                solution.add(j+1);
                i = i-1; 
                j = j+L;                
            }
            if (i<0 || j==days.size()) break;
        }
        return solution;
    }

    public static void main(String[] args) {
        P100 problem = new P100();
        String data = "3 2 1 2 4 7 3";
        ArrayList<Integer>solution = problem.bestSolution(data);
        System.out.println("Data: " + data);
        System.out.print("Solution: ");
        for (int x : solution) {
            System.out.print(x + " ");
        }
        System.out.print("\n\n\n");

        data = "2 5 11 2 12 6 19 10 13 2 16 1";
        solution = problem.bestSolution(data);
        System.out.println("Data: " + data);
        System.out.print("Solution: ");
        for (int x : solution) {
            System.out.print(x + " ");
        }
        System.out.print("\n\n\n");
    }
}