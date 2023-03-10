package ke.co.branch.chat.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ke.co.branch.chat.databinding.MainListItemBinding
import ke.co.branch.chat.view.ChatActivity
import ke.co.branch.core.network.models.Chat

class ChatGroupAdapter : RecyclerView.Adapter<ChatGroupAdapter.MyVH>() {
    private var items: MutableList<Chat>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val binding =
            MainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyVH(binding)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        val model = items?.get(position)

        MainListItemBinding.bind(holder.itemView).apply {
            userName.text = model?.userId
            chatDate.text = model?.timeStamp
            textMessage.text = model?.latestMessage

            root.setOnClickListener {
                val intent = Intent(textMessage.context, ChatActivity::class.java)
                intent.putExtra("USERNAME_EXTRA", model?.userId)
                intent.putExtra("THREAD_EXTRA", model?.threadId)
                intent.putExtra("AGENT_EXTRA", model?.threadId.toString())
                it.context.startActivity(intent)
            }
        }
    }

    fun setItems(items: MutableList<Chat>?) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems() = items

    fun addItem(item: Chat) {
        this.items?.add(item)
        notifyItemInserted(itemCount)
    }

    fun remove(item: Chat) {
        this.items?.remove(item)
        notifyItemRemoved(itemCount)
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class MyVH(binding: MainListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
