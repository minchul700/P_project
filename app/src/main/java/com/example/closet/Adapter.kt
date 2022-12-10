package com.example.closet

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import closet.databinding.ListItemBinding
import com.example.closet.DetailActivity.Companion.CLOSET


//Adapter 클래스 선언
class Adapter() : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    //어댑터에서 관리하는 아이템의 개수를 반환
    override fun getItemCount() = numOfCloset

    // 뷰 객체와 뷰홀더(ItemViewHolder)를 생성하여 반환(뷰홀더가 새로 만들어지는 시점에만 호출)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }//end of onCreateViewHolder

    //뷰홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //데이터 바인딩
        holder.bindData(ClosetList[position])

    }//end of onBindViewHolder

    /* 뷰홀더(ItemViewHolder) 클래스 선언 */
    inner class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        //아이템뷰(list_item 레이아웃)에 Data 데이터 바인딩
        fun bindData(ClosetData: ClosetData.Closet_Data) {
                binding.tvCloset.text = ClosetData.closetName
                binding.tvSerial.text = ClosetData.Serial
                ClosetData.imgRes?.let { binding.imgPhoto.setImageResource(it) }

                /* 리사이클러뷰의 itemView(뷰홀더)를 클릭하면 상세보기 화면 생성(DetailActivity)
                   - itemView: ViewHolder
                     public abstract static class ViewHolder { public final View itemView; }  */
                itemView.setOnClickListener {
                    val mIntent = Intent(itemView.context, DetailActivity::class.java)

                    Log.i("CLOSET", "Data:" + ClosetData.closetName)

                    /* 클릭한 itemView에 맞는 Data의 상수명을 인텐트에 저장
                       - Data.name : enum class에 선언된 상수이름을 반환  */
                    mIntent.putExtra(CLOSET, 0)
                    itemView.context.startActivity(mIntent)
            }

        }
    }//end of ItemViewHolder
}//end of Adapter
