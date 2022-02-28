package com.nagwa.task.file_download

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nagwa.task.R
import com.nagwa.task.databinding.ListFileRowBinding
import com.nagwa.domain_layer.entities.FileEntity
import com.nagwa.domain_layer.entities.FileState

class FilesAdapter(val onDownloadFile:(file:FileEntity)->Unit) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {
    private var fileList = listOf<FileEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = DataBindingUtil.inflate<ListFileRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_file_row,
            parent,
            false
        )
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bindData(fileList[position])
    }

    override fun getItemCount() = fileList.size

    inner class FileViewHolder(private val binding: ListFileRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(fileEntity: FileEntity) {
            binding.fileEntity=fileEntity
            binding.executePendingBindings()
            binding.imvState.setOnClickListener {
                if (fileEntity.state==FileState.Failure||fileEntity.state==FileState.Idle){
                    fileEntity.state=FileState.DownLoading(-1)
                    onDownloadFile(fileEntity)
                    notifyItemChanged(adapterPosition)
                }


            }
        }

    }
    fun setFileList(list: List<FileEntity>) {
        this.fileList=list
        notifyDataSetChanged()
    }

}