package livraria_abc;

import java.util.*;
import static livraria_abc.ABCBookStore.currentAccess;
import static livraria_abc.ABCBookStore.currentClient;
import static livraria_abc.ABCBookStore.currentClientName;


public class Client 
{
    static ArrayList<String> cpf = new ArrayList<>();
    static ArrayList<String> rg = new ArrayList<>();
    static ArrayList<String> nameArray = new ArrayList<>();
    static ArrayList<String> genderArray = new ArrayList<>();
    static ArrayList<String> addressArray = new ArrayList<>();
    static ArrayList<String> cepArray = new ArrayList<>();
    static ArrayList<String> dtBirthArray = new ArrayList<>();
    static ArrayList<String> phoneArray = new ArrayList<>();
    
    private static ArrayList<String> loginArray = new ArrayList<>();
    private static ArrayList<String> passwordArrray = new ArrayList<>();
    
        
    Scanner input = new Scanner (System.in);
    Scanner lerString = new Scanner (System.in);
    
    ABCBookStore livraria = new ABCBookStore ();
        
        /**
         * Client especific menu
         * @return the client menu
         */
        public void menuClient ()
        {
            int optionCRUDClient = 0;
            
            do 
            {
                System.out.println("\nOlá " + currentClientName + "! Aqui você pode gerenciar sua conta.");
                System.out.println("1- Alterar dados conta");
                System.out.println("2- Consultar dados da conta");
                System.out.println("3- Excluir conta");
                System.out.println("4- Voltar para o menu principal\n");

                System.out.println("Digite a opcao: ");
                optionCRUDClient = input.nextInt();
			
               
                switch (optionCRUDClient) 
                {
                    case 1:
                    {
                        alterarCliente();
                        break;
                    }
                    case 2:
                    {
                        viewClient();

                        break;
                    }
                    case 3:
                    {
                        removeClient();

                        break;
                    }
                    case 4:
                    {
                        livraria.showMenu();
                        break;
                    }
                    default:
                    {
                        System.out.println("\nOpção Inválida!\n");
                        break;
                    }
                }
                       
            } while (optionCRUDClient != 4);
                
        }
        
        /**
         * Allows the client to edit his data
         * @return the client menu
         */
        public  void alterarCliente()
        {
            int i = 0;
            
            i = cpf.indexOf(currentClient);
            
            System.out.println ("\nDigite o nome do cliente: ");
            String temporaryName = lerString.nextLine();
				
            System.out.println("\nDigite o sexo do cliente: ");
            String temporaryGener = lerString.nextLine();
			
            System.out.println("\nDigite CPF civil do cliente: ");
            String temporaryCPF = lerString.nextLine();
			
            System.out.println("\nDigite o RG do cliente: ");
            String temporaryRG = lerString.nextLine();
				
            System.out.println("\nDigite o endereco do cliente: ");
            String temporaryAdreess = lerString.nextLine();
				
            System.out.println("\nDigite o telefone do cliente: ");
            String temporaryPhone = lerString.nextLine();
				
            System.out.println("\nDigite o CEP do cliente: ");
            String temporaryCEP = lerString.nextLine();
				
            System.out.println("\nDigite a data de nascimento do cliente: ");
            String temporaryDateBirthday = lerString.nextLine();
            
            
            cpf.set(i,temporaryCPF);
            rg.set(i, temporaryRG);
            nameArray.set(i,temporaryName);
            genderArray.set(i,temporaryGener);
            addressArray.set(i,temporaryAdreess);
            cepArray.set(i,temporaryCEP);
            dtBirthArray.set(i,temporaryDateBirthday);
            phoneArray.set(i,temporaryPhone);
            
            currentClient = temporaryCPF;
            currentClientName = temporaryName;
            
            System.out.println("\nDados atualizados com sucesso!\n");
            
            String opcao = "";
            System.out.println("Deseja alterar a conta de login e senha?\n 1-SIM  |  0-NÃO");
            opcao = lerString.nextLine();
            
            if( opcao.equals("1") )
            {
                System.out.println("\nDigite a nova conta: ");
                String novoLogin = lerString.nextLine();
				
                System.out.println("\nDigite a nova senha: ");
                String novaSenha = lerString.nextLine();
                
                loginArray.set(i, novoLogin);
                passwordArrray.set(i, novaSenha);
                
                System.out.println("\nDados atualizados com sucesso!\n");
                
                menuClient();
                
            }
            else
            {
                menuClient();
            }
            
        }
        
