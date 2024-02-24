fun missingNumber(array: IntArray): Int{

    for(num in array){
        if(num < 0){
            println("Wystepuje liczba ujemna")
            return -1
        }
    }

    val set = array.toSet()
    if (set.size != array.size) {
        println("Nie wszystkie elementy są unikalne")
        return -1
    }


    val map = mutableMapOf<Int, Int>()
    var missingNum : Int = 0

    for(num in array){
        if(map.containsKey(num)) { map[num] = map[num]!! +1 }
        else { map[num] = 1 }
    }

    for(i in 0..array.size){
        if(!map.containsKey(i)) { missingNum = i }
    }

    return missingNum
}

fun isPalindrome(arg : String): Boolean{
    val n = arg.length

    for(i in 0..n/2){
        if(arg[i] != arg[n-1-i]){
            println("\nPodane słowo nie jest palindromem")
            return false
        }
    }

    println("\nPodane słowo jest palindromem")
    return true
}

fun printPascal(height: Int) {
    require(height >= 1) { "Wysokość trójkąta Pascala musi być co najmniej 1" }

    val triangle = mutableListOf<List<Int>>()

    for (i in 0..< height) {
        val row = mutableListOf<Int>()

        for (j in 0..i) {
            val value = if (j == 0 || j == i) 1 else triangle[i - 1][j - 1] + triangle[i - 1][j]
            row.add(value)
        }

        triangle.add(row)
    }

    println("\nTrójkąt Pascala wysokości $height:")
    for (row in triangle) {
        println(row.joinToString(" "))
    }
}

fun main() {
    val numbers = intArrayOf(2, 4, 5, 3, 1, 6)
    println(missingNumber(numbers))

    val word: String = "abba"
    println(isPalindrome(word))

    val height: Int = 4
    println(printPascal(height))
}
