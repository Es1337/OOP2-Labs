import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        try{
            checkInput(args);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        int N, M, P;
        
        try {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
            P = Integer.parseInt(args[2]);
            
            double[][] valuesA = new double[N][M];
            double[][] valuesB = new double[M][P];
            
            List threads = new ArrayList<>();
            Random rand = new Random();
            
            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < M; j++)
                {
                    valuesA[i][j] = rand.nextDouble();
                }
            }
            
            for(int i = 0; i < M; i++)
            {
                for(int j = 0; j < P; j++)
                {
                    valuesB[i][j] = rand.nextDouble();
                }
            }
            
            Matrix A = new Matrix(N, M, valuesA);
            Matrix B = new Matrix(M, P, valuesB);
            
            Matrix C = new Matrix(A.multiply(B));
            
            Matrix D = new Matrix(N, P, new double[N][P]);
            
            System.out.println("Matrix C :\n" + C.toString());
            
            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < P; j++)
                {
                    CellMultiplicator task = new CellMultiplicator(A, B, D, i, j);
                    Thread thread = new Thread(task);
                    thread.start();
                    threads.add(thread);
                    
                    if(threads.size() % 8 == 0)
                    {
                        wait(threads);
                    }
                }
            }
            
            System.out.println("Matrix D :\n" + D.toString());
            
            System.out.println("Are they equal :\n" + C.equals(D));

        }
        catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
        }
        
    }

    /**
     * Waits for threads to finish
     * Handles Interrupted Exception
     * @param threads List of threads
     */
    private static void wait(List<Thread> threads)
    {
        for(Thread thread : threads)
        {
            try{
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        threads.clear();
    }

    /**
     * Checks if input has enough arguments
     * if not throws exception
     * @param args input to Main
     * @throws Exception generic exception thrown
     */
    private static void checkInput(String[] args) throws Exception{
        if(args.length < 3)
            throw new Exception("Not enough arguments");
    }
}