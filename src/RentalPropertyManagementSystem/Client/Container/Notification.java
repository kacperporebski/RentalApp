package RentalPropertyManagementSystem.Client.Container;

public class Notification
{
    private Property newProperty;
    private Date date;

    public Notification(Property property, Date date)
    {
        newProperty = property;
        this.date = date;
    }

    public void display(){
        //TODO Where we displaying this dog? Displayed in GUI, not even sure if we need this... We will see
    }

    public String toString()
    {
        return date.toString() + "  Property: " + newProperty.toString() +"\n";
    }

    public Property getNewProperty() {
        return newProperty;
    }
}
