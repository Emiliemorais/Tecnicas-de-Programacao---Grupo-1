
package livraria_abc;

import java.util.ArrayList;
import java.util.List;

public class Tipo_pagamento
{
    //Atributos fixos
    static List<Tipo_pagamento> Tipos = new ArrayList<>();
    String tipo;
    String descricao;
	
    //Construtor
    public Tipo_pagamento(String tipo, String descricao) 
    {
        this.tipo = tipo;
        this.descricao = descricao;
    }
}