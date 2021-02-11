public class StudentWFiIS1 extends Student implements StudentUSOS {
    private String[] przedmioty;
    private int rok;

    /* IMPLEMETACJA METOD: */
    StudentWFiIS1(String i, String n, int id, int r, String przed1, double ocena1, String przed2, double ocena2, String przed3, double ocena3) {
        super(i, n, id, ocena1, ocena2, ocena3);
        rok = r;
        przedmioty = new String[]{przed1, przed2, przed3};
    }

    public String toString(){

        return String.format(
            "[%d] %s", 
            rok, super.toString()
        );
    }

    public double srednia(){
        return average();
    }

    public void listaPrzedmiotow(){
        for(int i = 0; i < 3; i++)
        {
            System.out.println(String.format(
                "%d. %s: %s",
                i+1, przedmioty[i], ""+getGrade(i)
            ));
        }
    }
}