        /**
         * Display the data of the current client
         * @return menuClient 
         */
        public void viewClient()
        {
            int i = 0;
            
            i = cpf.indexOf(currentClient);
            
            System.out.println("\nDados cadastrados para '" + currentClientName + "':");
            System.out.println("CPF: " + cpf.get(i) );
            System.out.println("RG: " + rg.get(i) );
            System.out.println("Sexo: " + genderArray.get(i) );
            System.out.println("Endereco: " + addressArray.get(i) );
            System.out.println("CEP: " + cepArray.get(i) );
            System.out.println("Data de nascimento: " + dtBirthArray.get(i) );
            System.out.println("Telefone: " + phoneArray.get(i) );
            System.out.println("Conta: " + loginArray.get(i) );
            System.out.println("Senha: **********\n");
                
            menuClient();
                
        }
        
        /**
         * Delete the current client account 
         * @return the books catalogue if the exclusion was made, or the client menu if does not
         */
        public  void removeClient()
        {
            String opcao = "";
            String inputForHeadMenu = "\nDeseja mesmo exluir sua conta " 
                               + currentClientName + "?\n 1-SIM  |  0-NÃO\n";
            
            System.out.println(inputForHeadMenu);
            
            opcao =  lerString.nextLine();
            
            if( opcao.equals("1") )
            {
                int i = 0;
                
                i = cpf.indexOf(currentClient);
                                
                cpf.remove(i);
                rg.remove(i);
                nameArray.remove(i);
                genderArray.remove(i);
                addressArray.remove(i);
                cepArray.remove(i);
                dtBirthArray.remove(i);
                phoneArray.remove(i);
                loginArray.remove(i);
                passwordArrray.remove(i);
                
                currentAccess = false;
                currentClient = "blank";
                currentClientName = "blank";
                                
                Cart.emptyCart();
                
                livraria.showMenu();
                
            }
            else
            {
                menuClient();
            }
        }
        
        /**
         * Register a new client
         * @return void
         */
        public  void includeClient() 
        {
                String inputForHeadMenu = "\nOlá Vistante! Bem vindo à livraria ABC!\nPercebemos que você ainda não possui cadastro "
                                        + "em nosso site.\nPara se cadastrar, complete os campos abaixo: ";
                
                System.out.println(inputForHeadMenu);
		
                System.out.println("\nSeu nome: ");
				String name = lerString.nextLine();
				
				System.out.println("\nSeu sexo: ");
				String gener = lerString.nextLine();
				
				System.out.println("\nSeu CPF: ");
				String cpf = lerString.nextLine();
				
				System.out.println("\nSeu RG: ");
				String rg = lerString.nextLine();
				
				System.out.println("\nSeu endereço: ");
				String adreess = lerString.nextLine();
				
				System.out.println("\nSeu telefone: ");
				String phone =lerString.nextLine();
				
				System.out.println("\nSeu CEP: ");
				String cep = lerString.nextLine();
				
				System.out.println("\nSua data de nascimento: ");
				String dateBirthiday = lerString.nextLine();
				
				System.out.println("\nInforme agora uma conta que será usada para fazer login: ");
				String login = lerString.nextLine();
				
				System.out.println("\nInforme uma senha para sua conta: ");
				String password = lerString.nextLine();
                
                Client.cpf.add(cpf);
                Client.rg.add(rg);
                Client.nameArray.add(name);
                Client.genderArray.add(gener);
                Client.addressArray.add(adreess);
                Client.cepArray.add(cep);
                Client.dtBirthArray.add(dateBirthiday);
                Client.phoneArray.add(phone);
                Client.loginArray.add(login);
                Client.passwordArrray.add(password);
                
                currentAccess = true;
                currentClient = cpf;
                currentClientName = name;
                
                System.out.println("\nCliente cadastrado com sucesso!\n");
                menuClient();
        }
        
