package livraria_abc;

import java.util.*;
import static livraria_abc.Livraria_ABC.acessoAtual;
import static livraria_abc.Livraria_ABC.clienteAtual;
import static livraria_abc.Livraria_ABC.nomeClienteAtual;


public class Cliente 
{
    static ArrayList<String> cpf = new ArrayList<>();
    static ArrayList<String> rg = new ArrayList<>();
    static ArrayList<String> nome = new ArrayList<>();
    static ArrayList<String> sexo = new ArrayList<>();
    static ArrayList<String> endereco = new ArrayList<>();
    static ArrayList<String> cep = new ArrayList<>();
    static ArrayList<String> dataNascimento = new ArrayList<>();
    static ArrayList<String> telefone = new ArrayList<>();
    private static ArrayList<String> conta = new ArrayList<>();
    private static ArrayList<String> senha = new ArrayList<>();
        
    Scanner input = new Scanner(System.in);
    Scanner lerString = new Scanner(System.in);
    
     Livraria_ABC livraria = new Livraria_ABC();
        // Menu específico para o cliente
        public void menuCliente()
        {
            int opcaoCliente = 0;
            
            do 
            {
                System.out.println("\nOlá " + nomeClienteAtual + "! Aqui você pode gerenciar sua conta.");
                System.out.println("1- Alterar dados conta");
                System.out.println("2- Consultar dados da conta");
                System.out.println("3- Excluir conta");
                System.out.println("4- Voltar para o menu principal\n");

                System.out.println("Digite a opcao: ");
                opcaoCliente = input.nextInt();
			
               
                switch (opcaoCliente) 
                {
                    case 1:
                    {
                        alterarCliente();
                        break;
                    }
                    case 2:
                    {
                        consultarDadosCliente();

                        break;
                    }
                    case 3:
                    {
                        excluirConta();

                        break;
                    }
                    case 4:
                    {
                        livraria.Menu();
                        break;
                    }
                    default:
                    {
                        System.out.println("\nOpção Inválida!\n");
                        break;
                    }
                }
                       
            } while (opcaoCliente != 4);
                
        }
        
