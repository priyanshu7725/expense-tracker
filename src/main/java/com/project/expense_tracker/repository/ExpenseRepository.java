package com.project.expense_tracker.repository;

import com.project.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("SELECT sum(e.amount) FROM Expense e WHERE e.category =:category")
    Double getTotalByCategory(String category);
}
