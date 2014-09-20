
package livraria_abc;

public class Bookstore 
{
    // Receives the identification number (CNPJ - Cadastro Nacional de Pessoa Juridica - in portuguese) of the bookstore
    int cnpjBookstore;
    
    // Receives the name of the bookstore
    String bookstoreName;
    
    // Receives the address of the bookstore
    String bookstoreAddress;
    
    // Receives the telephone of the bookstore
    int bookstoreTelephone;
         
    // Constructor 
    public Bookstore ()
    {
        this.cnpjBookstore = 54505052;
        this.bookstoreName = "Livraria ABC";
        this.bookstoreAddress = "Avenida Getúlio Vargas nº 69";
        this.bookstoreTelephone = 32345698;
    }   
}
