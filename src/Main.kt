/**
 * Kotlin Collections Task 3 - Monkey Errors
 *
 *       OOOOO  OOO   OOO
 *          O  O   O O   O
 *         O   O   O O   O
 *       OOOOO  OOO   OOO
 * +-------------+-------------+
 * |    __v__    |     ___     |
 * |   ( o o )   |    /   |    |
 * |    (---)    |       _/    |
 * |    __|__    |      |      |
 * |   /|. .|\   |      o      |
 * +-------------+-------------+
 *
 * It seems that some of the zookeepers are not very
 * good at their job, and keep getting the cage numbers
 * muddled up. Can you add some error checking to the
 * system to help indicate when things go wrong?
 */


/**
 * Constant vales used to define some key values
 * which can then be used throughout the code...
 */
const val NUMCAGES = 8      // The total number of cages
const val EMPTY = "---"     // Represents an empty cage


/**
 * Program entry point
 */
fun main() {
    //-------------------------------------------------
    println("Setting up the cages...")

    val cages = setupCages()

    showMonkeyCages(cages)
    println()

    //-------------------------------------------------
    println("Placing monkeys into cages...")

    placeMonkeyInCage(cages, 1, "Kevin")
    placeMonkeyInCage(cages, 8, "Sally")
    placeMonkeyInCage(cages, 4, "Pam")
    placeMonkeyInCage(cages, 3, "Samson")
    placeMonkeyInCage(cages, 5, "Frank")
    placeMonkeyInCage(cages, 6, "Jim")

    println()

    showMonkeyCages(cages)
    println("Monkeys: ${monkeyCount(cages)}")
    println("Empty: ${emptyCount(cages)}")
    println()

    //-------------------------------------------------
    println("Trying some invalid placements...")

    placeMonkeyInCage(cages, 0, "Nigel")
    placeMonkeyInCage(cages, NUMCAGES + 1, "Nigel")

    println()

    //-------------------------------------------------
    println("Trying some more invalid placements...")

    placeMonkeyInCage(cages, 2, "")
    placeMonkeyInCage(cages, 2, "        ")

    println()

    //-------------------------------------------------
    println("Trying to clear invalid cages...")

    clearCage(cages, 0)
    clearCage(cages, NUMCAGES + 1)

    println()

    //-------------------------------------------------
    println("Trying to do some invalid swaps...")

    swapCages(cages, 0, 0)
    swapCages(cages, 0, 1)
    swapCages(cages, 1, 0)

    swapCages(cages, NUMCAGES + 1, NUMCAGES + 1)
    swapCages(cages, NUMCAGES + 1, NUMCAGES)
    swapCages(cages, NUMCAGES, NUMCAGES + 1)
}






/**
 * Creates and returns a Mutable List, size NUMCAGES,
 * populated with strings representing empty cages
 */
fun setupCages(): MutableList<String> {
    val cageList = mutableListOf<String>()
    for (i in 1..NUMCAGES) cageList.add(EMPTY)
    return cageList
}


/**
 * Put a given monkey into the specified cage number (1...MAX)
 */
fun placeMonkeyInCage(cageList: MutableList<String>, cageNum: Int, name: String) {
    println("+++ Putting $name into cage $cageNum")
    if(name.isNotBlank()) {
        if (cageNum in 1..NUMCAGES) {
            cageList.removeAt(cageNum - 1)
            cageList.add(index = cageNum - 1, name)

        } else {
            println("ERROR PLACING MONKEY IN CAGE: Cage number $cageNum is out of range.")
        }
    }
    else println("ERROR PLACING MONKEY IN CAGE: Monkey name '$name' is blank.")
}


/**
 * Display a list of all cages in the given list in the format:
 *
 * CAGES
 * - Cage 1: Kevin
 * - Cage 2: ---
 * - Cage 3: Samson
 * - Cage 4: Pam
 * - Etc.
 */
fun listAllCages(cageList: List<String>) {
    println("CAGES")

    for(i in 0..< cageList.size)  {
        println("- Cage ${i + 1}:  ${cageList[i]}")
    }
}


/**
 * Display a list of all monkeys found in the given cage list:
 *
 * MONKEYS
 * - Kevin
 * - Samson
 * - Pam
 * - Etc.
 */
fun listAllMonkeys(cageList: List<String>) {
    println("MONKEYS")

    for (i in (0..< cageList.size)) {
        if  (cageList[i] != EMPTY) {
            println("- ${cageList[i]}")
        }
    }
}


/**
 * Display a list of all empty cages in the given cage list:
 *
 * EMPTY CAGES
 * - Cage 2
 * - Cage 7
 * - Etc.
 */
