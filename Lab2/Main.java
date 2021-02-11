import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        BigInteger p = new BigInteger("397");
        BigInteger q = new BigInteger("103");
        BigInteger n = p.multiply(q);
        BigInteger fiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = q;

        BigInteger[] input = new BigInteger[100];
        int[] inputToInt = new int[100];

        int j = 0;
        int k = 0;
        int l = 0;

        System.out.println("Input To Integer:");
        for(char c : args[0].toCharArray())
        {
            inputToInt[j] = c;
            input[j] = new BigInteger(Integer.toString(inputToInt[j]));

            System.out.print(Integer.toString(input[j].intValue()) + " ");
            j++;
        }

        System.out.println();
        System.out.println("--------------------");

        BigInteger[] codedInput = new BigInteger[100];
        BigInteger[] decodedInput = new BigInteger[100];
        
        while(fiN.divide(BigInteger.TWO).compareTo(e) < 0)
        {
            if(e.gcd(fiN) == BigInteger.ONE)
            {
                break;
            }
            e = e.add(BigInteger.TWO);
        }
        
        BigInteger d = new BigInteger(Integer.toString(extendedEuclid(e.intValue(), fiN.intValue())));
             
        System.out.println("Coded Input:");
        for(char c : args[0].toCharArray())
        {
            codedInput[k] = input[k].modPow(e, n);

            System.out.print(codedInput[k].intValue() + " ");
            k++;
        }

        System.out.println();
        System.out.println("--------------------");

        System.out.println("Decoded Input:");
        for(char c : args[0].toCharArray())
        {
            decodedInput[l] = codedInput[l].modPow(d, n);

            System.out.print((char)decodedInput[l].intValue());
            l++;
        }

        System.out.println();

    }

    public static int extendedEuclid(int a, int b)
    {
        int x0 = 1;
        int x = 0;
        int b0 = b;
        int tmp; 
        int q;
        
        while(b != 0)
        {
            q = a/b;
            tmp = x;
            x = x0 - q*x;
            x0 = tmp;
            tmp = a%b;
            a = b;
            b = tmp;
        }

        if(x0 < 0)
            x0 = x0 + b0;

        return x0;
    }
    
}
