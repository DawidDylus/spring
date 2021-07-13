package com.backend.app.spring.services;

import com.backend.app.spring.models.Expense;
import com.backend.app.spring.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }

    public Expense updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", expense.getId())));

        savedExpense.setExpenseName((expense.getExpenseName()));
        savedExpense.setExpenseCategory((expense.getExpenseCategory()));
        savedExpense.setExpenseAmount((expense.getExpenseAmount()));

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(){
       return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name){
       return expenseRepository.findByName(name)
               .orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Expense by Name %s", name)));
    }

    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }
}
