class Main {
    public void test() {
        // Wykorzystanie konstruktorów:
        Complex c1 = new Complex(2.5, 13.1);
        Complex c2 = new Complex(-8.5, -0.9);
        System.out.println(c1); // 2.5 + 13.1i
        System.out.println(c2); // -8.5 - 0.9i
        System.out.println(new Complex(4.5)); // 4.5
        System.out.println(new Complex()); // 0.0
        System.out.println(new Complex(0, 5.1)); // 5.1i
        System.out.println();

        // Stałe typu Complex:
        System.out.println(Complex.I); // 1.0i
        System.out.println(Complex.ZERO); // 0.0
        System.out.println(Complex.ONE); // 1.0
        System.out.println();

        // Wykorzystanie metod zwracających wynik obliczeń:
        System.out.println("Re(c1) = " + c1.getRe()); // Re(c1) = 2.5
        System.out.println("Im(c1) = " + c1.getIm()); // Im(c1) = 13.1
        System.out.println("c1 + c2 = " + Complex.add(c1, c2)); // c1 + c2 = -6.0 + 12.2i
        System.out.println("c1 - c2 = " + Complex.subtract(c1, c2)); // c1 - c2 = 11.0 + 14.0i
        System.out.println("c1 * c2 = " + Complex.multiply(c1, c2)); // c1 * c2 = -9.46 - 113.6i
        System.out.println("c1 * 15.1 = " + Complex.multiply(c1, 15.1)); // c1 * 15.1 = 37.75 + 197.81i
        System.out.println("c1 / c2 = " + Complex.divide(c1, c2)); // c1 / c2 = -0.4522310429783739 - 1.4932931836846426i
        System.out.println("|c1| = " + c1.mod()); // |c1| = 13.336416310238668
        System.out.println("sqrt(243.36) = " + Complex.sqrt(243.36)); // sqrt(243.36) = 15.6
        System.out.println("sqrt(-243.36) = " + Complex.sqrt(-243.36)); // sqrt(-243.36) = 15.6i
        Complex c3 = new Complex(2.5, 13.1);
        System.out.println(c1.equals(c2)); // false
        System.out.println(c1.equals(c3)); // true
        // Poniższe wywołanie - dla chętnych :)
        // System.out.println(c1.equals("test ze zlym obiektem")); // false
        System.out.println();

        // Metoda zamieniająca liczbę na jej sprzężenie:
        c1.conjugate();
        System.out.println("c1* = " + c1); // c1* = 2.5 - 13.1i

        // Metoda zamieniająca liczbę na przeciwną:
        c1.opposite();
        System.out.println("-c1 = " + c1); // -c1 = -2.5 + 13.1i
    }

    public static void main(String[] args) {
    System.out.println("------------- Zadanie 1 -------------");
    new Main().test();

    if(args.length == 3)
    {
        System.out.println();
        System.out.println("------------- Zadanie 2 -------------");
        
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);

        double delta = Math.pow(b, 2) - 4*a*c;
        Complex result1 = Complex.divide(Complex.add(new Complex(-b), Complex.sqrt(delta)), new Complex(2*a));
        Complex result2 = Complex.divide(Complex.subtract(new Complex(-b), Complex.sqrt(delta)), new Complex(2*a));

        System.out.println("x1 = " + result1 + ", x2 = " + result2);
    }
}
}

class Complex {
    private double re;
    private double im;

    public Complex(){
          re = 0.0;
          im = 0.0;
    }
    
    public Complex(double input){
        re = input;
    }
    
    public Complex(double inputRe, double inputIm){
        re = inputRe;
        im = inputIm;
    }

    double getRe(){
        return re;
    }

    double getIm(){
        return im;
    }

    static Complex add(Complex c1, Complex c2){
        return new Complex(c1.getRe() + c2.getRe(), c1.getIm() + c2.getIm());
    }

    static Complex subtract(Complex c1, Complex c2){
        return new Complex(c1.getRe() - c2.getRe(), c1.getIm() - c2.getIm());
    }

    static Complex multiply(Complex c1, Complex c2){
        double a = c1.getRe();
        double b = c1.getIm();
        double c = c2.getRe();
        double d = c2.getIm();

        return new Complex((a*c) - (b*d), (b*c) + (a*d));
    }

    static Complex multiply(Complex c1, double d){
        double a = c1.getRe();
        double b = c1.getIm();

        return new Complex(a*d, b*d);
    }

    static Complex divide(Complex c1, Complex c2){
        double a = c1.getRe();
        double b = c1.getIm();
        double c = c2.getRe();
        double d = c2.getIm();

        return new Complex(((a*c) + (b*d))/(Math.pow(c, 2) + Math.pow(d, 2)), ((b*c) - (a*d))/(Math.pow(c, 2) + Math.pow(d, 2)));
    }

    double mod(){
        return Math.sqrt(Math.pow(this.getRe(), 2) + Math.pow(this.getIm(), 2));
    }

    static Complex sqrt(double input){
        if(input < 0)
            return new Complex(0.0, Math.sqrt(Math.abs(input)));
        else
            return new Complex(Math.sqrt(input));
    }

    boolean equals(Complex c){
        if(this.getRe() == c.getRe() && this.getIm() == c.getIm())
        {
            return true;
        }

        return false;
    }
    void conjugate(){
        this.im = -this.im;
    }

    void opposite(){
        this.re = -this.re;
        this.im = -this.im;
    }
    
    public String toString(){
        if(this.getRe() == 0.0 && this.getIm() != 0.0)
            return this.getIm() + "i";
        else if(this.getIm() == 0.0)
            return String.valueOf(this.getRe());
        else if(this.getIm() < 0)
            return this.getRe() + " " + this.getIm() + "i";
        return this.getRe() + " + " + this.getIm() + "i";
    }

    static Complex I = new Complex(0.0, 1.0);
    static Complex ZERO = new Complex(0.0);
    static Complex ONE = new Complex(1.0);

    
  }