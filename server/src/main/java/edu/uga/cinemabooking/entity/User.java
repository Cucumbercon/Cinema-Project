package edu.uga.cinemabooking.entity;

public class User {
    
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;

    // empty contrusctor
    public User() {

    }

    public User(String id, String firstName, String lastName, String email, String password, String phoneNumber) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get method for phone number
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

    /**
     * This method is to change the user's password.
     * 
     * @param curPassword current password
     * @param newPassword new password
     * @return if successed
     */
    public Boolean setPassword(String curPassword, String newPassword) {
        if (this.password.equals(curPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

}
