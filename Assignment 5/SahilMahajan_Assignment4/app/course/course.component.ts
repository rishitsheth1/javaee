import { Component, OnInit } from '@angular/core';
//
import { CourseService, Course } from '../service/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  courses: Course[];

  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.getCourses();
  }
  //
  getCourses()
  {
    this.courseService.getCourses().subscribe(
      response => {this.courses = response; }
     );
  }
  //
  createCourse(courseCode, courseName, courseDescription) {
    const course = {
        courseCode: courseCode,
        courseName: courseName,
        courseDescription: courseDescription

    };
    this.courseService.createCourse(course).subscribe(
        data => {
            // refresh the list
            this.getCourses();
            alert('Course created successfully.');

            return true;
        },
        error => {
            console.error('Error saving food!');
        }
    );
  }
  //
  updateCourse(courseCode, courseName, courseDescription) {
    const course = {
        courseCode: courseCode,
        courseName: courseName,
        courseDescription: courseDescription

    };
    this.courseService.updateCourse(course).subscribe(
        data => {
            // refresh the list
            this.getCourses();
            alert('Course updated successfully.');

            return true;
        },
        error => {
            console.error('Error saving food!');
        }
    );
  }
  //
  deleteCourse(course: Course): void {
    this.courseService.deleteCourse(course)
      .subscribe( data => {
        this.courses = this.courses.filter(u => u !== course);
      });
  }
  //

}