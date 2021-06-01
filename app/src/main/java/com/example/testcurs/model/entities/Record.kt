package com.example.testcurs.model.entities

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Record", strict = false)
data class Record @JvmOverloads constructor(
    @field:Attribute(name = "Date") var date: String?=null,
    @field:Element(name = "Value") var value: String?=null
)

