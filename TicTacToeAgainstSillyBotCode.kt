package tictactoe

val cells = MutableList(9) {"_"} // Board Date
var isGameOver = false


fun main() {
    greeting()
    printField()
    guide()
    while (!isGameOver) {
        userMove()
        printField()
        boardCheck()
        if (isGameOver) break
        botMove()
        printField()
        boardCheck()
        if (isGameOver) break
        println("The bot has made its move, now it's your turn!")
    }
}

fun greeting() {
    println("Hello! This is a game of tic-tac-toe in the console, \n" +
            "in which you can play against a bot. Type \"/start\" to start the game.")
    while (true) {
        if (readln() == "/start") return else println("Unknown command. Please type \"/start\" without quotes.")
    }
}

fun printField() {
    println(
        "--------- \n" +
                "| ${cells[0]} ${cells[1]} ${cells[2]} | \n" +
                "| ${cells[3]} ${cells[4]} ${cells[5]} | \n" +
                "| ${cells[6]} ${cells[7]} ${cells[8]} | \n" +
                "---------")
}

fun guide() {
    println("This is the playing field, to make a move, write the row number and cell number separated by a space.\n" +
            "Example: \"1 2\". The move will be made in the first row, the second cell.")
}

fun userMove(){
    while (true) {
        val step = readln()
        if (step.split(" ").map {it.toIntOrNull()}.toMutableList().contains(null)) {
            println("Please, enter numbers not text.")
        } else {
            val s = step.split(" ").map { it.toInt() }.toMutableList()
            when {
                s[0] < 1 || s[0] > 3 || s[1] < 1 || s[1] > 3 -> println("Coordinates should be from 1 to 3!")
                cells[calibrateCords(s)] != "_" -> println("This cell is occupied! Choose another one!")
                else -> {
                    cells[calibrateCords(s)] = "X"; return
                }
            }
        }
    }
}

fun calibrateCords(input: MutableList<Int>): Int {
    when (input[0]) {
        1 -> return (input[0] + input[1] - 2)
        2 -> return (input[0] + input[1])
        3 -> return (input[0] + input[1] + 2)
    }
    return 0
}

fun boardCheck() {
    // winner check
    val winLines = " ${cells[0]}${cells[1]}${cells[2]} ${cells[3]}${cells[4]}${cells[5]} ${cells[6]}${cells[7]}${cells[8]} " +
            " ${cells[0]}${cells[3]}${cells[6]} ${cells[1]}${cells[4]}${cells[7]} ${cells[2]}${cells[5]}${cells[8]} " +
            " ${cells[0]}${cells[4]}${cells[8]} ${cells[2]}${cells[4]}${cells[6]}"
    val winX = winLines.contains("XXX")
    val winO = winLines.contains("000")
    // count check
    var countFree = 0
    for (ch in cells) {
        when (ch) {
            "_" -> countFree ++
        }
    }
    when {
        winX && winO -> { println("Impossible"); return }
        winX -> { println("You win, congratulations!"); isGameOver = true }
        winO -> { println("Bot wins the game :("); isGameOver = true }
        countFree == 0 -> { println("Draw!"); isGameOver = true }
        else -> println("Great move! The game continues, Bot's turn.")
    }
}

fun botMove() {
    val emptyCells = mutableListOf<Int>()
    for (i in cells.indices) {
        if (cells[i] == "_") emptyCells.add(i)
    }
    cells[emptyCells.random()] = "0"
    emptyCells.clear()
    Thread.sleep(2000 )
}