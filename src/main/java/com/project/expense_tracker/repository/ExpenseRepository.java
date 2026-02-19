package com.project.expense_tracker.repository;

import com.project.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("SELECT sum(e.amount) FROM Expense e WHERE e.category =:category")
    Double getTotalByCategory(String category);

    @Query("SELECT e FROM Expense e WHERE e.category =:category ORDER BY e.date DESC, e.id DESC")
    List<Expense> listByCategory(String category);

    @Query("SELECT sum(e.amount) FROM Expense e")
    Double getTotalExpense();

    List<Expense> findByDateBetweenOrderByDateDescIdDesc(LocalDate start, LocalDate end);
}
