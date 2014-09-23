package demo.refactoring;

public class User {
	private String userName;
	
	private User(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public static User signIn(String name, String pass) {
		return new User(name);
	}
		
}
