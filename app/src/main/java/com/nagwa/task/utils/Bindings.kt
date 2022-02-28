package com.nagwa.task.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nagwa.task.R
import com.nagwa.domain_layer.entities.FileState


@BindingAdapter("setState")
fun ProgressBar.setState(responseState: FileState) {
    visibility = if (responseState is FileState.DownLoading)
        View.VISIBLE
    else
        View.GONE
    progress = if (responseState is FileState.DownLoading) responseState.progress
    else
        100

    isIndeterminate=responseState is FileState.DownLoading&&responseState.progress==-1
}

@BindingAdapter("setState")
fun TextView.setState(responseState: FileState) {
    visibility = if (responseState is FileState.DownLoading&&responseState.progress!=-1)
        View.VISIBLE
    else
        View.GONE
    text = if (responseState is FileState.DownLoading) "${responseState.progress}%"
    else
        "100%"
}
@BindingAdapter("setState")
fun ImageView.setState(responseState: FileState) {
    visibility = if (responseState is FileState.DownLoading)
        View.GONE
    else
        View.VISIBLE
    when(responseState){
        FileState.Completed -> setImageResource(R.drawable.ic_baseline_check_circle_24)
        FileState.Failure->setImageResource(R.drawable.ic_baseline_retry_24)
        else->setImageResource(R.drawable.ic_baseline_cloud_download_24)
    }

}



