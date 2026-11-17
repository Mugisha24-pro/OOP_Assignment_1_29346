package com.auca.feepayment;

import com.auca.exceptions.ValidationException;
import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.student.Student;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FeePayment implements Identifiable, Registrable {
    private String paymentId;
    private Student student;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String receiptNumber;

    public FeePayment(String paymentId, Student student, BigDecimal amount,
                     LocalDate paymentDate, String paymentMethod, String receiptNumber) {
        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String getId() {
        return paymentId;
    }

    @Override
    public String getDisplayName() {
        return student == null ? "Unknown student" : student.getDisplayName();
    }

    @Override
    public boolean isActive() {
        return paymentId != null && !paymentId.isBlank();
    }

    @Override
    public void validate() throws ValidationException {
        if (paymentId == null || paymentId.isBlank()) {
            throw new ValidationException("Payment ID is required.");
        }
        if (student == null) {
            throw new ValidationException("Student is required.");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Amount must be strictly positive.");
        }
        if (paymentDate == null || paymentDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Payment date must be valid and not in the future.");
        }
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new ValidationException("Payment method is required.");
        }
        if (receiptNumber == null || receiptNumber.isBlank()) {
            throw new ValidationException("Receipt number is required.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println("FeePayment: " + student.getDisplayName() + " - " + amount + " via " + paymentMethod);
    }

    @Override
    public String getRegistrationSummary() {
        return "FeePayment: " + student.getDisplayName() + " | " + amount + " | receipt " + receiptNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
