
package livraria_abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static livraria_abc.Livraria_ABC.acessoAtual;
import static livraria_abc.Livraria_ABC.Menu;
import static livraria_abc.Livraria_ABC.desconto;
import static livraria_abc.Livraria_ABC.clienteAtual;

    public class Cesta
    {
        static String Cod_cesta = "blank";
        static String Cliente_cesta="blank";
        static List<Cesta> Itens_quant = new ArrayList<>();
        String Itens_add;
        int Quant_add;
        
        // Objeto que gera um número aleatório
        static Random gera_num = new Random();
        
        // Objeto utilizado para ler números inteiros e de ponto flutuante do usuário
        static Scanner input = new Scanner(System.in);
    
        // Objeto utilizado para ler strings do usuário
        static Scanner lerString = new Scanner(System.in);
        
        // Inicio Getters and Setters
        public String getItens_add() {
            return Itens_add;
        }

        public void setItens_add(String Itens_add) {
            this.Itens_add = Itens_add;
        }

        public int getQuant_add() {
            return Quant_add;
        }

        public void setQuant_add(int Quant_add) {
            this.Quant_add = Quant_add;
        }
        // Fim Getters and Setters
        
        // Construtor padrão
        public Cesta(){}
        
        // Construtor
        public Cesta(String itens, int quant)
        {
            this.Itens_add = itens;
            this.Quant_add = quant;
        }
        
        // Adiciona um livro para a cesta do cliente
        public static void Adicionar_itens_a_cesta(String isbn_livro, int quantidade, String cliente_c)
        {
           if(acessoAtual) 
           {
            /* Verifica se já tem uma cesta aberta para o cliente "cliente_c".
               Se não houver, é aberta uma.
            */   
            if(!cliente_c.equals(Cliente_cesta))
            {
                int cod_c;
                String cod_s;
                boolean ok_cod=false;
                
                cod_c = gera_num.nextInt(10000);
                cod_s = Integer.toString(cod_c);
                    
                while(ok_cod==false)
                { 
                    if(Cod_cesta.contains(cod_s))
                    {
                        ok_cod = false;
                        cod_c = gera_num.nextInt(10000);
                        cod_s = Integer.toString(cod_c);
                    }
                    else
                        ok_cod = true;
                }
                
                Cod_cesta = cod_s;
                Cliente_cesta = cliente_c ;
                
            }
            
            int indice_livro;
            
            indice_livro = Livro.ISBN.indexOf(isbn_livro);
                   
            Cesta i1 = new Cesta();
            
            i1.Itens_add = isbn_livro;
            i1.Quant_add = quantidade;
            
            Cesta itens_quant = new Cesta(i1.Itens_add, i1.Quant_add);
            
            boolean ok;
            ok = Itens_quant.add(itens_quant);
            
            if(ok) 
                System.out.println("'"+Livro.Titulo.get(indice_livro)+"' foi adicionado à sua cesta!");
            
            Menu();
          }
          else
          {
              System.out.println("\nÉ necessário fazer login primeiro.\n"); 
              
              String cliente_l;
              
              Cliente cliente = new Cliente();
              
              cliente_l = cliente.efetuarLogin();
              
              if(!"blank".equals(cliente_l))
                  Adicionar_itens_a_cesta(isbn_livro, quantidade, cliente_l);
              else
              {
                  System.out.println("\nLogin mal sucedido. Tente Novamente.\n");
                  
                  Menu();
              }
          }
        }
        
        // Esvazia uma cesta e retorna o resultado da operação
        public static boolean Esvaziar_cesta()
        {
            Cod_cesta = "blank";
            Cliente_cesta = "blank";
            desconto = 0;
            
            int t=0;
            while(!Itens_quant.isEmpty())
            {
                Itens_quant.remove(t);
            }
            
            if(Itens_quant.isEmpty())
                return true;
            else
                return false;
        }
        
        // Lista todos itens cadastrados na cesta atual
        public static void Listar_itens_cesta ()
        {
            if(acessoAtual)
            {
                int indice_Clogado;
                
                indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                
                System.out.println("\nOlá "+Cliente.nome.get(indice_Clogado)+"!\n");
                
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.nome.get(indice_Clogado)+", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+Cod_cesta);
                    for(p=0; p<Itens_quant.size(); p++)
                    {
                        indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                        livro = Livro.Titulo.get(indice_Livro);
                        
                        System.out.println("Item "+(p+1)+": '"+livro+"'");
                        System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                        System.out.println("Preço(un.): R$"+Livro.Preco.get(indice_Livro)+"\n");
                        
                    }
                    
                    float total;
                    
                    total = Calcula_valor_compra();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", total);
                }
                
                Menu();
            }
            else
            {
                System.out.println("\nNenhum cliente logado. Faça login primeiro!");
                
                Menu();
            }
        }
        
        // Listar alternativo - Descobrir porquê..
        public static void Listar_itens_cesta_ ()
        {
                int indice_Clogado;
                
                indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                
                           
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.nome.get(indice_Clogado)+", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+Cod_cesta);
                    for(p=0; p<Itens_quant.size(); p++)
                    {
                        indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                        livro = Livro.Titulo.get(indice_Livro);
                        
                        System.out.println("Item "+(p+1)+": '"+livro+"'");
                        System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                        System.out.println("Preço(un.): R$"+Livro.Preco.get(indice_Livro)+"\n");
                        
                    }
                    
                    float total;
                    
                    total = Calcula_valor_compra();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", total);
                }
           
        }
        
        // Retorna o catálogo de livros
        public static void Continuar_comprando()
        {
            Livro.Mostrar_catalogo();
        }
        
        // Retira um livro de uma cesta
        public static void Retirar_itens_cesta()
        {
            if(Itens_quant.isEmpty())
            {
                System.out.println("\nCesta vazia. Vamos lá, inclua algo agora mesmo!\n");
                Menu();
            }
            else
            {
                String livro_retirar;
                
                int p, indice_Livro;
                    
                String livro;
                    
                System.out.println("Código da cesta: "+Cod_cesta);
                for(p=0; p<Itens_quant.size(); p++)
                {
                    indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                    livro = Livro.Titulo.get(indice_Livro);
                        
                    System.out.println("Item "+(p+1)+": '"+livro+"'");
                    System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                }
                int item;
                boolean item_ok = false;
                
                do
                {
                    System.out.println("\nInforme o número do item que deseja retirar da cesta: ");
                    livro_retirar = lerString.nextLine();
                
                    item = Integer.parseInt(livro_retirar);
                
                    if(item>0 && item<Itens_quant.size())
                        item_ok = true;
                    else
                        item_ok = false;
                    
                }while(item_ok==false);
                
                Cesta remove = Itens_quant.remove(item-1);
                
                int ind;
                
                ind = Livro.ISBN.indexOf(remove.Itens_add);
                
                System.out.println("\n'"+Livro.Titulo.get(ind)+"'/"+remove.Quant_add+" foi removido da cesta.\n");
                
                Menu();
            }
        }
        
        // Calcula o valor total de todos os itens da cesta
        public static float Calcula_valor_compra()
        {
            if(Itens_quant.isEmpty())
            {
                return 0;
            }
            else
            {
                String livro, preco_livro;
                int p, pos_livro;
                float valor_compra=0, subtotal, preco;
                
                for(p=0; p<Itens_quant.size(); p++)
                {
                    livro = Itens_quant.get(p).Itens_add;
                    
                    pos_livro = Livro.ISBN.indexOf(livro);
                    
                    preco_livro = Livro.Preco.get(pos_livro);
                    
                    preco = Float.parseFloat(preco_livro);
                    
                    subtotal = preco*Itens_quant.get(p).Quant_add;
                    
                    valor_compra +=subtotal;                                        
                }
                
                valor_compra = valor_compra - (valor_compra*desconto);
                                                
                return valor_compra;
            }
       
        }
        
        // Inclui um desconto a partir de um cupom informado
        public static void Incluir_desconto()
        {
            String cupom;
            
            System.out.println("\nInforme o código do seu cupom de desconto:");
            cupom = lerString.nextLine();
            
            boolean verify_cup = false;
            
            // Verifica se o cupom é cadastrado no sistema
            verify_cup = TCupom.Verifica_cupom(cupom);
            
            if(verify_cup)
            {
                float valor;
                
                valor = TCupom.Verifica_valor_desconto(cupom);
                
                System.out.println("\nParabéns! Você ganhou "+valor+"% de desconto em qualquer livro!\n");
                
                desconto = valor/100;
                
            }
            else
            {
                System.out.println("\nCumpom não cadastrado em nosso sistema.\n");
                Menu();              
            }
            
        }
        
        // Cria um inteiro aleatório que será usado na classe 'Compra' 
        public static int Fechar_cesta()
        {
            if(!Itens_quant.isEmpty())
            {
                int cod_compra;
            
                cod_compra = gera_num.nextInt(10000);
                
                desconto = 0;
                            
                return cod_compra; /// Codigo que vai para classe 'Compra'
            }
            else
            {
                System.out.println("\nCesta Vazia!\n");
                return -1;
            }
        }
        
    }
    