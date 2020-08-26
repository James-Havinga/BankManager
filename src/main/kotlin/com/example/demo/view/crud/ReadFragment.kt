package com.example.demo.view.crud

import com.example.demo.controller.UserController
import com.example.demo.model.User
import tornadofx.*

class ReadFragment : Fragment("Users") {
    private val userController: UserController by inject()
    override val root = listview<User> {
        items = userController.users
        cellFormat {
            text = this.item.name + "   R" + this.item.balance.toString()
            onDoubleClick {
                if (selectedItem != null) {

                }
            }
        }
    }
}