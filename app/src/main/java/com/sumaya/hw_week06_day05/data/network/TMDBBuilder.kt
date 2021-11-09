package com.sumaya.hw_week06_day05.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBBuilder {

    private const val BASE_URL =
        "https://api.themoviedb.org/3/"

    private fun retrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    val TMDBAPI: TMDBAPI =
        retrofit().create(com.sumaya.hw_week06_day05.data.network.TMDBAPI::class.java)

}

/*
fun main() {
    val star = "*"
    for (i in 1..10) {

        if (i % 2 != 0) {
            for (r in 10 downTo i + 1) {
                print(" ")
            }
            for (e in 1..i) {
                print(star + " ")
            }
            println()
        }
    }
}
*/

/*
fun main(){
    val num1 =1
    val num2 =2
   println("result = "+cal(num1,num2))
}

fun cal(num1:Int,num2:Int):Int{
    return num1+num2
}*/

/*fun main() {
    val str = "wert"
   com(str, 0, str.lastIndex, true)
    val str1 = "ababa"
   com(str1, 0, str1.lastIndex, true)

}

fun com(str: String, ind1: Int, ind2: Int, boo: Boolean){

    if (ind1 < ind2 && ind1 != ind2) {
        if (str[ind1] == str[ind2]) {
            com(str, ind1 + 1, ind2 - 1, true)
        } else {
            com(str, ind1 + 1, ind2 - 1, false)
        }
    }else{
        println(" $boo")
    }

}*/

