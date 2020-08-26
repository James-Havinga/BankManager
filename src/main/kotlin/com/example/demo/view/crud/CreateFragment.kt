package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.controller.UserController
import com.example.demo.model.Account
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import tornadofx.*

class CreateFragment : Fragment("Create") {
    private val nameString = SimpleStringProperty()
    private val idInt = SimpleIntegerProperty()
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val userController: UserController by inject()
    private val accountController: AccountController by inject()
    override val root = vbox {
        form {
            fieldset {
                field("Name")
                textfield(nameString)
                field("ID Number")
                textfield(idInt)
                field("Account Type")
                combobox<Account>(comboboxObject) {
                    items = accountController.accounts
                    cellFormat {
                        text = this.item.account_name
                    }
                }
            }
            button("Create User") {
                action {
                    userController.postUser(nameString.value, idInt.value, comboboxObject.value.account_name, balance = 0)
                    nameString.value = ""; idInt.value = 0
                    find<PopupDialog>(params = mapOf("message" to "New User Added")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}