fun missingNumber(array: IntArray): Int{

    for(num in array){
        if(num < 0){
            println("Wystepuje liczba ujemna")
            return -1
        }
    }

    val set = array.toSet()
    if(set.size != array.size){
        println("Nie wszystkie elementy sa unikalne")
        return -1
    }

    val n = array.size
    var missingNumber = -1
    array.sort()

    for(num in 0..n-1){
        if(array[num] != num){
            missingNumber = num
            break
        }
    }

    if(missingNumber == -1){
        missingNumber = n
    }

    println("Brakujaca liczba: ")
    return missingNumber
}

fun main() {
    val numbers = intArrayOf(2, 4, 5, 3, 0, 6)
    println(missingNumber(numbers))
}
