package com.example.learnkotlinbasics

fun main(){
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each rows:")
    val seatsPerRow = readln().toInt()
    val totalSeats = rows * seatsPerRow
    val frontRows = rows / 2
    val backRows = rows - frontRows
    val mutableList2D = mutableListOf<MutableList<Char>>()
    var totalTicketsSold = 0
    var totalAmountSold = 0
    val totalValue = if (totalSeats > 60){
        frontRows * seatsPerRow * 10 + backRows * seatsPerRow * 8
    } else {
        totalSeats * 10
    }

    repeat(rows){
        val row = mutableListOf<Char>()
        repeat(seatsPerRow){
            row.add('S')
        }
        mutableList2D.add(row)
    }
    do {
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        val ans = readln().toInt()
        when (ans) {
            1 -> {
                println("\nCinema:")
                print("  ")
                for (i in mutableList2D[0].indices) {
                    print("${i+1} ")
                }
                println("")
                for (i in mutableList2D.indices) {
                    print("${i+1} ")
                    for (j in mutableList2D[i].indices){
                        print("${mutableList2D[i][j]} ")
                    }
                    println("")
                }
            }
            2 -> {
                do {
                    println("Enter a row number:")
                    val rowNum = readln().toInt()
                    println("Enter a seat number in that row:")
                    val seatNum = readln().toInt()
                    if (rowNum > rows || seatNum > seatsPerRow) {
                        println("Wrong input!")
                    } else {
                        if (mutableList2D[rowNum-1][seatNum-1] == 'B'){
                            println("That ticket has already been purchased!")
                        } else {
                            mutableList2D[rowNum-1][seatNum-1] = 'B'
                            totalTicketsSold++
                            if (totalSeats > 60 && rowNum > frontRows){
                                println("Ticket price: $8")
                                totalAmountSold += 8
                            } else {
                                println("Ticket price: $10")
                                totalAmountSold += 10
                            }
                            break
                        }
                    }
                } while (true)
            }
            0 -> break
            3 -> {
                val percentage: Double = 100 * totalTicketsSold.toDouble() / totalSeats.toDouble()
                val formatPercentage = "%6.2f".format(percentage)
                println("Number of purchased tickets: $totalTicketsSold")
                println("Percentage: $formatPercentage%")
                println("Current income: \$$totalAmountSold")
                println("Total income: \$$totalValue")
            }
            else -> println("Invalid ans!")
        }
    } while (ans != 0)
}