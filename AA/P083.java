package AA;

public class P083{
    public int best(String data){
        String[] parts = data.split("\\p{Space}+");
        int M = parts.length - 2;
        if (M==0) return 0;
        int N = Integer.parseInt(parts[0]);
        int L = Integer.parseInt(parts[1]);
        int[] days = new int[M];
        for (int i = 0; i<M; ++i) days[i] = Integer.parseInt(parts[i+2]);
        int[][] almacen = new int[N][M];
        int pick=0, dont_pick=0;
        for (int i=0;i<N;i++) almacen[i][M-1] = days[M-1];
        for (int j=M-2;j>=0;--j){
            for (int i=0;i<N;++i){
                if (j+L >= M || i==N-1) pick = days[j];
                else pick = days[j] + almacen[i+1][j+L];
                dont_pick = almacen[i][j+1];
                almacen[i][j] = Math.max(pick, dont_pick);
            }
        }
        return almacen[0][0];
    }

}