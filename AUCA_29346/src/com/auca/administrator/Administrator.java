package com.auca.administrator;

import com.auca.abstracts.Person;
import com.auca.exceptions.ValidationException;

import java.time.LocalDate;

public class Administrator extends Person {
    private String staffNumber;
    private String jobTitle;
    private String assignedOffice;
    private LocalDate employmentDate;
    private int accessLevel;
    private boolean active;

    public Administrator(String personId, String firstName, String lastName, String email,
                        String phoneNumber, LocalDate dateOfBirth, String staffNumber,
                        String jobTitle, String assignedOffice, LocalDate employmentDate,
                        int accessLevel, boolean active) {
        super(personId, firstName, lastName, email, phoneNumber, dateOfBirth);
        this.staffNumber = staffNumber;
        this.jobTitle = jobTitle;
        this.assignedOffice = assignedOffice;
        this.employmentDate = employmentDate;
        this.accessLevel = accessLevel;
        this.active = active;
    }

    @Override
    public String getRoleDescription() {
        return "Administrator";
    }

    @Override
    public void validate() throws ValidationException {
        validateBasePersonDetails();
        if (staffNumber == null || staffNumber.isBlank()) {
            throw new ValidationException("Staff number is required.");
        }
        if (jobTitle == null || jobTitle.trim().length() < 2 || jobTitle.trim().length() > 60) {
            throw new ValidationException("Job title must be between 2 and 60 characters.");
        }
        if (assignedOffice == null || assignedOffice.isBlank()) {
            throw new ValidationException("Assigned office is required.");
        }
        if (employmentDate == null || employmentDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Employment date must be valid and not in the future.");
        }
        if (accessLevel < 0) {
            throw new ValidationException("Access level must be zero or greater.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println("Administrator: " + getDisplayName() + " | ID " + getId() + " | Staff no: " + staffNumber);
    }

    @Override
    public String getRegistrationSummary() {
        return "Administrator: " + getDisplayName() + " (" + staffNumber + ")";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }

    public void setAssignedOffice(String assignedOffice) {
        this.assignedOffice = assignedOffice;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public boolean isActiveFlag() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
