package Client;

import Client.Container.Observer;

public interface Subject
{
    void registerObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObserver();
}
