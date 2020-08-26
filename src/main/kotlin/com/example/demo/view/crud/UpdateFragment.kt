package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.controller.UserController
import com.example.demo.model.Account
import com.example.demo.model.User
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import tornadofx.*

class UpdateFragment : Fragment("Edit") {
    private val comboboxObject1 = SimpleObjectProperty<User>()
    private val comboboxObject2 = SimpleObjectProperty<Account>()
    private val newNameString = SimpleStringProperty()
    private val newIdInt = SimpleIntegerProperty()
    private val userController: UserController by inject()
    private val accountController: AccountController by inject()

    override val root = vbox {
        form {
            combobox<User>(comboboxObject1) {
                items = userController.users
                cellFormat {
                    text = this.item.name
                }
            }
            fieldset {
                field("New Name")
                textfield(newNameString)
                field("New Id Number")
                textfield(newIdInt)
                field("Account Type")
                combobox<Account>(comboboxObject2) {
                    items = accountController.accounts
                    cellFormat {
                        text = this.item.account_name
                    }
                }
            }
            button("Update User") {
                action {
                        userController.putUser(
                        comboboxObject1.get(),
                        newNameString.value,
                        newIdInt.intValue(),
                        comboboxObject2.value.account_name,
                        newBalanceInt = comboboxObject1.value.balance
                    )
                    newNameString.value = ""; newIdInt.value = 0; comboboxObject1.value = null
                    find<PopupDialog>(mapOf("message" to "User Updated")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}