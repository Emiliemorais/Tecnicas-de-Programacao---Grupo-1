
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

public class PaymentType
{
    // Receives a list of  payment types
    static List<PaymentType> listTypes = new ArrayList<>();
 
    // Receives a payment type
        String paymentType;
        
    // Receives a description of a payment type
    String paymentDescription;
	
    //Constructor
    public PaymentType(String paymentType, String paymentDescription) 
    {
        this.paymentType = paymentType;
        this.paymentDescription = paymentDescription;
    }
}