
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
    public static int infoAboutBook(){
        System.out.println("\tConfira nossos produtos:\n");
        
        int i=0;
        for (i=0; i<listISBN.size(); i++)
        {
            System.out.println( "\tTitulo: " + listTitle.get(i) );
            System.out.println( "\tAutor: " + listAuthor.get(i) );
            System.out.println( "\tEditora: " + listPublishingHouse.get(i) );
            System.out.println( "\tEdição/Ano: " + listEdition.get(i) + "/"
                                                 + listYearPublication.get(i) );
            System.out.println( "\tTema: " + listTheme.get(i) );
            System.out.println( "\tPor apenas R$" + listPrice.get(i) + "!\n" );
        }
        
        return i;
    }
    public static void showCatalog()
    {
        // i - Receives the index of the book
        int i;        
        i = infoAboutBook();    
        showMenu();
    }
       
    /* Search a book by the ISBN or by the title and return the data registered
        If the book is not found the client can recommend it.      
     */
    public int ReceivesIndexBook(String researchedBookByUser){
        int i = -1;
            
        if (listISBN.contains(researchedBookByUser))
        {
            i = listISBN.indexOf(researchedBookByUser);
        }
        else if (listTitle.contains(researchedBookByUser))
        {
            i = listTitle.indexOf(researchedBookByUser);
        }
        else
        {
            // Nothing to do
        }
        
        return i;
    }
    
    public void showBookFound(int i){
        String researchedStatus = "\nEncontramos o seguinte livro em nosso"
                                  + " sistema por apenas R$"
                                  + listPrice.get(i) + ":\n";
        System.out.println(researchedStatus);

        System.out.println( "Titulo: " + listTitle.get(i) );
        System.out.println( "Autor: " + listAuthor.get(i) );
        System.out.println( "Editora: " + listPublishingHouse.get(i) );
        System.out.println( "Edição/Ano: " + listEdition.get(i)
                           + "/" + listYearPublication.get(i) );
        System.out.println( "Tema: " + listTheme.get(i) );
    }
    
    public String optionForBuyBook(){
        String option = "";
        if (!currentAccess)
        {
            String purchaseBookMenuNoCliente = "\nDeseja comprar esse livro?"
                                    + " Faça login agora mesmo!\n "
                                    + "1-Fazer login  0-Voltar para catálogo";
            System.out.println(purchaseBookMenuNoCliente);
        }
        else
        {
            String purchaseBookMenu = "\n" + currentClientName + ","
                                    + " deseja comprar esse livro?!\n"
                                    + " 1-SIM  0-Voltar para catálogo";
            System.out.println(purchaseBookMenu);
        }

        option = inputString.nextLine();
        
        return option;
    }
    
    public void showAmountBook(int i, int bookQuantity , String loginClient){
        int indexOfTheClient = Client.cpf.indexOf(currentClient);

        String inputQuantityByCliente = "\nOlá " + Client.nameArray.get(indexOfTheClient)
                                      + ".\nInforme a quantidade de '" 
                                      + listTitle.get(i) + "' desejada: ";
        System.out.println(inputQuantityByCliente);
        bookQuantity = input.nextInt();

        Cart.addItemsToCart(listISBN.get(i),bookQuantity,loginClient);
        
    }
    
    public void buyingBook(int i){
        // Receives the login of the client
        String loginClient = "";

        // Receives the quantity of books 
        int bookQuantity = -1;

        // Client class's instance to access the class
        Client client = new Client();

        loginClient = client.getLogin();

        if (currentAccess)
        {
            // Receives the index of the client
            showAmountBook(i, bookQuantity, loginClient);
        }
        else
        {
            // Nothing to do
        }
    }
    
    public void showOptionBookExists(String researchedBookByUser){
        // i - Receives the index of the book
        int i=0;
        i= ReceivesIndexBook(researchedBookByUser);

        //Show books founds
        showBookFound(i);

        // Receives the option from the user about the buying of the book
        String option = "";

        optionForBuyBook();
        
        option = optionForBuyBook();

        if ("1".equals(option))
        {
            buyingBook(i);
        }
        else
        {
            showCatalog();
        }
    }
    
    public String recommendationByUser(){
        String optionRecommendation = "";
        String messageRecommendation = "\nSinto muito mas não temos esse livro em estoque,"
                                     + " mas seria um prazer receber sua recomendação "
                                     + "de livros para que possamos aumentar nosso"
                                     + " acervo e melhor serví-lo!";
        System.out.println(messageRecommendation);

        String messageInputRecommendation = "Deseja recomendar esse livro para aquisição?\n"
                                   + "1-SIM  | 0-NÃO";
        System.out.println(messageInputRecommendation);
        optionRecommendation = inputString.nextLine();
        
        return optionRecommendation;
    }
    
    public void acceptrecommendation(String researchedBookByUser){
        boolean verifyAddRecommendation = false;

        verifyAddRecommendation = listBookRecommendation.add(researchedBookByUser);

        if (verifyAddRecommendation)
        {
            String messageSucessRecommendation = "\nSua recomendação foi recebida com sucesso! "
                                               + "Agradecemos sua participação e iremos analisar o seu pedido e,"
                                               + " se possível, este livro estará em breve nas nossas prateleiras!";
            System.out.println(messageSucessRecommendation);
        }
        else
        {
            // Nothing to do
        }
    }
            
    public void showOptionBookNotExists(String researchedBookByUser){
        
        String optionRecommendation = "";
        optionRecommendation = recommendationByUser();

        if ("1".equals(optionRecommendation))
        {
            // Receives the status (sucess or failure) of addition of the book in the recommendation list 
            acceptrecommendation(researchedBookByUser);
        }
        else
        {
            // Nothing to do
        }

        showMenu();
    }
    
    public void queryBook ()
    {
        // Receives the ISBN or the title of the book researched by user
        String researchedBookByUser;
           
        System.out.println("\nInforme o ISBN ou o título do livro que deseja:");
        researchedBookByUser = inputString.nextLine();
          
        if (listISBN.contains(researchedBookByUser) || listTitle.contains(researchedBookByUser))
        {
            //show Options to Book if Exists 
            showOptionBookExists(researchedBookByUser);
        }
        else
        {
            // Receives the option from the user about the recommendation of the book
            showOptionBookNotExists(researchedBookByUser);
        }    
    }
    
}
