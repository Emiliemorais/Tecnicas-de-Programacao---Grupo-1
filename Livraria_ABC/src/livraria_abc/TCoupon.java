
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

// Tipo Cupom de desconto
public class TCoupon
{
    static List <TCoupon>  couponValue=  new ArrayList<> ();
    String cupom;
    float valor_desconto;
        
    public TCoupon()
    {
    
    }
       
    // Verifica se um dado cupom está cadastrado no sistema
    public static boolean ChecksCoupon (String coupon_cpr)
    {
        
        int i=0;
        int checkCouponSearchedThereDataBase=0;
           
        int quantityValueCupon =couponValue.size(); 
        
        for(i=0; i<quantityValueCupon; i++)
        {
            boolean currentValueCouponEqualsGetCouponValue = couponValue.get(i).getCoupon().equals(coupon_cpr); 
                
            if(currentValueCouponEqualsGetCouponValue)
            {
                checkCouponSearchedThereDataBase++;
                  
                break;
            }
            else
            {
                // Nothing to do
            }
            
        }
            
        boolean returnMethod;
        if(checkCouponSearchedThereDataBase == 0)
        {

            returnMethod = false;
        }
        else
        {
        
            returnMethod = true;
        }
            
        return returnMethod;
    }
        
    // Verifica o valor do desconto oferecido por um dado cupom
    public static float CheksValueDiscount(String cprCoupon)
    {
        float valueDiscount = 0;
            
        int i = 0;
            
        int quantityValueCupon =couponValue.size();
        
        for(i=0; i<quantityValueCupon; i++)
        {
            
            boolean currentValueCouponEqualsGetCouponValue = couponValue.get(i).getCoupon().equals(cprCoupon);
                    
            if(currentValueCouponEqualsGetCouponValue)
            {
                valueDiscount = couponValue.get(i).getValueDiscount();
                    
                break;
            }
            else
            {
                // Nothing to do
            }
        }
          
        return valueDiscount;
    }
    
    // Construtor
    public TCoupon(String codCoupon, float valueOfCoupon)
    {
        this.cupom = codCoupon;
        this.valor_desconto = valueOfCoupon;
    }
    
    // Getters
    public String getCoupon() 
    {
        
        return cupom;
    }

    public float getValueDiscount() 
    {
        
        return valor_desconto;
    }
                
}
    