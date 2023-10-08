package edu.uga.cinemabooking.entity;

public class User {
    
    int id;
    String name;
    String email;
    String password;
    String phoneNumber;

    // empty contrusctor
    public User() {

    }

    public User(int id, String name, String email, String password, String phoneNumber) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + "]";
	}

    public void setPassword(String password) {
        this.password = password;
    }

    // /**
    //  * This method is to change the user's password.
    //  * 
    //  * @param curPassword current password
    //  * @param newPassword new password
    //  * @return if successed
    //  */
    // public Boolean setPassword(String curPassword, String newPassword) {
    //     if (this.password.equals(curPassword)) {
    //         this.password = newPassword;
    //         return true;
    //     }
    //     return false;
    // }

}
