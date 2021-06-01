package com.example.testcurs.model.entities

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs",strict = false)
data class ValCurs @JvmOverloads constructor(
    @field:ElementList(name = "Record",inline = true,required = false) var records:List<Record>?=null )

