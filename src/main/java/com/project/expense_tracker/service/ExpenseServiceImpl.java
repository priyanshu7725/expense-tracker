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
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Integer id) {
        expenseRepository.deleteById(id);
    }
}
