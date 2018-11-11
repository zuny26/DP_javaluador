import java.util.ArrayList;

public class P100{
    int N;
    int L; 
    ArrayList<Integer>days;
    int[][] almacen;

    public int f(int i, int n){
        // System.out.println("Call: i=" + i + " n=" + n);
        if (n<0 || i>=days.size()) return 0;
        else if (this.almacen[n][i] != -1) return this.almacen[n][i];
        this.almacen[n][i] = Math.max(days.get(i) + f(i+L, n-1), f (i+1, n));
        return this.almacen[n][i];
    }

    public Integer bestSolutionSimplePDRA(String data){
        String[] parts = data.split("\\p{Space}+");
        
        N = Integer.parseInt(parts[0]);
        L = Integer.parseInt(parts[1]);
        days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }
        this.almacen = new int[N][days.size()];
        for (int i=0;i<N;i++){
            for (int j=0;j<days.size();j++){
                this.almacen[i][j] = -1;
            }
        }
        return f(0, N-1);
    }

    public Integer bestSolutionSimplePDI(String data){
        String[] parts = data.split("\\p{Space}+");
        
        N = Integer.parseInt(parts[0]);
        L = Integer.parseInt(parts[1]);
        days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }
        this.almacen = new int[N][days.size()];
        int pick=0, dont_pick=0;
        for (int i=0;i<N;i++) almacen[i][days.size()-1] = days.get(days.size()-1);
        for (int j=days.size()-2;j>=0;j--){
            for (int i=N-1;i>=0;i--){
                if (j+L >= days.size() || i==0) pick = days.get(j);
                else pick = days.get(j) + almacen[i-1][j+L];
                dont_pick = almacen[i][j+1];
                almacen[i][j] = Math.max(pick, dont_pick);
            }
        }
        return almacen[N-1][0];
    }

    public int value(ArrayList<Integer>positions){
        int v = 0;
        for (Integer i : positions) {
            v += days.get(i-1);
        }
        return v;
    }

    public ArrayList<Integer> f(ArrayList<Integer>positions, int i){ 
        if (i>days.size() || positions.size()>=N) return positions; 
        else {
            ArrayList<Integer>dont_pick = new ArrayList<>(f(positions, i+1));
            positions.add(i);
            ArrayList<Integer>pick =  new ArrayList<>(f(positions, i+L));
            int value_dont_pick = value(dont_pick);
            int value_pick = -1; 
            if (!pick.equals(positions)) value_pick = days.get(i-1) + value(pick);
            if (value_pick >= value_dont_pick) return pick; 
            else {
                positions.remove(positions.size()-1);
                return dont_pick;
            }
        }
    }

    // public ArrayList<Integer>bestSolution(String data){
    //     String[] parts = data.split("\\p{Space}+");
    //     N = Integer.parseInt(parts[0]);
    //     L = Integer.parseInt(parts[1]);
    //     ArrayList<Integer> days = new ArrayList<>();
    //     for (int i = 2; i<parts.length; i++){
    //         days.add(Integer.parseInt(parts[i]));
    //     }
    //     ArrayList<Integer>solution = new ArrayList<>();
    //     return f(solution, 1);
    // }

    public static void main(String[] args) {
        P100 problem = new P100();
        int solution = problem.bestSolutionSimplePDI("3 2 1 2 4 7 3");
        System.out.println("Solucion simple: " + solution);

        // ArrayList<Integer> complete_solution = problem.bestSolution("3 2 1 2 4 7 3");
        // System.out.print("Solucion completa: ");
        // for (Integer i : complete_solution) {
        //     System.out.print(i + " ");
        // }
        
        solution = problem.bestSolutionSimplePDI("2 5 11 2 12 6 19 10 13 2 16 1");
        System.out.println("\nSolucion simple: " + solution);
    }
}