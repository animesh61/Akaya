package com.example.akaya.ui.model

import com.google.gson.annotations.SerializedName

data class HomeResult(
    @SerializedName("status")
    var status: Status1? = null,
    @SerializedName("result")
    var result:Result1?=null)


data class Status1(
@SerializedName("error_code")
var error_code: Int? = 0,
@SerializedName("message")
var message: String? = null)

data class Result1(
@SerializedName("offersList")
val offerlist: List<offersList> = listOf(),
@SerializedName("categoryList")
val categoryList:List<categoryList> = listOf())

data class offersList(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("date")
    val date:String="")
data class categoryList(
    @SerializedName("categoryImage")
    val categoryImage: String = "",
    @SerializedName("categoryName")
    val categoryName:String=""
)
