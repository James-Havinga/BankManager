package com.example.demo.utilities

import com.example.demo.model.Account
import com.example.demo.model.User

class AccountDao {

    fun readAccount(): List<Account> {
        val connection = Database().connection
        val resultSet = connection
                .createStatement()
                .executeQuery("SELECT * FROM Accounts")
        val accountList = ArrayList<Account>()
        while (resultSet.next()) {
            val name = resultSet.getString("account_name")
            val fee = resultSet.getInt("fee")
            val interest = resultSet.getInt("interest")
            val freeTransactions = resultSet.getInt("free_transactions")
            accountList += Account(name, fee, interest, freeTransactions)
        }
        resultSet.close()
        connection.close()
        return accountList
    }
}