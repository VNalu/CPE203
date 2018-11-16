import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalTime;

import org.junit.Test;

// import jdk.internal.jline.internal.TestAccessible;

public class TestCases
{
    @Test
    public void testExercise1()
    {
        final CourseSection one = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 40), LocalTime.of(11, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 40), LocalTime.of(11, 0));

        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void testExercise2()
    {
        final CourseSection one = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
            LocalTime.of(1, 10), LocalTime.of(2, 0));

        assertFalse(one.equals(two));
        assertFalse(two.equals(one));
    }

    @Test
    public void testExercise3()
    {
        final CourseSection one = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 40), LocalTime.of(11, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 40), LocalTime.of(11, 0));

        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void testExercise4()
    {
        final CourseSection one = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection two = new CourseSection("CSC", "203", 34,
            LocalTime.of(9, 10), LocalTime.of(10, 0));

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void testExercise5()
    {
        final CourseSection oneCourse = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        
        ArrayList<CourseSection> vCourses = new ArrayList<CourseSection>();
        vCourses.add(oneCourse);
        vCourses.add(twoCourse);
        vCourses.add(threeCourse);
        vCourses.add(fourCourse);
        final Student one = new Student("Pollock", "Veronica", 19, vCourses);


        final CourseSection oneCourseA = new CourseSection("ASCI", "225", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourseA = new CourseSection("ENGL", "145", 30,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourseA = new CourseSection("FSN", "250", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourseA = new CourseSection("STAT", "218", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));

        ArrayList<CourseSection> aCourses = new ArrayList<CourseSection>();
        aCourses.add(oneCourseA);
        aCourses.add(twoCourseA);
        aCourses.add(threeCourseA);
        aCourses.add(fourCourseA);
        final Student two = new Student("Lee", "Ariana", 19, aCourses);

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void testExercise6()
    {
        final CourseSection oneCourse = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        
        ArrayList<CourseSection> vCourses = new ArrayList<CourseSection>();
        vCourses.add(oneCourse);
        vCourses.add(twoCourse);
        vCourses.add(threeCourse);
        vCourses.add(fourCourse);
        final Student one = new Student("Pollock", "Veronica", 19, vCourses);


        final CourseSection oneCourseA = new CourseSection("ASCI", "225", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourseA = new CourseSection("ENGL", "145", 30,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourseA = new CourseSection("FSN", "250", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourseA = new CourseSection("STAT", "218", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));

        ArrayList<CourseSection> aCourses = new ArrayList<CourseSection>();
        aCourses.add(oneCourseA);
        aCourses.add(twoCourseA);
        aCourses.add(threeCourseA);
        aCourses.add(fourCourseA);
        final Student two = new Student("Lee", "Ariana", 19, aCourses);

        assertFalse(one.equals(two));
        assertFalse(two.equals(one));
    }

    @Test
    public void testExercise7()
    {
        final CourseSection oneCourse = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        
        ArrayList<CourseSection> vCourses = new ArrayList<CourseSection>();
        vCourses.add(oneCourse);
        vCourses.add(twoCourse);
        vCourses.add(threeCourse);
        vCourses.add(fourCourse);
        final Student one = new Student("Pollock", "Veronica", 19, vCourses);

        final CourseSection oneCourse2 = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse2 = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse2 = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse2 = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
    

        ArrayList<CourseSection> aCourses = new ArrayList<CourseSection>();
        aCourses.add(oneCourse2);
        aCourses.add(twoCourse2);
        aCourses.add(threeCourse2);
        aCourses.add(fourCourse2);
        final Student two = new Student("Pollock", "Veronica", 19, aCourses);

        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void testExercise8()
    {
        final CourseSection oneCourse = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        
        ArrayList<CourseSection> vCourses = new ArrayList<CourseSection>();
        vCourses.add(oneCourse);
        vCourses.add(twoCourse);
        vCourses.add(threeCourse);
        vCourses.add(fourCourse);
        final Student one = new Student("Pollock", "Veronica", 19, vCourses);

        final CourseSection oneCourse2 = new CourseSection("CSC", "203", 35,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection twoCourse2 = new CourseSection("STAT", "213", 48,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection threeCourse2 = new CourseSection("PHYS", "141", 25,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection fourCourse2 = new CourseSection("PHIL", "312", 149,
            LocalTime.of(9, 10), LocalTime.of(10, 0));
    

        ArrayList<CourseSection> aCourses = new ArrayList<CourseSection>();
        aCourses.add(oneCourse2);
        aCourses.add(twoCourse2);
        aCourses.add(threeCourse2);
        aCourses.add(fourCourse2);
        final Student two = new Student("Pollock", "Veronica", 19, aCourses);

        assertEquals(one.hashCode(), two.hashCode());
    }
}
