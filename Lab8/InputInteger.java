import java.util.InputMismatchException;

public class InputInteger {
    public static void main(String[] args) {
        SystemInWrapper input = new SystemInWrapper();
        boolean condition = true;
        
        while(condition) {
            System.out.println("> Podaj liczbe calkowita wieksza od 1:");
            try {
                int i = input.sc.nextInt();
                compare(i);
                condition = false;
            }
            catch(InputMismatchException ime) {
                System.out.println("BLAD: Podaj liczbe calkowita!");
                input.sc.nextLine();
            }
            catch(LessThanOneException ltoe){
                System.out.println("BLAD: " + ltoe.info);
                input.sc.nextLine();
            }
        }
    }

    public static void compare(int i) throws LessThanOneException {
        if(i < 2)
        {
            throw new LessThanOneException(i);
        }
    }

}

class LessThanOneException extends Exception{
    public String info;
    public LessThanOneException(int i) {
        info = "Zbyt mała wartosc nx: " + i;
    }
}