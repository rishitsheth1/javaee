package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
//
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//
import com.example.demo.models.Course;
//
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class CourseController {
	//initialize a list of courses
	private List<Course> courses = createList();
	//
	@RequestMapping(value = "/courses", method = RequestMethod.GET, produces = "application/json")
	public List<Course> getCourses() {
		return courses;
	}
	@PostMapping
	public Course createCourse(@RequestBody Course course) {
		courses.add(course);
		System.out.println(courses);
		return course;
	}
	//
	@DeleteMapping(path = { "/courses/{courseCode}" })
	public @ResponseBody void deleteCourse(@PathVariable("courseCode") String courseCode) {
		for (Course course : courses) {
			if (course.getCourseCode().equals(courseCode)) {
				courses.remove(course);
				break;
			}
		}
	}
	//
	@PutMapping
    public @ResponseBody void updateCourse(@RequestBody Course updatedCourse)
    {
		System.out.println(updatedCourse.getCourseCode());
		for (Course course : courses) {
			if (course.getCourseCode().equals(updatedCourse.getCourseCode())) {
				//update the course
				course.setCourseName(updatedCourse.getCourseName());
				course.setCourseDescription(updatedCourse.getCourseDescription());
				break;
			}
		}
        
    }

	
	private static List<Course> createList() {
		List<Course> courses = new ArrayList<>();
		Course comp308 = new Course();
		comp308.setCourseCode("COMP-308");
		comp308.setCourseName("Emerging Technologies");
		comp308.setCourseDescription("Emerging SOftware Technologies");
		//
		Course comp254 = new Course();
		comp254.setCourseCode("COMP-254");
		comp254.setCourseName("Data Structures & Algorithms");
		comp254.setCourseDescription("Data Structures and Algorithms and Complexity");
		//
		courses.add(comp308);
		courses.add(comp254);
		return courses;
	}

}