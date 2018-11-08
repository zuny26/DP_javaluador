import java.util.ArrayList;

public class P100{
    int N;
    int L; 
    public ArrayList<Integer>bestSolution(String data){
        String[] parts = data.split("\\p{Space}+");
        N = Integer.parseInt(parts[0]);
        L = Integer.parseInt(parts[1]);
        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 2; i<parts.length; i++){
            days.add(Integer.parseInt(parts[i]));
        }

        System.out.println("N: " + N);
        System.out.println("L: " + L);
        System.out.print("days: ");
        for (Integer i : days) {
            System.out.print(i + " ");
        }
        return null;

    }
    public static void main(String[] args) {
        P100 problem = new P100();
        problem.bestSolution("3 2 1 2 4 7 3");
    }
}