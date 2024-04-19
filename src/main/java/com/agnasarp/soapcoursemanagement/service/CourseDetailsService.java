package com.agnasarp.soapcoursemanagement.service;

import com.agnasarp.soapcoursemanagement.domain.Course;
import com.agnasarp.soapcoursemanagement.exception.CourseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//Course details service
@Service
public class CourseDetailsService {

    static List<Course> courses = new ArrayList<>();

    static {

        Course course1 = new Course(1, "Java 11", "Java 11 course");
        Course course2 = new Course(2, "Spring 5", "Spring 5 course");
        Course course3 = new Course(3, "Spring boot", "Spring boot course");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
    }

    //    Get course by id
    public Course getCourseById(int id) {

        return courses.stream().filter(c -> c.getId() == id).findAny().orElseThrow(() -> new CourseNotFoundException("Invalid course id " + id));
    }

    //    Get all courses
    public List<Course> getAllCourses() {

        return courses;
    }

    //    Delete a specific course
    public Status deleteCourseById(int id) {

        Iterator<Course> courseIterator = courses.iterator();
        while (courseIterator.hasNext()) {
            Course course = courseIterator.next();
            if (course.getId() == id) {
                courseIterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILS;
    }

    public enum Status {
        SUCCESS, FAILS
    }
}
