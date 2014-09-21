
package livraria_abc;

import java.util.ArrayList;
import java.util.Scanner;
import static livraria_abc.ABCBookStore.showMenu;
import static livraria_abc.ABCBookStore.currentAccess;
import static livraria_abc.ABCBookStore.currentClient;
import static livraria_abc.ABCBookStore.currentClientName;

public class Book
{
    // Receives a list of ISBN from books
    static ArrayList<String> listISBN = new ArrayList<>();

    // Receives a list of titles from books
    static ArrayList<String> listTitle = new ArrayList<>();

    // Receives a list of thems from books
    static ArrayList<String> listTheme = new ArrayList<>();

    // Receives a list of prices from books
    static ArrayList<String> listPrice = new ArrayList<>();

    // Receives a list of authors from books
    static ArrayList<String> listAuthor = new ArrayList<>();
    
    // Receives a list of publishing houses from books
    static ArrayList<String> listPublishingHouse = new ArrayList<>();
    
    // Receives a list of years of publication from books
    static ArrayList<String> listYearPublication = new ArrayList<>();
    
    // Receives a list of edition from books
    static ArrayList<String> listEdition = new ArrayList<>();
    
    // Receives a list of books recommendation from books
    static ArrayList<String> listBookRecommendation = new ArrayList<>();
    
   // Receives a instance of Scanner class to receive the numbers from the user
    static Scanner input = new Scanner(System.in);
    
    // Receives a instance of Scanner class to receive the data in text from the user
    static Scanner inputString = new Scanner(System.in);
    
    // Show the catalog of books registered on the system
    public static void showCatalog ()
    {
        // i - Receives the index of the book
        int i = 0;
           
        System.out.println("\tConfira nossos produtos:\n");
        for(i=0; i<listISBN.size(); i++)
        {
            System.out.println( "\tTitulo: " + listTitle.get(i) );
            System.out.println( "\tAutor: " + listAuthor.get(i) );
            System.out.println( "\tEditora: " + listPublishingHouse.get(i) );
            System.out.println( "\tEdição/Ano: " + listEdition.get(i) + "/"
                                + listYearPublication.get(i) );
            System.out.println( "\tTema: " + listTheme.get(i) );
            System.out.println( "\tPor apenas R$" + listPrice.get(i) + "!\n" );
        }
            
        showMenu();
    }
       
    /* Search a book by the ISBN or by the title and return the data registered
        If the book is not found the client can recommend it.      
     */
    public void queryBook ()
    {
        // Receives the ISBN or the title of the book researched by user
        String researchedBookByUser;
           
        System.out.println("\nInforme o ISBN ou o título do livro que deseja:");
        researchedBookByUser = inputString.nextLine();
          
        if( listISBN.contains(researchedBookByUser) || listTitle.contains(researchedBookByUser) )
        {
            // i - Receives the index of the book
            int i = -1;
            
            if( listISBN.contains(researchedBookByUser) )
            {
                i = listISBN.indexOf(researchedBookByUser);
            }
            else if( listTitle.contains(researchedBookByUser) )
            {
                i = listTitle.indexOf(researchedBookByUser);
            }
            else
            {
                // Nothing to do
            }
                
            System.out.println("\nEncontramos o seguinte livro em nosso"
                               + " sistema por apenas R$"
                               + listPrice.get(i) + ":\n");
                
            System.out.println( "Titulo: " + listTitle.get(i) );
            System.out.println( "Autor: " + listAuthor.get(i) );
            System.out.println( "Editora: " + listPublishingHouse.get(i) );
            System.out.println( "Edição/Ano: " + listEdition.get(i)
                               + "/" + listYearPublication.get(i) );
            System.out.println( "Tema: " + listTheme.get(i) );
                
            // Receives the option from the user about the buying of the book
            String option = "";
            
            if( !currentAccess )
            {
                System.out.println("\nDeseja comprar esse livro?"
                                   + " Faça login agora mesmo!\n "
                                   + "1-Fazer login  0-Voltar para catálogo");
            }
            else
            {
                System.out.println("\n" + currentClientName + ","
                                   + " deseja comprar esse livro?!\n"
                                   + " 1-SIM  0-Voltar para catálogo");
            }
                
            option = inputString.nextLine();
                
            if( "1".equals(option) )
            {
                // Receives the login of the client
                String loginClient = "";

                // Receives the quantity of books 
                int bookQuantity = -1;

                // Client class's instance to access the class
                Cliente client = new Cliente();
                    
                loginClient = client.efetuarLogin();
                    
                if( currentAccess )
                {
                    // Receives the index of the client
                    int indexOfTheClient = Cliente.cpf.indexOf(currentClient);
                        
                    System.out.println("\nOlá " + Cliente.nome.get(indexOfTheClient)
                                       + ".\nInforme a quantidade de '" 
                                       + listTitle.get(i) + "' desejada: ");
                    bookQuantity = input.nextInt();
                    
                    Cart.addItemsToCart(listISBN.get(i),bookQuantity,loginClient);
                }
                else
                {
                    // Nothing to do
                }
            }
            else
            {
                showCatalog();
            }
        }
        else
        {
            // Receives the option from the user about the recommendation of the book
            String optionRecommendation = "";
            
            System.out.println("\nSinto muito mas não temos esse livro em estoque,"
                               + " mas seria um prazer receber sua recomendação "
                               + "de livros para que possamos aumentar nosso"
                               + " acervo e melhor serví-lo!");
            System.out.println("Deseja recomendar esse livro para aquisição?\n"
                               + "1-SIM  | 0-NÃO");
            optionRecommendation = inputString.nextLine();
                
            if( "1".equals(optionRecommendation) )
            {
                // Receives the status (sucess or failure) of addition of the book in the recommendation list 
                boolean verifyAddRecommendation = false;
                
                verifyAddRecommendation = listBookRecommendation.add(researchedBookByUser);
                    
                if( verifyAddRecommendation )
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
               
            showMenu();
        }
            
    }
}