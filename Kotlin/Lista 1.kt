fun checkDivisibility(arg : Int): String {
    val numbers = arrayOf(3, 5, 7, 11, 13, 15, 21)
    var ans = ""

    for (num in numbers){
        if(arg % num == 0){
            when (num) {
                3 -> ans += "trzy"
                5 -> ans += "piec"
                7 -> ans += "siedem"
                11 -> ans += "jedenascie"
                13 -> ans += "trzynascie"
                15 -> ans += "pietnascie"
                21 -> ans += "dwadziesciajeden"
            }
        }
    }

    return ans
}

fun main() {
    println(checkDivisibility(15015))
}
