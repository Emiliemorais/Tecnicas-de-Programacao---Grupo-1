
package livraria_abc;

import java.util.*;

public class Livraria_ABC
{
    // Se houver algum login aberto, acessoAtual = true;
    static boolean acessoAtual = false; 
    // Se houver algum login aberto, acessoAtual = cpf do cliente logado;
    static String clienteAtual = "blank";
    static String nomeClienteAtual = "blank";
    static float desconto = 0;
            
    public static class Livraria
    {
        int cnpj;
        String nome;
        String endereco;
        int telefone;
         
        // Construtor 
        public Livraria ()
        {
            this.cnpj = 54505052;
            this.nome = "Livraria ABC";
            this.endereco = "Avenida Getúlio Vargas nº 69";
            this.telefone = 32345698;
        }   
    }
        
    public static class Cliente 
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
        
        // Menu específico para o cliente
        public static void menuCliente()
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
                        Menu();
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
        public static void alterarCliente()
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
        public static void consultarDadosCliente()
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
        public static void excluirConta()
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
                                
                Cesta.Esvaziar_cesta();
                
                Menu();
                
            }
            else
            {
                menuCliente();
            }
        }
        
        // Método que cadastra um novo cliente
        public static void cadastrarCliente() 
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
        public static String efetuarLogin()
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
            
                        Menu();
                        return cliente_logado;
                    }
                    else
                    {
                        Cliente.cadastrarCliente();
                
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
        public static void efetuarLogout()
        {
            if(acessoAtual)
            {
                String nome;
                String option;
                
                if( !Cesta.Itens_quant.isEmpty() )
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
                        
                        ok = Cesta.Esvaziar_cesta();
                        
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
                        Livro.Mostrar_catalogo();
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
                
                Menu();
            }
            else
            {
                System.out.println("\nNenhuma conta logada no momento.\n");
                
                Menu();
            }
        }
        
        // Logout alternativo
        public static void efetuarLogout_()
        {
            if(acessoAtual)
            {
                String nome;
                String option;
                
                if( !Cesta.Itens_quant.isEmpty() )
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
                        
                        ok = Cesta.Esvaziar_cesta();
                        
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
                        Menu();
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
    
    public static class Livro
    {
       static ArrayList<String> ISBN = new ArrayList<>();
       static ArrayList<String> Titulo = new ArrayList<>();
       static ArrayList<String> Tema = new ArrayList<>();
       static ArrayList<String> Preco = new ArrayList<>();
       static ArrayList<String> Autor = new ArrayList<>();
       static ArrayList<String> Editora = new ArrayList<>();
       static ArrayList<String> Ano_publicacao = new ArrayList<>();
       static ArrayList<String> Edicao = new ArrayList<>();
       //Cliente informa um livro que deseja
       static ArrayList<String> livro_recomendacao = new ArrayList<>();
       
       // Mostra o catálogo de livros cadastrados no sistema
        public static void Mostrar_catalogo()
        {
            int i;
            
            System.out.println("\tConfira nossos produtos:\n");
            for(i=0; i<ISBN.size(); i++)
            {
                System.out.println("\tTitulo: "+Titulo.get(i));
                System.out.println("\tAutor: "+Autor.get(i));
                System.out.println("\tEditora: "+Editora.get(i));
                System.out.println("\tEdição/Ano: "+Edicao.get(i)+"/"+Ano_publicacao.get(i));
                System.out.println("\tTema: "+Tema.get(i));
                System.out.println("\tPor apenas R$"+Preco.get(i) + "!\n");
            }
            
            Menu();
        }
       
        /*  Procura um livro pelo ISBN ou pelo título e retorna os dados
           cadastrados, se encontrar. Se não, pergunta ao cliente se 
           deseja recomendar aquisição.       
        */
        public static void Consultar_livro ()
        {
            String livro_procurar;
            
            System.out.println("\nInforme o ISBN ou o título do livro que deseja:");
            livro_procurar = lerString.nextLine();
            
            if(ISBN.contains(livro_procurar) || Titulo.contains(livro_procurar))
            {
                int i=-1;
                if(ISBN.contains(livro_procurar))
                {
                    i = ISBN.indexOf(livro_procurar);
                }
                else if(Titulo.contains(livro_procurar))
                {
                    i = Titulo.indexOf(livro_procurar);
                }
                
                System.out.println("\nEncontramos o seguinte livro em nosso sistema por apenas R$"+Preco.get(i) +":\n");
                
                System.out.println("Titulo: "+Titulo.get(i));
                System.out.println("Autor: "+Autor.get(i));
                System.out.println("Editora: "+Editora.get(i));
                System.out.println("Edição/Ano: "+Edicao.get(i)+"/"+Ano_publicacao.get(i));
                System.out.println("Tema: "+Tema.get(i));
                
                String opcao;
                if(!acessoAtual)
                    System.out.println("\nDeseja comprar esse livro? Faça login agora mesmo!\n 1-Fazer login  0-Voltar para catálogo");
                else
                    System.out.println("\n"+nomeClienteAtual+", deseja comprar esse livro?!\n 1-SIM  0-Voltar para catálogo");
                
                opcao = lerString.nextLine();
                
                if("1".equals(opcao))
                {
                    String cliente_log;
                    int quant;
                    
                    cliente_log = Cliente.efetuarLogin();
                    
                    if(acessoAtual)
                    {
                        int indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                        
                        System.out.println("\nOlá "+Cliente.nome.get(indice_Clogado)+".\nInforme a quantidade de '"+Titulo.get(i)+"' desejada: ");
                        quant = input.nextInt();
                    
                        Cesta.Adicionar_itens_a_cesta(ISBN.get(i), quant, cliente_log);
                    }
                }
                else
                    Mostrar_catalogo();
            }
            else
            {
                String recomenda;
                System.out.println("\nSinto muito mas não temos esse livro em estoque, mas seria um prazer receber sua recomendação de livros para que possamos aumentar nosso acervo e melhor serví-lo!");
                System.out.println("Deseja recomendar esse livro para aquisição?\n1-SIM  | 0-NÃO");
                recomenda = lerString.nextLine();
                
                if("1".equals(recomenda))
                {
                    boolean ok;
                    ok = livro_recomendacao.add(livro_procurar);
                    
                    if(ok)
                        System.out.println("\nSua recomendação foi recebida com sucesso! Agradecemos sua participação e iremos analisar o seu pedido e, se possível, este livro estará em breve nas nossas prateleiras!");
                }
                
                Menu();
            }
            
        }
    }
        
    public static class Cesta
    {
        static String Cod_cesta = "blank";
        static String Cliente_cesta="blank";
        static List<Cesta> Itens_quant = new ArrayList<>();
        String Itens_add;
        int Quant_add;
        
        // Inicio Getters and Setters
        public String getItens_add() {
            return Itens_add;
        }

        public void setItens_add(String Itens_add) {
            this.Itens_add = Itens_add;
        }

        public int getQuant_add() {
            return Quant_add;
        }

        public void setQuant_add(int Quant_add) {
            this.Quant_add = Quant_add;
        }
        // Fim Getters and Setters
        
        // Construtor padrão
        public Cesta(){}
        
        // Construtor
        public Cesta(String itens, int quant)
        {
            this.Itens_add = itens;
            this.Quant_add = quant;
        }
        
        // Adiciona um livro para a cesta do cliente
        public static void Adicionar_itens_a_cesta(String isbn_livro, int quantidade, String cliente_c)
        {
           if(acessoAtual) 
           {
            /* Verifica se já tem uma cesta aberta para o cliente "cliente_c".
               Se não houver, é aberta uma.
            */   
            if(!cliente_c.equals(Cliente_cesta))
            {
                int cod_c;
                String cod_s;
                boolean ok_cod=false;
                
                cod_c = gera_num.nextInt(10000);
                cod_s = Integer.toString(cod_c);
                    
                while(ok_cod==false)
                { 
                    if(Cod_cesta.contains(cod_s))
                    {
                        ok_cod = false;
                        cod_c = gera_num.nextInt(10000);
                        cod_s = Integer.toString(cod_c);
                    }
                    else
                        ok_cod = true;
                }
                
                Cod_cesta = cod_s;
                Cliente_cesta = cliente_c ;
                
            }
            
            int indice_livro;
            
            indice_livro = Livro.ISBN.indexOf(isbn_livro);
                   
            Cesta i1 = new Cesta();
            
            i1.Itens_add = isbn_livro;
            i1.Quant_add = quantidade;
            
            Cesta itens_quant = new Cesta(i1.Itens_add, i1.Quant_add);
            
            boolean ok;
            ok = Itens_quant.add(itens_quant);
            
            if(ok) 
                System.out.println("'"+Livro.Titulo.get(indice_livro)+"' foi adicionado à sua cesta!");
            
            Menu();
          }
          else
          {
              System.out.println("\nÉ necessário fazer login primeiro.\n"); 
              
              String cliente_l;
              
              cliente_l = Cliente.efetuarLogin();
              
              if(!"blank".equals(cliente_l))
                  Adicionar_itens_a_cesta(isbn_livro, quantidade, cliente_l);
              else
              {
                  System.out.println("\nLogin mal sucedido. Tente Novamente.\n");
                  
                  Menu();
              }
          }
        }
        
        // Esvazia uma cesta e retorna o resultado da operação
        public static boolean Esvaziar_cesta()
        {
            Cod_cesta = "blank";
            Cliente_cesta = "blank";
            desconto = 0;
            
            int t=0;
            while(!Itens_quant.isEmpty())
            {
                Itens_quant.remove(t);
            }
            
            if(Itens_quant.isEmpty())
                return true;
            else
                return false;
        }
        
        // Lista todos itens cadastrados na cesta atual
        public static void Listar_itens_cesta ()
        {
            if(acessoAtual)
            {
                int indice_Clogado;
                
                indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                
                System.out.println("\nOlá "+Cliente.nome.get(indice_Clogado)+"!\n");
                
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.nome.get(indice_Clogado)+", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+Cod_cesta);
                    for(p=0; p<Itens_quant.size(); p++)
                    {
                        indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                        livro = Livro.Titulo.get(indice_Livro);
                        
                        System.out.println("Item "+(p+1)+": '"+livro+"'");
                        System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                        System.out.println("Preço(un.): R$"+Livro.Preco.get(indice_Livro)+"\n");
                        
                    }
                    
                    float total;
                    
                    total = Calcula_valor_compra();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", total);
                }
                
                Menu();
            }
            else
            {
                System.out.println("\nNenhum cliente logado. Faça login primeiro!");
                
                Menu();
            }
        }
        
        // Listar alternativo - Descobrir porquê..
        public static void Listar_itens_cesta_ ()
        {
                int indice_Clogado;
                
                indice_Clogado = Cliente.cpf.indexOf(clienteAtual);
                
                           
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.nome.get(indice_Clogado)+", sua cesta está vazia!");
                }
                else
                {
                    int p, indice_Livro;
                    
                    String livro;
                    
                    System.out.println("Código da cesta: "+Cod_cesta);
                    for(p=0; p<Itens_quant.size(); p++)
                    {
                        indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                        livro = Livro.Titulo.get(indice_Livro);
                        
                        System.out.println("Item "+(p+1)+": '"+livro+"'");
                        System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                        System.out.println("Preço(un.): R$"+Livro.Preco.get(indice_Livro)+"\n");
                        
                    }
                    
                    float total;
                    
                    total = Calcula_valor_compra();
                    System.out.printf("\nPreço total até agora: R$%.2f\n", total);
                }
           
        }
        
        // Retorna o catálogo de livros
        public static void Continuar_comprando()
        {
            Livro.Mostrar_catalogo();
        }
        
        // Retira um livro de uma cesta
        public static void Retirar_itens_cesta()
        {
            if(Itens_quant.isEmpty())
            {
                System.out.println("\nCesta vazia. Vamos lá, inclua algo agora mesmo!\n");
                Menu();
            }
            else
            {
                String livro_retirar;
                
                int p, indice_Livro;
                    
                String livro;
                    
                System.out.println("Código da cesta: "+Cod_cesta);
                for(p=0; p<Itens_quant.size(); p++)
                {
                    indice_Livro = Livro.ISBN.indexOf(Itens_quant.get(p).getItens_add());
                    
                    livro = Livro.Titulo.get(indice_Livro);
                        
                    System.out.println("Item "+(p+1)+": '"+livro+"'");
                    System.out.println("Quantidade: "  +Itens_quant.get(p).getQuant_add());
                }
                int item;
                boolean item_ok = false;
                
                do
                {
                    System.out.println("\nInforme o número do item que deseja retirar da cesta: ");
                    livro_retirar = lerString.nextLine();
                
                    item = Integer.parseInt(livro_retirar);
                
                    if(item>0 && item<Itens_quant.size())
                        item_ok = true;
                    else
                        item_ok = false;
                    
                }while(item_ok==false);
                
                Cesta remove = Itens_quant.remove(item-1);
                
                int ind;
                
                ind = Livro.ISBN.indexOf(remove.Itens_add);
                
                System.out.println("\n'"+Livro.Titulo.get(ind)+"'/"+remove.Quant_add+" foi removido da cesta.\n");
                
                Menu();
            }
        }
        
        // Calcula o valor total de todos os itens da cesta
        public static float Calcula_valor_compra()
        {
            if(Itens_quant.isEmpty())
            {
                return 0;
            }
            else
            {
                String livro, preco_livro;
                int p, pos_livro;
                float valor_compra=0, subtotal, preco;
                
                for(p=0; p<Itens_quant.size(); p++)
                {
                    livro = Itens_quant.get(p).Itens_add;
                    
                    pos_livro = Livro.ISBN.indexOf(livro);
                    
                    preco_livro = Livro.Preco.get(pos_livro);
                    
                    preco = Float.parseFloat(preco_livro);
                    
                    subtotal = preco*Itens_quant.get(p).Quant_add;
                    
                    valor_compra +=subtotal;                                        
                }
                
                valor_compra = valor_compra - (valor_compra*desconto);
                                                
                return valor_compra;
            }
       
        }
        
        // Inclui um desconto a partir de um cupom informado
        public static void Incluir_desconto()
        {
            String cupom;
            
            System.out.println("\nInforme o código do seu cupom de desconto:");
            cupom = lerString.nextLine();
            
            boolean verify_cup = false;
            
            // Verifica se o cupom é cadastrado no sistema
            verify_cup = TCupom.Verifica_cupom(cupom);
            
            if(verify_cup)
            {
                float valor;
                
                valor = TCupom.Verifica_valor_desconto(cupom);
                
                System.out.println("\nParabéns! Você ganhou "+valor+"% de desconto em qualquer livro!\n");
                
                desconto = valor/100;
                
            }
            else
            {
                System.out.println("\nCumpom não cadastrado em nosso sistema.\n");
                Menu();              
            }
            
        }
        
        // Cria um inteiro aleatório que será usado na classe 'Compra' 
        public static int Fechar_cesta()
        {
            if(!Itens_quant.isEmpty())
            {
                int cod_compra;
            
                cod_compra = gera_num.nextInt(10000);
                
                desconto = 0;
                            
                return cod_compra; /// Codigo que vai para classe 'Compra'
            }
            else
            {
                System.out.println("\nCesta Vazia!\n");
                return -1;
            }
        }
        
    }
    
    // Armazena o endereço para a entrega do produto
    static String end_compra = "blank";
    
    // Armazena o tipo de pagamento escolhido pelo cliente
    static String tipo_pag = "blank";
    
    // Armazena o codigo da compra gerado pelo método 'Fechar_cesta()'
    static int COD_compra = -1;
    
    public static class Compra
    {
        static List<Compra> Compras = new ArrayList<>();
        int Cod_compra;
        String Data_compra;
        float Valor_compra;
        String Endereco_entrega;
        String Tipo_pag;
        
        // Construtor padrão
        public Compra(){}
        
        // Construtor
        public Compra(int cod, String data, float valor, String endereco, String tipo_pag)
        {
            this.Cod_compra = cod;
            this.Data_compra = data;
            this.Valor_compra = valor;
            this.Endereco_entrega = endereco;
            this.Tipo_pag = tipo_pag;
        }
        
        // Armazena a compra na lista 'Compras'
        public static void Cadastrar_compra()
        {
            Compra comp = new Compra();
            
            comp.Endereco_entrega = end_compra;
            comp.Tipo_pag = tipo_pag;
            comp.Valor_compra = Cesta.Calcula_valor_compra();
            comp.Cod_compra = COD_compra;
           
            Compras.add(comp);
            
        }
        
        // Cancela uma compra com a confirmação do usuário
        public static void Cancelar_compra()
        {
            String opcao;
            
            System.out.println("\nDeseja mesmo cancelar a compra? 1-SIM  2-NÃO");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                end_compra="blank";
                tipo_pag="blank";
                COD_compra = -1;
                
                Menu();
            }
            else
            {
                Finalizar_compra();
            }    
        }

        // Verifica se o endereço a ser utiizado será o cadastrado no sistema
        public static void Verificar_endereco()
        {
            int i;
            String opcao;
            
            i = Cliente.cpf.indexOf(clienteAtual);
                        
            System.out.println("\nO endereço de entrega será: '"+Cliente.endereco.get(i)+"'?\n1-SIM  0- ALTERAR ENDEREÇO");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                end_compra = Cliente.endereco.get(i);
                
                Verificar_pagamento();
            }
            else
            {
                String novo_endereco;
                
                System.out.println("\nInforme o novo endereço de entrega: ");
                novo_endereco = lerString.nextLine();
                
                end_compra = novo_endereco;
                
                Verificar_pagamento();
            }
        }
        
        // Verifica o valor final e qual o tipo do pagamento
        public static void Verificar_pagamento()
        {
            float total;
            String tipo_p;
            
            total = Cesta.Calcula_valor_compra();
            
            System.out.printf("\nO valor total a ser pago é R$%.2f.\n",total);
            
            boolean ok = false;
            
            do
            {
                System.out.println("\nQual será a forma de pagamento?\n1-Boleto  |  2-Cartão de crédito");
                tipo_p = lerString.nextLine();
            
                if(tipo_p.equals("1"))
                {
                    tipo_pag = Tipo_pagamento.Tipos.get(0).tipo;
                    ok = true;
                }
                else if(tipo_p.equals("2"))
                {
                    tipo_pag = Tipo_pagamento.Tipos.get(1).tipo;
                    ok = true;
                }
                else
                {
                    System.out.println("\nOpção Inválida\n");
                    ok = false;
                }   
            }while(ok==false);
            
            Finalizar_compra();
        }
        
        // Finaliza uma compra e esvazia a cesta aberta se confirmado
        public static void Finalizar_compra()
        {
            String opcao;
            
            System.out.println("\nSeu pedido é: ");
            
            Cesta.Listar_itens_cesta_();
            
            System.out.println("\nEndereço de entrega: "+end_compra);
            System.out.println("\nTipo de pagamento: "+tipo_pag);
            
            System.out.println("\nDeseja finalizar a compra?\n 1-SIM  |  0-CANCELAR COMPRA");
            opcao = lerString.nextLine();
            
            if(opcao.equals("1"))
            {
                System.out.println("\nCompra realizada com sucesso!\n");
                
                Cesta.Esvaziar_cesta();
                
                Cadastrar_compra();
                
                Menu();
            }
            else
            {
                Cancelar_compra();
            }
        }
    }
    
    // Método para imprimir uma mensagem de saída ao usuário
    public static void Exit()
    {
        System.out.println("\nObrigado por utilizar nossos serviços! A Livraria ABC agrade e volte sempre!");
    }
    
    // Menu de opções
    public static void Menu()
    {
        String opcao = "";
        
        // Verifica se tem alguem logado.
        if(acessoAtual)
        {
            if(!opcao.equals("0"))
            {
                System.out.println("\nOlá "+nomeClienteAtual+"! O que deseja na Livraria Virtual ABC?");
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
            
                opcao = lerString.nextLine();
            }
            
            switch(opcao)
            {
                case "1":
                {
                    Livro.Mostrar_catalogo();
                    break;
                }
                case "2":
                {
                    Livro.Consultar_livro();
                    break;
                }
                case "3":
                {
                    String livro_desejado;
                    System.out.println("\nInforme o ISBN ou o título do livro que deseja: ");
                    livro_desejado = lerString.nextLine();
                    
                    if(Livro.ISBN.contains(livro_desejado) || Livro.Titulo.contains(livro_desejado))
                    {
                        int i=-1, quantidade_livros;
                        if(Livro.ISBN.contains(livro_desejado))
                        {
                            i = Livro.ISBN.indexOf(livro_desejado);
                        }
                        else if(Livro.Titulo.contains(livro_desejado))
                        {
                            i = Livro.Titulo.indexOf(livro_desejado);
                        }
                        
                        System.out.println("\nInforme a quantidade de '"+Livro.Titulo.get(i)+"' desejada:");
                        quantidade_livros = input.nextInt();
                        
                        Cesta.Adicionar_itens_a_cesta(Livro.ISBN.get(i), quantidade_livros, clienteAtual);
                    }
                    else
                    {
                        System.out.println("\nNão encontramos '"+livro_desejado+"' em nosso sistema. Verifique a grafia e tente novamente.\n");
                        Menu();
                    }
                    
                    break;
                }
                case "4":
                {
                    Cesta.Listar_itens_cesta();
                    break;
                }
                case "5":
                {
                    Cliente.efetuarLogout();
                    break;
                }
                case "6":
                {
                    Cesta.Retirar_itens_cesta();
                    break;
                }
                case "7":
                {
                    Cesta.Incluir_desconto();
                    break;
                }
                case "8":
                {
                    int cod_compra;
                    
                    cod_compra = Cesta.Fechar_cesta();
                    
                    if(cod_compra==-1)
                        Menu();
                    else
                    {
                        COD_compra = cod_compra;
                        Compra.Verificar_endereco();
                    }
                    //// Passar esse codigo para classe compra. Se cod_compra = -1 ta errado. VALIDAR!!!
                    
                    break;
                }
                case "9":
                {
                    Cliente.menuCliente();
                    break;
                }
                case "0":
                {
                    Cliente.efetuarLogout_();
                        
                    Exit();
                        
                       // break;
                }
                default:
                { 
                    if(!"0".equals(opcao))
                        System.out.println("\nOpção Inválida!\n");
                   
                }
            }
        }
        else
        {
            if(!opcao.equals("0"))
            {            
                System.out.println("\nOlá visitante! O que deseja na Livraria Virtual ABC?");
                System.out.println("1 - Olhar catálogo");
                System.out.println("2 - Procurar por Livro específico");
                System.out.println("3 - Adicionar livro à cesta");
                System.out.println("4 - Efetuar_login");
                System.out.println("0 - Fechar página e sair do sistema");
            
                opcao = lerString.nextLine();
            }
            
            switch(opcao)
            {
                case "1":
                {
                    Livro.Mostrar_catalogo();
                    break;
                }
                case "2":
                {
                    Livro.Consultar_livro();
                    break;
                }
                case "3":
                {
                    System.out.println("\nÉ necessário fazer login primeiro.");
                    
                    Cliente.efetuarLogin();
                    
                    Menu();
                    break;
                }
                case "4":
                {
                    Cliente.efetuarLogin();
                    break;
                }
                case "0":
                {
                    Exit();
                       
                   // break;
                }
                default:
                {
                    if(!"0".equals(opcao))
                        System.out.println("\nOpção Inválida!\n");
                    
                }
            }
        }
    }
    
    // Objeto que gera um número aleatório
    static Random gera_num = new Random();
    
    // Objeto utilizado para ler números inteiros e de ponto flutuante do usuário
    static Scanner input = new Scanner(System.in);
    
    // Objeto utilizado para ler strings do usuário
    static Scanner lerString = new Scanner(System.in);

    public static void main(String[] args)
    {
        Livraria livraria = new Livraria();
        
        /*
            Os dados abaixo foram inseridos manualmente
            para simular um banco de dados da Livraria_ABC
        */
            Livro.ISBN.add(0, "9780345486572");
            Livro.Titulo.add(0, "Dom Casmurro");
            Livro.Tema.add(0,"Literatura brasileira");
            Livro.Preco.add(0, "12.50");
            Livro.Autor.add(0, "Machado de Assis");
            Livro.Editora.add(0, "Pearson");
            Livro.Ano_publicacao.add(0,"2010");
            Livro.Edicao.add(0,"3ª");
            
            Livro.ISBN.add(1, "9780964729230");
            Livro.Titulo.add(1, "A Cabana");
            Livro.Tema.add(1,"Ficção");
            Livro.Preco.add(1, "32.49");
            Livro.Autor.add(1, "William P. Young");
            Livro.Editora.add(1, "Windblown");
            Livro.Ano_publicacao.add(1,"2007");
            Livro.Edicao.add(1,"2ª");
            
            Livro.ISBN.add(2, "9788508106707");
            Livro.Titulo.add(2, "Vida de droga");
            Livro.Tema.add(2,"Infanto-Juvenil");
            Livro.Preco.add(2, "15.80");
            Livro.Autor.add(2, "Walcyr Carrasco");
            Livro.Editora.add(2, "Ática");
            Livro.Ano_publicacao.add(1,"2005");
            Livro.Edicao.add(2,"4ª");
            
            TCupom cupom = new TCupom();
            cupom.cupom = "0X2R";
            cupom.valor_desconto = 20;
            TCupom.cupom_valor.add(cupom);
            
            Tipo_pagamento t1 = new Tipo_pagamento("Boleto","");
            Tipo_pagamento t2 = new Tipo_pagamento("Cartão","Aceitamos Visa, MasterCard e Cielo");
            Tipo_pagamento.Tipos.add(t1);
            Tipo_pagamento.Tipos.add(t2);
            
                        
            System.out.println("\n\t\t\t\tBem-vindo(a) a "+livraria.nome+"!\n");
            Livro.Mostrar_catalogo();
                
            Menu();           
    }
}
