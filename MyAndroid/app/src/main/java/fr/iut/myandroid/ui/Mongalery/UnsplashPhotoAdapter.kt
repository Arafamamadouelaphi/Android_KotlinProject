package fr.iut.myandroid.ui.Mongalery

 import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import fr.iut.myandroid.R
import fr.iut.myandroid.data.PhotoUnsplash
import fr.iut.myandroid.databinding.ItemUnsplashPhotoBinding

class UnsplashPhotoAdapter (private  val  listner :OnItemClickListener):
    PagingDataAdapter<PhotoUnsplash, UnsplashPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listner.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: PhotoUnsplash) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewUserName.text = photo.user.username
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: PhotoUnsplash)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoUnsplash>() {
            override fun areItemsTheSame(oldItem: PhotoUnsplash, newItem: PhotoUnsplash) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoUnsplash, newItem: PhotoUnsplash) =
                oldItem == newItem
        }
    }
}