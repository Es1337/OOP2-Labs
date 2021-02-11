public class StudentWFiIS2 implements StudentUSOS {
    private String[] przedmioty;
    private int rok;
    private Student stud;

    /* IMPLEMETACJA METOD: */
    StudentWFiIS2(String i, String n, int id, int r, String przed1, double ocena1, String przed2, double ocena2, String przed3, double ocena3) {
        stud = new Student(i, n, id, ocena1, ocena2, ocena3);
        rok = r;
        przedmioty = new String[]{przed1, przed2, przed3};
    }

    public String toString(){

        return String.format(
            "[%d] %s",
            rok, stud.toString()
        );
    }

    public double srednia(){
        return stud.average();
    }

    public void listaPrzedmiotow(){
        for(int i = 0; i < 3; i++)
        {
            System.out.println(String.format(
                "%d. %s: %s",
                i+1, przedmioty[i], ""+stud.getGrade(i)
            ));
        }
    }
}
