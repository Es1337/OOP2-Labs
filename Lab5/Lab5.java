public class Lab5 {
    public static void main(String[] args) {
        Man[] staff = new Man[6];
        staff[0] = new Student("Jan", "Kowalski", 211152, 3.5, 4.0, 5.0);
        staff[1] = new Student("Marek", "Styczen", 211150, 4.5, 3.5, 5.0);
        staff[2] = new Graduate("inz.", "Franciszek", "Nowak", 223123, 2018, 5.0, 4.5, 4.0);
        staff[3] = new Graduate("mgr inz.", "Lukasz", "Maj", 200122, 2012, 5.0, 4.5, 3.0);
        staff[4] = new Dean("prof.", "Wojciech", "Luzny", 2008, 2012);
        staff[5] = new Dean("prof.", "Janusz", "Wolny", 2012, 2020);
        
        printStaff(staff);

        System.out.println();
        System.out.println(staff[0].compare(staff[1]));
        System.out.println(staff[1].compare(staff[0]));
        System.out.println(staff[1].compare(staff[2]));
        System.out.println(staff[1].compare(staff[4]));

        System.out.println();
        System.out.println(staff[4].compare(staff[5]));
        System.out.println(staff[5].compare(staff[4]));
        System.out.println(staff[5].compare(staff[0]));
    }

    public static void printStaff(Man[] tab) {
        for (var c : tab)
            if (c != null) {
                System.out.println(c.getClass().getName() + ": " + c);
                System.out.println("  Average = " + c.average());
                System.out.println();
            } else
                break;
    }
}
