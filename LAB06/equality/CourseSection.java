import java.time.LocalTime;
import java.util.Objects;;

class CourseSection
{
    private final String prefix;
    private final String number;
    private final int enrollment;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public CourseSection(final String prefix, final String number,
        final int enrollment, final LocalTime startTime, final LocalTime endTime)
    {
        this.prefix = prefix;
        this.number = number;
        this.enrollment = enrollment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // additional likely methods not defined since they are not needed for testing
    public boolean equals(Object other) {
        if (other != null) {
            if (CourseSection.class == other.getClass()) {

                /*
                // For debugging purpose
                if (this.prefix == ((CourseSection)other).getPrefix()) {
                    System.out.println("prefix matches");
                    if (this.number == ((CourseSection)other).getNumber()) {
                        System.out.println("number matches");
                        if (this.enrollment == ((CourseSection)other).getEnrollment()) {
                            System.out.println("enrollment matches");
                            if (this.startTime == ((CourseSection)other).getStartTime()) {
                                System.out.println("start matches");
                                if (this.endTime == ((CourseSection)other).getEndTime()) {
                                    System.out.println("end matches");
                                }
                            }
                        }
                    }
                }
                System.out.println();
                */

                return (this.prefix == ((CourseSection)other).getPrefix()) &&
                    (this.number == ((CourseSection)other).getNumber()) &&
                    (this.enrollment == ((CourseSection)other).getEnrollment()) &&
                    (((CourseSection)other).getStartTime().equals(this.startTime)) &&
                    (this.endTime == ((CourseSection)other).getEndTime());
            }
        }
        return false;
    }

    public int hashCode() {
        int result = 0;
        if (prefix != null) {
            result += prefix.hashCode();
        }
        if (number != null) {
            result += number.hashCode();
        }
        if (enrollment != 0) {
            result += enrollment;
        }
        if (startTime != null) {
            result += startTime.hashCode();
        }
        if (endTime != null) {
            result += endTime.hashCode();
        }
        return result;
    }

    public String getPrefix() {
        return prefix;
    }
    public String getNumber() {
        return number;
    }
    public int getEnrollment() {
        return enrollment;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
}