        // Método que altera os dados dos clientes
        public  void alterarCliente()
        {
            int i = 0;
            
            i = cpf.indexOf(clienteAtual);
            
            System.out.println("\nDigite o nome do cliente: ");
            String novoNome = lerString.nextLine();
				
            System.out.println("\nDigite o sexo do cliente: ");
            String novoSexo = lerString.nextLine();
			
            System.out.println("\nDigite CPF civil do cliente: ");
            String novoCpf = lerString.nextLine();
			
            System.out.println("\nDigite o RG do cliente: ");
            String novoRg = lerString.nextLine();
				
            System.out.println("\nDigite o endereco do cliente: ");
            String novoEndereco = lerString.nextLine();
				
            System.out.println("\nDigite o telefone do cliente: ");
            String novoTelefone = lerString.nextLine();
				
            System.out.println("\nDigite o CEP do cliente: ");
            String novoCep = lerString.nextLine();
				
            System.out.println("\nDigite a data de nascimento do cliente: ");
            String novaDataNascimento = lerString.nextLine();
            
            
            cpf.set(i,novoCpf);
            rg.set(i, novoRg);
            nome.set(i,novoNome);
            sexo.set(i,novoSexo);
            endereco.set(i,novoEndereco);
            cep.set(i,novoCep);
            dataNascimento.set(i,novaDataNascimento);
            telefone.set(i,novoTelefone);
            
            clienteAtual = novoCpf;
            nomeClienteAtual = novoNome;
            
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
                
                conta.set(i, novoLogin);
                senha.set(i, novaSenha);
                
                System.out.println("\nDados atualizados com sucesso!\n");
                
                menuCliente();
                
            }
            else
            {
                menuCliente();
            }
            
        }
        
        // Método que imprime os dados cadastrados do cliente que está logado
        public void consultarDadosCliente()
        {
            int i = 0;
            
            i = cpf.indexOf(clienteAtual);
            
            System.out.println("\nDados cadastrados para '" + nomeClienteAtual + "':");
            System.out.println("CPF: " + cpf.get(i) );
            System.out.println("RG: " + rg.get(i) );
            System.out.println("Sexo: " + sexo.get(i) );
            System.out.println("Endereco: " + endereco.get(i) );
            System.out.println("CEP: " + cep.get(i) );
            System.out.println("Data de nascimento: " + dataNascimento.get(i) );
            System.out.println("Telefone: " + telefone.get(i) );
            System.out.println("Conta: " + conta.get(i) );
            System.out.println("Senha: **********\n");
                
            menuCliente();
                
        }
        
        // Método que exclui uma conta cadastrada
        public  void excluirConta()
        {
            String opcao = "";
            System.out.println("\nDeseja mesmo exluir sua conta " 
                               + nomeClienteAtual + "?\n 1-SIM  |  0-NÃO\n");
            
            opcao =  lerString.nextLine();
            
            if( opcao.equals("1") )
            {
                int i = 0;
                
                i = cpf.indexOf(clienteAtual);
                                
                cpf.remove(i);
                rg.remove(i);
                nome.remove(i);
                sexo.remove(i);
                endereco.remove(i);
                cep.remove(i);
                dataNascimento.remove(i);
                telefone.remove(i);
                conta.remove(i);
                senha.remove(i);
                
                acessoAtual = false;
                clienteAtual = "blank";
                nomeClienteAtual = "blank";
                                
                Livraria_ABC.Cesta.Esvaziar_cesta();
                
                livraria.Menu();
                
            }
            else
            {
                menuCliente();
            }
        }
        
        // Método que cadastra um novo cliente
        public  void cadastrarCliente() 
        {
                System.out.println("\nOlá Vistante! Bem vindo à livraria ABC!\nPercebemos que você ainda não possui cadastro em nosso site.\nPara se cadastrar, complete os campos abaixo: ");
		
                System.out.println("\nSeu nome: ");
		String nome = lerString.nextLine();
		
		System.out.println("\nSeu sexo: ");
		String sexo = lerString.nextLine();
		
		System.out.println("\nSeu CPF: ");
		String cpf = lerString.nextLine();
		
		System.out.println("\nSeu RG: ");
		String rg = lerString.nextLine();
		
		System.out.println("\nSeu endereço: ");
		String endereco = lerString.nextLine();
		
		System.out.println("\nSeu telefone: ");
		String telefone =lerString.nextLine();
		
		System.out.println("\nSeu CEP: ");
		String cep = lerString.nextLine();
		
		System.out.println("\nSua data de nascimento: ");
		String dataNasc = lerString.nextLine();
		
		System.out.println("\nInforme agora uma conta que será usada para fazer login: ");
		String login = lerString.nextLine();
		
		System.out.println("\nInforme uma senha para sua conta: ");
		String senha = lerString.nextLine();
                
                Cliente.cpf.add(cpf);
                Cliente.rg.add(rg);
                Cliente.nome.add(nome);
                Cliente.sexo.add(sexo);
                Cliente.endereco.add(endereco);
                Cliente.cep.add(cep);
                Cliente.dataNascimento.add(dataNasc);
                Cliente.telefone.add(telefone);
                Cliente.conta.add(login);
                Cliente.senha.add(senha);
                
                acessoAtual = true;
                clienteAtual = cpf;
                nomeClienteAtual = nome;
                
                System.out.println("\nCliente cadastrado com sucesso!\n");
                menuCliente();
        }
        
        //Retorna o cpf o cliente logado se o login for bem sucedido ou "blank" caso contrário.
        public String efetuarLogin()
        {
            String verifyCadastro = "1";
            if( !acessoAtual && !Cliente.conta.isEmpty() )  
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
                if(!acessoAtual)
                {
                    if( !Cliente.conta.isEmpty() )
                    {
                        int tries = 0;
                        String conta; 
                        String psw;
                        String cliente_logado = "blank";
                        boolean verify_psw = false;
            
                        do
                        {
                            System.out.println("\nInforme sua conta: ");
                            conta = lerString.nextLine();
            
                            System.out.println("\nInforme sua senha: ");
                            psw = lerString.nextLine();
            
                            if( Cliente.conta.contains(conta) )
                            {
                                int indice;
                                
                                indice = Cliente.conta.indexOf(conta);
                
                                if( senha.get(indice).equals(psw) )
                                {
                                    verify_psw = true;
                                    acessoAtual = true;
                                    clienteAtual = cpf.get(indice);
                                    nomeClienteAtual = nome.get(indice);
                                
                                    cliente_logado = cpf.get(indice);
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
                                System.out.println("\nA conta '" + conta 
                                                   + "' não existe em nossos cadastros."
                                                   + " Informe uma conta existente.\n");
                                tries++;
                            }
                
                        }while( (verify_psw == false && tries <= 3) 
                                    || ( Cliente.conta.contains(conta) 
                                            == false && tries <= 3 ) );
            
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
            
                        livraria.Menu();
                        return cliente_logado;
                    }
                    else
                    {
                        cadastrarCliente();
                
                        return "blank";
                    }
            
                }
                else
                {
                    return clienteAtual;
                } 
            }
            else
            {
                cadastrarCliente();
                return "blank";
            }
           
        }
        
        // Verifica se possui itens na cesta do cliente logado e então efetua o logout
        public void efetuarLogout()
        {
            if(acessoAtual)
            {
                String nome;
                String option;
                
                if( !Livraria_ABC.Cesta.Itens_quant.isEmpty() )
                {
                    System.out.println("\nVocê perderá os itens "
                                       + "adicionados à sua cesta."
                                       + "\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = lerString.nextLine();
                    
                    if( "1".equals(option) )
                    {
                        nome = nomeClienteAtual;
                
                        acessoAtual = false;
                        clienteAtual = "blank";
                        nomeClienteAtual = "blank";
                       
                        boolean ok;
                        
                        ok = Livraria_ABC.Cesta.Esvaziar_cesta();
                        
                        if(ok)
                        {
                            System.out.println("\n" + nome + ", sua conta foi"
                                               + " encerrada com sucesso!\n");
                        }
                        else
                        {
                            // Nothing to do
                        }
                    }
                    else
                    {
                        Livraria_ABC.Livro.Mostrar_catalogo();
                    }
                }
                else
                {
                    nome = nomeClienteAtual;
                
                    acessoAtual = false;
                    clienteAtual = "blank";
                    nomeClienteAtual = "blank";
                
                    System.out.println("\n" + nome + ", sua conta"
                                       + " foi encerrada com sucesso!\n");
                }
                
                livraria.Menu();
            }
            else
            {
                System.out.println("\nNenhuma conta logada no momento.\n");
                
                livraria.Menu();
            }
        }
        
        // Logout alternativo
        public  void efetuarLogout_()
        {
            if(acessoAtual)
            {
                String nome;
                String option;
                
                if( !Livraria_ABC.Cesta.Itens_quant.isEmpty() )
                {
                    System.out.println("\nVocê perderá os itens"
                                       + " adicionados à sua cesta."
                                       + "\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = lerString.nextLine();
                    
                    if( "1".equals(option) )
                    {
                        nome = nomeClienteAtual;
                
                        acessoAtual = false;
                        clienteAtual = "blank";
                        nomeClienteAtual = "blank";
                       
                        boolean ok;
                        
                        ok = Livraria_ABC.Cesta.Esvaziar_cesta();
                        
                        if(ok)
                        {
                            System.out.println("\n" + nome + ", sua conta foi"
                                               + " encerrada com sucesso!\n");
                        }
                        else
                        {
                            // Nothing to do
                        }
                    }
                    else
                    {
                        livraria.Menu();
                    }
                    
                }
                else
                {
                    nome = nomeClienteAtual;
                
                    acessoAtual = false;
                    clienteAtual = "blank";
                    nomeClienteAtual = "blank";
                
                    System.out.println("\n" + nome + ", sua conta"
                                       + " foi encerrada com sucesso!\n");
                }
                
            }
            else
            {
                // Nothing to do
            }

        }
}
