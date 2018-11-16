import java.util.List;
import java.util.Objects;

class Student {
    private final String surname;
    private final String givenName;
    private final int age;
    private final List<CourseSection> currentCourses;

    public Student(final String surname, final String givenName, final int age,
        final List<CourseSection> currentCourses)
    {
        this.surname = surname;
        this.givenName = givenName;
        this.age = age;
        this.currentCourses = currentCourses;
    }

    public boolean equals(Object other) {
        if (other != null) {
            if (Student.class == other.getClass()) {
                List<CourseSection> otherCourses = ((Student)other).getCurrentCourses();
                if (this.currentCourses.size() == otherCourses.size()) {
                    for (int c=0; c<(this.currentCourses).size(); c++) {
                        if (!((this.currentCourses.get(c)).equals(otherCourses.get(c)))) {
                            return false;
                        }
                    }
                    
                    return (this.surname == ((Student)other).getSurname()) &&
                        (this.givenName == ((Student)other).getGivenName()) &&
                        (this.age == ((Student)other).getAge());
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int result = 0;
        if (surname != null) {
            result += surname.hashCode();
        }
        if (givenName != null) {
            result += givenName.hashCode();
        }
        if (age != 0) {
            result += age;
        }
        if (currentCourses != null) {
            for (CourseSection c : currentCourses) {
                result += c.hashCode();
            }
        }
        return result;
    }

    public String getSurname() {
        return surname;
    }
    public String getGivenName() {
        return givenName;
    }
    public int getAge() {
        return age;
    }
    public List<CourseSection> getCurrentCourses() {
        return currentCourses;
    }
}
