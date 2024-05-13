package by.eapp.mts.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContactDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_num")
    val phoneNumber: String,
    @SerializedName("avatar")
    val contactIcon: String,
    @SerializedName("balance")
    val balance: Int
)
