package com.matejdev.demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.matejdev.demo.R
import com.matejdev.demo.base.extensions.argNotNull
import com.matejdev.demo.base.extensions.baseActivity
import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.base.view.ViewModelFragment
import com.matejdev.demo.databinding.FragmentPostListBinding
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.view.adapter.PostListAdapter
import com.matejdev.demo.presentation.viewmodel.PostListViewModel
import com.matejdev.demo.presentation.viewmodel.state.PostListState
import kotlinx.android.synthetic.main.fragment_post_list.postListRecycler
import javax.inject.Inject

class PostListFragment : ViewModelFragment<PostListViewModel, FragmentPostListBinding>(), PostListAdapter.ItemClickListener {

    companion object {
        private const val USER_KEY = "userKey"

        fun newInstance(user: UserModel) = PostListFragment().apply {
            val args = Bundle()
            args.putSerializable(USER_KEY, user)
            this.arguments = args
        }
    }

    private val user: UserModel by argNotNull(USER_KEY)

    @Inject
    lateinit var navigator: BrowserNavigator

    private val stateObserver = Observer<PostListState> { state ->
        state.apply {
            showLoader?.let { baseActivity().showLoader(it) }
            showError?.let { navigator.showError() }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_post_list

    override fun getViewModelClass() = PostListViewModel::class.java

    override fun inflateAndBindModel(inflater: LayoutInflater, container: ViewGroup?) =
        inflateView(inflater, container).apply { model = this@PostListFragment.model }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpModelObservers()
        setUpModelData()
        model.loadPosts(user.id)
    }

    override fun onResume() {
        super.onResume()
        setUpInitialUiState()
    }

    override fun onItemClick(view: View, post: PostModel) {
        LogUtil.logClick(post.id)
        navigator.showComments(post)
    }

    private fun setUpRecyclerView() {
        postListRecycler.let {
            it.addItemDecoration(DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL))
            it.layoutManager = LinearLayoutManager(this.activity)
            it.adapter = PostListAdapter().apply { itemClickListener = this@PostListFragment }
        }
    }

    private fun setUpModelObservers() {
        model.state.observe(this, stateObserver)
    }

    private fun setUpModelData() {
        model.user.value = user
    }

    private fun setUpInitialUiState() {
        baseActivity().apply {
            showUpButton(true)
            title = getString(R.string.fragment_post_list_title)
        }
    }
}
