package com.project.expense_tracker.service;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.exception.InvalidDataException;
import com.project.expense_tracker.exception.ResourceNotFoundException;
import com.project.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    // inject repository
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Integer id) {
        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense ID not found - " + id));
    }

    @Override
    public Expense save(Expense expense) {
        if ( expense.getDate() == null) expense.setDate(LocalDate.now());

        String cat = expense.getCategory();

        // handling both null and blank
        if ( cat == null || cat.trim().isEmpty()) throw new InvalidDataException("Category must not be blank");
        else expense.setCategory(cat.trim().toLowerCase());

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Integer id) {
        findById(id);
        expenseRepository.deleteById(id);
    }

    @Override
    public Double getTotal() {

        Double result = expenseRepository.getTotalExpense();

        if ( result == null) return 0.0;

        return result;
    }

    @Override
    public Double getTotalByCategory(String category) {

        Double result;

        // handling both null and blank
        if ( category == null || category.trim().isEmpty()) {
            throw new InvalidDataException("Category must not be blank");
        }

        result = expenseRepository.getTotalByCategory(category.trim().toLowerCase());

        if ( result == null) return 0.0;

        return result;
    }

    @Override
    public List<Expense> listByCategory(String category) {

        if ( category == null || category.trim().isEmpty()) {
            throw new InvalidDataException("Category must not be blank");
        }
        return expenseRepository.listByCategory(category.trim().toLowerCase());
    }

    @Override
    public List<Expense> getExpensesInDateRange(LocalDate start, LocalDate end) {

        if (end == null) {
            end = LocalDate.now();
        }
        if (start == null) {
            start = end.minusDays(30);
        }
        if ( start.isAfter(end)) {
            throw new InvalidDataException("Start date cannot be after end date");
        }
        return expenseRepository.findByDateBetweenOrderByDateDescIdDesc(start, end);
    }

    @Override
    public Double getTotalForRange(LocalDate start, LocalDate end) {

        if ( end == null) end = LocalDate.now();
        if ( start == null) start = end.minusDays(30);
        if ( start.isAfter(end)) throw new InvalidDataException("Start date cannot be after end date");

        Double result = expenseRepository.getTotalForRange(start, end);

        if ( result == null) return 0.0;
        return result;
    }

    @Override
    public Double getTotalForMonth(Integer year, Integer month) {

        if ( year == null) year = LocalDate.now().getYear();
        if ( month == null) month = LocalDate.now().getMonthValue();

        if ( month < 1 || month > 12) throw new InvalidDataException("Month must be between 1 and 12");

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return getTotalForRange(start, end);
    }
}
