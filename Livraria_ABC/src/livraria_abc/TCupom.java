
package livraria_abc;

import java.util.*;

    public class TCupom
    {
        static List <TCupom>  cupom_valor = new ArrayList<>();
        String cupom;
        float valor_desconto;
        
        public TCupom(){}
        
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
        
        public TCupom(String cod_cupom, float valor)
        {
            this.cupom = cod_cupom;
            this.valor_desconto = valor;
        }
        
        public String getCupom() {
            return cupom;
        }

        public float getValor_desconto() {
            return valor_desconto;
        }
                
    }