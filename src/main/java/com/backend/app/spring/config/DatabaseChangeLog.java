package com.backend.app.spring.config;

import com.backend.app.spring.repository.ExpenseRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.backend.app.spring.models.Expense;
import com.backend.app.spring.models.ExpenseCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.backend.app.spring.models.ExpenseCategory.*;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedDatabase", author = "Dawid")
    public void seedDatabase(ExpenseRepository expenseRepository){
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(createNewExpense("Movie Tickets", ENTERTAINMENT, BigDecimal.valueOf(60)));
        expenseList.add(createNewExpense("Netflix", ENTERTAINMENT, BigDecimal.valueOf(40)));
        expenseList.add(createNewExpense("Gym", MISC, BigDecimal.valueOf(30)));
        expenseList.add(createNewExpense("Internet", UTILITIES, BigDecimal.valueOf(10)));
        expenseList.add(createNewExpense("Dinner", RESTAURANT, BigDecimal.valueOf(20)));

        expenseRepository.insert(expenseList);
    }

    private Expense createNewExpense(String expenseName, ExpenseCategory expenseCategory, BigDecimal amount){
        Expense expense = new Expense();
        expense.setExpenseName(expenseName);
        expense.setExpenseAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        return expense;
    }
}


