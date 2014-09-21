
package livraria_abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static livraria_abc.ABCBookStore.deliveryAddress;
import static livraria_abc.ABCBookStore.paymentType;
import static livraria_abc.ABCBookStore.buyingCode;
import static livraria_abc.ABCBookStore.showMenu;
import static livraria_abc.ABCBookStore.currentClient;

    public class Purchase
    {
        static List<Purchase> purchaseList = new ArrayList<>(); // List with all the purchases
        int purchaseCode; // Code used to perform the purchase
        String purchaseDate; // Date that the purchase was made
        float purchaseValue; // Purchase value
        String deliveryAddress; // Address to the delivery 
        String paymentType; // Type of payment
                
        // Objeto utilizado para ler números inteiros e de ponto flutuante do usuário
        static Scanner inputNumbers = new Scanner(System.in);
        // inputNumber - Receives numbers
    
        // Objeto utilizado para ler strings do usuário
        static Scanner readString = new Scanner(System.in);
        // readString - Reads strings from the user
        
        public Purchase()
        {
            
        }
        
        /*
         * Purchase constructor
         * @param purchaseCode - Code used to perform the purchase
         * @param purchaseDate - Date that the purchase was made
         * @param purchaseValue - Purchase value
         * @param deliveryAddress - Address to the delivery 
         * @param paymentType - Type of payment
         */
        public Purchase(int purchaseCode, String purchaseDate, float purchaseValue, String deliveryAddress, String paymentType)
        {
            this.purchaseCode = purchaseCode;
            this.purchaseDate = purchaseDate;
            this.purchaseValue = purchaseValue;
            this.deliveryAddress = deliveryAddress;
            this.paymentType = paymentType;
        }
        
        // Method that add a new purchase to the list
        public static void addPurchase()
        {
            Purchase purchaseInstance = new Purchase();
            // purchaseInstance - Instance of class "Purchase"
            
            purchaseInstance.deliveryAddress = ABCBookStore.this.deliveryAddress;
            purchaseInstance.paymentType = ABCBookStore.this.paymentType;
            purchaseInstance.purchaseValue = Cart.calculateCartValue();
            purchaseInstance.purchaseCode = buyingCode;
           
            purchaseList.add(purchaseInstance);
        }
        
        // Method that cancels the ongoing purchase
        public static void cancelPurchase()
        {
            String cancelOption; // Gives a option, whether to cancel or not
            
            System.out.println("\nDeseja mesmo cancelar a compra? 1-SIM  2-NÃO");
            cancelOption = readString.nextLine();
            
            if(cancelOption.equals("1") )
            {
                ABCBookStore.this.deliveryAddress="blank";
                ABCBookStore.this.paymentType="blank";
                buyingCode = -1;
                
                showMenu();
            }
            else
            {
                endPurchase();
            }    
        }

        // Method that checks the received address
        public static void checkAddress()
        {
            int indexOfCPF; // Position of the CPF (Brazilian document) from the actual client in the list
            String changeAddressOption; // Gives a option, whether to change the address or not
            
            indexOfCPF = Cliente.cpf.indexOf(currentClient);
                        
            System.out.println("\nO endereço de entrega será: '"+Cliente.endereco.get(indexOfCPF)+"'?\n1-SIM  0- ALTERAR ENDEREÇO");
            changeAddressOption = readString.nextLine();
            
            if(changeAddressOption.equals("1") )
            {
                ABCBookStore.this.deliveryAddress = Cliente.endereco.get(indexOfCPF);
                
                checkPayment();
            }
            else
            {
                String newDeliveryAddress; // Receives the new delivery address
                
                System.out.println("\nInforme o novo endereço de entrega: ");
                newDeliveryAddress = readString.nextLine();
                
                ABCBookStore.this.deliveryAddress = newDeliveryAddress;
                
                checkPayment();
            }
        }
        
        // Checks the final value and which will be the payment type
        public static void checkPayment()
        {
            float totalValue; // Total value of the purchase
            String paymentType; // Type of payment
            
            totalValue = Cart.calculateCartValue();
            
            System.out.printf("\nO valor total a ser pago é R$%.2f.\n",totalValue);
            
            boolean ok = false;
            
            do
            {
                System.out.println("\nQual será a forma de pagamento?\n1-Boleto  |  2-Cartão de crédito");
                paymentType = readString.nextLine();
            
                if(paymentType.equals("1") )
                {
                    ABCBookStore.this.paymentType = PaymentType.listTypes.get(0).paymentType;
                    ok = true;
                }
                else if(paymentType.equals("2") )
                {
                    ABCBookStore.this.paymentType = PaymentType.listTypes.get(1).paymentType;
                    ok = true;
                }
                else
                {
                    System.out.println("\nOpção Inválida\n");
                    ok = false;
                }   
            }while(ok==false);
            
            endPurchase();
        }
        
        // Finaliza uma compra e esvazia a cesta aberta se confirmado
        public static void endPurchase()
        {
            String cancelPurchaseOption; // Gives a option, whether to cancel the purchase or not
            
            System.out.println("\nSeu pedido é: ");
            
            Cart.Listar_itens_cesta_();
            
            System.out.println("\nEndereço de entrega: "+ABCBookStore.this.deliveryAddress);
            System.out.println("\nTipo de pagamento: "+ABCBookStore.this.paymentType);
            
            System.out.println("\nDeseja finalizar a compra?\n 1-SIM  |  0-CANCELAR COMPRA");
            cancelPurchaseOption = readString.nextLine();
            
            if(cancelPurchaseOption.equals("1"))
            {
                System.out.println("\nCompra realizada com sucesso!\n");
                
                Cart.emptyCart();
                
                addPurchase();
                
                showMenu();
            }
            else
            {
                cancelPurchase();
            }
        }
    }
    