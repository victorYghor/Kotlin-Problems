val vowels: List<Char> = listOf('a', 'e', 'i', 'o', 'u', 'y')
val consonants: List<Char> = listOf('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z')

fun fixClusterVowels(cluster: String): String {
    val listOfVowels: MutableList<Char> = cluster.toMutableList()

    val correctionI = listOfVowels.size / 2
    for (i in 2 until(listOfVowels.lastIndex + correctionI) step 3) {
        listOfVowels.add(i, consonants.random())
    }
    return listOfVowels.joinToString("")
}

fun fixClusterConsonants(cluster:String): String {
    val listOfConsonants: MutableList<Char> = cluster.toMutableList()

    val correctionI = listOfConsonants.size / 2
    for (i in 2 until(listOfConsonants.lastIndex + correctionI) step 3) { // start in the index 2 because a vowel is put in the 3rd element if a put index 3 the word fit in the 4th and don't solve the problem
        listOfConsonants.add(i, vowels.random())
    }
    return listOfConsonants.joinToString("")
}

fun findClusters(word: String): List<Int?> {
    val clusterIndices: List<Int?>
    for (i in word.indices) {
        var quantV = 0
        do {
            if (vowels.contains(word[i + quantV]) && i + quantV + 1 <= word.lastIndex) {
                ++quantV
            } else if (i + quantV == word.lastIndex && vowels.contains(word[i + quantV])) {
                ++quantV
            }
        } while (i + quantV <= word.lastIndex && vowels.contains(word[i + quantV]))

        if (quantV >= 3) {
            clusterIndices = listOf(i, i + quantV)
            return clusterIndices
        }

        var quantC = 0
        do {
            if (consonants.contains(word[i + quantC]) && i + quantC + 1 <= word.lastIndex) {
                ++quantC
            } else if (i + quantC == word.lastIndex && consonants.contains(word[i + quantC])) {
                ++quantC
            }
        } while (i + quantC <= word.lastIndex && consonants.contains(word[i + quantC]))

        if (quantC >= 3) {
            clusterIndices = listOf(i, i + quantC)
            return clusterIndices
        }
    }
    clusterIndices = listOf(null)
    return clusterIndices
}

fun solveClusters(wordParam: String): String {
    var word = wordParam
    var indexCluster = findClusters(word)
    while (indexCluster[0] != null) {
        val cluster: String = word.substring(indexCluster[0] ?: 0, indexCluster[1] ?: 0)

        if (vowels.contains(cluster[0])) {
            val fixedCluster = fixClusterVowels(cluster)
            word = word.replaceFirst(cluster, fixedCluster)
            indexCluster = findClusters(word)

        } else if (consonants.contains(cluster[0])) {
            val fixedCluster = fixClusterConsonants(cluster)
            word = word.replaceFirst(cluster, fixedCluster)
            indexCluster = findClusters(word)
        } else {
            break
        }
    }
    return word
}

fun main() {
    val word = readln()
    println(solveClusters(word))
    println(solveClusters(word).length - word.length)
}
