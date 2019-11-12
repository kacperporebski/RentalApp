package Client;

import Client.Container.Fee;

import java.util.ArrayList;

public class FinanceInstitution {

    private ArrayList<Fee> payments;

    public FinanceInstitution(){
        payments = new ArrayList<Fee>();
    }

    public void addPayment(Fee payment){
        payments.add(payment);
    }

    public boolean paymentReceived(Fee checkFee){
        for(Fee f : payments){
            if(f.getID() == checkFee.getID())
                return true;
        }
        return false;
    }

}
