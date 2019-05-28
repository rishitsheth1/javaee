import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class Course {

  constructor(
    public courseCode: string,
    public courseName: string,
    public courseDescription: string,
  ) {}

}

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(
    private httpClient: HttpClient
  ) {  }

  getCourses()
  {
    console.log('test service call to server');
    return this.httpClient.get<Course[]>('http://localhost:8080/courses');
  }
  //
  public deleteCourse(course) {
    return this.httpClient.delete<Course>('http://localhost:8080/courses' + '/' + course.courseCode
    );
  }
  public updateCourse(course) {
    return this.httpClient.put<Course>('http://localhost:8080/courses', course);

  }

  //
  public createCourse(course) {
    return this.httpClient.post<Course>('http://localhost:8080/courses', course);
  }

}
