package com.matejdev.demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.matejdev.demo.R
import com.matejdev.demo.base.extensions.baseActivity
import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.base.view.ViewModelFragment
import com.matejdev.demo.databinding.FragmentUserListBinding
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.view.adapter.UserListAdapter
import com.matejdev.demo.presentation.viewmodel.UserListViewModel
import com.matejdev.demo.presentation.viewmodel.state.UserListState
import kotlinx.android.synthetic.main.fragment_user_list.userListRecycler
import javax.inject.Inject

/**
 * Displays a user list
 */
class UserListFragment : ViewModelFragment<UserListViewModel, FragmentUserListBinding>(), UserListAdapter.ItemClickListener {

    companion object {
        fun newInstance() = UserListFragment().apply {
            val args = Bundle()
            this.arguments = args
        }
    }

    @Inject
    lateinit var navigator: BrowserNavigator

    private val stateObserver = Observer<UserListState> { state ->
        state.apply {
            showLoader?.let { baseActivity().showLoader(it) }
            showError?.let { navigator.showError() }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_user_list

    override fun getViewModelClass() = UserListViewModel::class.java

    override fun inflateAndBindModel(inflater: LayoutInflater, container: ViewGroup?) =
        inflateView(inflater, container).apply { model = this@UserListFragment.model }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpModelObservers()
    }

    override fun onResume() {
        super.onResume()
        setUpInitialUiState()
    }

    override fun onItemClick(view: View, user: UserModel) {
        LogUtil.logClick(user.id)
        navigator.showPosts(user)
    }

    private fun setUpRecyclerView() {
        userListRecycler.let {
            it.addItemDecoration(DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL))
            it.layoutManager = LinearLayoutManager(this.activity)
            it.adapter = UserListAdapter().apply { itemClickListener = this@UserListFragment }
        }
    }

    private fun setUpModelObservers() {
        model.state.observe(this, stateObserver)
    }

    private fun setUpInitialUiState() {
        baseActivity().apply {
            showUpButton(false)
            title = getString(R.string.fragment_user_list_title)
        }
    }
}
