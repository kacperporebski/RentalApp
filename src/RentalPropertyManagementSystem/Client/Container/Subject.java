package RentalPropertyManagementSystem.Client.Container;

public interface Subject
{
    void registerObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}
