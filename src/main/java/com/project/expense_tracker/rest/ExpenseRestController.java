package com.project.expense_tracker.rest;

import com.project.expense_tracker.entity.Expense;
import com.project.expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseRestController {

    // define service as a field
    private final ExpenseService expenseService;

    // inject service
    @Autowired
    public ExpenseRestController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // GET - view all expense
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }

    // GET - view an expense by id
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Integer id) {
        return expenseService.findById(id);
    }

    // POST - add an expense
    @PostMapping("/expenses")
    public Expense createExpense(@RequestBody Expense expense) {
        expense.setId(null);
        return expenseService.save(expense);
    }

    // PUT - update an expense
    @PutMapping("/expenses")
    public Expense updateExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

    // DELETE - delete an expense
    @DeleteMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable Integer id) {

        Expense expense = expenseService.findById(id);

        if ( expense == null) {
            throw new RuntimeException("Expense ID not found - " + id);
        }

        expenseService.deleteById(id);

        return "Deleted Expense Id - " + id;
    }

    // exposing endpoint for getting total
    @GetMapping("/expenses/total")
    public Double getTotalExpense() {
        return expenseService.getTotal();
    }

    // exposing endpoint for getting total by category
    @GetMapping("/expenses/total/{category}")
    public Double getTotalByCategory(@PathVariable String category) {
        return expenseService.getTotalByCategory(category);
    }

    // exposing endpoint for getting list of expense by category
    @GetMapping("/expenses/category/{category}")
    public List<Expense> getListByCategory(@PathVariable String category) {
        return expenseService.listByCategory(category);
    }
}
