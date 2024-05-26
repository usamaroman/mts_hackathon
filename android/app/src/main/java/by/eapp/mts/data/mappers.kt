package by.eapp.mts.data

import by.eapp.mts.GetAllUserNamesQuery
import by.eapp.mts.data.remote.model.ContactDto
import by.eapp.mts.domain.model.Contact

fun GetAllUserNamesQuery.User.toContact() = Contact(
    id = id,
    name = name,
    contactIcon = avatar,
    phoneNumber = phoneNum,


)