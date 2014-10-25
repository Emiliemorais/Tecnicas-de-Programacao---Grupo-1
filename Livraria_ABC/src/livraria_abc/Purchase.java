
package livraria_abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static livraria_abc.ABCBookStore.buyingCode;
import static livraria_abc.ABCBookStore.showMenu;
import static livraria_abc.ABCBookStore.currentClient;

    public class Purchase
    {
        // List with all the purchases
        static List<Purchase> purchaseList = new ArrayList<>();

        // Code used to perform the purchase
        int purchaseCode;

        // Date that the purchase was made
        String purchaseDate;

        // Purchase value
        float purchaseValue;

        // Address to the delivery
        String deliveryAddress;

        // Type of payment
        String paymentType;

        
        // Scanner object used to read real numbers from user
        static Scanner inputNumbers = new Scanner(System.in);

        // Scanner object used to read strings from user
        static Scanner readString = new Scanner(System.in);
        
        // Class general constructor
        public Purchase()
        {
            
        }

        /**
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
            // purchaseInstance - Instance of class "Purchase"
            Purchase purchaseInstance = new Purchase();

            purchaseInstance.deliveryAddress = ABCBookStore.deliveryAddress;
            purchaseInstance.paymentType = ABCBookStore.paymentType;
            purchaseInstance.purchaseValue = Cart.calculateCartValue();
            purchaseInstance.purchaseCode = buyingCode;

            purchaseList.add(purchaseInstance);
        }

        // Method that cancels the ongoing purchase
        public static void cancelPurchase()
        {
            // Gives a option, whether to cancel or not
            String cancelOption;

            System.out.println("\nDeseja mesmo cancelar a compra? 1-SIM  2-NÃO");
            cancelOption = readString.nextLine();

            if( cancelOption.equals("1") )
            {
                ABCBookStore.deliveryAddress = "blank";
                ABCBookStore.paymentType = "blank";
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
            // Position of the CPF (Brazilian document) from the actual client in the list
            int indexOfCPF;

            // Gives a option, whether to change the address or not
            String changeAddressOption;

            indexOfCPF = Client.cpf.indexOf(currentClient);

            String toBePrinted = "\nO endereço de entrega será: '"
                               + Client.addressArray.get(indexOfCPF)
                               + "'?\n1-SIM  0- ALTERAR ENDEREÇO";

            System.out.println(toBePrinted);
            changeAddressOption = readString.nextLine();

            if(changeAddressOption.equals("1") )
            {
                ABCBookStore.deliveryAddress = Client.addressArray.get(indexOfCPF);

                checkPayment();
            }
            else
            {
                // Receives the new delivery address
                String newDeliveryAddress;

                System.out.println("\nInforme o novo endereço de entrega: ");
                newDeliveryAddress = readString.nextLine();

                ABCBookStore.deliveryAddress = newDeliveryAddress;

                checkPayment();
            }
        }

        // Checks the final value and which will be the payment type
        public static void checkPayment()
        {
            // Total value of the purchase
            float totalValue;

            // Type of payment
            String paymentType;

            totalValue = Cart.calculateCartValue();

            System.out.printf("\nO valor total a ser pago é R$%.2f.\n", totalValue);

            boolean ok = false;

            do
            {
                String toBePrinted = "\nQual será a forma de pagamento?"
                                   + "\n1-Boleto  |  2-Cartão de crédito";

                System.out.println(toBePrinted);
                paymentType = readString.nextLine();

                if( paymentType.equals("1") )
                {
                    ABCBookStore.paymentType = PaymentType.listTypes.get(0).paymentType;
                    ok = true;
                }
                else if( paymentType.equals("2") )
                {
                    ABCBookStore.paymentType = PaymentType.listTypes.get(1).paymentType;
                    ok = true;
                }
                else
                {
                    System.out.println("\nOpção Inválida\n");
                    ok = false;
                }
            }while(ok == false);

            endPurchase();
        }

        // Finaliza uma compra e esvazia a cesta aberta se confirmado
        // Finalizes a purchase and get the ongoing cart empty
        public static void endPurchase()
        {
            // Gives a option, whether to cancel the purchase or not
            String cancelPurchaseOption;

            System.out.println("\nSeu pedido é: ");

            Cart.listCartItems();

            System.out.println("\nEndereço de entrega: " + ABCBookStore.deliveryAddress);
            System.out.println("\nTipo de pagamento: " + ABCBookStore.paymentType);

            System.out.println("\nDeseja finalizar a compra?\n 1-SIM  |  0-CANCELAR COMPRA");
            cancelPurchaseOption = readString.nextLine();

            if( cancelPurchaseOption.equals("1") )
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
