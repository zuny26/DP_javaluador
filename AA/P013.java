package AA;

import java.util.ArrayList;


public class P013{

    public ArrayList<Integer> bestSolution(String[] data){
        ArrayList<Integer> current = new ArrayList<>();
        ArrayList<Integer> mlist = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        int[][] cruces = new int[data.length][2];
        int M = data.length;
        int N = 0;
        for(int i=0;i<M;++i){
            for (int j=0;j<2;++j){
                cruces[i][j] = Integer.parseInt(data[i].split("\\p{Space}+")[j]);
                if (cruces[i][j]>N) N = cruces[i][j];
            }
        }
        for (int i=0;i<M;++i){
            if (!solution.contains(cruces[i][0]) && !solution.contains(cruces[i][1])) solution.add(cruces[i][1]);
        }
        if (solution.size()==1) return solution;
        boolean visited[] = new boolean[N+1];
        int m=0; 
        int j=0;
        int last=0;
        int node; 
        boolean light = true;
        while(current.size()>0 || m<M){
            if (current.size()+1>=solution.size() || m>=M || j>1){
                if (current.size()==0) break;
                last=current.remove(current.size()-1);
                m = mlist.remove(mlist.size()-1);
                visited[last] = false;
                if(last==cruces[m][0]) j=1;
                else if (last==cruces[m][1]) j=2;
                continue;
            }
            if (!visited[cruces[m][0]] && !visited[cruces[m][1]]){
                node = cruces[m][j];
                visited[node]=true;
                current.add(node);
                mlist.add(m++);
            } else m++;
            light = true;
            j=0;
            // for(int x : current) System.out.print(x + " ");
            // System.out.println();
            for (int i=0;i<M && light;++i)
                if (!current.contains(cruces[i][0]) && !current.contains(cruces[i][1])) light = false;
            if (light) solution = new ArrayList<>(current);
        }
        
        return solution;
    }

    public static void main(String[] args) {
        P013 problem = new P013();
        // String[] data = {"0 1", "1 2", "2 3"};
        // String[] data = {"0 1", "0 2", "0 3", "0 4"};
        // String[] data = {"0 1", "1 2", "2 3", "3 4"};
        String[] data = {"0 1", "1 2", "2 4", "2 3", "4 7", "7 5", "7 6", "3 8", "4 1", "2 6", "6 8", "1 3", "9 1", "9 7", "10 1", "7 10"};
        ArrayList<Integer> solution = problem.bestSolution(data);
        for (int x : solution){
            System.out.print(x + " ");
        }
    }
}