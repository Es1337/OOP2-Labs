package Lab1;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

      double n = Double.parseDouble(args[0]);
      double a = Double.parseDouble(args[1]);
      double b = Double.parseDouble(args[2]);

      double x;
      double y;
      double t;

      Random r = new Random();

      for (int i = 0; i < n; i++)
      {
        t = Math.PI * r.nextDouble() * 2;
        x = r.nextDouble() * a * Math.cos(t);
        y = r.nextDouble() * b * Math.sin(t);

        System.out.println(x + ", " + y);
      }

    }

}