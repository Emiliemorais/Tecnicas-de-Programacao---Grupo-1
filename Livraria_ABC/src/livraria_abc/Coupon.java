
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

// Tipo Cupom de desconto
public class Coupon
{
    static List <Coupon>  cupom_valor = new ArrayList<>();
    String coupon;
    float discountedValue;
        
    public Coupon(){}
       
    // Verifica se um dado cupom está cadastrado no sistema
    public static boolean Verifica_cupom(String cupom_cpr)
    {
        int i, couponSameCpr=0;
            
        for(i=0; i<cupom_valor.size(); i++)
        {
            if(cupom_valor.get(i).getCupom().equals(cupom_cpr))
            {
                couponSameCpr++;
                  
                break;
            }
        }
            
        if(couponSameCpr==0)
            return false;
        else
            return true;
    }
        
    // Verifica o valor do desconto oferecido por um dado cupom
    public static float Verifica_valor_desconto(String cupom_cpr)
    {
        float valor_desconto = 0;
            
        int i;
            
        for(i=0; i<cupom_valor.size(); i++)
        {
            if(cupom_valor.get(i).getCupom().equals(cupom_cpr))
            {
                valor_desconto = cupom_valor.get(i).getValor_desconto();
                   
                break;
            }
        }
          
        return valor_desconto;
    }
    
    // Construtor
    public Coupon(String cod_cupom, float valor)
    {
        this.coupon = cod_cupom;
        this.discountedValue = valor;
    }
    
    // Getters
    public String getCupom() {
        return coupon;
    }

    public float getValor_desconto() {
        return discountedValue;
    }
                
}
    