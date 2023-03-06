package com.example.my_travel_guide

class User( email: String, date_payent: Int) {
    val formatter = DateTimeFormatter.ofPattern("DD-MM-YYYY")
    var date_payent = LocalDate.parse(date_payent, formatter)

}