import java.util.Arrays;

abstract class Man {
    private final String name;
    private final String surname;

    /** Constuctor setting name and surname */
    protected Man(String setName, String setSurname) {
        name = setName;
        surname  = setSurname;
    }

    protected String getName() {
        return name;
    }

    protected String getSurname() {
        return surname;
    }

    /** Returns string in format: Name Surname */
    public String toString() {
        return name + " " + surname;
    }

    abstract public Man compare(Man ob);

    public double average() {
        return 0.0;
    }
}

class Student extends Man {
    private final int indexNumber;
    private final double[] grades;

    public double[] getGrades() {
        return grades;
    }

    public int getIndex() {
        return indexNumber;
    }

    /** Constuctor using super constructor for name and surname, and setting index and grades  */
    public Student(String setName, String setSurname, int setIndex, double grade1, double grade2, double grade3) {
        super(setName, setSurname);
        indexNumber = setIndex;
        grades = new double[]{grade1, grade2, grade3};
    }

    /** Comparing students, returns reference to the student with higher grade average or null if its not a student*/
    @Override
    public Man compare(Man ob) {
        if(ob instanceof Student)
        {
            Student otherStudent = (Student)ob;
            if(this.average() > otherStudent.average())
            {
                return this;
            }
            else{
                return otherStudent;
            }
        }
        else 
            return null;
    }

    /** Returns string in format: Name Surname, id number: Index, grades: [Grade1, Grade2, Grade3] */
    @Override
    public String toString() {
        String superString = super.toString();
        return superString + ", id number: " + indexNumber + ", grades: " + Arrays.toString(grades);
    }

    /** Returns grade average */
    @Override
    public double average() {
        return (grades[0] + grades[1] + grades[2])/3.;
    }
}

class Graduate extends Student {
    private final int gradYear;
    private final String degree;

    public String getDegree() {
        return degree;
    }

    public int getGradYear() {
        return gradYear;
    }

    /** Constuctor using super constructor for name, surname, index and grades, and setting graduation year and degree  */
    public Graduate(String setDegree, String setName, String setSurname,
                    int setIndex, int setYear,
                    double grade1, double grade2, double grade3) {
        super(setName, setSurname, setIndex, grade1, grade2, grade3);
        gradYear = setYear;
        degree = setDegree;
    }

    /** Returns string in format: Degree Name Surname, id number: Index, grades: [Grade1, Grade2, Grade3], year of graduation: GradYear */
    @Override
    public String toString() {
        String superString = super.toString();
        return degree + " " + superString + ", year of graduation: " + gradYear;
    }
}

class Dean extends Man {
    private final String degree;
    private final int startOfTerm;
    private final int endOfTerm;

    public String getDegree() {
        return degree;
    }

    public int getStartOfTerm() {
        return startOfTerm;
    }

    public int getEndOfTerm() {
        return endOfTerm;
    }

    /** Constuctor using super constructor for name and surname, and setting start and end of term and degree  */
    public Dean(String setDegree, String setName, String setSurname, int setStart, int setEnd) {
        super(setName, setSurname);
        degree = setDegree;
        startOfTerm = setStart;
        endOfTerm = setEnd;
    }

    /** Comparing deans, returns reference to the dean ending term later or null if its not a dean */
    @Override
    public Man compare(Man ob) {
        if(ob instanceof Dean)
        {    
            Dean otherDean = (Dean)ob;
            if(this.getEndOfTerm() > otherDean.getEndOfTerm())
            {
                return this;
            }
            else
            {
                return otherDean;
            }
        }
        else    
            return null;
    }

    /** Returns string in format: Degree Name Surname, Dean of the faculty from StartOfTerm to EndOfTerm */
    @Override
    public String toString() {
        String superString = super.toString();
        return degree + " " + superString + ", Dean of the faculty from " + startOfTerm + " to " + endOfTerm;
    }
}