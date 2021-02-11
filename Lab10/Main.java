public class Main {
    public static void main(String[] args) {
        System.out.println(translator(args[0].split("")));
    }

    public static String translator(String[] input) {
        Class str = String.class; 
        Stack<String> s = new Stack(input.length, str);
        String result = "";
        for(int i = 0; i < input.length; i++)
        {
            try{
                Character ch = input[i].charAt(0);
                String c = input[i];
                if(ch.equals('+') || ch.equals('-') || ch.equals('*') || ch.equals('/')) {
                    String x1 = s.pop();
                    String x2 = s.pop();
                    result = String.format("(%s%s%s)", x2, c, x1);
                    s.push(result);
                }
                else {
                    s.push(c);
                }
            }
            catch (StackOverflowException e) {

            }
            catch (StackUnderflowException e) {

            }
        }
        if(!s.isEmpty())
            System.out.print("BLAD DANYCH WEJSCIOWYCH! Koniec algorytmu a stos nie jest pusty:" + s);
        return result;
    }

}

