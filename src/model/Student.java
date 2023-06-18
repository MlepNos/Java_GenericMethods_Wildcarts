package model;

import util.QueryItem;

import java.util.Random;

public class Student implements QueryItem {
    private String name;
    private String course;
    private int yearStarted;
    protected static Random random = new Random();
    private static String[] firstNames = {"Ann","Bill","Cathy","John","Tim"};
    private static String[] courses ={"C++","Java","Python","C","JS"};

    public Student(){
        int lastNameIndex = random.nextInt(65,91);  // A-Z
        name = firstNames[random.nextInt(5)]+ " " + (char) lastNameIndex;
        course = courses[random.nextInt(5)];
        yearStarted = random.nextInt(2018,2023);
    }

    @Override
    public String toString() {
        return "%-15s %-15s %d".formatted(name,course,yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean MatchFieldValue(String fieldName, String value) {
        String fName = fieldName.toUpperCase();
        return switch (fName){
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted== (Integer.parseInt(value));
            default -> false;
        };
    }
}
