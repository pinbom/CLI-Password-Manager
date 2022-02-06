import java.util.Random; 
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class PasswordManager {
	// Generates a random string of Alphanumeric & Special characters (!@#$%^&*)
	public String PasswordBuilder(int passwordLength) {
		String password = " ";
		Random random = new Random();
		String character = " ";
		int characterSelect = 0;
		int max = 70;
		
		for (int i = 0; i < passwordLength; i++) {
			String[] characters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9", "!", "@", "#", "$", "%", "^", "&", "*"} ;
			characterSelect = random.nextInt(max);
			character = characters[characterSelect];
			password = password + character;
		}
		return password;
	}
	public static void main(String[] args) throws IOException {
		//Creates a Log file for the passwords the user has created
		File passwordLog = new File("passwordLog.txt");
		if (passwordLog.createNewFile()) {
			System.out.println("Password Log Created");
		} else {
			System.out.println("Password Log Exists");
		}
		
		Scanner selectOption = new Scanner (System.in);
		
		System.out.println("Do you want to generate a new password, or view saved ones?" + "\n" + "\n" + "[1.] Generate a New Password" + "[2.] View Saved Passwords");

		int optionChoice = selectOption.nextInt();
		
		if(optionChoice == 1) {
		// Sets the length of the generated password
		int passwordLength = 0;
		Scanner passLngth = new Scanner (System.in);
		try {
			System.out.println("How long do you want your password to be?");
		
			passwordLength = passLngth.nextInt();	
		} catch (Exception InputMismatchException) {
			System.out.println("Please use a whole number as the input! Killing program");
			System.exit(0);
		}


		Scanner setSiteName = new Scanner (System.in);
		
		System.out.println("What site is this password for?");

		String siteName = setSiteName.next().toString();

		PasswordManager passGen = new PasswordManager();
		String generatedPass = passGen.PasswordBuilder(passwordLength);
		System.out.println("Your password for " + siteName + " is: " + generatedPass);
		
		// Writes saved password to the password log
		FileWriter savePassword = new FileWriter("passwordLog.txt", true);
		savePassword.write( siteName + ": " + generatedPass + System.lineSeparator());
		savePassword.close();
		passLngth.close();
		setSiteName.close();

	} else if(optionChoice == 2) {
		Scanner savedPasswords = new Scanner(passwordLog);
		savedPasswords.useDelimiter("\\Z");
		System.out.println("\n" + savedPasswords.next() + "\n");
		savedPasswords.close();
	}  
	selectOption.close();
  }
}
