package com.auca.abstracts;

import com.auca.exceptions.ValidationException;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;

import java.time.LocalDate;

public abstract class Person implements Identifiable, Registrable {
    private String personId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    protected Person(String personId, String firstName, String lastName,
                     String email, String phoneNumber, LocalDate dateOfBirth) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return personId;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public boolean isActive() {
        return personId != null && !personId.isBlank();
    }

    public abstract String getRoleDescription();

    public abstract void validate() throws ValidationException;

    protected void validateBasePersonDetails() throws ValidationException {
        if (personId == null || personId.isBlank()) {
            throw new ValidationException("Person ID is required.");
        }
        if (firstName == null || firstName.trim().length() < Registrable.MIN_NAME_LENGTH || firstName.trim().length() > Registrable.MAX_NAME_LENGTH) {
            throw new ValidationException("First name must be between 2 and 60 characters.");
        }
        if (lastName == null || lastName.trim().length() < Registrable.MIN_NAME_LENGTH || lastName.trim().length() > Registrable.MAX_NAME_LENGTH) {
            throw new ValidationException("Last name must be between 2 and 60 characters.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") || email.length() > Registrable.MAX_EMAIL_LENGTH) {
            throw new ValidationException("Email is invalid or exceeds the maximum length.");
        }
        if (phoneNumber == null || !phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
            throw new ValidationException("Phone number must contain 10-15 digits, optionally starting with +.");
        }
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new ValidationException("Date of birth must be a valid date in the past.");
        }
    }

    public void printDetails() {
        System.out.println("Person: " + getDisplayName() + " (" + personId + ")");
    }

    public String getRegistrationSummary() {
        return getRoleDescription() + ": " + getDisplayName() + " (" + personId + ")";
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
