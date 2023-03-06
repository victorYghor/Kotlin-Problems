fun main() {
    val minorRange = readln().toDouble()
    val majorRange = readln().toDouble()
    val divider = readln().toInt()

    fun minFactor(minorRange:Double, dvider:Int):Double {
        if (Math.floor(minorRange / divider) * divider >= minorRange) {
            return Math.floor(minorRange /  divider)
        } else if (Math.ceil(minorRange / divider) * divider >= minorRange) {
            return Math.ceil(minorRange / divider)
        } else {
            return 0.0
        }
    }

    fun maxFactor(majorRange:Double, divider:Int):Double {
        if (Math.floor(majorRange / divider) * divider <= majorRange) {
            return Math.floor(majorRange / divider)
        } else if (Math.ceil(majorRange / divider) * divider <= majorRange ){
            return Math.ceil(majorRange / divider)
        } else {
            return 0.0
        }
    }

    println((maxFactor(majorRange, divider) - minFactor(minorRange, divider) + 1).toInt())
}