fun listEmptyCages(cageList: List<String>) {
    println("EMPTY CAGES")

    for (i in (0..< cageList.size)) {
        if  (cageList[i] == EMPTY) {
            println("- Cage ${i + 1}")
        }
    }
}


/**
 * Display a full list of all monkeys and the cages they are in.
 * The names and cage numbers should line up in neat columns:
 *
 * MONKEYS & CAGES
 * - Kevin  (Cage 1)
 * - Samson (Cage 3)
 * - Pam    (Cage 4)
 * - Etc.
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun listAllMonkeysAndCages(cageList: List<String>) {
    println("MONKEYS & CAGES")

    for(i in 0..< cageList.size)  {
        if  (cageList[i] != EMPTY) {
            println("- ${cageList[i].padEnd(7)}   (Cage ${i + 1})")
        }

    }
}


/**
 * Returns the number of monkeys found in the given cage list
 */
fun monkeyCount(cageList: List<String>): Int {
    var numMonkeys = 0
    for (i in 0..< cageList.size) {
        if (cageList[i] != EMPTY) {
            numMonkeys++
        }
    }
    return numMonkeys
}


/**
 * Returns the number of cages that are empty in the given cage list
 */
fun emptyCount(cageList: List<String>): Int {

    var numEmptyCages = 0
    for (i in 0..< cageList.size) {
        if (cageList[i] == EMPTY) {
            numEmptyCages++
        }
    }
    return numEmptyCages
}


/**
 * Show all cages from the given list, formatted as a horizontal table:
 *
 * +--------+--------+--------+--------+----
 * | Cage 1 | Cage 2 | Cage 3 | Cage 4 | Etc.
 * +--------+--------+--------+--------+----
 * | Kevin  | ---    | Samson | Pam    | Etc.
 * +--------+--------+--------+--------+----
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun showMonkeyCages(cageList: List<String>) {

    println("+--------+--------+--------+--------+--------+--------+--------+--------+")
    println("| Cage 1 | Cage 2 | Cage 3 | Cage 4 | Cage 5 | Cage 6 | Cage 7 | Cage 8 |")
    println("+--------+--------+--------+--------+--------+--------+--------+--------+")
    for ((i, monkey) in cageList.withIndex()) {
        print("| ${monkey.padEnd(7)}")
    }
    println("|")
    println("+--------+--------+--------+--------+--------+--------+--------+--------+")

}


/**
 * Make a given cage empty (if a monkey was in it, it's gone now!)
 */
fun clearCage(cageList: MutableList<String>, cageNum: Int) {
    println("--- Clearing cage $cageNum")
    if (cageNum in 1..NUMCAGES) {
        cageList.removeAt(index = cageNum)
        cageList.add(index = cageNum - 1, EMPTY)
    }
    else {
        println("ERROR CLEARING CAGE: Cage number $cageNum is out of range.")
    }

}


/**
 * Swap the contents of two given cages.
 *
 * If one was full and the other empty, then the monkey just swaps
 * into the empty cage.
 */
fun swapCages(cageList: MutableList<String>, cageNum1: Int, cageNum2: Int) {
    println("<-> Swapping cages $cageNum1 and $cageNum2")
    if(cageNum1 in 1..NUMCAGES && cageNum2 in 1..NUMCAGES) {
        val monkey1 = cageList[cageNum1 - 1]
        val monkey2 = cageList[cageNum2 - 1]

        cageList.removeAt(cageNum1 - 1,)
        cageList.add(index = cageNum1 - 1, monkey2)
        cageList.removeAt(cageNum2 - 1)
        cageList.add(index = cageNum2 - 1, monkey1)
    }
    else {
        if (cageNum1 in 1..NUMCAGES) {
            println("ERROR SWAPPING CAGES: Cage number $cageNum2 is out of range.")
        }
        else {
            println("ERROR SWAPPING CAGES: Cage number $cageNum1 is out of range.")
        }
    }

}
/**
 * ========================================================
 * ABOVE THIS COMMENT, PLACE A COPY OF ALL THE FUNCTIONS
 * YOU WORKED ON AND GOT WORKING IN TASK 2
 * ========================================================
 *
 * You will be modifying the following functions to add
 * error-checking:
 * - placeMonkeyInCage()
 * - clearCage()
 * - swapCages()
 *
 * If an error is detected, a suitable error message should
 * be shown such as:
 * - ERROR PLACING MONKEY: cage number 20 is out of range
 * - ERROR CLEARING CAGE: cage number 0 is out of range
 * - Etc.
 *
 * ========================================================
 */