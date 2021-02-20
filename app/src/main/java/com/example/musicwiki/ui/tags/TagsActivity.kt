package com.example.musicwiki.ui.tags

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.musicwiki.BaseActivity
import com.example.musicwiki.R
import com.example.musicwiki.ViewModelProviderFactory
import com.example.musicwiki.databinding.ActivityTagsBinding
import com.example.musicwiki.models.tags.Tag
import com.example.musicwiki.ui.adapter.TagsAdapter
import com.example.musicwiki.ui.genresDetail.GenreDetailActivity
import com.example.musicwiki.utils.OnClickListener
import com.example.musicwiki.utils.Status
import javax.inject.Inject

class TagsActivity : BaseActivity<ActivityTagsBinding>(), OnClickListener {

    @Inject
    lateinit var adapter: TagsAdapter

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var binding: ActivityTagsBinding

    private lateinit var viewmodel: TagsViewModel

    private var isExpanded = false

    override val layout: Int
        get() = R.layout.activity_tags

    override val toolbarVisible: Boolean
        get() = false

    override fun init(bind: ActivityTagsBinding) {
        binding = bind
        viewmodel = ViewModelProvider(this, factory).get(TagsViewModel::class.java)
        binding.recyclerView.adapter = adapter
        tagsObserver()
    }

    private fun tagsObserver() {
        viewmodel.observeTags().observe(this, Observer {
            if (it != null)
                when (it.status) {
                    Status.LOADING -> adapter.displayLoading()
                    Status.SUCCESS -> setAdapter(it.data?.toptags?.tag as ArrayList)
                    Status.ERROR -> adapter.displayError(it.message!!)
                }
        })
    }

    private fun setAdapter(list: ArrayList<Tag>) {
        binding.imageViewExpand.setOnClickListener {
            if (!isExpanded) {
                isExpanded = true
                requestManager.load(R.drawable.ic_baseline_arrow_circle_up_24)
                    .into(binding.imageViewExpand)
                adapter.expandList(list.size)
            } else {
                isExpanded = false
                requestManager.load(R.drawable.ic_baseline_arrow_circle_down_24)
                    .into(binding.imageViewExpand)
                adapter.expandList(10)
            }
        }
        adapter.setData(list, this)
        adapter.expandList(10)
    }

    override fun tagClickListener(tag: Tag) {
        Toast.makeText(this, tag.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this,GenreDetailActivity::class.java)
        intent.putExtra(com.example.musicwiki.utils.TAG,tag.name)
        startActivity(intent)
    }

}