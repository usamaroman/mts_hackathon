package by.eapp.mts.data

import by.eapp.mts.data.remote.model.ContactDto
import by.eapp.mts.domain.model.Contact

fun ContactDto.toContact() = Contact(
    id = id,
    name = name,
    contactIcon = contactIcon,
    phoneNumber = phoneNumber,
    balance = balance
)