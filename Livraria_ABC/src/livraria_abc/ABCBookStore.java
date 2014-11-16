package livraria_abc;

import java.util.*;

public class ABCBookStore
{
    /*
    *   Receives the current condition of the system about an access
    *   If some login was open currentAccess = true;
    */
    static boolean currentAccess = false; 
    
    /*
    *   Receives the current client logged into the system
    *   If some login was open currentClient = cpf do cliente logado;
    */
    static String currentClient = "blank";
    
    // Receives the current client's name logged into the system
    static String currentClientName = "blank";
    
    // Receives the value of the discount
    static float discount = 0;
    
    // Receives the code of the buying - used like a global variable
    static int buyingCode = -1;
    
    // Receives the delivery address of the book
    static String deliveryAddress = "blank";
    
    // Receives the type of the payment
    static String paymentType = "blank";
    
    // Print an exit message to the user
    public static void showExitMessage ()
    {
        System.out.println("\nObrigado por utilizar nossos serviços! A Livraria ABC agrade e volte sempre!");
    }
    
    public static String showOptionMenu(){
        String option = "";
        
        if(!option.equals("0"))
        {
            System.out.println("\nOlá "+currentClientName+"! O que deseja na Livraria Virtual ABC?");
            System.out.println("1 - Olhar catálogo");
            System.out.println("2 - Procurar por Livro específico");
            System.out.println("3 - Adicionar livro à cesta");
            System.out.println("4 - Visualizar cesta");
            System.out.println("5 - Efetuar logout");
            System.out.println("6 - Retirar item da cesta");
            System.out.println("7 - Incluir cupom de desconto");
            System.out.println("8 - Fechar cesta e finalizar compra");
            System.out.println("9 - Acessar sua conta");
            System.out.println("0 - Efetuar logout, fechar página e sair do sistema");
            
            option = inputString.nextLine();
        }else{
            //Noting to do
        }
        return option;
    }
    
    public static void finishCart(){
        // Receives the code of the buying
        int buyingCode;

        buyingCode = Cart.closeCart();

        if( buyingCode == -1 )
        {
            showMenu();
        }
        else
        {
            buyingCode = buyingCode;
            Purchase.checkAddress();
        }
        //// Passar esse codigo para classe compra. Se cod_compra = -1 ta errado. VALIDAR!!!

    }
    public static void addBookCart(){
        String researchedBookByUser;
                    
        System.out.println("\nInforme o ISBN ou o título do livro que deseja: ");
        researchedBookByUser = inputString.nextLine();

        boolean bookOfTheResearchedBookByUserByISBN = Book.listISBN.contains(researchedBookByUser);
        boolean bookOfTheResearchedBookByUserByTitle = Book.listTitle.contains(researchedBookByUser);
        
        if( bookOfTheResearchedBookByUserByISBN || bookOfTheResearchedBookByUserByTitle )
        {
            // Receives the index of the book researched by user
            int indexOfTheBook = -1;

            // Receives the quantity of books 
            int bookQuantity;

            
            if( bookOfTheResearchedBookByUserByISBN )
            {
                indexOfTheBook = Book.listISBN.indexOf(researchedBookByUser);
            }
            else if( bookOfTheResearchedBookByUserByTitle )
            {
                indexOfTheBook = Book.listTitle.indexOf(researchedBookByUser);
            }
            else
            {
                // Nothing to do
            }

            System.out.println("\nInforme a quantidade de '"+Book.listTitle.get(indexOfTheBook)+"' desejada:");
            bookQuantity = input.nextInt();

            Cart.addItemsToCart(Book.listISBN.get(indexOfTheBook), bookQuantity, currentClient);
        }
        else
        {
            String messageFailResearched = "\nNão encontramos '"+researchedBookByUser+"' em nosso sistema."
                                         + " Verifique a grafia e tente novamente.\n";
            System.out.println(messageFailResearched);
            showMenu();
        }
    }
    
