package calendarapp;

import java.io.Serializable;

/**
 *
 * @author Faust
 */
public class Address extends Location implements Serializable {
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String addressLine3 = "";
    //Perhaps consider auto generating this based off of zipcode
    private String city = "";   
    private int zipCode = -1; //Set to negative one to test for failure
    private String state = "";
    private String country = "";
            
	/**
	 * Creates a Test Address
	 */
	public Address() {
		//Builds test address
		super();
		this.addressLine1 = "Test Line 1";
        this.addressLine2 = "Test Line 2";
        this.addressLine3 = "Test Line 3";
        this.zipCode = 12345;
        this.city = "Harrisburg";
        this.state = "Pa";
        this.country = "Dauphin";
	}
            
	/**
	 * Creates an Address
	 * <p>
	 * @param name
	 * @param description
	 * @param addressLine1
	 * @param addressLine2
	 * @param addressLine3
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 */
	public Address(String name, String description, String addressLine1, 
            String addressLine2, String addressLine3, String city, String state,
            int zipCode, String country) {
        super(name, description);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    /**
     * Takes the Address and returns it as a String
	 * <p>
	 * It will format the Address into a String that is styled in the 
	 * conventional way to display address.
	 * <p>
     * @return the Address object in the conventional way to format addresses
     * @Override
     */
    public String toString() {
        String eol = System.getProperty("line.separator");
        String addressString = addressLine1 + eol;
        if (!addressLine2.isEmpty()) {
            addressString = addressString + addressLine2 + eol;
        }
        if (!addressLine3.isEmpty()) {
            addressString = addressString + addressLine3 + eol;
        }
        return addressString + this.city + ", " + this.state + " " +this.zipCode;
    }

    /**
	 * Gets the first line of the Address
	 * <p>
     * @return the Address object's addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
	 * Gets the second line of the Address
	 * <p>
     * @return the Address object's addressLine2
     */
    public String  getAddressLine2() {
        return addressLine2;
    }

    /**
	 * Gets the third line of the Address
	 * <p>
     * @return the Address object's addressLine3
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
	 * Gets the zip code of the Address
	 * <p>
     * @return the Address object's zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
	 * Gets the State where the Address is located
	 * <p>
     * @return Address object's the state
     */
    public String getState() {
        return state;
    }

    /**
	 * Gets the country of the Address
	 * <p>
     * @return Address object's the country
     */
    public String getCountry() {
        return country;
    }
    
    public String getCity(){
        return city;
    }

    /**
	 * Sets the first line of the Address
	 * <p>
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
	 * Sets the second line of the Address
	 * <p>
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
	 * Sets the third line of the Address
	 * <p>
     * @param addressLine3 the addressLine3 to set
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
	 * Sets the zip code of the Address
	 * <p>
     * @param zipCode the zipCode to set
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
	 * Sets the State of the address
	 * <p>
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
	 * Sets the Country of the Address
	 * <p>
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
}
