package com.example.demo.view.crud


import com.example.demo.controller.UserController
import com.example.demo.model.User
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.StageStyle
import tornadofx.*

class DepositFragment : Fragment("Deposit") {
    private val comboboxObject1 = SimpleObjectProperty<User>()
    private val newBalanceInt = SimpleIntegerProperty()
    private val userController: UserController by inject()

    override val root = vbox {
        form {
            combobox<User>(comboboxObject1) {
                items = userController.users
                cellFormat {
                    text = this.item.name
                }
            }
            fieldset {
                field("Deposit Amount")
                textfield(newBalanceInt)
            }
            button("Deposit") {
                action {
                    userController.putUser(
                            comboboxObject1.get(),
                            newNameString = comboboxObject1.value.name,
                            newIdInt = comboboxObject1.value.id,
                            newAccountString = comboboxObject1.value.account,
                            newBalanceInt = newBalanceInt.value
                    )
                    comboboxObject1.value = null
                    find<PopupDialog>(mapOf("message" to "Deposit Successful")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}