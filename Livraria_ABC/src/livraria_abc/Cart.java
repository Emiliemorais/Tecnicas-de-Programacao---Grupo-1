
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
        public String getItemsToAddOnCart() 
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
        public Cart(String items, int quantity)
        {
            this.itemsToAddOnCart = items;
            this.quantityOfItens = quantity;
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
                // Used to receive the cartCodeInteger as a String
                String cartCodeString = " ";
                               
                cartCodeString = createCartCode();
                
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
              Client client = new Client();
              
              clientCpfToAddItens = client.getLogin();
              
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
        
        /**
         * This method create a code to cart
         * @return - Return the cart code generated
         */
        private static String createCartCode() 
        {
            // Used to receive the result from 'ramdomGenerator'
            int cartCodeInteger = 0;
            
            // Used to receive the cartCodeInteger as a String
            String cartCodeString = " ";
            
            cartCodeInteger = randomGenerator.nextInt(10000);
            cartCodeString = Integer.toString(cartCodeInteger);
            cartCodeString = validateCartCode(cartCodeString, cartCodeInteger);    
            
            return cartCodeString;
        }
      
        /**
         * This method is used to validate the cart code generate
         * to ensure that there is not a repeated code
         * @param cartCodeString - Receives the cart code number in String format
         * @param cartCodeInteger - Receives the cart code number 
         * @return - Return the cart code validated 
         */
        private static String validateCartCode(String cartCodeString,int cartCodeInteger) 
        {
            // Verify if the code generated already exists. If so, okCartCode = false
            boolean okCartCode = false;
            
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
            return cartCodeString;
        }
        /**
         * Make the cart empty
         * @return - Return the status of the cart, if the cart is empty or not
         */
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
        
        // Show all items on cart and back to menu
        public static void showCartItens ()
        {
            if( currentAccess )
            {
                // Method to get the client
                int loggedClientIndex = verifyClientLogged();

                if( itemsAndQuantity.isEmpty() )
                {
                    System.out.println("\n" + Client.nameArray.get(loggedClientIndex)
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
                        Cart item = itemsAndQuantity.get(p);
                        
                        bookIndex = Book.listISBN.indexOf(item.getItemsToAddOnCart());
                    
                        bookTitle = Book.listTitle.get(bookIndex);
                        
                        System.out.println("Item " + (p+1) + ": '" + bookTitle + "'");
                        System.out.println("Quantidade: "  + item.getQuantityOfItens());
                        System.out.println("Preço(un.): R$"+ Book.listPrice.get(bookIndex) + "\n");
                        
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
        
        /**
         * This method is used to get the client that is consulting the cart
         * @return - Return the position of the client in the list
         * 
         */
        private static int verifyClientLogged() 
        {
            /* Receive the position of client cpf on the list of clients 
             * (Refers to 'Client' class)
             */
            int loggedClientIndex = 0;

            loggedClientIndex = Client.cpf.indexOf(currentClient);

            System.out.println("\nOlá " + Client.nameArray.get(loggedClientIndex) + "!\n");
            
            return loggedClientIndex;
                
        }

        /*
         *  This method lists the items of the cart without 
         *  the option to back the menu
         */
        public static void listCartItems ()
        {
                
                int currentClientIndex;
                
                currentClientIndex = Client.cpf.indexOf(currentClient);
                
                           
                if( itemsAndQuantity.isEmpty() )
                {
                    System.out.println("\n" + Client.nameArray.get(currentClientIndex)
                                       + ", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+cartCode);
                    for(p=0; p<itemsAndQuantity.size(); p++)
                    {
                        indice_Livro = Book.listISBN.indexOf(itemsAndQuantity.get(p).getItemsToAddOnCart());
                    
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
        public static void removeCartIten()
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
                                                
                int listSize = itemsAndQuantity.size();
                listCartItems();
                
                // Receive the parse of 'livro_retirar' to an Integer
                int itemToRemove = 0;
                
                // Verify if the item typed by te user is valid. If so, okItem = true
                boolean okItem = false;
                
                do
                {
                    System.out.println("\nInforme o número do item que deseja retirar da cesta: ");
                    bookToRemove = inputString.nextLine();
                
                    itemToRemove = Integer.parseInt(bookToRemove);
                                                           
                    if( itemToRemove > 0 && itemToRemove <= listSize )
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
                
                String itemRemoved = removedBookName.itemsToAddOnCart;
                int quantityOfItemRemoved = removedBookName.quantityOfItens;
                
                indexOfRemovedBook = Book.listISBN.indexOf(itemRemoved);
                
                System.out.println("\n'" + Book.listTitle.get(indexOfRemovedBook)
                                   + "'/" + quantityOfItemRemoved + " foi removido da cesta.\n");
                
                showMenu();
            }
        }
        
        /**
         * Calculate the total of cart by all items on the cart
         * @return - Return the total value of the items
         */
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
                String bookPriceString = "";
                
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
                
                int listSize = itemsAndQuantity.size();
                
                // 'p' is used to walk through cart items list
                for( int p = 0; p < listSize; p++ )
                {
                    Cart item = itemsAndQuantity.get(p);
                    
                    bookIsbnOnCart = item.itemsToAddOnCart;
                    
                    bookIndex = Book.listISBN.indexOf(bookIsbnOnCart);
                    
                    bookPriceString = Book.listPrice.get(bookIndex);
                    
                    bookPriceFloat = Float.parseFloat(bookPriceString);
                    
                    subtotal = bookPriceFloat*item.quantityOfItens;
                    
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
            
            verifyCoupon = Coupon.Verifica_cupom(couponCode);
            
            if( verifyCoupon )
            {
                // Receive the discount value of the coupon informed by user
                float couponDiscountValue = 0;
                
                couponDiscountValue = Coupon.Verifica_valor_desconto(couponCode);
                
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
        
        /**
         * Close the cart generating a new random number that 
         * will be returned as the purchase unique code
         * @return - Return the purchase code
         */
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
    