    public static void showMenuForLogger(String option){
        option = showOptionMenu();
            
            // Book class's instance to access the class
            Book book = new Book();
            
            // Client class's instance to access the class
            Client client = new Client();
            
            switch(option)
            {
                case "1":
                {
                    book.showCatalog();
                    break;
                }
                case "2":
                {
                    book.queryBook();
                    break;
                }
                case "3":
                {
                    // Receives the listISBN or the title of the book researched by user
                    addBookCart();
                    
                    break;
                }
                case "4":
                {
                    Cart.showCartItems();
                    break;
                }
                case "5":
                {
                    client.getLogout();
                    break;
                }
                case "6":
                {
                    Cart.removeCartItem();
                    break;
                }
                case "7":
                {
                    Cart.includeDiscount();
                    break;
                }
                case "8":
                {
                    finishCart();
                    break;
                }
                case "9":
                {
                    client.menuClient();
                    break;
                }
                case "0":
                {
                    client.efetuarLogout_();
                        
                    showExitMessage();
                        
                       // break;
                }
                default:
                { 
                    if( !"0".equals(option) )
                    {
                        System.out.println("\nOpção Inválida!\n");
                    }
                    else
                    {
                        // Nothing to do
                    }
                   
                }
            }
    }
    public static void showMenuForNotLogger( String option){
        if( !option.equals("0") )
        {            
            System.out.println("\nOlá visitante! O que deseja na Livraria Virtual ABC?");
            System.out.println("1 - Olhar catálogo");
            System.out.println("2 - Procurar por Livro específico");
            System.out.println("3 - Adicionar livro à cesta");
            System.out.println("4 - Efetuar_login");
            System.out.println("0 - Fechar página e sair do sistema");

            option = inputString.nextLine();
        }else{
            //nothing to do
        }


        // Book class's instance to access the class
        Book book = new Book();

        // Client class's instance to access the class
        Client client = new Client();

        switch(option)
        {
            case "1":
            {
                book.showCatalog();
                break;
            }
            case "2":
            {
                book.queryBook();
                break;
            }
            case "3":
            {
                System.out.println("\nÉ necessário fazer login primeiro.");

                client.getLogin();

                showMenu();
                break;
            }
            case "4":
            {
                client.getLogin();
                break;
            }
            case "0":
            {
                showExitMessage();

               // break;
            }
            default:
            {
                if( !"0".equals(option) ){
                    System.out.println("\nOpção Inválida!\n");
                }
                else
                {
                    // Nothing to do
                }
            }
        }
    }
    public static void showMenu ()
    {
        // Receives the option from the user about what to do in the system
        String option = "";
        
        // Checks if someone has logged.
        if( currentAccess )
        {
            showMenuForLogger(option);
        }
        else
        {
            // Nothing to do
        }
    }
    
    
    // Receives a instance of Scanner class to receive the numbers from the user
    static Scanner input = new Scanner(System.in);
    
    // Receives a instance of Scanner class to receive the data in text from the user
    static Scanner inputString = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Bookstore class's instance to access the class
        Bookstore bookstore = new Bookstore();
        
        
        //The following data were inserted to simulate a database of the ABCBookStore 
        
        Book.listISBN.add(0, "9780345486572");
        Book.listTitle.add(0, "Dom Casmurro");
        Book.listTheme.add(0,"Literatura brasileira");
        Book.listPrice.add(0, "12.50");
        Book.listAuthor.add(0, "Machado de Assis");
        Book.listPublishingHouse.add(0, "Pearson");
        Book.listYearPublication.add(0,"2010");
        Book.listEdition.add(0,"3ª");

        Book.listISBN.add(1, "9780964729230");
        Book.listTitle.add(1, "A Cabana");
        Book.listTheme.add(1,"Ficção");
        Book.listPrice.add(1, "32.49");
        Book.listAuthor.add(1, "William P. Young");
        Book.listPublishingHouse.add(1, "Windblown");
        Book.listYearPublication.add(1,"2007");
        Book.listEdition.add(1,"2ª");

        Book.listISBN.add(2, "9788508106707");
        Book.listTitle.add(2, "Vida de droga");
        Book.listTheme.add(2,"Infanto-Juvenil");
        Book.listPrice.add(2, "15.80");
        Book.listAuthor.add(2, "Walcyr Carrasco");
        Book.listPublishingHouse.add(2, "Ática");
        Book.listYearPublication.add(1,"2005");
        Book.listEdition.add(2,"4ª");

        // Coupoun class's instance to access the class
        Coupon coupoun = new Coupon();

        coupoun.coupon = "0X2R";
        coupoun.discountedValue = 20;
        Coupon.cupom_valor.add(coupoun);

        // PaymentType class's instance to access the class
        PaymentType paymentType1 = new PaymentType("Boleto","");
        PaymentType paymentType2 = new PaymentType("Cartão","Aceitamos Visa, MasterCard e Cielo");

        PaymentType.listTypes.add(paymentType1);
        PaymentType.listTypes.add(paymentType2);
            
                        
        System.out.println("\n\t\t\t\tBem-vindo(a) a " + bookstore.bookstoreName + "!\n");
        Book.showCatalog();
                
        showMenu();           
    }
}