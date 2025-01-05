package com.practice.splitexpense.strategies;

import com.practice.splitexpense.dtos.Transaction;
import com.practice.splitexpense.models.Expense;
import com.practice.splitexpense.models.ExpenseType;
import com.practice.splitexpense.models.User;
import com.practice.splitexpense.models.UserExpense;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GreedySettleUpStrategy implements  SettleUpStrategy{
    public Map<Long,Double>  getNetBalance(List<Expense> expenses)
    {
        //this method is taking list of expense
        //and calc for each user, its net balnce(paid - actual share)

        Map<Long,Double> usersBalance = new HashMap<>();
        for(Expense expense : expenses)
        {
            //System.out.println(expense.getOwedBy().size() + " " + expense.getPaidBy().size());
            for(UserExpense userExpense:expense.getOwedBy())
            {
                if(userExpense.getType().equals(ExpenseType.PAID)) continue;
                //System.out.println(userExpense.getUser().getId() + " " + userExpense.getExpense().getId());
                User user = userExpense.getUser();
                Long id = user.getId();
                if(!usersBalance.containsKey(id))
                {
                    usersBalance.put(id, 0.0);
                }
//                else
//                {
                    usersBalance.put(id, usersBalance.get(id) - userExpense.getAmount());

            }

            for(UserExpense userExpense:expense.getPaidBy())
            {
                if(userExpense.getType().equals(ExpenseType.OWED)) continue;
                User user = userExpense.getUser();
                Long id = user.getId();
                if(!usersBalance.containsKey(id))
                {
                    usersBalance.put(id, 0.0);
                }
                //else
                //{
                    usersBalance.put(id, usersBalance.get(id) + userExpense.getAmount());
                //}
            }
        }
        usersBalance.forEach((key, value) -> System.out.println("User ID: " + key + " - Balance: " + value));
        return usersBalance;
    }
    @Override
    public List<Transaction>  settleUp(List<Expense> expenses) {
        Map<Long,Double> usersBalance = getNetBalance(expenses);
        //now we will divide the map into 2 containers
        //one for those who paid
        //other for those who have to pay
        //iterate the map
        TreeSet<Pair<Long, Double>> expenseTree = new TreeSet<>((left, right) -> (int) (left.getSecond() - right.getSecond()));
        for (Map.Entry<Long, Double> entry : usersBalance.entrySet()) {
            expenseTree.add(Pair.of(entry.getKey(), entry.getValue()));
        }
        List<Transaction> transactionList = new ArrayList<>();
        while(!expenseTree.isEmpty())
        {
            Pair<Long, Double> smallestPair = expenseTree.first();
            Pair<Long, Double> largestPair = expenseTree.last();
            System.out.println(largestPair.getFirst() + " " + largestPair.getSecond());
            System.out.println(smallestPair.getFirst() + " " + smallestPair.getSecond());
            if(smallestPair.getFirst().equals(largestPair.getFirst())) break;
            Transaction t = Transaction.builder()
                    .from(smallestPair.getFirst())
                    .to(largestPair.getFirst())
                    .amount(Math.min(-smallestPair.getSecond(),largestPair.getSecond()))
                    .build();

            expenseTree.remove(largestPair);
            expenseTree.remove(smallestPair);
            if((-smallestPair.getSecond()) < largestPair.getSecond())
            {
                expenseTree.add(Pair.of(largestPair.getFirst(),smallestPair.getSecond() + largestPair.getSecond() ));
            }
            else if((-smallestPair.getSecond()) > largestPair.getSecond())
            {
                expenseTree.add(Pair.of(smallestPair.getFirst(),smallestPair.getSecond() + largestPair.getSecond() ));
            }
            transactionList.add(t);
        }
        System.out.println(transactionList.size());
        return transactionList;
    }
}
