package com.auca.main;

import com.auca.administrator.Administrator;
import com.auca.assessment.Assessment;
import com.auca.bookloan.BookLoan;
import com.auca.classroom.Classroom;
import com.auca.course.Course;
import com.auca.department.Department;
import com.auca.enrollment.Enrollment;
import com.auca.exceptions.ValidationException;
import com.auca.feepayment.FeePayment;
import com.auca.interfaces.Identifiable;
import com.auca.librarybook.LibraryBook;
import com.auca.student.Student;
import com.auca.teacher.Teacher;
import com.auca.abstracts.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final List<Department> departments = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Administrator> administrators = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Classroom> classrooms = new ArrayList<>();
    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static final List<Assessment> assessments = new ArrayList<>();
    private static final List<FeePayment> payments = new ArrayList<>();
    private static final List<LibraryBook> books = new ArrayList<>();
    private static final List<BookLoan> loans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("AUCA School Management System");
        System.out.println("==================================================");


        while (true) {
            printMenu();
            int choice = readInt("Select an option: ", 0, 19);
            if (choice == 0) {
                System.out.println("Exiting the system. Goodbye!");
                return;
            }

            switch (choice) {
                case 1 -> registerDepartment();
                case 2 -> registerStudent();
                case 3 -> registerTeacher();
                case 4 -> registerAdministrator();
                case 5 -> registerCourse();
                case 6 -> registerClassroom();
                case 7 -> enrollStudent();
                case 8 -> recordAssessment();
                case 9 -> recordPayment();
                case 10 -> registerLibraryBook();
                case 11 -> borrowBook();
                case 12 -> returnBook();
                case 13 -> viewAllRecords();
                case 14 -> searchById();
                case 15 -> updateRecord();
                case 16 -> showStudentAcademicSummary();
                case 17 -> showPaymentHistory();
                case 18 -> showAvailableBooks();
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu");
        System.out.println("1 Register Department");
        System.out.println("2 Register Student");
        System.out.println("3 Register Teacher");
        System.out.println("4 Register Administrator");
        System.out.println("5 Register Course");
        System.out.println("6 Register Classroom");
        System.out.println("7 Enroll Student");
        System.out.println("8 Record Assessment");
        System.out.println("9 Record Fee Payment");
        System.out.println("10 Register Library Book");
        System.out.println("11 Borrow Book");
        System.out.println("12 Return Book");
        System.out.println("13 View All Records");
        System.out.println("14 Search by ID");
        System.out.println("15 Update Record");
        System.out.println("16 Show Student Academic Summary");
        System.out.println("17 Show Payment History");
        System.out.println("18 Show Available Books");
        System.out.println("19 Demonstrate Polymorphism");
        System.out.println("0 Exit");
    }

    private static void registerDepartment() {
        String departmentId = readRequiredText("Department ID: ");
        String name = readRequiredText("Department name: ");
        String code = readRequiredText("Department code: ");
        String location = readRequiredText("Office location: ");
        String email = readRequiredText("Contact email: ");

        Department department = new Department(departmentId, name, code, location, email, null);
        try {
            department.validate();
            departments.add(department);
            System.out.println("Department registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerStudent() {
        if (departments.isEmpty()) {
            System.out.println("No department exists. Register a department before creating a student.");
            return;
        }
        Department department = chooseDepartment();
        if (department == null) {
            return;
        }

        String personId = readRequiredText("Person ID: ");
        String firstName = readRequiredText("First name: ");
        String lastName = readRequiredText("Last name: ");
        String email = readRequiredText("Email: ");
        String phone = readRequiredText("Phone: ");
        LocalDate dob = readDate("Date of birth (yyyy-MM-dd): ");
        String memberNumber = readRequiredText("Member number: ");
        LocalDate joinDate = readDate("Join date (yyyy-MM-dd): ");
        String academicStatus = readRequiredText("Academic status: ");
        String institutionalEmail = readRequiredText("Institutional email: ");
        String advisorName = readRequiredText("Advisor name: ");
        String studentNumber = readRequiredText("Student number: ");
        String program = readRequiredText("Program name: ");
        LocalDate admissionDate = readDate("Admission date (yyyy-MM-dd): ");
        int studyYear = readInt("Study year (1-6): ", 1, 6);
        String guardianPhone = readRequiredText("Guardian phone: ");
        boolean scholarship = readBoolean("Scholarship status (true/false): ");

        Student student = new Student(personId, firstName, lastName, email, phone, dob,
                memberNumber, department, joinDate, academicStatus, institutionalEmail,
                advisorName, studentNumber, program, admissionDate, studyYear,
                guardianPhone, scholarship);
        try {
            student.validate();
            students.add(student);
            System.out.println("Student registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerTeacher() {
        if (departments.isEmpty()) {
            System.out.println("No department exists. Register a department before creating a teacher.");
            return;
        }
        Department department = chooseDepartment();
        if (department == null) {
            return;
        }

        String personId = readRequiredText("Person ID: ");
        String firstName = readRequiredText("First name: ");
        String lastName = readRequiredText("Last name: ");
        String email = readRequiredText("Email: ");
        String phone = readRequiredText("Phone: ");
        LocalDate dob = readDate("Date of birth (yyyy-MM-dd): ");
        String memberNumber = readRequiredText("Member number: ");
        LocalDate joinDate = readDate("Join date (yyyy-MM-dd): ");
        String academicStatus = readRequiredText("Academic status: ");
        String institutionalEmail = readRequiredText("Institutional email: ");
        String advisorName = readRequiredText("Advisor name: ");
        String employeeNumber = readRequiredText("Employee number: ");
        String specialization = readRequiredText("Specialization: ");
        LocalDate hireDate = readDate("Hire date (yyyy-MM-dd): ");
        BigDecimal salary = readBigDecimal("Salary: ");
        String officeNumber = readRequiredText("Office number: ");
        String employmentType = readRequiredText("Employment type: ");

        Teacher teacher = new Teacher(personId, firstName, lastName, email, phone, dob,
                memberNumber, department, joinDate, academicStatus, institutionalEmail,
                advisorName, employeeNumber, specialization, hireDate, salary,
                officeNumber, employmentType);
        try {
            teacher.validate();
            teachers.add(teacher);
            System.out.println("Teacher registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerAdministrator() {
        String personId = readRequiredText("Person ID: ");
        String firstName = readRequiredText("First name: ");
        String lastName = readRequiredText("Last name: ");
        String email = readRequiredText("Email: ");
        String phone = readRequiredText("Phone: ");
        LocalDate dob = readDate("Date of birth (yyyy-MM-dd): ");
        String staffNumber = readRequiredText("Staff number: ");
        String jobTitle = readRequiredText("Job title: ");
        String office = readRequiredText("Assigned office: ");
        LocalDate employmentDate = readDate("Employment date (yyyy-MM-dd): ");
        int accessLevel = readInt("Access level: ", 0, 100);
        boolean active = readBoolean("Active (true/false): ");

        Administrator admin = new Administrator(personId, firstName, lastName, email,
                phone, dob, staffNumber, jobTitle, office, employmentDate, accessLevel, active);
        try {
            admin.validate();
            administrators.add(admin);
            System.out.println("Administrator registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerCourse() {
        if (departments.isEmpty()) {
            System.out.println("No department exists. Register a department first.");
            return;
        }
        if (teachers.isEmpty()) {
            System.out.println("No teacher exists. Register a teacher before creating a course.");
            return;
        }

        Department department = chooseDepartment();
        Teacher teacher = chooseTeacher();
        String courseId = readRequiredText("Course ID: ");
        String code = readRequiredText("Course code: ");
        String title = readRequiredText("Course title: ");
        int credits = readInt("Credit hours (1-6): ", 1, 6);

        Course course = new Course(courseId, code, title, credits, department, teacher);
        try {
            course.validate();
            courses.add(course);
            System.out.println("Course registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerClassroom() {
        String id = readRequiredText("Classroom ID: ");
        String building = readRequiredText("Building name: ");
        String room = readRequiredText("Room number: ");
        int capacity = readInt("Capacity (>0): ", 1, 1000);
        int floor = readInt("Floor number (>=0): ", 0, 100);
        boolean available = readBoolean("Available (true/false): ");

        Classroom classroom = new Classroom(id, building, room, capacity, floor, available);
        try {
            classroom.validate();
            classrooms.add(classroom);
            System.out.println("Classroom registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void enrollStudent() {
        if (students.isEmpty()) {
            System.out.println("No student exists. Register a student before enrolling.");
            return;
        }
        if (courses.isEmpty()) {
            System.out.println("No course exists. Register a course before enrolling a student.");
            return;
        }

        Student student = chooseStudent();
        Course course = chooseCourse();
        String enrollmentId = readRequiredText("Enrollment ID: ");
        String semester = readRequiredText("Semester: ");
        LocalDate date = readDate("Enrollment date (yyyy-MM-dd): ");
        String status = readRequiredText("Enrollment status (ACTIVE/COMPLETED/DROPPED): ");

        boolean duplicate = false;
        for (Enrollment existing : enrollments) {
            if (existing.getStudent() != null && existing.getStudent().getId().equals(student.getId())
                    && existing.getCourse() != null && existing.getCourse().getId().equals(course.getId())
                    && existing.getSemester().equalsIgnoreCase(semester)
                    && existing.isActive()) {
                duplicate = true;
            }
        }
        if (duplicate) {
            System.out.println("Duplicate active enrollment found for the same student, course and semester.");
            return;
        }

        Enrollment enrollment = new Enrollment(enrollmentId, student, course, date, semester, status);
        try {
            enrollment.validate();
            enrollments.add(enrollment);
            System.out.println("Enrollment recorded successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void recordAssessment() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollment exists. Register an enrollment before recording an assessment.");
            return;
        }

        Enrollment enrollment = chooseEnrollment();
        if (enrollment == null || !"ACTIVE".equalsIgnoreCase(enrollment.getEnrollmentStatus())) {
            System.out.println("Only active enrollments can be assessed.");
            return;
        }

        String id = readRequiredText("Assessment ID: ");
        String type = readRequiredText("Assessment type: ");
        double max = readDouble("Maximum score: ");
        double obtained = readDouble("Obtained score: ");
        LocalDate date = readDate("Assessment date (yyyy-MM-dd): ");

        Assessment assessment = new Assessment(id, enrollment, type, max, obtained, date);
        try {
            assessment.validate();
            assessments.add(assessment);
            System.out.println("Assessment recorded successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void recordPayment() {
        if (students.isEmpty()) {
            System.out.println("No student exists. Register a student before recording a fee payment.");
            return;
        }

        Student student = chooseStudent();
        BigDecimal amount = readBigDecimal("Amount: ");
        LocalDate paymentDate = readDate("Payment date (yyyy-MM-dd): ");
        String method = readRequiredText("Payment method: ");
        String receipt = readRequiredText("Receipt number: ");

        for (FeePayment payment : payments) {
            if (payment.getReceiptNumber().equalsIgnoreCase(receipt)) {
                System.out.println("Duplicate receipt number found. Payment not recorded.");
                return;
            }
        }

        String paymentId = "PAY-" + (payments.size() + 1);
        FeePayment feePayment = new FeePayment(paymentId, student, amount, paymentDate, method, receipt);
        try {
            feePayment.validate();
            payments.add(feePayment);
            System.out.println("Fee payment recorded successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void registerLibraryBook() {
        String id = readRequiredText("Book ID: ");
        String isbn = readRequiredText("ISBN: ");
        String title = readRequiredText("Title: ");
        String author = readRequiredText("Author: ");
        int totalCopies = readInt("Total copies: ", 0, 1000);
        int availableCopies = readInt("Available copies: ", 0, 1000);

        LibraryBook book = new LibraryBook(id, isbn, title, author, totalCopies, availableCopies);
        try {
            book.validate();
            books.add(book);
            System.out.println("Library book registered successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void borrowBook() {
        if (students.isEmpty()) {
            System.out.println("No student exists. Register a student before borrowing a book.");
            return;
        }
        if (books.isEmpty()) {
            System.out.println("No book exists. Register a library book before borrowing.");
            return;
        }

        Student student = chooseStudent();
        LibraryBook book = chooseBook();
        if (book.getAvailableCopies() <= 0) {
            System.out.println("No available copy for this book. Borrow cannot proceed.");
            return;
        }

        String loanId = "LOAN-" + (loans.size() + 1);
        LocalDate borrowedDate = readDate("Borrowed date (yyyy-MM-dd): ");
        LocalDate dueDate = readDate("Due date (yyyy-MM-dd): ");
        BookLoan loan = new BookLoan(loanId, student, book, borrowedDate, dueDate, null);
        try {
            loan.validate();
            loans.add(loan);
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            System.out.println("Book borrowed successfully.");
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    private static void returnBook() {
        if (loans.isEmpty()) {
            System.out.println("No active loan exists.");
            return;
        }

        BookLoan selected = chooseLoan();
        if (selected == null) {
            return;
        }
        LocalDate returnDate = readDate("Returned date (yyyy-MM-dd): ");
        selected.setReturnedDate(returnDate);
        selected.getBook().setAvailableCopies(selected.getBook().getAvailableCopies() + 1);
        System.out.println("Book returned successfully.");
    }

    private static void viewAllRecords() {
        System.out.println("\nDepartments");
        for (Department department : departments) {
            System.out.println(department.getRegistrationSummary());
        }
        System.out.println("\nStudents");
        for (Student s : students) {
            s.printDetails();
        }
        System.out.println("\nTeachers");
        for (Teacher t : teachers) {
            t.printDetails();
        }
        System.out.println("\nAdministrators");
        for (Administrator admin : administrators) {
            admin.printDetails();
        }
        System.out.println("\nCourses");
        for (Course course : courses) {
            course.printDetails();
        }
        System.out.println("\nClassrooms");
        for (Classroom classroom : classrooms) {
            classroom.printDetails();
        }
        System.out.println("\nEnrollments");
        for (Enrollment e : enrollments) {
            e.printDetails();
        }
        System.out.println("\nAssessments");
        for (Assessment assessment : assessments) {
            assessment.printDetails();
        }
        System.out.println("\nPayments");
        for (FeePayment payment : payments) {
            payment.printDetails();
        }
        System.out.println("\nBooks");
        for (LibraryBook book : books) {
            book.printDetails();
        }
        System.out.println("\nLoans");
        for (BookLoan loan : loans) {
            loan.printDetails();
        }
    }

    private static void searchById() {
        String id = readRequiredText("Enter ID to search: ");
        List<String> matches = new ArrayList<>();

        for (Department d : departments) if (d.getId().equalsIgnoreCase(id)) matches.add(d.getRegistrationSummary());
        for (Student s : students) if (s.getId().equalsIgnoreCase(id) || s.getStudentNumber().equalsIgnoreCase(id)) matches.add(s.getRegistrationSummary());
        for (Teacher t : teachers) if (t.getId().equalsIgnoreCase(id) || t.getEmployeeNumber().equalsIgnoreCase(id)) matches.add(t.getRegistrationSummary());
        for (Administrator a : administrators) if (a.getId().equalsIgnoreCase(id) || a.getStaffNumber().equalsIgnoreCase(id)) matches.add(a.getRegistrationSummary());
        for (Course c : courses) if (c.getId().equalsIgnoreCase(id) || c.getCourseCode().equalsIgnoreCase(id)) matches.add(c.getRegistrationSummary());
        for (Classroom c : classrooms) if (c.getId().equalsIgnoreCase(id)) matches.add(c.getRegistrationSummary());
        for (Enrollment e : enrollments) if (e.getId().equalsIgnoreCase(id)) matches.add(e.getRegistrationSummary());
        for (Assessment a : assessments) if (a.getId().equalsIgnoreCase(id)) matches.add(a.getRegistrationSummary());
        for (FeePayment p : payments) if (p.getId().equalsIgnoreCase(id) || p.getReceiptNumber().equalsIgnoreCase(id)) matches.add(p.getRegistrationSummary());
        for (LibraryBook b : books) if (b.getId().equalsIgnoreCase(id) || b.getIsbn().equalsIgnoreCase(id)) matches.add(b.getRegistrationSummary());
        for (BookLoan l : loans) if (l.getId().equalsIgnoreCase(id)) matches.add(l.getRegistrationSummary());

        if (matches.isEmpty()) {
            System.out.println("No matching record found.");
        } else {
            for (String m : matches) {
                System.out.println(m);
            }
        }
    }

    private static void updateRecord() {
        String id = readRequiredText("Record ID to update: ");

        for (Department d : departments) {
            if (d.getId().equalsIgnoreCase(id)) {
                d.setDepartmentName(readRequiredText("New department name: "));
                d.setDepartmentCode(readRequiredText("New department code: "));
                d.setOfficeLocation(readRequiredText("New office location: "));
                d.setContactEmail(readRequiredText("New contact email: "));
                System.out.println("Department updated.");
                return;
            }
        }
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                s.setFirstName(readRequiredText("New first name: "));
                s.setLastName(readRequiredText("New last name: "));
                s.setEmail(readRequiredText("New email: "));
                s.setPhoneNumber(readRequiredText("New phone: "));
                System.out.println("Student updated.");
                return;
            }
        }
        for (Teacher t : teachers) {
            if (t.getId().equalsIgnoreCase(id)) {
                t.setFirstName(readRequiredText("New first name: "));
                t.setLastName(readRequiredText("New last name: "));
                System.out.println("Teacher updated.");
                return;
            }
        }
        System.out.println("No record found to update.");
    }

    private static void showStudentAcademicSummary() {
        if (students.isEmpty()) {
            System.out.println("No student exists.");
            return;
        }
        Student student = chooseStudent();
        int enrollmentCount = 0;
        int assessmentCount = 0;
        double totalScore = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent() != null && enrollment.getStudent().getId().equals(student.getId())) {
                enrollmentCount++;
            }
        }
        for (Assessment assessment : assessments) {
            if (assessment.getEnrollment().getStudent() != null && assessment.getEnrollment().getStudent().getId().equals(student.getId())) {
                assessmentCount++;
                totalScore += assessment.getObtainedScore();
            }
        }
        System.out.println("Student: " + student.getDisplayName());
        System.out.println("Enrollments: " + enrollmentCount);
        System.out.println("Assessments: " + assessmentCount);
        System.out.println("Average score: " + (assessmentCount == 0 ? 0 : totalScore / assessmentCount));
    }

    private static void showPaymentHistory() {
        if (students.isEmpty()) {
            System.out.println("No student exists.");
            return;
        }
        Student student = chooseStudent();
        boolean found = false;
        for (FeePayment payment : payments) {
            if (payment.getStudent() != null && payment.getStudent().getId().equals(student.getId())) {
                found = true;
                System.out.println(payment.getRegistrationSummary());
            }
        }
        if (!found) {
            System.out.println("No payment history found for this student.");
        }
    }

    private static void showAvailableBooks() {
        boolean found = false;
        for (LibraryBook book : books) {
            if (book.getAvailableCopies() > 0) {
                found = true;
                System.out.println(book.getRegistrationSummary());
            }
        }
        if (!found) {
            System.out.println("No available books at the moment.");
        }
    }

    private static Department chooseDepartment() {
        if (departments.isEmpty()) {
            return null;
        }
        System.out.println("Available departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDepartmentName());
        }
        int selected = readInt("Select department number: ", 1, departments.size());
        return departments.get(selected - 1);
    }

    private static Teacher chooseTeacher() {
        if (teachers.isEmpty()) {
            return null;
        }
        System.out.println("Available teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getDisplayName());
        }
        int selected = readInt("Select teacher number: ", 1, teachers.size());
        return teachers.get(selected - 1);
    }

    private static Student chooseStudent() {
        if (students.isEmpty()) {
            return null;
        }
        System.out.println("Available students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getDisplayName());
        }
        int selected = readInt("Select student number: ", 1, students.size());
        return students.get(selected - 1);
    }

    private static Course chooseCourse() {
        if (courses.isEmpty()) {
            return null;
        }
        System.out.println("Available courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseTitle());
        }
        int selected = readInt("Select course number: ", 1, courses.size());
        return courses.get(selected - 1);
    }

    private static Enrollment chooseEnrollment() {
        if (enrollments.isEmpty()) {
            return null;
        }
        System.out.println("Available enrollments:");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ". " + enrollments.get(i).getRegistrationSummary());
        }
        int selected = readInt("Select enrollment number: ", 1, enrollments.size());
        return enrollments.get(selected - 1);
    }

    private static LibraryBook chooseBook() {
        if (books.isEmpty()) {
            return null;
        }
        System.out.println("Available books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getTitle());
        }
        int selected = readInt("Select book number: ", 1, books.size());
        return books.get(selected - 1);
    }

    private static BookLoan chooseLoan() {
        if (loans.isEmpty()) {
            return null;
        }
        System.out.println("Active loans:");
        for (int i = 0; i < loans.size(); i++) {
            if (loans.get(i).getReturnedDate() == null) {
                System.out.println((i + 1) + ". " + loans.get(i).getRegistrationSummary());
            }
        }
        int selected = readInt("Select loan number: ", 1, loans.size());
        return loans.get(selected - 1);
    }

    private static String readRequiredText(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (!value.isBlank()) {
                return value.trim();
            }
            System.out.println("This field is required. Please try again.");
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(readLine(prompt));
                if (value < min || value > max) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer between " + min + " and " + max + ".");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value.");
            }
        }
    }

    private static BigDecimal readBigDecimal(String prompt) {
        while (true) {
            try {
                return new BigDecimal(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid monetary amount.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            String input = readLine(prompt);
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Please use the format yyyy-MM-dd.");
            }
        }
    }

    private static boolean readBoolean(String prompt) {
        while (true) {
            String value = readLine(prompt).toLowerCase(Locale.ROOT);
            if (value.equals("true") || value.equals("false")) {
                return Boolean.parseBoolean(value);
            }
            System.out.println("Please enter true or false.");
        }
    }
}
