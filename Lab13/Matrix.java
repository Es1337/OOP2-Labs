public class Matrix {
    private final int rows;
    private final int columns;
    private final double m[][];

    public Matrix(int _rows, int _columns, double[][] _values)
    {
        rows = _rows;
        columns = _columns;
        m = _values;
    }
    
    public Matrix(Matrix other)
    {
        rows = other.rows;
        columns = other.columns;
        m = other.m;
    }
    
    public double[][] getM()
    {
        return m;
    }

    public Matrix multiply(Matrix other)
    {
        double[][] newValues = new double[rows][other.columns];
        Matrix result = new Matrix(rows, other.columns, newValues);
        if(this.columns == other.rows)
        {
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < other.columns; j++)
                {
                    result.m[i][j] = getCell(this, other, i, j);
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = true;

        if(columns != ((Matrix)obj).columns)
            result = false;
        else if(rows != ((Matrix)obj).rows)
            result = false;
        else
        {
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < columns; j++)
                {
                    if(this.m[i][j] != ((Matrix)obj).m[i][j])
                        result = false;
                }
            }
        }
        
        return result;
    }

    @Override
    public String toString()
    {
        String result = "";
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                result += String.format("[%f] ", m[i][j]);
            }
            result += "\n-----------------------------\n";
        }
        return result;
    }

    public static double getCell(Matrix a, Matrix b, int row, int col)
    {
        double cell = 0.0;
        for(int i = 0; i < b.rows; i++)
        {
            cell += a.m[row][i] * b.m[i][col];
        }
        return cell;
    } 
}

class CellMultiplicator implements Runnable 
{
    private Matrix result;
    private Matrix m1;
    private Matrix m2;
    private final int row;
    private final int col;

    public CellMultiplicator(Matrix m1, Matrix m2, Matrix result, int row, int col)
    {
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;
        this.row = row;
        this.col = col;
    }

    @Override
    public void run()
    {
        result.getM()[row][col] = Matrix.getCell(m1, m2, row, col);
    }
}
