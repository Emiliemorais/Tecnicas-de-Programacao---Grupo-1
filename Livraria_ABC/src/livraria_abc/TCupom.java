
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

// Tipo Cupom de desconto
public class TCupom
{
    static List <TCupom>  cupom_valor = new ArrayList<>();
    String cupom;
    float valor_desconto;
        
    public TCupom(){}
       
    // Verifica se um dado cupom está cadastrado no sistema
    public static boolean Verifica_cupom(String cupom_cpr)
    {
        int i, s=0;
            
        for(i=0; i<cupom_valor.size(); i++)
        {
            if(cupom_valor.get(i).getCupom().equals(cupom_cpr))
            {
                s++;
                  
                break;
            }
        }
            
        if(s==0)
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
    public TCupom(String cod_cupom, float valor)
    {
        this.cupom = cod_cupom;
        this.valor_desconto = valor;
    }
    
    // Getters
    public String getCupom() {
        return cupom;
    }

    public float getValor_desconto() {
        return valor_desconto;
    }
                
}
    