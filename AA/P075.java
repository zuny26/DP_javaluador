package AA; 

import java.util.ArrayList;
import java.util.Comparator;

public class P075{
    private int N; 
    private int goods[];
    private ArrayList<Integer> solution;
    private ArrayList<Integer> current;
    private int pesimistic;
    private int accumulated[];

    private int fi(int x1, int x2, int x3){
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    private int upperBound(int n, int x1, int x2, int x3){
        int y1=x1,y2=x2,y3=x3;
        int aux1=0,aux2=0,aux3=0;
        for (int i=0;i<N && i<=n; ++i){
            aux1 = fi(y1+goods[i], y2, y3);
            aux2 = fi(y1, y2+goods[i], y3);
            aux3 = fi(y2, y2, y3+goods[i]);
            int min = Math.min(aux1, Math.min(aux2, aux3));
            if (aux1 == min){
                y1 = aux1;
                solution.add(1);
            }
            else if (aux2 == min){
                y2 = aux2;
                solution.add(2);
            }
            else if (aux3 == min){
                y3 = aux3; 
                solution.add(3);
            }
        }
        return fi(y1, y2, y3);
    }

    private int lowerBound(int n, int x1, int x2, int x3){
        int y1 = Math.max(x1, Math.max(x2, x3));
        int y2 = Math.min(x1, Math.min(x2, x3));
        int y3 = (x1 + x2 + x3) - y1 - y2;
        int totalGap = (y1-y2) + (y1 - y3);
        if ( accumulated[n] > totalGap) return ( accumulated[n]-totalGap) % 3; 
        else return (totalGap -  accumulated[n])/2; 
    }
    private int gap(int n, int x1, int x2, int x3){
        // for (int x : current) System.out.print(x + " ");
        // System.out.println();
        if (n==-1){
            int result = fi(x1, x2, x3);
            if (result<pesimistic){
                pesimistic = result;
                solution = new ArrayList<>(current);
            }
            return result;
        }
        int optimistic = lowerBound(n, x1, x2, x3);
        if (optimistic >= pesimistic) return Integer.MAX_VALUE;
        current.set(n, 1);
        int option1 = gap(n-1, x1+goods[n], x2, x3);
        current.set(n, 2);
        int option2 = gap(n-1, x1, x2+goods[n], x3);
        current.set(n, 3);
        int option3 = gap(n-1, x1, x2, x3+goods[n]);
        return Math.min(option1, Math.min(option2, option3));
    }

    public ArrayList<Integer>bestSolution(String data){
        solution = new ArrayList<>();
        current = new ArrayList<>();
        String[] parts = data.split("\\p{Space}+");
        N = parts.length;
        goods = new int[N];
        accumulated = new int [N];
        for (int i=0;i<N;++i){
            goods[i] = Integer.parseInt(parts[i]);
            current.add(-1);
            if (i==0) accumulated[i] = goods[i];
            else accumulated[i] = goods[i] + accumulated[i-1];
        }
        pesimistic = upperBound(N, 0, 0, 0);
        
        int best = gap(N-1, 0, 0, 0);
        // for (int x : goods){
        //     System.out.println(x);
        // }
        // System.out.println(best);
        return solution;
    }
    public int GetGood(int i){
        return goods[i];
    }

    public static void main (String[] args){
        String data = "424 124 409 721 887 617 334 666 158 55";
        // String data = "10 9 6 3";
        // String data = "14 20 12";  
        P075 problem = new P075();
        ArrayList<Integer> solution = problem.bestSolution(data);
        for (int x : solution) System.out.print(x + " ");
        System.out.println();
        int x1=0, x2=0, x3=0;
        for (int i=0;i<solution.size();++i){
            if (solution.get(i)==1) x1 += problem.GetGood(i);
            else if (solution.get(i)==2) x2 +=  problem.GetGood(i);
            else if (solution.get(i)==3) x3 +=  problem.GetGood(i);
        }
        System.out.println("*******************************");
        System.out.println("1\t" + x1);
        System.out.println("2\t" + x2);
        System.out.println("3\t" + x3);
        System.out.println("Fi\t" + (Math.max(x1, Math.max(x2, x3))-Math.min(x1, Math.min(x2, x3))));
    }
}