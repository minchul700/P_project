package com.example.closet

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import closet.databinding.ListItemBinding
import com.example.closet.DetailActivity.Companion.CLOSET
import org.eclipse.paho.client.mqttv3.MqttMessage


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
        //리스트에 인덱스로 접근, 해당 내용 바인딩
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

                    mqttClient!!.publish(TOPIC, MqttMessage((ClosetData.Serial)?.toByteArray())) //4번 메세지 전송

                    /* 클릭한 itemView에 맞는 Data의 상수명을 인텐트에 저장
                       - Data.name : enum class에 선언된 상수이름을 반환  */
                    mIntent.putExtra(CLOSET, adapterPosition)
                    
                    //로딩창 띄우기

                    customProgressDialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT));
                    customProgressDialog?.show()
                    val handler = Handler()
                    handler.postDelayed({
                        itemView.context.startActivity(mIntent)
                    }, 3000) //3초 뒤에 세부 화면으로 이동

            }

        }
    }//end of ItemViewHolder
}//end of Adapter
