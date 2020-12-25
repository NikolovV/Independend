package Exams.VaniPlanning;

import java.time.LocalDate;

public class Invoice {

    private String number;
    private String companyName;
    private double subtotal;
    private Department department;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Invoice(String number, String companyName, double subtotal, Department department, LocalDate issueDate, LocalDate dueDate) {
        this.number = number;
        this.companyName = companyName;
        this.subtotal = subtotal;
        this.department = department;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        return this.getNumber().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Invoice other = (Invoice) o;
        return this.number.equals(other.number) &&
                this.companyName.equals(other.companyName) &&
                Double.compare(other.subtotal, this.subtotal) == 0 &&
                this.department.equals(other.department) &&
                this.issueDate.compareTo(other.issueDate) == 0 &&
                this.dueDate.compareTo(other.dueDate) == 0;
    }
}
