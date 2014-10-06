
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

// Tipo Cupom de desconto
public class TCoupon
{
    static List <TCoupon>  cupom_valor = new ArrayList<> ();
    String cupom;
    float valor_desconto;
        
    public TCoupon(){}
       
    // Verifica se um dado cupom está cadastrado no sistema
    public static boolean ChecksCoupon (String coupon_cpr)
    {
        int i, checkCouponSearchedThereDataBase=0;
           
        for(i=0; i<cupom_valor.size(); i++)
        {
            if(cupom_valor.get(i).getCoupon().equals(coupon_cpr))
            {
                checkCouponSearchedThereDataBase++;
                  
                break;
            }
        }
            
        if(checkCouponSearchedThereDataBase==0)
            return false;
        else
            return true;
    }
        
    // Verifica o valor do desconto oferecido por um dado cupom
    public static float CheksValueDiscount(String cprCoupon)
    {
        float valor_desconto = 0;
            
        int i;
            
        for(i=0; i<cupom_valor.size (); i++)
        {
            if(cupom_valor.get(i).getCoupon().equals(cprCoupon))
            {
                valor_desconto = cupom_valor.get(i).getValueDiscount ();
                   
                break;
            }
        }
          
        return valor_desconto;
    }
    
    // Construtor
    public TCoupon (String codCoupon, float valueOfCoupon)
    {
        this.cupom = codCoupon;
        this.valor_desconto = valueOfCoupon;
    }
    
    // Getters
    public String getCoupon () {
        return cupom;
    }

    public float getValueDiscount () {
        return valor_desconto;
    }
                
}
    