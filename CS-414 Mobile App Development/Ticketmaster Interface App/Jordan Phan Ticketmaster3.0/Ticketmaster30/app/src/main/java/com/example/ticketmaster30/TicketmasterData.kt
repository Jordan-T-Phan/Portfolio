package com.example.ticketmaster30


data class TicketmasterData(
    val _embedded: Embeddeds1,
    val page: Pages

)
data class Pages(
    val totalElements: Int


)
data class Embeddeds1(
    val events: List<Eventss>

)
data class Eventss(
    val name: String,
    val id: String,
    val url: String,
    val images: List<Imagess>,
    val dates: Datess,
    val _embedded: Embeddeds2,
    val priceRanges: List<PriceRangess>

)
data class PriceRangess(
    val min: Double,
    val max: Double

)
data class Embeddeds2(
    val venues: List<Venuess>


)
data class Venuess(
   val name: String,
    val city: Citys,
    val state: States,
    val address: Addresss
)
data class States(
    val name: String

)
data class Addresss(
    val line1: String

)
data class Citys(
    val name: String

)
data class Datess(
    val start: Startss


)
data class Startss(
    val localDate: String,
    val localTime: String

)
data class Imagess(
    val url: String,
    val width: Int,
    val height: Int
)



