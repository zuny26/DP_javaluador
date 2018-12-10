package AA;

import java.util.ArrayList;

public class P101{

    public ArrayList<Integer> bestSolution(String[] data){
        ArrayList<Integer> solution = new ArrayList<>();
        int K = data.length;
        int p = data[0].split("\\p{Space}+").length;
        int current_best = 0;
        int[][] prices = new int[K][p+1];
        int[][] optimista = new int[K][p-1];
        for (int i = 0; i < K; i++){
            String[] parts = data[i].split("\\p{Space}+");
            prices[i][0] = 0; // no visitado
            for (int j = 1; j<=p; j++){
                prices[i][j] = Integer.parseInt(parts[j-1]);
            }
        }
        // método voraz:
        int product_best = Integer.MAX_VALUE;
        for (int j=2; j<=p; j++){
            product_best = Integer.MAX_VALUE;
            int trip = 0;
            int k = 0;
            for (int i = 0; i<K; i++){
                if( prices[i][j] < product_best) {
                    product_best = prices[i][j]; 
                    k = i;
                }
            }
            if (prices[k][0] == 0){
                trip += prices[k][1];
                prices[k][0] = 1;
            }
            solution.add(k+1);
            current_best += product_best + trip;
        }
        
        for (int j=p-2; j>=0; j--){
            for (int i=0; i<K; i++){
                if (j==p-2) optimista[i][j] = prices[i][j+2];
                else {
                    product_best = Integer.MAX_VALUE;
                    for (int k=0; k<K; k++){
                        if (optimista[k][j+1] < product_best) product_best = optimista[k][j+1];
                    }
                    optimista[i][j] = prices[i][j+2] + product_best;
                }
            }
        }
        // for (int i=0; i<K; i++){
        //     for (int j=0;j<p-1; j++){
        //         System.out.print(optimista[i][j]+ " ");
        //     }
        //     System.out.println();
        // }
        int current = 0;
        ArrayList<Integer>path = new ArrayList<>();
        Boolean finished = false; 
        int i = 0; 
        int j = 2; 
        int aux = 0; 
        // ramificación y poda:
        while (!finished){
            if(i<K){
                current = current + prices[i][j] + (!path.contains(i+1) ? prices[i][1] : 0);
                path.add(i+1);
                if (j==p && current < current_best) {
                    current_best = current;
                    solution = new ArrayList<Integer>(path);
                }
                if (j<p) aux = optimista[i][j-2]-prices[i][j];
                else aux = 0;
                j++;
            }
            if (i>=K || aux +current > current_best || j>p){
                j--;
                if (j<2 && i>=K) finished = true;
                else{
                    i = path.get(path.size()-1)-1;
                    path.remove(path.size()-1);
                    current = current - prices[i][j] - (!path.contains(i+1) ? prices[i][1] : 0);
                    i++;
                }   
            } else i=0;
        }
        // System.out.println("Current best: " + current_best);
        return solution;
    }
    public static void main(String[] args) {
        String[] data = {"10 4 4 99 4 4", "11 5 5 5 5 5"};
        P101 problem = new P101();
        ArrayList<Integer> solution = problem.bestSolution(data);
        for (Integer x : solution){
            System.out.print(x + " ");
        }
    }
    
}