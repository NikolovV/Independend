package Exams.VaniPlanning;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AgencyImpl implements Agency {
    private final Map<String, Invoice> invoiceByNumber;
    private final Map<LocalDate, Set<Invoice>> invoicesByDueDate;
    private final Set<Invoice> payedInvoices;

    public AgencyImpl() {
        this.invoiceByNumber = new HashMap<>();
        this.invoicesByDueDate = new HashMap<>();
        this.payedInvoices = new HashSet<>();
    }

    @Override
    public void create(Invoice invoice) {
        String invoiceNumber = invoice.getNumber();
        LocalDate dueDate = invoice.getDueDate();

        if (this.invoiceByNumber.containsKey(invoiceNumber)) {
            throw new IllegalArgumentException();
        }

        this.invoiceByNumber.putIfAbsent(invoiceNumber, invoice);
        this.invoicesByDueDate.putIfAbsent(dueDate, new HashSet<>());
        this.invoicesByDueDate.get(dueDate).add(invoice);
    }

    @Override
    public boolean contains(String number) {
        return this.invoiceByNumber.containsKey(number);
    }

    @Override
    public int count() {
        return this.invoiceByNumber.size();
    }

    @Override
    public void payInvoice(LocalDate dueDate) {
        if (!this.invoicesByDueDate.containsKey(dueDate)) {
            throw new IllegalArgumentException();
        }

        this.invoicesByDueDate.get(dueDate)
                .forEach(invoice -> {
                    invoice.setSubtotal(0);
                    this.payedInvoices.add(invoice);
                });
    }

    @Override
    public void throwInvoice(String number) {
        if (!this.invoiceByNumber.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        Invoice invoiceToRemove = this.invoiceByNumber.get(number);
        this.invoiceByNumber.remove(number);
        this.invoicesByDueDate.get(invoiceToRemove.getDueDate()).remove(invoiceToRemove);
        this.payedInvoices.remove(invoiceToRemove);
    }

    @Override
    public void throwPayed() {
        this.payedInvoices.forEach(invoice -> {
            this.invoiceByNumber.remove(invoice.getNumber());
            this.invoicesByDueDate.get(invoice.getDueDate()).remove(invoice);
        });
        this.payedInvoices.clear();
    }

    @Override
    public Iterable<Invoice> getAllInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        return this.invoiceByNumber.values().stream()
                .filter(invoice -> invoice.getIssueDate().compareTo(startDate) >= 0 &&
                        invoice.getIssueDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(Invoice::getIssueDate)
                        .thenComparing(Invoice::getDueDate))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> searchByNumber(String number) {
        List<Invoice> result = this.invoiceByNumber.values().stream()
                .filter(x -> x.getNumber().contains(number))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Invoice> throwInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = new ArrayList<>();
        this.invoiceByNumber.values().forEach(invoice ->
        {
            if (invoice.getDueDate().compareTo(startDate) > 0 &&
                    invoice.getDueDate().compareTo(endDate) < 0) {
                result.add(invoice);
            }
        });

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (Invoice invoice : result) {
            this.invoiceByNumber.remove(invoice.getNumber());
            this.invoicesByDueDate.remove(invoice.getDueDate());
            this.payedInvoices.remove(invoice);
        }

        return result;
    }

    @Override
    public Iterable<Invoice> getAllFromDepartment(Department department) {
        return this.invoiceByNumber.values().stream()
                .filter(invoice -> invoice.getDepartment().equals(department))
                .sorted(Comparator.comparing(Invoice::getSubtotal, Comparator.reverseOrder())
                        .thenComparing(Invoice::getIssueDate))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> getAllByCompany(String companyName) {
        return this.invoiceByNumber.values().stream()
                .filter(invoice -> invoice.getCompanyName().equals(companyName))
                .sorted(Comparator.comparing(Invoice::getNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void extendDeadline(LocalDate endDate, int days) {
        if (!this.invoicesByDueDate.containsKey(endDate)) {
            throw new IllegalArgumentException();
        }

        this.invoicesByDueDate.get(endDate).forEach(invoice -> invoice.setDueDate(endDate.plusDays(days)));
    }
}
