package com.example.demo.utilities

import com.example.demo.model.User
import java.sql.Timestamp

class UserDao {
    fun addUser(user: User) {
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("INSERT INTO Users(name, idNumber, account, balance) VALUES(?, ?, ?, ?)")
        preparedStatement.setString(1, user.name)
        preparedStatement.setInt(2, user.id)
        preparedStatement.setString(3, user.account)
        preparedStatement.setInt(4, user.balance)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }

    fun readUser(): List<User> {
        val connection = Database().connection
        val resultSet = connection
                .createStatement()
                .executeQuery("SELECT * FROM Users WHERE date_deleted IS NULL")
        val userList = ArrayList<User>()
        while (resultSet.next()) {
            val name = resultSet.getString("name")
            val id = resultSet.getInt("idNumber")
            val account = resultSet.getString("account")
            val balance = resultSet.getInt("balance")
            userList += User(name, id, account, balance)
        }
        resultSet.close()
        connection.close()
        return userList
    }

    fun updateUser(name: String, user: User): User {
        val connection =  Database().connection
        var param = ""
        val paramId = ", idNumber = ? "
        var optionalParamIndex1 = 2
        print(user)
        if (user.id.toString().isNotEmpty()) param = paramId
        val preparedStatement = connection.prepareStatement("UPDATE Users SET name = ? $param WHERE name = ?")
        preparedStatement.setString(1, user.name)
        if (param.isNotEmpty()) {
            preparedStatement.setInt(optionalParamIndex1, user.id)
            optionalParamIndex1  = optionalParamIndex1.inc()
        }
        preparedStatement.setString(optionalParamIndex1, name)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
        return user

    }

    fun deleteUser(name: String) {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("UPDATE Users SET date_deleted = ? WHERE name = ?")
        preparedStatement.setString(1, timeStamp.toString())
        preparedStatement.setString(2, name)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }
}

