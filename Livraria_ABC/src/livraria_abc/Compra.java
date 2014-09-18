
package livraria_abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static livraria_abc.Livraria_ABC.end_compra;
import static livraria_abc.Livraria_ABC.tipo_pag;
import static livraria_abc.Livraria_ABC.COD_compra;
import static livraria_abc.Livraria_ABC.Menu;
import static livraria_abc.Livraria_ABC.clienteAtual;

    public class Compra
    {
        static List<Compra> Compras = new ArrayList<>();
        int Cod_compra;
        String Data_compra;
        float Valor_compra;
        String Endereco_entrega;
        String Tipo_pag;
                
        
        // Objeto utilizado para ler números inteiros e de ponto flutuante do usuário
        static Scanner input = new Scanner(System.in);
    
        // Objeto utilizado para ler strings do usuário
        static Scanner lerString = new Scanner(System.in);
        
        // Construtor padrão
        public Compra(){}
        
        // Construtor
        public Compra(int cod, String data, float valor, String endereco, String tipo_pag)
        {
            this.Cod_compra = cod;
            this.Data_compra = data;
            this.Valor_compra = valor;
            this.Endereco_entrega = endereco;
            this.Tipo_pag = tipo_pag;
        }
        
        // Armazena a compra na lista 'Compras'
        public static void Cadastrar_compra()
        {
            Compra comp = new Compra();
            
            comp.Endereco_entrega = end_compra;
            comp.Tipo_pag = tipo_pag;
            comp.Valor_compra = Cart.calculateCartValue();
            comp.Cod_compra = COD_compra;
           
            Compras.add(comp);
            
        }
        
        // Cancela uma compra com a confirmação do usuário
        public static void Cancelar_compra()
        {
            String opcao;
            
            System.out.println("\nDeseja mesmo cancelar a compra? 1-SIM  2-NÃO");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                end_compra="blank";
                tipo_pag="blank";
                COD_compra = -1;
                
                Menu();
            }
            else
            {
                Finalizar_compra();
            }    
        }

        // Verifica se o endereço a ser utiizado será o cadastrado no sistema
        public static void Verificar_endereco()
        {
            int i;
            String opcao;
            
            i = Cliente.cpf.indexOf(clienteAtual);
                        
            System.out.println("\nO endereço de entrega será: '"+Cliente.endereco.get(i)+"'?\n1-SIM  0- ALTERAR ENDEREÇO");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                end_compra = Cliente.endereco.get(i);
                
                Verificar_pagamento();
            }
            else
            {
                String novo_endereco;
                
                System.out.println("\nInforme o novo endereço de entrega: ");
                novo_endereco = lerString.nextLine();
                
                end_compra = novo_endereco;
                
                Verificar_pagamento();
            }
        }
        
        // Verifica o valor final e qual o tipo do pagamento
        public static void Verificar_pagamento()
        {
            float total;
            String tipo_p;
            
            total = Cart.calculateCartValue();
            
            System.out.printf("\nO valor total a ser pago é R$%.2f.\n",total);
            
            boolean ok = false;
            
            do
            {
                System.out.println("\nQual será a forma de pagamento?\n1-Boleto  |  2-Cartão de crédito");
                tipo_p = lerString.nextLine();
            
                if(tipo_p.equals("1"))
                {
                    tipo_pag = Tipo_pagamento.Tipos.get(0).tipo;
                    ok = true;
                }
                else if(tipo_p.equals("2"))
                {
                    tipo_pag = Tipo_pagamento.Tipos.get(1).tipo;
                    ok = true;
                }
                else
                {
                    System.out.println("\nOpção Inválida\n");
                    ok = false;
                }   
            }while(ok==false);
            
            Finalizar_compra();
        }
        
        // Finaliza uma compra e esvazia a cesta aberta se confirmado
        public static void Finalizar_compra()
        {
            String opcao;
            
            System.out.println("\nSeu pedido é: ");
            
            Cart.Listar_itens_cesta_();
            
            System.out.println("\nEndereço de entrega: "+end_compra);
            System.out.println("\nTipo de pagamento: "+tipo_pag);
            
            System.out.println("\nDeseja finalizar a compra?\n 1-SIM  |  0-CANCELAR COMPRA");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                System.out.println("\nCompra realizada com sucesso!\n");
                
                Cart.emptyCart();
                
                Cadastrar_compra();
                
                Menu();
            }
            else
            {
                Cancelar_compra();
            }
        }
    }
    