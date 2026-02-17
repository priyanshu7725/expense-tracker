package com.project.expense_tracker.service;

import com.project.expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> findAll();

    Expense findById(Integer id);

    Expense save(Expense expense);

    void deleteBydId(Integer id);
}
