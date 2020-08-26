package com.example.demo.view.crud


import com.example.demo.controller.UserController
import com.example.demo.model.User
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.StageStyle
import tornadofx.*

class WithdrawFragment : Fragment("Withdraw") {
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
                field("Withdraw Amount")
                textfield(newBalanceInt)
            }
            button("Withdraw") {
                action {
                    userController.withdraw(
                            comboboxObject1.get(),
                            newNameString = comboboxObject1.value.name,
                            newIdInt = comboboxObject1.value.id,
                            newAccountString = comboboxObject1.value.account,
                            newBalanceInt = newBalanceInt.value
                    )
                    comboboxObject1.value = null
                    find<PopupDialog>(mapOf("message" to "Withdraw Successful")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}