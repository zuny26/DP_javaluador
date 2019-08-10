package AA; 

public class P074{
    private int N; 
    private int goods[];
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
            }
            else if (aux2 == min){
                y2 = aux2;
            }
            else if (aux3 == min){
                y3 = aux3; 
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
        if (n==0){
            int result = Math.min(fi(x1+goods[0], x2, x3), Math.min(fi(x1, x2+goods[0], x3), fi(x1, x2, x3+goods[0])));
            if (result<pesimistic) pesimistic = result; 
            return result;
        }
        int option1 = (lowerBound(n-1, x1+goods[n], x2, x3) >= pesimistic) ? Integer.MAX_VALUE : gap(n-1, x1+goods[n], x2, x3);
        int option2 = (lowerBound(n-1, x1, x2+goods[n], x3) >= pesimistic) ? Integer.MAX_VALUE : gap(n-1, x1, x2+goods[n], x3);
        int option3 = (lowerBound(n-1, x1, x2, x3+goods[n]) >= pesimistic) ? Integer.MAX_VALUE : gap(n-1, x1, x2, x3+goods[n]);
        return Math.min(option1, Math.min(option2, option3));
    }

    public int best(String data){
        String[] parts = data.split("\\p{Space}+");
        N = parts.length;
        goods = new int[N];
        accumulated = new int [N];
        for (int i=0;i<N;++i){
            goods[i] = Integer.parseInt(parts[i]);
            if (i==0) accumulated[i] = goods[i];
            else accumulated[i] = goods[i] + accumulated[i-1];
        }
        pesimistic = upperBound(N, 0, 0, 0);
        int best = gap(N-1, 0, 0, 0);
        return best;
    }

    public static void main (String[] args){
        String data = "424 124 409 721 887 617 334 666 158 55";
        // String data = "10 9 6 3";
        // String data = "14 20 12";  
        // String data = "100 99 2 10 10 10 10 10 10 10 10 10 15"; 
        P074 problem = new P074();
        int best = problem.best(data);
        System.out.println(best);
    }
}