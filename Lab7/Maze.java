import java.util.Scanner;
import java.util.Random;

public class Maze {
    public static void main(String[] args) {
        char c;
        int nx = Integer.parseInt(args[0]);
        int ny = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        char[][] board = new char[nx][ny];
        boolean gameOn = true;
        int[] player = new int[2];
        
        player = setupBoard(board, nx, ny, p);

        info();
        
        while(gameOn) {
            printBoard(board, nx, ny);
            
            System.out.println();
            System.out.print("Wybierz opcje: ");
            
            Scanner sc = new Scanner(System.in);
            c = sc.next().charAt(0);
            
            
            System.out.println();
            
            for(Enums.Option option : Enums.Option.values())
            {
                if(option.getKey() == c)
                {    
                    CheckStep canMove = (b, pX, pY, dir) -> {
                        if(b[pX + dir.getX()][pY + dir.getY()] == 'X') return false;
                        else return true;
                    };
                    System.out.println(option);
                    
                    if(option == Enums.Option.EXIT)
                    {
                        gameOn = false;
                        break;
                    }

                    if(option == Enums.Option.RESET)
                    {
                        player = setupBoard(board, nx, ny, p);
                        break;
                    }

                    if(step(option.getDirection(), canMove, board, player[0], player[1]))
                    {
                        player[0] += option.getDirection().getX();
                        player[1] += option.getDirection().getY();
                    }

                    if(player[0] == nx/2 && player[1] == 0)
                    {
                        gameOn = false;
                        System.out.println("WYGRALES");
                    }
                }
            }

        }
    }

    @FunctionalInterface
    public interface CheckStep {
        boolean test(char[][] board, int i0, int j0, Enums.Direction dir);
    }

    /**
     * Moves the player mark 'o' if the move is viable
     * Returns true if the move was viable, false otherwise
     * @param dir move Direction enumerator
     * @param check lambda implementing CheckStep interface, used to avoid passing same arguments twice
     * @param board
     * @param pX player X location
     * @param pY player Y location
     * @return true/false
     */
    public static boolean step(Enums.Direction dir, CheckStep check, char[][] board, int pX, int pY) {
        boolean result;
        if(check.test(board, pX, pY, dir)) {
            board[pX][pY] = ' ';
            pX += dir.getX();
            pY += dir.getY();
            board[pX][pY] = 'o';
            result = true;
        }
        else {
            System.out.println("NIE UDALO SIE WYKONAC TAKIEGO RUCHU.");
            result = false;
        }
        return result;
    }

    /**
     * Prints out list of options available to the player
     */
    public static void info() {
        System.out.println("Dostepne opcje:");

        for(Enums.Option opt : Enums.Option.values())
            System.out.println(opt);

        System.out.println();
    }

    /**
     * Sets up the board for play
     * Returns vector of player starting coordinates
     * @param board
     * @param nx x dimension of the board
     * @param ny y dimension of the board
     * @param p probabilty of a field on the board being 'X'
     * @return int[2]{1, ny-2} player location
     */
    public static int[] setupBoard(char[][] board, int nx, int ny, double p) {
        Random rand = new Random();
        for(int x = 0; x < nx; x++)
        {
            for(int y = 0; y < ny; y++)
            {
                board[x][y] = ' ';
            }
        }
        
        for(int x = 0; x < nx; x++)
        {
            for(int y = 0; y < ny; y++)
            {
                if(x == 0 || y == 0 || x == nx-1 || y == ny-1)
                {
                    board[x][y] = 'X';
                }
                
                if(x == nx/2 && y == 0)
                {
                    board[x][y] = ' ';
                }
            }
        }
        
        for(int x = 1; x < nx-1; x++)
        {
            for(int y = 1; y < ny-1; y++)
            {
                if(rand.nextDouble() < p)
                {
                    board[x][y] = 'X';
                }
            }
        }

        board[1][ny-2] = 'o';

        return new int[]{1, ny-2};
    }
    
    /**
     * Prints the board out to the console
     * (0,0) coordinate is top left field
     * (nx-1, ny-1) is bottom right
     * y is vertical
     * x is horizontal
     * @param board
     * @param nx board x dimension
     * @param ny board y dimension
     */
    public static void printBoard(char[][] board, int nx, int ny) {
        for(int y = 0; y < ny; y++)
        {
            for(int x = 0; x < nx; x++)
            {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
    }
}