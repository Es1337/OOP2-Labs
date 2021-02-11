import java.util.Arrays;
import java.util.Random;

class Main {
    
    public void test() {
        Random random = new Random();
        long a;
        long b;
        long reference;

        BigInt num1;
        BigInt num2;

        for(int i = 0; i < 1000; i++)
        {    
            while(true)
            {
                a = random.nextLong();
                b = random.nextLong();
                
                
                if(a < Long.MAX_VALUE/2 && b < Long.MAX_VALUE/2)
                {
                    break;
                }
                
                if(a > 0 && b > 0)
                {
                    break;
                }
            }
            reference = a+b;
            
            num1 = new BigInt(a+"");
            num2 = new BigInt(String.valueOf(b).getBytes());
            
            if(BigInt.add(num1, num2).equals(new BigInt(reference+"")))
            {
                System.out.println(i+1 + " equals");
            }
            else
            {
                try
                {
                    throw new Exception(i+1 + " not equals");
                }
                catch(Exception e)
                {
                    System.out.println(BigInt.add(num1, num2) + " " + new BigInt(reference+""));
                    System.out.println(e);
                }
            }
        }
        
        num1 = new BigInt(1L+"");
        num2 = new BigInt(999999999999999999L+"");
        reference = 1L + 999999999999999999L;

        if(BigInt.add(num1, num2).equals(new BigInt(reference+"")))
        {
            System.out.println(1001 + " equals");
        }
        else
        {
            try
            {
                throw new Exception(1001 + " not equals");
            }
            catch(Exception e)
            {
                System.out.println(BigInt.add(num1, num2) + " " + new BigInt(reference+""));
                System.out.println(e);
            }
        }

        num2 = new BigInt(1L+"");
        num1 = new BigInt(999999999999999999L+"");

        if(BigInt.add(num1, num2).equals(new BigInt(reference+"")))
        {
            System.out.println(1002 + " equals");
        }
        else
        {
            try
            {
                throw new Exception(1002 + " not equals");
            }
            catch(Exception e)
            {
                System.out.println(BigInt.add(num1, num2) + " " + new BigInt(reference+""));
                System.out.println(e);
            }

        }
    }
    public static void main(String[] args) {
        new Main().test();
    }
}

class BigInt {
    private final byte[] num;
    
    public BigInt(String number) {
        num = String.valueOf(number).getBytes();
    }
    
    public BigInt(byte[] number) {
        int length = number.length;
        num = Arrays.copyOf(number, length);
    }
    
    public BigInt(BigInt number) {
        int length = number.num.length;
        num = Arrays.copyOf(number.num, length);
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if (!(o instanceof BigInt)) { 
            return false; 
        } 

        BigInt number = (BigInt)o;

        if(this.getNum().length != number.getNum().length)
            return false;
        
        int index = 0;
        
        while(index < this.getNum().length)
        {
            if(this.getNum()[index] != number.getNum()[index])
                return false;
            
            index++;
        }
        
        return true;
    }
    
    public String toString() {
        return new String(this.getNum());
    }
    
    public byte[] getNum() {
        return num;
    }
    
    public static BigInt add(BigInt num1, BigInt num2) {
        long val1 = Long.parseLong(new String(num1.getNum()));
        long val2 = Long.parseLong(new String(num2.getNum()));

        long sum = val1 + val2;

        return new BigInt(sum+"");
    }
}