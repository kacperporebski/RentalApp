package RentalPropertyManagementSystem.Client.Container;

public class Address {

   private String street;
   private String city;
   private String postalCode;
   private int houseNum;
   private CityQuadrants cityQuadrant;

      public Address(String house, String st, String city, String quad)throws NumberFormatException{
        try{
            houseNum = Integer.parseInt(house);
            street=st;
            this.city=city;
            cityQuadrant = CityQuadrants.valueOf(quad);

        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }



      }

    public CityQuadrants getCityQuadrant() {
        return cityQuadrant;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return houseNum + " " + street + " " + cityQuadrant + " " + city;
    }
}
