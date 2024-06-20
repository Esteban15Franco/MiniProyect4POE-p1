public class Student {
    private String name;
    private double grade;
    private String id; 

    public Student(String name, double grade, String id) {
        this.name = name;
        this.grade = grade;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + grade; 
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[1], Double.parseDouble(parts[2]), parts[0]); 
    }
}