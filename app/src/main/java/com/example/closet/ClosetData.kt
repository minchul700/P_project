package com.example.closet

import closet.R
var numOfCloset = 1; //옷장 개수 관리를 위한 변수
var ClosetList: MutableList<ClosetData.Closet_Data> = mutableListOf(ClosetData.Closet_Data(R.drawable.img, "옷장1", "00001", 25.3, 45.2, 1000)) // 옷장 리스트

sealed class ClosetData{
    data class Closet_Data(var imgRes: Int?, var closetName: String?, var Serial: String?, var temp: Double?, var humid: Double?, var weight: Int?) : ClosetData()


}
fun add_closet(_imgRes:Int,_closetName:String, _Serial:String){
    val tmp = ClosetData.Closet_Data(_imgRes, _closetName, _Serial, null, null, null)
    ClosetList.add(tmp)
    numOfCloset++
}

fun state_update(_temp: Double, _humid: Double, _weight:Int): Unit {

}




