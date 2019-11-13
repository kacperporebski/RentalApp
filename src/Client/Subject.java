package Client;

import Client.Container.Observer;

public interface Subject
{
    void register(Observer obs);
    void remove(Observer obs);
    void notifyObserver();
}
