import java.util.ArrayList;

public class P100{
    int N;
    int L; 
    ArrayList<Integer>days;

    public int f(int i, int n){
        // System.out.println("Call: i=" + i + " n=" + n);
        if (n==0 || i>=days.size()) return 0; 
        else return Math.max(days.get(i-1) + f(i+L, n-1), f(i+1, n));
    }

    public Integer bestSolutionSimple(String data){
        String[] parts = data.split("\\p{Space}+");
        N = Integer.parseInt(parts[0]);
        L = Integer.parseInt(parts[1]);
        days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }
        return f(1, N);
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
        int solution = problem.bestSolutionSimple("3 2 1 2 4 7 3");
        System.out.println("Solucion simple: " + solution);

        ArrayList<Integer> complete_solution = problem.bestSolution("3 2 1 2 4 7 3");
        System.out.print("Solucion completa: ");
        for (Integer i : complete_solution) {
            System.out.print(i + " ");
        }
        
        solution = problem.bestSolutionSimple("2 5 11 2 12 6 19 10 13 2 16 1");
        System.out.println("\nSolucion: " + solution);
    }
}