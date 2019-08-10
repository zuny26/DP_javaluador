package AA; 

import java.util.ArrayList;

public class P072{
    private int M1; 
    private int M2;
    private int N;
    private int pedidos[][];

    public int best(int m1, int m2, int i){
        if (i==N) return 0;
        int furgo1 = (pedidos[i][0] > m1) ? 0 : pedidos[i][1] + best(m1-pedidos[i][0], m2, i+1);
        int furgo2 = (pedidos[i][0] > m2) ? 0 : pedidos[i][1] + best(m1, m2-pedidos[i][0], i+1);
        int no_coger = best(m1, m2, i+1);
        return Math.max(no_coger, Math.max(furgo1, furgo2));
    }

    public int best(String[] data){
        String pesos[] = data[0].split("\\p{Space}+");
        String valores[] = data[1].split("\\p{Space}+");
        String furgonetas[] = data[2].split("\\p{Space}+");
        M1 = Integer.parseInt(furgonetas[0]);
        M2 = Integer.parseInt(furgonetas[1]);
        N = pesos.length; 
        pedidos = new int[N][2];
        int lowerBound[] = new int[N];
        for (int i=0;i<N;++i){
            pedidos[i][0] = Integer.parseInt(pesos[i]);
            pedidos[i][1] = Integer.parseInt(valores[i]);
            if (i==0) lowerBound[i] = pedidos[i][1];
            else lowerBound[i] = pedidos[i][1] + lowerBound[i-1];
        }
        int m1=M1, m2=M2;
        ArrayList<Integer>current = new ArrayList<>();
        ArrayList<Integer>solution = new ArrayList<>();
        int upperBound = 0; 
        for (int i=0; i<N; ++i){
            if (pedidos[i][0] <= m1){
                upperBound += pedidos[i][1];
                m1 = m1 - pedidos[i][0];
                solution.add(1);
            }
            else if (pedidos[i][0] <= m2){
                upperBound += pedidos[i][1]; 
                m2 = m2 - pedidos[i][0];
                solution.add(2);
            } else solution.add(0);
            current.add(-1);
        }
        int i = N-1; 
        int d = 2; 
        int m[] = new int [2]; 
        m[0] = M1;
        m[1] = M2; 
        int currentValue = 0; 
        boolean posible = true; 
        while (!(i+1>=N && d<0)){
            if (d<0 || i<0 || currentValue + lowerBound[i] <= upperBound ){
                d = current.remove(i+1) - 1; 
                if (d+1!=0){
                    currentValue = currentValue - pedidos[i+1][1]; 
                    m[d] += pedidos[i+1][0];
                }
                i++;
            }
            else {
                posible = d==0 ? true : pedidos[i][0] <= m[d-1];
                if (!posible) d--;
                else {
                    current.add(i, d);
                    currentValue += (d==0) ? 0 : pedidos[i][1];
                    if (d!=0) m[d-1] -= pedidos[i][0];
                    d=2;
                    if (i==0 && currentValue > upperBound){
                        upperBound = currentValue;
                        solution = new ArrayList<>(current);
                    }
                    i--;
                }
            }
        }
        return upperBound;
    }
    public static void main(String[] args) {
        String data[] = {"450 1200 4096 2700", "30 40 50 60", "2500 5000"};
        // String data[] = {"5 3 7", "1 2 3", "5 7"};
        P072 problem = new P072();
        System.out.println(problem.best(data));
    }
}