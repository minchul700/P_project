package com.example.closet

var numOfCloset = 0; //옷장 개수 관리를 위한 변수
var ClosetList: MutableList<ClosetData.Closet_Data> = mutableListOf() // 옷장 리스트
sealed class ClosetData{
    data class Closet_Data(var imgRes: Int?, var closetName: String?, var Serial: String?, var temp: String?, var humid: String?, var weight: String?) : ClosetData()
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
fun state_update(_Serial: String,_temp: String, _humid: String, _weight:String) {
    for (i in 0 until numOfCloset) {
        if(ClosetList[i].Serial == _Serial) {
            ClosetList[i].temp = _temp
            ClosetList[i].humid = _humid
            ClosetList[i].weight = _weight
            break;
        }
    }
}
fun set_num(_num:Int){
    numOfCloset = _num
}
fun set_list(_imgRes:Int?, _closetName: String, _Serial:String){
    val tmp = ClosetData.Closet_Data(_imgRes, _closetName, _Serial, null, null, null)
    ClosetList.add(tmp)
}




