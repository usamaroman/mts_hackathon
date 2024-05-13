package by.eapp.mts.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("contacts")
    val contacts: List<ContactDto>
)
