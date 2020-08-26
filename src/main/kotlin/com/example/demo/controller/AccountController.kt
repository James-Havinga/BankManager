package com.example.demo.controller

import com.example.demo.model.Account
import com.example.demo.model.User
import com.example.demo.utilities.AccountDao
import com.example.demo.utilities.UserDao
import tornadofx.*

class AccountController : Controller() {
    val accounts = SortedFilteredList(items = getAllUser().observable())

    private fun getAllUser(): List<Account> = AccountDao().readAccount()
}