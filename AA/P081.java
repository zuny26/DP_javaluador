package AA;

public class P081{

    private int N;
    private int tasks[][];
    private int almacen[][];

    private int best(int day, int index){
        int best = 0; 
        if (almacen[index][day]!=0){
            best = almacen[index][day];
            // System.out.println("ALMACEN:");
        }
        else if (index+1==N){
            best = (tasks[index][2]-day >= tasks[index][0]) ? tasks[index][1] : 0;
            almacen[index][day] = best;
        }
        else {
            boolean possible = tasks[index][2]-day >= tasks[index][0];
            int coger = possible ? tasks[index][1] + best(day+tasks[index][0], index+1) : 0;
            int no_coger = best(day, index+1);
            best= Math.max(coger, no_coger);
            almacen[index][day] = best;
        }
        // System.out.println("day " + day + "\tindex " + index + "\tbest " + best);
        return best;
    }

    public int best(String[] data){
        N = data[0].split("\\p{Space}+").length; // number of tasks
        tasks = new int [N][3];
        int M = 0; // latest deadline
        for (int i=0;i<3;i++){
            String[] aux = data[i].split("\\p{Space}+");
            for (int j=0;j<N;j++){
                tasks[j][i] = Integer.parseInt(aux[j]);
                if (i==2 && tasks[j][2]>M) M = tasks[j][2];
            }
        }
        almacen = new int[N][M+1];
        return best(0, 0);
    }

    public static void main(String[] args) {
        // String[] data = {"8 2 10", "35 25 75", "9 10 10"};
        // String[] data = {"7 16 19 17 28 6 22 14 12 12", "26 9 94 14 1 15 49 96 6 27", "2 9 10 16 36 53 69 85 86 86"};
        String[] data = {"15 16 14", "40 41 25", "25 25 29"};
        P081 problem = new P081();
        System.out.println(problem.best(data));   
    }
}