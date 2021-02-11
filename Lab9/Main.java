import java.lang.Math;
import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double result = 0.0;
        try{
            String split[] = splitArgs(args[0]);
            Class c = Math.class;
            
            try {
                if(split.length > 3)
                {
                    checkArgs();
                }
                else if(split.length > 2)
                {
                    Method m = c.getMethod(split[0], double.class, double.class);
                    double arg1 = Double.parseDouble(split[1]);
                    double arg2 = Double.parseDouble(split[2]);
                    result = (double)m.invoke(null, arg1, arg2);
                }
                else if(split.length > 1)
                {
                    Method m = c.getMethod(split[0], double.class);
                    double arg1 = Double.parseDouble(split[1]);
                    result = (double)m.invoke(null, arg1);
                }
                else
                {
                    checkArgs();
                }
    
                System.out.println("Wynik: " + result);
            }
            catch(NoSuchMethodException e) {
                System.out.println("Nie ma takiej metody!");
            }
            catch(InvocationTargetException e) {
                System.out.println(e.toString());
            }
            catch(IllegalAccessException e) {
                System.out.println(e.toString());
            }
            catch(NumberFormatException e) {
                System.out.println("Argumenty funkcji musza byc liczbami!");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Zla liczba argumentow funkcji! Podaj jedna lub dwie liczby.");
            }
            catch(NumberOfArgumentsException e) {
                System.out.println("Zla liczba argumentow funkcji! Podaj jedna lub dwie liczby.");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){ 
            System.out.println("Nie podano nic do obliczenia!");
        }
        
    }

    public static String[] splitArgs(String arg) {
        return Arrays.stream(arg.split("[\\s+(),]")).filter(w -> w.isEmpty() == false).toArray(String[]::new);
    }

    public static void checkArgs() throws NumberOfArgumentsException{
        throw new NumberOfArgumentsException();
    }
}

class NumberOfArgumentsException extends Exception {
    public NumberOfArgumentsException() {
        System.out.println("");
    }
}
