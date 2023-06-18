import model.LPAStudent;
import model.Student;
import util.QueryItem;
import util.QueryList;

import java.util.ArrayList;
import java.util.List;

record Employee(String name) implements QueryItem  {

    @Override
    public boolean MatchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        int studentCount =10;
        List<Student> students = new ArrayList<>();
        for(int i=0;i<studentCount;i++){
            students.add(new Student());
        }
        students.add(new LPAStudent());

        PrintList(students);
        System.out.println("-".repeat(50));
        PrintListGeneric(students);

        System.out.println("=".repeat(50));

        List<LPAStudent> lpastudents = new ArrayList<>();
        for(int i=0;i<studentCount;i++){
            lpastudents.add(new LPAStudent());
        }
//        PrintList(lpastudents);  we can not do this because it expects a student type parameter
        PrintListGeneric(lpastudents);

        System.out.println("=".repeat(50));

        PrintListGenericWithBounds(students);
        System.out.println("-".repeat(50));
        PrintListGenericWithBounds(lpastudents);

        System.out.println("=".repeat(50));

        //Method With Wildcart (Upper Bound)
        System.out.println("Methods With Wildcart (Upper Bound)\n");

        PrintListsWildcartUpperBound(students);
        System.out.println("-".repeat(50));
        PrintListsWildcartUpperBound(lpastudents);

        System.out.println("=".repeat(50));

        testList(new ArrayList<String>(List.of("Able","Barry")));
        testList(new ArrayList<Integer>(List.of(2,3,5,8)));

        System.out.println("=".repeat(50));

        var queryList = new QueryList<>(lpastudents);
        var matchesCourse = queryList.getMatches("COURSE","PYTHON");
        PrintListGenericWithBounds(matchesCourse);

        var matchesYear = queryList.getMatches("YEARSTARTED","2022");
        PrintListGenericWithBounds(matchesYear);

        System.out.println("=".repeat(50));

        //Using the Static version of getMatches
        var studentJS = QueryList.getMatches(students,"COURSE","JS");
        PrintListGenericWithBounds(studentJS);


        /*Employee is not a subtype of Student thats why this wont work ->
        Employee employee = new Employee("Marcus");
        QueryList<employee> employeeQueryList = new QueryList<>();
        */

    }

    //We can not use PrintList for LPAStudents
    public static void PrintList(List<Student> students){
        for(var student : students){
            System.out.println(student.getYearStarted()+ ": "+student);
        }
        System.out.println();
    }


    //This generic method can be used for all nonPirimitive types
    public static <T> void PrintListGeneric(List<T> students){
        for(var student : students){
            System.out.println(student);
        }
        System.out.println();
    }

    //The generic method below can be used just for Student type of parameters(Student,LPAStudent)
    public static <T extends Student> void PrintListGenericWithBounds(List<T> students){
        for(var student : students){
            System.out.println(student.getYearStarted()+ ": "+student);
        }
        System.out.println();
    }

    //With the use of a wildcart with an upper bound (extends) we can specify the parameter type, in this case it will be a Student or a LPAstudent
    public static void PrintListsWildcartUpperBound(List<? extends Student> students){
        for(var student : students){
            System.out.println(student.getYearStarted()+ ": "+student);
        }
        System.out.println();
    }

    /* the 2 methods below have the same erasure and clash, thats why we can not override both methods like this


    public static void testList(List<String> list){
        for(var element : list){
            System.out.println("String: " + element.toUpperCase());
        }
    }
    public static void testList(List<Integer> list){
        for(var element : list){
            System.out.println("Integer: " + element.floatValue());
        }
    }
     */

    public static void testList(List<?> list){
        for(var element : list){
            if(element instanceof String s){
                System.out.println("String: " + s.toUpperCase());
            }
            else if(element instanceof  Integer i){
                System.out.println("Integer: " + i.floatValue());
            }

        }
    }

}