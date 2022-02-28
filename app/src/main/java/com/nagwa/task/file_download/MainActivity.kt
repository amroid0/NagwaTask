package com.nagwa.task.file_download

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nagwa.task.R
import com.nagwa.task.base.ViewModelFactory
import com.nagwa.task.utils.ResponseState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    var fileAdapter: FilesAdapter? = null

    @Inject
    lateinit var filesViewModelFactory: ViewModelFactory<FileViewModel>

    private val viewModel: FileViewModel by lazy {
        ViewModelProvider(this, filesViewModelFactory).get(FileViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
       setContentView( R.layout.activity_main)
        setupRecycle()
        observeData()
        viewModel.getFilesList()

    }

    private fun setupRecycle() {
        fileAdapter = FilesAdapter {
            viewModel.downloadFile(it)

        }
       recycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = fileAdapter
        }


    }

    private fun observeData() {
        viewModel.listState.observe(this, Observer {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    this,
                    "something went wrong !!",
                    Toast.LENGTH_SHORT
                ).show()
                ResponseState.Loading -> {
                    loading.visibility = View.VISIBLE
                   recycle.visibility = View.GONE

                }
                is ResponseState.Success -> {

                    loading.visibility = View.GONE
                    recycle.visibility = View.VISIBLE
                    fileAdapter?.setFileList(it.item)

                }
            }


        })

        viewModel.fileDownloadState.observe(this, Observer {
            fileAdapter?.notifyDataSetChanged()
        })


    }


}