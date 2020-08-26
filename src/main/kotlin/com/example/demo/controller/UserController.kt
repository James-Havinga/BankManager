package com.example.demo.controller

import com.example.demo.model.Account
import com.example.demo.model.User
import com.example.demo.utilities.UserDao
import tornadofx.*

class UserController : Controller() {
    val users = SortedFilteredList(items = getAllUser().observable())
    fun postUser(name: String, id: Int, account: String, balance: Int) {
        val user = User(name, id, account, balance)
        val dao = UserDao()
        dao.addUser(user)
        users += user
    }

    private fun getAllUser(): List<User> = UserDao().readUser()

    fun putUser(oldUser: User, newNameString: String, newIdInt: Int, newAccountString: String, newBalanceInt: Int) {
        val newAmount = newBalanceInt + oldUser.balance
        val newUser = User(newNameString, newIdInt, newAccountString, newAmount)
        val dao = UserDao()
        dao.updateUser(oldUser.name, newUser)
        with(users) {
            remove(oldUser)
            add(newUser)
        }
    }

    fun deleteUser(user: User) {
        val dao = UserDao()
        dao.deleteUser(user.name)
        users.remove(user)
    }

    fun withdraw(oldUser: User, newNameString: String, newIdInt: Int, newAccountString: String, newBalanceInt: Int) {
        val newAmount = oldUser.balance - newBalanceInt
        val newUser = User(newNameString, newIdInt, newAccountString, newAmount)
        val dao = UserDao()
        dao.updateUser(oldUser.name, newUser)
        with(users) {
            remove(oldUser)
            add(newUser)
        }
    }
}