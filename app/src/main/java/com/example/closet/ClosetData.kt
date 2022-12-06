package com.example.closet

import closet.R

enum class ClosetData(val imgRes: Int, val closetName: String, val Serial: String, val temp: Double, val humid: Double, val weight: Int) {
    CLOSET1(R.drawable.img, "옷장1", "00001", 25.3,45.2,1000),
    CLOSET2(R.drawable.img, "옷장2", "00002",26.3,55.2,800),
    CLOSET3(R.drawable.img, "옷장3", "00003",24.3,48.2,750)
}