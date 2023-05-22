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
    
    var accountBalance = (0..1000).random() // simulate user input
    println("The current balance is $accountBalance dollars.")
    var money = (0..1000).random() // simulate user input
    println("The amount you transferred is $money dollars.")
    
    var output = 0
    
    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }
    
    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The checking balance is $accountBalance dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }
    
    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("You successfully deposited $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }
    
    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("This account is completely paid off! Do not deposit money!")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }
    
    fun transfer(mode: String) {
        val transferAmount: Int
        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdraw(money)
                } else {
                    transferAmount = withdraw(money)
                }
                println("The amount you withdrew is $transferAmount dollars.")
            }
            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount = deposit(money)
                }
                println("The amount you deposited is $transferAmount dollars.")
            }
            else -> return
        }
    }
    
    var isSystemOpen = true
    var option = 0
    
    while (isSystemOpen) {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the app")
        println("Which option do you choose? (1, 2, 3 or 4)")
        option = (1..5).random() // simulate user input
        println("The selected option is $option")
        when (option) {
            1 -> println("The current balance is $accountBalance dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }

}
