fun main() {
    println("Welcome to our banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")
    var accountType = ""
    var userChoice = 0
    
    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = (1..5).random() // simulate user input
        println("The selected option is $userChoice")
        accountType = when (userChoice) {
            1 -> "debit"
	        2 -> "credit"
            3 -> "checking"
            else -> continue
        }
    }
    
    println("You have created a $accountType account.")
}
