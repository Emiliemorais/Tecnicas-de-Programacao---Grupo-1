
package livraria_abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static livraria_abc.ABCBookStore.currentAccess;
import static livraria_abc.ABCBookStore.showMenu;
import static livraria_abc.ABCBookStore.discount;
import static livraria_abc.ABCBookStore.currentClient;

    public class Cart
    {
        //  Contains the cart unique code
        static String cartCode = "blank";
        
        // Contains the cpf (unique code of a client) of the cart owner 
        static String cpfOfCartOwner = "blank";
        
        // List that contains all items of a cart and their quantity
        static List<Cart> itemsAndQuantity = new ArrayList<>();
        
        // String that contains the item to be included on cart
        String itemsToAddOnCart;
        
        // Contains the quantity of the item to be included
        int quantityOfItens;
        
        // Generates a random integer
        static Random randomGenerator = new Random();
        
        // Scanner object used to read integer and float data from user
        static Scanner input = new Scanner(System.in);
    
        // Scanner object used to read strings data from user
        static Scanner inputString = new Scanner(System.in);
        
        // Start Getters and Setters
        public String getItens_add() 
        {
            return itemsToAddOnCart;
        }

        public void setItens_add(String Itens_add) 
        {
            this.itemsToAddOnCart = Itens_add;
        }

        public int getQuantityOfItens() 
        {
            return quantityOfItens;
        }

        public void setQuantityOfItens(int quantityOfItens) 
        {
            this.quantityOfItens = quantityOfItens;
        }
        // End Getters and Setters
        
        // General Construtor
        public Cart(){}
        
        // Constructor
        public Cart(String itens, int quant)
        {
            this.itemsToAddOnCart = itens;
            this.quantityOfItens = quant;
        }
        
        /**
         * Add items to a cart
         * @param bookToAddIsbn - Book listISBN to add on cart
         * @param quantityToAdd - Quantity of books to add on cart
         * @param clientCpfToAddItems - Client who is shopping the books
         */
        public static void addItemsToCart(String bookToAddIsbn,
                                          int quantityToAdd,
                                          String clientCpfToAddItems)
        {
           if( currentAccess ) 
           {
            /*
             * Check if there is a cart open to client "clientCpfToAddItems".
             * If doesn't, is opened one.
             */   
            if( !clientCpfToAddItems.equals(cpfOfCartOwner) )
            {
                // Used to receive the result from 'ramdomGenerator'
                int cartCodeInteger = 0;
                
                // Used to receive the cartCodeInteger as a String
                String cartCodeString = "";
                
                // Verify if the code generated already exists. If so, okCartCode = false
                boolean okCartCode = false;
                
                cartCodeInteger = randomGenerator.nextInt(10000);
                cartCodeString = Integer.toString(cartCodeInteger);
                    
                while( okCartCode == false )
                { 
                    if( cartCode.contains(cartCodeString) )
                    {
                        okCartCode = false;
                        cartCodeInteger = randomGenerator.nextInt(10000);
                        cartCodeString = Integer.toString(cartCodeInteger);
                    }
                    else
                    {
                        okCartCode = true;
                    }
                }
                
                cartCode = cartCodeString;
                cpfOfCartOwner = clientCpfToAddItems ;
                
            }
            else
            {
                // Nothing to do
            }
            
            /* 
             * Receive the position of 'bookToAddIsbn' on the list of books
             * (refers to 'Book' class)
             */
            int bookIndex = 0;
            
            bookIndex = Book.listISBN.indexOf(bookToAddIsbn);
            
            // Contains the items and quantity to include on cart
            Cart itemsAndQuantityToAddOnList = new Cart(bookToAddIsbn, quantityToAdd);
            
            // Receive the result of the method 'add' from lists
            boolean okAddOnList = false;
            
            okAddOnList = itemsAndQuantity.add(itemsAndQuantityToAddOnList);
            
            if( okAddOnList ) 
            {
                System.out.println("'" + Book.listTitle.get(bookIndex) 
                                   + "' foi adicionado à sua cesta!");
            }
            else
            {
                // Nothing to do
            }
            
            showMenu();
          }
          else
          {
              System.out.println("\nÉ necessário fazer login primeiro.\n"); 
              
              // Receive the result of the method 'login'
              String clientCpfToAddItens;
              
              // Instantiated to get access to the method 'login'
              Cliente client = new Cliente();
              
              clientCpfToAddItens = client.efetuarLogin();
              
              if( !"blank".equals(clientCpfToAddItens) )
              {
                  addItemsToCart(bookToAddIsbn, quantityToAdd, clientCpfToAddItens);
              }
              else
              {
                  System.out.println("\nLogin mal sucedido. Tente Novamente.\n");
                  
                  showMenu();
              }
          }
        }
        
        // Make the cart empty
        public static boolean emptyCart()
        {
            cartCode = "blank";
            cpfOfCartOwner = "blank";
            discount = 0;
            
            // Used to acess the index of List to remove items
            int t = 0;
            while( !itemsAndQuantity.isEmpty() )
            {
                itemsAndQuantity.remove(t);
            }
            
            if( itemsAndQuantity.isEmpty() )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        // List all items on cart
        public static void listCartItems ()
        {
            if( currentAccess )
            {
                /* Receive the position of client cpf on the list of clients 
                 * (Refers to 'Client' class)
                 */
                int loggedClientIndex = 0;
                
                loggedClientIndex = Cliente.cpf.indexOf(currentClient);
                
                System.out.println("\nOlá " + Cliente.nome.get(loggedClientIndex) + "!\n");
                
                if( itemsAndQuantity.isEmpty() )
                {
                    System.out.println("\n" + Cliente.nome.get(loggedClientIndex)
                                       + ", sua cesta está vazia!");
                }
                else
                {
                    /* 
                     * Receive the position of 'bookToAddIsbn' on the list of books
                     * (refers to 'Book' class)
                     */
                    int bookIndex = 0;
                    
                    // Receive the book title in 'bookIndex' position on the 'Book' class
                    String bookTitle = "";
                    
                    System.out.println("Código da cesta: " + cartCode);
                    // 'p' is used to walk through cart items list
                    for( int p = 0; p < itemsAndQuantity.size(); p++ )
                    {
                        bookIndex = Book.listISBN.indexOf(itemsAndQuantity.get(p).getItens_add());
                    
                        bookTitle = Book.listTitle.get(bookIndex);
                        
                        System.out.println("Item " + (p+1) + ": '" + bookTitle + "'");
                        System.out.println("Quantidade: "  + itemsAndQuantity
                                                             .get(p)
                                                             .getQuantityOfItens());
                        System.out.println("Preço(un.): R$" 
                                           + Book.listPrice.get(bookIndex) + "\n");
                        
                    }
                    
                    float totalValueOfCart;
                    
                    totalValueOfCart = calculateCartValue();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", totalValueOfCart);
                }
                
                showMenu();
            }
            else
            {
                System.out.println("\nNenhum cliente logado. Faça login primeiro!");
                
                showMenu();
            }
        }
        
        // Listar alternativo - Descobrir porquê..
        public static void Listar_itens_cesta_ ()
        {
                int indice_Clogado;
                
                indice_Clogado = Cliente.cpf.indexOf(currentClient);
                
                           
                if(itemsAndQuantity.isEmpty())
                {
                    System.out.println("\n"+Cliente.nome.get(indice_Clogado)+", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+cartCode);
                    for(p=0; p<itemsAndQuantity.size(); p++)
                    {
                        indice_Livro = Book.listISBN.indexOf(itemsAndQuantity.get(p).getItens_add());
                    
                        livro = Book.listTitle.get(indice_Livro);
                        
                        System.out.println("Item "+(p+1)+": '"+livro+"'");
                        System.out.println("Quantidade: "  +itemsAndQuantity.get(p).getQuantityOfItens());
                        System.out.println("Preço(un.): R$"+Book.listPrice.get(indice_Livro)+"\n");
                        
                    }
                    
                    float total;
                    
                    total = calculateCartValue();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", total);
                }
           
        }
        
        // Return the catalogue of books
        public static void continueShopping()
        {
            Book.showCatalog();
        }
        
        //  Remove an item from cart
        public static void removeCartItem()
        {
            if( itemsAndQuantity.isEmpty() )
            {
                System.out.println("\nCesta vazia. Vamos lá, inclua algo agora mesmo!\n");
                showMenu();
            }
            else
            {
                // Receive the number (on a specific listing) of a book to be removed from cart
                String bookToRemove = "";
                
                /* 
                 * Receive the position of 'bookToAddIsbn' on the list of books
                 * (refers to 'Book' class)
                 */
                int bookIndex = 0;
                
                // Receive the book name which is on cart
                String bookNameOnCart = "";
                
                System.out.println("Código da cesta: "+cartCode);
                // 'p' is used to walk through cart items list
                for(int p = 0; p < itemsAndQuantity.size(); p++)
                {
                    bookIndex = Book.listISBN.indexOf(itemsAndQuantity.get(p).getItens_add());
                    
                    bookNameOnCart = Book.listTitle.get(bookIndex);
                        
                    System.out.println("Item " + (p+1) + ": '" + bookNameOnCart + "'");
                    System.out.println("Quantidade: "  + itemsAndQuantity.get(p).getQuantityOfItens());
                }
                
                // Receive the parse of 'livro_retirar' to an Integer
                int itemToRemove = 0;
                
                // Verify if the item typed by te user is valid. If so, okItem = true
                boolean okItem = false;
                
                do
                {
                    System.out.println("\nInforme o número do item que deseja retirar da cesta: ");
                    bookToRemove = inputString.nextLine();
                
                    itemToRemove = Integer.parseInt(bookToRemove);
                
                    if( itemToRemove > 0 && itemToRemove < itemsAndQuantity.size() )
                    {
                        okItem = true;
                    }
                    else
                    {
                        okItem = false;
                    }
                    
                }while( okItem == false );
                
                // Receive the name of the book that was removed from cart
                Cart removedBookName = itemsAndQuantity.remove(itemToRemove-1);
                
                /* 
                 * Receive the position of the removed book on the list of books 
                 * (refers to 'Book' class)
                 */
                int indexOfRemovedBook = 0;
                
                indexOfRemovedBook = Book.listISBN.indexOf(removedBookName.itemsToAddOnCart);
                
                System.out.println("\n'"+Book.listTitle.get(indexOfRemovedBook)+"'/"+removedBookName.quantityOfItens+" foi removido da cesta.\n");
                
                showMenu();
            }
        }
        
        // Calculate the total of cart by all items on the cart
        public static float calculateCartValue()
        {
            if( itemsAndQuantity.isEmpty() )
            {
                return 0;
            }
            else
            {
                // Receive the listISBN of the book added on the position 'p' on the cart items list
                String bookIsbnOnCart = "";
                
                // Receive the price of the book on the position 'p' on the cart items list
                String bookPriceString;
                
                /* 
                 * Receive the position of 'bookIsbnOnCart' on the list of books
                 * (refers to 'Book' class)
                 */
                int bookIndex = 0;
                
                // Receive the total of all items on cart
                float totalValueOfCart = 0;
                
                // Receive the price of each item and it respective quantity, and then sum it to the totalValueOfCart
                float subtotal = 0;
                
                // Receive the parse result of 'bookPriceString' to float
                float bookPriceFloat = 0;
                
                // 'p' is used to walk through cart items list
                for( int p = 0; p < itemsAndQuantity.size(); p++ )
                {
                    bookIsbnOnCart = itemsAndQuantity.get(p).itemsToAddOnCart;
                    
                    bookIndex = Book.listISBN.indexOf(bookIsbnOnCart);
                    
                    bookPriceString = Book.listPrice.get(bookIndex);
                    
                    bookPriceFloat = Float.parseFloat(bookPriceString);
                    
                    subtotal = bookPriceFloat*itemsAndQuantity.get(p).quantityOfItens;
                    
                    totalValueOfCart +=subtotal;                                        
                }
                
                totalValueOfCart = totalValueOfCart - (totalValueOfCart*discount);
                                                
                return totalValueOfCart;
            }
       
        }
        
        // Include the discount on the total value of cart
        public static void includeDiscount()
        {
            // Receive the code of a coupon informed by user
            String couponCode = "";
            
            System.out.println("\nInforme o código do seu cupom de desconto:");
            couponCode = inputString.nextLine();
            
            // Receive the result of the method 'verifyCoupon' (refers to 'TCoupon' class)
            boolean verifyCoupon = false;
            
            verifyCoupon = TCupom.Verifica_cupom(couponCode);
            
            if( verifyCoupon )
            {
                // Receive the discount value of the coupon informed by user
                float couponDiscountValue = 0;
                
                couponDiscountValue = TCupom.Verifica_valor_desconto(couponCode);
                
                System.out.println("\nParabéns! Você ganhou "
                                   + couponDiscountValue 
                                   + "% de desconto em qualquer livro!\n");
                
                discount = couponDiscountValue/100;
                
            }
            else
            {
                System.out.println("\nCumpom não cadastrado em nosso sistema.\n");
                showMenu();              
            }
            
        }
        
        //  Close the cart generating a new random number that will be returned as the purchase unique code
        public static int closeCart()
        {
            if( !itemsAndQuantity.isEmpty() )
            {
                int purchaseCode = 0;
            
                purchaseCode = randomGenerator.nextInt(10000);
                
                discount = 0;
                            
                return purchaseCode;
            }
            else
            {
                System.out.println("\nCesta Vazia!\n");
                
                return -1;
            }
        }
        
    }
    