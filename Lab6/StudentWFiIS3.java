public class StudentWFiIS3 extends Student {
    private StudentUSOS stud;

    /* IMPLEMETACJA METOD: */
    StudentWFiIS3(String i, String n, int id, int r, String przed1, double ocena1, String przed2, double ocena2, String przed3, double ocena3) {
        super(i, n, id, ocena1, ocena2, ocena3);

        stud = new StudentUSOS() {
            int rok;
            String[] przedmioty;
            {
            rok = r;
            przedmioty = new String[]{przed1, przed2, przed3};
            }
            @Override
            public String toString() {
                return String.format(
                    "[%d] %s \n", 
                    rok, super.toString()
                );
            }
            @Override
            public double srednia(){
                return average();
            }
            @Override
            public void listaPrzedmiotow(){
                for(int i = 0; i < 3; i++)
                {
                    System.out.println(String.format(
                        "%d. %s: %s", 
                        i+1, przedmioty[i], ""+getGrade(i)
                    ));
                }
            }
        };
    }

    public double srednia(){
        return stud.srednia();
    }

    public void listaPrzedmiotow(){
            stud.listaPrzedmiotow();
    }
}