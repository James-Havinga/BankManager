package com.example.demo.view.crud

import com.example.demo.model.User
import com.example.demo.controller.UserController
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.StageStyle
import tornadofx.*

class DeleteFragment : Fragment("Delete") {
    private val comboboxObject = SimpleObjectProperty<User>()
    private val userController: UserController by inject()
    override val root = vbox {
        form{
            combobox<User>(comboboxObject) {
                items = userController.users
                cellFormat {
                    text = this.item.name
                }
            }
            button("Delete User") {
                action {
                    userController.deleteUser(comboboxObject.get())
                    comboboxObject.value = null
                    find<PopupDialog>(mapOf("message" to "User Deleted")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}