package com.project.expense_tracker.service;

import com.project.expense_tracker.entity.Expense;
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
                        new RuntimeException("Expense Id - " + id + " was not found"));
    }

    @Override
    public Expense save(Expense expense) {
        if ( expense.getDate() == null) expense.setDate(LocalDate.now());

        String cat = expense.getCategory();

        // handling both null and blank
        if ( cat == null || cat.trim().isEmpty()) throw new RuntimeException("Category cannot be blank");
        else expense.setCategory(cat.trim().toLowerCase());

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Integer id) {
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
            throw new RuntimeException("Category cannot be blank");
        }

        result = expenseRepository.getTotalByCategory(category.trim().toLowerCase());

        if ( result == null) return 0.0;

        return result;
    }

    @Override
    public List<Expense> listByCategory(String category) {

        if ( category == null || category.trim().isEmpty()) {
            throw new RuntimeException("Category cannot be blank");
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
            throw new RuntimeException("Invalid Date Range");
        }
        return expenseRepository.findByDateBetweenOrderByDateDescIdDesc(start, end);
    }
}