        /** 
         * Do the login of an user.
         * @return the logged user CPF if the login succeeded or "blank" if does not  
         */
        public String getLogin()
        {
            String verifyCadastro = "1";
            if( !currentAccess && !Client.loginArray.isEmpty() )  
            {
                System.out.println("\nJá possui cadastro em nosso site?\nSe sim,"
                                   + " digite 1 e continue com sua compra. Se não,"
                                   + " digite 0 e cadastre-se agora mesmo!\n");
                verifyCadastro = lerString.nextLine();
            }
            else
            {
                // Nothing to do
            }
            
            if( "1".equals(verifyCadastro) )
            {
                if( !currentAccess )
                {
                    if( !Client.loginArray.isEmpty() )
                    {
                        int tries = 0;
                        String account; 
                        String psw;
                        String clientLoging = "blank";
                        boolean verify_psw = false;
            
                        do
                        {
                            System.out.println("\nInforme sua conta: ");
                            account = lerString.nextLine();
            
                            System.out.println("\nInforme sua senha: ");
                            psw = lerString.nextLine();
            
                            if( Client.loginArray.contains(account) )
                            {
                                int indice;
                                
                                indice = Client.loginArray.indexOf(account);
                
                                if( passwordArrray.get(indice).equals(psw) )
                                {
                                    verify_psw = true;
                                    currentAccess = true;
                                    currentClient = cpf.get(indice);
                                    currentClientName = nameArray.get(indice);
                                
                                    clientLoging = cpf.get(indice);
                                    tries = 0;
                                }
                                else
                                {
                                    verify_psw = false;
                                    System.out.println("\nSenha incorreta.\n");
                                    tries++;
                                }
                            }
                            else
                            {
                                System.out.println("\nA conta '" + account 
                                                   + "' não existe em nossos cadastros."
                                                   + " Informe uma conta existente.\n");
                                tries++;
                            }
                
                        }while( (verify_psw == false && tries <= 3) 
                                || ( Client.loginArray.contains(account) == false 
                                	&& tries <= 3 ) );
            
                        if(tries > 3)
                        {
                            System.out.println("\nTentativas de login excedidas."
                                               + " Para sua segurança fecharemos"
                                               + " a tela de login.\n");
                        }
                        else
                        {
                            // Nothing to do
                        }
            
                        livraria.showMenu();
                        return clientLoging;
                    }
                    else
                    {
                        includeClient();
                
                        return "blank";
                    }
            
                }
                else
                {
                    return currentClient;
                } 
            }
            else
            {
                includeClient();
                return "blank";
            }
           
        }
        
        /**
         * Do the logout of an user if there are no items in his cart
         * @return the books catalogue 
         */
        public void getLogout()
        {
            if(currentAccess)
            {
                String name;
                String option;
                
                if( !Cart.itemsAndQuantity.isEmpty() )
                {
                    System.out.println("\nVocê perderá os itens "
                                       + "adicionados à sua cesta."
                                       + "\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = lerString.nextLine();
                    
                    if( "1".equals(option) )
                    {
                        name = currentClientName;
                
                        currentAccess = false;
                        currentClient = "blank";
                        currentClientName = "blank";
                       
                        boolean ok;
                        
                        ok = Cart.emptyCart();
                        
                        if(ok)
                        {
                            System.out.println("\n" + name + ", sua conta foi"
                                               + " encerrada com sucesso!\n");
                        }
                        else
                        {
                            // Nothing to do
                        }
                    }
                    else
                    {
                        Book.showCatalog();
                    }
                }
                else
                {
                    name = currentClientName;
                
                    currentAccess = false;
                    currentClient = "blank";
                    currentClientName = "blank";
                
                    System.out.println("\n" + name + ", sua conta"
                                       + " foi encerrada com sucesso!\n");
                }
                
                livraria.showMenu();
            }
            else
            {
                System.out.println("\nNenhuma conta logada no momento.\n");
                
                livraria.showMenu();
            }
        }
        
        /**
         * Do the logout without calls the books catalogue
         */
        public  void efetuarLogout_()
        {
            if(currentAccess)
            {
                String name;
                String option;
                
                if( !Cart.itemsAndQuantity.isEmpty() )
                {
                    System.out.println("\nVocê perderá os itens"
                                       + " adicionados à sua cesta."
                                       + "\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = lerString.nextLine();
                    
                    if( "1".equals(option) )
                    {
                        name = currentClientName;
                
                        currentAccess = false;
                        currentClient = "blank";
                        currentClientName = "blank";
                       
                        boolean ok;
                        
                        ok = Cart.emptyCart();
                        
                        if(ok)
                        {
                            System.out.println("\n" + name + ", sua conta foi"
                                               + " encerrada com sucesso!\n");
                        }
                        else
                        {
                            // Nothing to do
                        }
                    }
                    else
                    {
                        livraria.showMenu();
                    }
                    
                }
                else
                {
                    name = currentClientName;
                
                    currentAccess = false;
                    currentClient = "blank";
                    currentClientName = "blank";
                
                    System.out.println("\n" + name + ", sua conta"
                                       + " foi encerrada com sucesso!\n");
                }
                
            }
            else
            {
                // Nothing to do
            }

        }
}
