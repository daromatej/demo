package com.matejdev.demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.matejdev.demo.R
import com.matejdev.demo.base.extensions.argNotNull
import com.matejdev.demo.base.extensions.baseActivity
import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.base.view.ViewModelFragment
import com.matejdev.demo.databinding.FragmentCommentListBinding
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.view.adapter.CommentListAdapter
import com.matejdev.demo.presentation.viewmodel.CommentListViewModel
import com.matejdev.demo.presentation.viewmodel.state.CommentListState
import kotlinx.android.synthetic.main.fragment_comment_list.commentListRecycler
import javax.inject.Inject

class CommentListFragment : ViewModelFragment<CommentListViewModel, FragmentCommentListBinding>(), CommentListAdapter.ItemClickListener {

    companion object {
        private const val POST_KEY = "postKey"

        fun newInstance(post: PostModel) = CommentListFragment().apply {
            val args = Bundle()
            args.putSerializable(POST_KEY, post)
            this.arguments = args
        }
    }

    private val post: PostModel by argNotNull(POST_KEY)

    @Inject
    lateinit var navigator: BrowserNavigator

    private val stateObserver = Observer<CommentListState> { state ->
        state.apply {
            showLoader?.let { baseActivity().showLoader(it) }
            showError?.let { navigator.showError() }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_comment_list

    override fun getViewModelClass() = CommentListViewModel::class.java

    override fun inflateAndBindModel(inflater: LayoutInflater, container: ViewGroup?) =
        inflateView(inflater, container).apply { model = this@CommentListFragment.model }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpModelObservers()
        setUpModelData()
        model.loadComments(post.id)
    }

    override fun onResume() {
        super.onResume()
        setUpInitialUiState()
    }

    override fun onItemClick(view: View, comment: CommentModel) {
        LogUtil.logClick(comment.id)
    }

    private fun setUpRecyclerView() {
        commentListRecycler.let {
            it.layoutManager = LinearLayoutManager(this.activity)
            it.adapter = CommentListAdapter().apply { itemClickListener = this@CommentListFragment }
        }
    }

    private fun setUpModelObservers() {
        model.state.observe(this, stateObserver)
    }

    private fun setUpModelData() {
        model.post.value = post
    }

    private fun setUpInitialUiState() {
        baseActivity().apply {
            showUpButton(true)
            title = getString(R.string.fragment_comment_list_title)
        }
    }
}
