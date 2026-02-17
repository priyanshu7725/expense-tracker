package com.project.expense_tracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="expense")
public class Expense {

    // define fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    // using nullable = false to validate with table schema in DB
    @Column(name="name", nullable = false)
    private String name;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name="category", nullable = false)
    private String category;

    // define non param and param constructor
    public Expense() {}

    public Expense(String name, Double amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }

    public Expense(String name, LocalDate date, Double amount, String category) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    // define setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // define toString

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                '}';
    }
}
