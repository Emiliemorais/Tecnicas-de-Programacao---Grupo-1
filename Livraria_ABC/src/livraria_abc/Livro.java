
package livraria_abc;

import java.util.ArrayList;
import java.util.Scanner;
import static livraria_abc.Livraria_ABC.Menu;
import static livraria_abc.Livraria_ABC.acessoAtual;
import static livraria_abc.Livraria_ABC.clienteAtual;
import static livraria_abc.Livraria_ABC.nomeClienteAtual;

public class Livro
{
    static ArrayList<String> ISBN = new ArrayList<>();
    static ArrayList<String> Titulo = new ArrayList<>();
    static ArrayList<String> Tema = new ArrayList<>();
    static ArrayList<String> Preco = new ArrayList<>();
    static ArrayList<String> Autor = new ArrayList<>();
    static ArrayList<String> Editora = new ArrayList<>();
    static ArrayList<String> Ano_publicacao = new ArrayList<>();
    static ArrayList<String> Edicao = new ArrayList<>();
    //Cliente informa um livro que deseja
    static ArrayList<String> livro_recomendacao = new ArrayList<>();
    
    // Objeto utilizado para ler números inteiros e de ponto flutuante do usuário
    static Scanner input = new Scanner(System.in);
    
    // Objeto utilizado para ler strings do usuário
    static Scanner lerString = new Scanner(System.in);
    
    // Mostra o catálogo de livros cadastrados no sistema
    public static void Mostrar_catalogo()
    {
        int i = 0;
           
        System.out.println("\tConfira nossos produtos:\n");
        for(i=0; i<ISBN.size(); i++)
        {
            System.out.println( "\tTitulo: " + Titulo.get(i) );
            System.out.println( "\tAutor: " + Autor.get(i) );
            System.out.println( "\tEditora: " + Editora.get(i) );
            System.out.println( "\tEdição/Ano: " + Edicao.get(i) + "/"
                                + Ano_publicacao.get(i) );
            System.out.println( "\tTema: " + Tema.get(i) );
            System.out.println( "\tPor apenas R$" + Preco.get(i) + "!\n" );
        }
            
        Menu();
    }
       
    /*  Procura um livro pelo ISBN ou pelo título e retorna os dados
        cadastrados, se encontrar. Se não, pergunta ao cliente se 
        deseja recomendar aquisição.       
     */
    public void Consultar_livro ()
    {
        String livro_procurar;
           
        System.out.println("\nInforme o ISBN ou o título do livro que deseja:");
        livro_procurar = lerString.nextLine();
          
        if( ISBN.contains(livro_procurar) || Titulo.contains(livro_procurar) )
        {
            int i = -1;
            
            if( ISBN.contains(livro_procurar) )
            {
                i = ISBN.indexOf(livro_procurar);
            }
            else if( Titulo.contains(livro_procurar) )
            {
                i = Titulo.indexOf(livro_procurar);
            }
            else
            {
                // Nothing to do
            }
                
            System.out.println("\nEncontramos o seguinte livro em nosso"
                               + " sistema por apenas R$"
                               + Preco.get(i) + ":\n");
                
            System.out.println( "Titulo: " + Titulo.get(i) );
            System.out.println( "Autor: " + Autor.get(i) );
            System.out.println( "Editora: " + Editora.get(i) );
            System.out.println( "Edição/Ano: " + Edicao.get(i)
                               + "/" + Ano_publicacao.get(i) );
            System.out.println( "Tema: " + Tema.get(i) );
                
            String opcao = "";
            
            if(!acessoAtual)
            {
                System.out.println("\nDeseja comprar esse livro?"
                                   + " Faça login agora mesmo!\n "
                                   + "1-Fazer login  0-Voltar para catálogo");
            }
            else
            {
                System.out.println("\n" + nomeClienteAtual + ","
                                   + " deseja comprar esse livro?!\n"
                                   + " 1-SIM  0-Voltar para catálogo");
            }
                
            opcao = lerString.nextLine();
                
            if( "1".equals(opcao) )
            {
                String cliente_log = "";
                int quant = -1;
                
                Cliente cliente = new Cliente();
                    
                cliente_log = cliente.efetuarLogin();
                    
                if(acessoAtual)
                {
                    int indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                        
                    System.out.println("\nOlá " + Cliente.nome.get(indice_Clogado)
                                       + ".\nInforme a quantidade de '" 
                                       + Titulo.get(i) + "' desejada: ");
                    quant = input.nextInt();
                    
                    Cart.addItemsToCart(ISBN.get(i),quant,cliente_log);
                }
                else
                {
                    // Nothing to do
                }
            }
            else
            {
                Mostrar_catalogo();
            }
        }
        else
        {
            String recomenda = "";
            
            System.out.println("\nSinto muito mas não temos esse livro em estoque,"
                               + " mas seria um prazer receber sua recomendação "
                               + "de livros para que possamos aumentar nosso"
                               + " acervo e melhor serví-lo!");
            System.out.println("Deseja recomendar esse livro para aquisição?\n"
                               + "1-SIM  | 0-NÃO");
            recomenda = lerString.nextLine();
                
            if( "1".equals(recomenda) )
            {
                boolean ok = false;
                
                ok = livro_recomendacao.add(livro_procurar);
                    
                if(ok)
                {
                    System.out.println("\nSua recomendação foi recebida com sucesso! Agradecemos sua participação e iremos analisar o seu pedido e, se possível, este livro estará em breve nas nossas prateleiras!");
                }
                else
                {
                    // Nothing to do
                }
            }
            else
            {
                // Nothing to do
            }
               
            Menu();
        }
            
    }
}
