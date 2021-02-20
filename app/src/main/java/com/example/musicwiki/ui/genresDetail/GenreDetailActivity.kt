package com.example.musicwiki.ui.genresDetail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.BaseActivity
import com.example.musicwiki.R
import com.example.musicwiki.ViewModelProviderFactory
import com.example.musicwiki.databinding.ActivityGenreDetailBinding
import com.example.musicwiki.models.tagDetail.TagDetail
import com.example.musicwiki.ui.adapter.ViewPagerFragmentAdapter
import com.example.musicwiki.utils.Status
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import javax.inject.Inject

class GenreDetailActivity : BaseActivity<ActivityGenreDetailBinding>() {

    override val layout: Int
        get() = R.layout.activity_genre_detail
    override val toolbarVisible: Boolean
        get() = true

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: GenreDetailViewModel

    private var tagName: String? = null

    // tab titles
    private val titles =
        arrayOf("Albums", "Artists", "Tracks")

    private lateinit var binding: ActivityGenreDetailBinding

    override fun init(bind: ActivityGenreDetailBinding) {
        binding = bind
        tagName = intent.getStringExtra(com.example.musicwiki.utils.TAG)
        binding.viewPager.adapter = ViewPagerFragmentAdapter(this,tagName)
        viewModel = ViewModelProvider(this, factory).get(GenreDetailViewModel::class.java)
        genreDetailObserver()
        TabLayoutMediator(binding.tabs, binding.viewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = titles[position]
            }
        ).attach()
    }

    private fun genreDetailObserver() {
        viewModel.observeTagDetail(tagName!!).observe(this, Observer {
            if (it != null)
                when (it.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        setData(it.data)
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message!!, Toast.LENGTH_SHORT).show()
                    }
                }
        })
    }

    private fun setData(data: TagDetail?) {
        binding.tagDetail = data
    }
}