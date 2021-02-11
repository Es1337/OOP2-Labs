import java.util.Random;

public class Maze {
    public static void main(String[] args) {
        // INPUTINTEGER
        String[] str = new String[]{""};
        new InputInteger().main(str);

        // MAZE
        char c;
        int nx = Integer.parseInt(args[0]);
        int ny = Integer.parseInt(args[1]);
        double p = 0.3;
        char[][] board = new char[nx][ny];
        boolean gameOn = true;
        int[] player = new int[2];
        int[] moveVector;
        SystemInWrapper input = new SystemInWrapper();
    
        player = setupBoard(board, nx, ny, p);
        while(gameOn) {
            printBoard(board, nx, ny);
            try {
                c = input.sc.next().charAt(0);
                moveVector = pickOption(c);

                CheckStep canMove = (b, pX, pY, dir) -> {
                    if(b[pX + dir[0]][pY + dir[1]] == 'X') return false;
                    else return true;
                };

                if(moveVector[0] == -1 && moveVector[1] == -1)
                {
                    gameOn = false;
                    continue;
                }

                step(moveVector, canMove, board, player[0], player[1]);

                    player[0] += moveVector[0];
                    player[1] += moveVector[1];
            }
            catch(OptionNotRecognizedException onre) {
                input.sc.nextLine();
            }
            catch(WallException we) {
                input.sc.nextLine();
            }
        }
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

    /**
     * Picks the correct move direction based on player input 
     * @param c input character 
     * @return vector containing player move direction,
     *  special case is 'q' where it returns {-1,-1}
     * @throws OptionNotRecognizedException if input was not recognized
     */
    public static int[] pickOption(char c) throws OptionNotRecognizedException{
        int[] moveVector = new int[]{0, 0};
        switch(c) {
            case 'a':
                moveVector = new int[]{-1, 0};
                break;
            case 'd':
                moveVector = new int[]{1, 0};
                break;
            case 'w':
                moveVector = new int[]{0, -1};
                break;
            case 's':
                moveVector = new int[]{0, 1};
                break;
            case 'q':
                moveVector = new int[]{-1, -1};
                break;
            default:
                throw new OptionNotRecognizedException("Wpisano nierozpoznany znak");
            
        }
        return moveVector;
    }

    /**
     * Moves the player mark 'o' if the move is viable
     * @param moveVector
     * @param check lambda implementing CheckStep interface, used to avoid passing same arguments twice
     * @param board
     * @param pX player X location
     * @param pY player Y location
     * @throws WallException if it hits a wall in the spot where the player tried to move 
     */
    public static void step(int[] moveVector, CheckStep check, char[][] board, int pX, int pY) throws WallException{
        if(check.test(board, pX, pY, moveVector)) {
            board[pX][pY] = ' ';
            pX += moveVector[0];
            pY += moveVector[1];
            board[pX][pY] = 'o';
            
        }
        else {
            throw new WallException("NIE UDALO SIE WYKONAC TAKIEGO RUCHU.");
        }
    }

    @FunctionalInterface
    public interface CheckStep {
        boolean test(char[][] board, int i0, int j0, int[] moveVector);
    }
}

