package com.example.closet

import android.text.Editable
import closet.R
var numOfCloset = 2; //옷장 개수 관리를 위한 변수
var ClosetList: MutableList<ClosetData.Closet_Data> = mutableListOf(ClosetData.Closet_Data(R.drawable.img, "옷장1", "00001", 25.3, 45.2, 1000)
,ClosetData.Closet_Data(R.drawable.img, "옷장2", "00001", 25.3, 45.2, 1000)) // 옷장 리스트

sealed class ClosetData{
    data class Closet_Data(var imgRes: Int?, var closetName: String?, var Serial: String?, var temp: Double?, var humid: Double?, var weight: Int?) : ClosetData()


}
//아이템 추가 메서드
fun add_closet(_imgRes:Int?, _closetName: String, _Serial:String){
    val tmp = ClosetData.Closet_Data(_imgRes, _closetName, _Serial, null, null, null)
    ClosetList.add(tmp)
    numOfCloset++
}

//아이템 제거 메서드 (삭제 후 정렬 )
fun delete_closet(index: Int){
    ClosetList.removeAt(index)
    numOfCloset--;
}

//상태 변경 (온습도, 무게 ) 메서드
fun state_update(index: Int,_temp: Double, _humid: Double, _weight:Int) {
    ClosetList[index].temp = _temp
    ClosetList[index].humid = _humid
    ClosetList[index].weight = _weight
}




