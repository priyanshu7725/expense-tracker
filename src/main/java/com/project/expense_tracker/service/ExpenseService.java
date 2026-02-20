package com.project.expense_tracker.service;

import com.project.expense_tracker.entity.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    List<Expense> findAll();

    Expense findById(Integer id);

    Expense save(Expense expense);

    void deleteById(Integer id);

    Double getTotal();

    Double getTotalByCategory(String category);

    List<Expense> listByCategory(String category);

    List<Expense> getExpensesInDateRange(LocalDate start, LocalDate end);

    Double getTotalForRange(LocalDate start, LocalDate end);
}
