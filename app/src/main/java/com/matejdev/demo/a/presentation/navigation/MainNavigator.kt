package com.matejdev.demo.a.presentation.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.matejdev.demo.R
import com.matejdev.demo.a.domain.model.ToDoModel
import com.matejdev.demo.a.presentation.view.ToDoListFragmentDirections
import com.matejdev.demo.a.presentation.view.WelcomeFragmentDirections

class MainNavigator(
    private val activity: FragmentActivity
) {

    fun showToDoList() {
        navController().navigate(
            WelcomeFragmentDirections.actionWelcomeToTodoList()
        )
    }

    fun showToDoCount() {
        navController().navigate(
            WelcomeFragmentDirections.actionWelcomeToTodoCount()
        )
    }

    fun showToDo(toDoModel: ToDoModel) {
        navController().navigate(
            ToDoListFragmentDirections.actionTodoListToTodo(toDoModel)
        )
    }

    private fun navController() = activity.findNavController(R.id.navHostFragment)
}
