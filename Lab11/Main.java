import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;

class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Random rand = new Random();

        String[] t1 = new String[n];

        for (int i = 0; i < n; i++)
        {
            int randSeed = rand.nextInt(16) + 5;
            StringBuilder sb = new StringBuilder("");

            for(int index = 0; index < randSeed; index++)
            {
                sb.append(rndChar(rand.nextInt(52)));
            }

            t1[i] = sb.toString();
            // System.out.println(t1[i]);
        }

        System.out.println("------------------");
        System.out.println("Losowanie " + n + " lancuchow.");
        String[] t2 = new String[m];
        int[] used = new int[m];
        Arrays.fill(used, -1);
        
        for(int i = 0; i < t2.length; i++)
        {
            int randSeed = (rand.nextInt(1000) + 1)%n;
            Arrays.sort(used);
            if(Arrays.binarySearch(used, randSeed) > 0)
            {
                i--;
                continue;
            }
            t2[i] = t1[randSeed];
            // System.out.println(t2[i] + ", " + Arrays.toString(used) + ", " + randSeed);
            used[0] = randSeed;
        }

        String[] t3 = new String[m];
        
        for(int i = 0; i < t3.length; i++)
        {
            int randSeed = rand.nextInt(16) + 5;
            StringBuilder sb = new StringBuilder("");

            for(int index = 0; index < randSeed; index++)
            {
                sb.append(rndChar(rand.nextInt(52)));
            }
            
            if(Arrays.binarySearch(t1, sb.toString()) > 0)
            {
                i--;
                continue;
            }
            t3[i] = sb.toString();
            // System.out.println(t3[i]);
        }
        System.out.println("Testowanie dla " + m + " lancuchow.");

        ArrayList<String> al = new ArrayList<String>();
        LinkedList<String> ll = new LinkedList<String>();
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        //// FILL
        System.out.println("------------------");
        System.out.println("FILL");

        long start = System.nanoTime();
        for(int i = 0; i < t1.length; i++)
        {
            al.add(t1[i]);
        }
        long elapsed = System.nanoTime() - start;
        System.out.print("ArrayList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t1.length; i++)
        {
            ll.add(t1[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("LinkedList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t1.length; i++)
        {
            tm.put(t1[i], 0);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("TreeMap(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t1.length; i++)
        {
            hm.put(t1[i], 0);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("HashMap(" + elapsed/1000000. + " ms)\n");

        System.out.println("BEGIN - size: AL: " + al.size() + " LL: " + ll.size() + " TM: " + tm.size() + " HM: " + hm.size());

        //// SEARCH what's there
        System.out.println("------------------");
        System.out.println("SEARCH what's there");

        start = System.nanoTime();
        for(int i = 0; i < t2.length; i++)
        {
            al.contains(t2[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("ArrayList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t2.length; i++)
        {
            ll.contains(t2[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("LinkedList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t2.length; i++)
        {
            tm.containsKey(t2[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("TreeMap(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t2.length; i++)
        {
            hm.containsKey(t2[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("HashMap(" + elapsed/1000000. + " ms)\n");

        //// SEARCH what's NOT there
        System.out.println("------------------");
        System.out.println("SEARCH what's NOT there");

        start = System.nanoTime();
        for(int i = 0; i < t3.length; i++)
        {
            al.contains(t3[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("ArrayList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t3.length; i++)
        {
            ll.contains(t3[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("LinkedList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t3.length; i++)
        {
            tm.containsKey(t3[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("TreeMap(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        for(int i = 0; i < t3.length; i++)
        {
            hm.containsKey(t3[i]);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("HashMap(" + elapsed/1000000. + " ms)\n");

        // FOR
        System.out.println("------------------");

        System.out.print("ArrayList - ");
        start = System.nanoTime();
        for(int i = 0; i < al.size(); i++)
        {
           al.get(i);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("for(" + elapsed/1000000. + " ms)\n");

        
        System.out.print("LinkedList - ");
        start = System.nanoTime();
        for(int i = 0; i < ll.size(); i++)
        {
           ll.get(i);
        }
        elapsed = System.nanoTime() - start;
        System.out.print("for(" + elapsed/1000000. + " ms)\n");

        // FOR-EACH

        System.out.print("ArrayList - ");
        start = System.nanoTime();
        for(String s : al)
        {

        }
        elapsed = System.nanoTime() - start;
        System.out.print("foreach(" + elapsed/1000000. + " ms)\n");

        
        System.out.print("LinkedList - ");
        start = System.nanoTime();
        for(String s : ll)
        {
            
        }
        elapsed = System.nanoTime() - start;
        System.out.print("foreach(" + elapsed/1000000. + " ms)\n");

        // ITERATOR

        System.out.print("ArrayList - ");
        start = System.nanoTime();
        for(Iterator i = al.iterator(); i.hasNext();)
        {
            i.next();
        }
        elapsed = System.nanoTime() - start;
        System.out.print("iterator(" + elapsed/1000000. + " ms)\n");

        
        System.out.print("LinkedList - ");
        start = System.nanoTime();
        for(Iterator<String> i = ll.iterator(); i.hasNext();)
        {
            i.next();
        }
        elapsed = System.nanoTime() - start;
        System.out.print("iterator(" + elapsed/1000000. + " ms)\n");

        //// CLEAR
        System.out.println("------------------");
        System.out.println("CLEAR");

        start = System.nanoTime();
        al.clear();
        elapsed = System.nanoTime() - start;
        System.out.print("ArrayList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        ll.clear();
        elapsed = System.nanoTime() - start;
        System.out.print("LinkedList(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        tm.clear();
        elapsed = System.nanoTime() - start;
        System.out.print("TreeMap(" + elapsed/1000000. + " ms), ");

        start = System.nanoTime();
        hm.clear();
        elapsed = System.nanoTime() - start;
        System.out.print("HashMap(" + elapsed/1000000. + " ms)\n");
        
        System.out.println("------------------");
        System.out.println("END - size: AL: " + al.size() + " LL: " + ll.size() + " TM: " + tm.size() + " HM: " + hm.size());
    }

    private static char rndChar(int x) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return alphabet.charAt(x);
    }
}