package ke.co.branch.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.branch.chat.R
import ke.co.branch.chat.databinding.ChatInItemBinding
import ke.co.branch.chat.databinding.ChatOutItemBinding
import ke.co.branch.chat.databinding.MainListItemBinding
import ke.co.branch.core.network.models.Chat
import ke.co.branch.core.network.models.Message

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<Message>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding0 = ChatInItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding = ChatOutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        when(viewType){
            0 -> {
                return MyVH0(binding0)
            }

            1 -> {
                return MyVH(binding)
            }

            else -> {
                return MyVH0(binding0)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = items?.get(position)
        if (model != null) {
            return if (model.agentId == null) 0
            else 1
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items?.get(position)

        if(holder is MyVH0) {
            ChatInItemBinding.bind(holder.itemView).apply {
                messageText.text = model?.message
                messageTime.text = model?.timeStamp
            }
        }

        if(holder is MyVH) {
            ChatOutItemBinding.bind(holder.itemView).apply {
                messageText.text = model?.message
                messageTime.text = model?.timeStamp
            }
        }
    }

    fun setItems(items: MutableList<Message>?) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems() = items

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class MyVH0(binding: ChatInItemBinding) : RecyclerView.ViewHolder(binding.root)
    inner class MyVH(binding: ChatOutItemBinding) : RecyclerView.ViewHolder(binding.root)
}
