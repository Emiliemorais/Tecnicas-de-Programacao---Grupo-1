
package livraria_abc;

import java.util.*;

public class Livraria_ABC
{
    // Se houver algum login aberto, acesso_atual = true;
    static boolean acesso_atual = false; 
    // Se houver algum login aberto, acesso_atual = CPF do cliente logado;
    static String cliente_atual = "blank";
    static String nome_cliente_atual = "blank";
    static float desconto = 0;
            
    public static class Livraria
    {
        int CNPJ;
        String Nome;
        String Endereco;
        int Telefone;
         
        // Construtor 
        public Livraria ()
        {
            this.CNPJ = 54505052;
            this.Nome = "Livraria ABC";
            this.Endereco = "Avenida Getúlio Vargas nº 69";
            this.Telefone = 32345698;
        }   
    }
        
    public static class Cliente 
    {
        static ArrayList<String> CPF = new ArrayList<>();
        static ArrayList<String> RG = new ArrayList<>();
	static ArrayList<String> Nome = new ArrayList<>();
	static ArrayList<String> Sexo = new ArrayList<>();
	static ArrayList<String> Endereco = new ArrayList<>();
	static ArrayList<String> CEP = new ArrayList<>();
	static ArrayList<String> Data_n = new ArrayList<>();
	static ArrayList<String> Telefone = new ArrayList<>();
        private static ArrayList<String> Conta = new ArrayList<>();
        private static ArrayList<String> Senha = new ArrayList<>();
        
        // Menu específico para o cliente
        public static void Menu_cliente()
        {
            int opcaocliente = 0;
		do 
                {
			System.out.println("\nOlá "+nome_cliente_atual+"! Aqui você pode gerenciar sua conta.");
			System.out.println("1- Alterar dados conta");
			System.out.println("2- Consultar dados da conta");
			System.out.println("3- Excluir conta");
			System.out.println("4- Voltar para o menu principal\n");

			System.out.println("Digite a opcao: ");
			opcaocliente = input.nextInt();
			
			switch (opcaocliente) 
                        {
                            case 1:
                            {
				Alterar_cliente();
				break;
                            }
                            case 2:
                            {
				Consultar_dados_cliente();

				break;
                            }
                            case 3:
                            {
				Excluir_conta();

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
                        
		} while (opcaocliente != 4);

        }
        
        // Método que altera os dados dos clientes
        public static void Alterar_cliente()
        {
            
            int i;
            
            i = CPF.indexOf(cliente_atual);
            
            System.out.println("\nDigite o nome do cliente: ");
            String novonome = ler_string.nextLine();
				
            System.out.println("\nDigite o sexo do cliente: ");
            String novosexo = ler_string.nextLine();
			
            System.out.println("\nDigite CPF civil do cliente: ");
            String novoCPF = ler_string.nextLine();
			
            System.out.println("\nDigite o RG do cliente: ");
            String novorg = ler_string.nextLine();
				
            System.out.println("\nDigite o endereco do cliente: ");
            String novoendereco = ler_string.nextLine();
				
            System.out.println("\nDigite o telefone do cliente: ");
            String novotelefone = ler_string.nextLine();
				
            System.out.println("\nDigite o CEP do cliente: ");
            String novoCEP = ler_string.nextLine();
				
            System.out.println("\nDigite a data de nascimento do cliente: ");
            String novodn = ler_string.nextLine();
            
            
            CPF.set(i,novoCPF);
            RG.set(i, novorg);
            Nome.set(i,novonome);
            Sexo.set(i,novosexo);
            Endereco.set(i,novoendereco);
            CEP.set(i,novoCEP);
            Data_n.set(i,novodn);
            Telefone.set(i,novotelefone);
            
            cliente_atual = novoCPF;
            nome_cliente_atual = novonome;
            
            System.out.println("\nDados atualizados com sucesso!\n");
            
            String op;
            System.out.println("Deseja alterar a conta de login e senha?\n 1-SIM  |  0-NÃO");
            op = ler_string.nextLine();
            
            if(op.equals("1"))
            {
                System.out.println("\nDigite a nova conta: ");
                String novologin = ler_string.nextLine();
				
                System.out.println("\nDigite a nova senha: ");
                String novasenha = ler_string.nextLine();
                
                Conta.set(i, novologin);
                Senha.set(i, novasenha);
                
                System.out.println("\nDados atualizados com sucesso!\n");
                
                Menu_cliente();
                
            }
            else
                Menu_cliente();
            
        }
        
        // Método que imprime os dados cadastrados do cliente que está logado
        public static void Consultar_dados_cliente()
        {
            int i;
            
            i = CPF.indexOf(cliente_atual);
            
                System.out.println("\nDados cadastrados para '"+nome_cliente_atual+"':");
                System.out.println("CPF: "+CPF.get(i));
                System.out.println("RG: "+RG.get(i));
                System.out.println("Sexo: "+Sexo.get(i));
                System.out.println("Endereco: "+Endereco.get(i));
                System.out.println("CEP: "+CEP.get(i));
                System.out.println("Data de nascimento: "+Data_n.get(i));
                System.out.println("Telefone: "+Telefone.get(i));
                System.out.println("Conta: "+Conta.get(i));
                System.out.println("Senha: **********\n");
                
                Menu_cliente();
                
        }
        
        // Método que exclui uma conta cadastrada
        public static void Excluir_conta()
        {
            String opcao;
            System.out.println("\nDeseja mesmo exluir sua conta "+nome_cliente_atual+"?\n 1-SIM  |  0-NÃO\n");
            opcao =  ler_string.nextLine();
            
            if(opcao.equals("1"))
            {
                int i;
                
                i = CPF.indexOf(cliente_atual);
                                
                CPF.remove(i);
                RG.remove(i);
                Nome.remove(i);
                Sexo.remove(i);
                Endereco.remove(i);
                CEP.remove(i);
                Data_n.remove(i);
                Telefone.remove(i);
                Conta.remove(i);
                Senha.remove(i);
                
                acesso_atual = false;
                cliente_atual = "blank";
                nome_cliente_atual = "blank";
                                
                Cesta.Esvaziar_cesta();
                
                Menu();
                
            }
            else
            {
                Menu_cliente();
            }
        }
        
        // Método que cadastra um novo cliente
        public static void Cadastrar_cliente() 
        {
                System.out.println("\nOlá Vistante! Bem vindo à livraria ABC!\nPercebemos que você ainda não possui cadastro em nosso site.\nPara se cadastrar, complete os campos abaixo: ");
		
                System.out.println("\nSeu nome: ");
		String nome = ler_string.nextLine();
		
		System.out.println("\nSeu sexo: ");
		String sexo = ler_string.nextLine();
		
		System.out.println("\nSeu CPF: ");
		String cpf = ler_string.nextLine();
		
		System.out.println("\nSeu RG: ");
		String rg = ler_string.nextLine();
		
		System.out.println("\nSeu endereço: ");
		String endereco = ler_string.nextLine();
		
		System.out.println("\nSeu telefone: ");
		String telefone =ler_string.nextLine();
		
		System.out.println("\nSeu CEP: ");
		String cep = ler_string.nextLine();
		
		System.out.println("\nSua data de nascimento: ");
		String dn = ler_string.nextLine();
		
		System.out.println("\nInforme agora uma conta que será usada para fazer login: ");
		String login = ler_string.nextLine();
		
		System.out.println("\nInforme uma senha para sua conta: ");
		String senha = ler_string.nextLine();
                
                CPF.add(cpf);
                RG.add(rg);
                Nome.add(nome);
                Sexo.add(sexo);
                Endereco.add(endereco);
                CEP.add(cep);
                Data_n.add(dn);
                Telefone.add(telefone);
                Conta.add(login);
                Senha.add(senha);
                
                acesso_atual = true;
                cliente_atual = cpf;
                nome_cliente_atual = nome;
                
                System.out.println("\nCliente cadastrado com sucesso!\n");
                Menu_cliente();
        }
        
        //Retorna o CPF o cliente logado se o login for bem sucedido ou "blank" caso contrário.
        public static String Efetuar_Login()
        {
          String verify_cadastro = "1";
          if(!acesso_atual && !Cliente.Conta.isEmpty())  
          {
              System.out.println("\nJá possui cadastro em nosso site?\nSe sim, digite 1 e continue com sua compra. Se não, digite 0 e cadastre-se agora mesmo!\n");
              verify_cadastro = ler_string.nextLine();
          }
          
          if("1".equals(verify_cadastro))
          {
          if(!acesso_atual)
          {
            if(!Cliente.Conta.isEmpty())
            {
            int tries=0;
            String conta, psw, cliente_logado="blank";
            boolean verify_psw = false;
            
            do
            {
                System.out.println("\nInforme sua conta: ");
                conta = ler_string.nextLine();
            
                System.out.println("\nInforme sua senha: ");
                psw = ler_string.nextLine();
            
                if(Conta.contains(conta))
                {
                    int indice;
                    indice = Conta.indexOf(conta);
                
                    if(Senha.get(indice).equals(psw))
                    {
                        verify_psw = true;
                        acesso_atual = true;
                        cliente_atual = CPF.get(indice);
                        nome_cliente_atual = Nome.get(indice);
                                
                        cliente_logado = CPF.get(indice);
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
                    System.out.println("\nA conta '"+conta+"' não existe em nossos cadastros. Informe uma conta existente.\n");
                    tries++;
                }
                
            }while((verify_psw==false && tries<=3) || (Conta.contains(conta)==false && tries<=3));
            
            if(tries>3)
                System.out.println("\nTentativas de login excedidas. Para sua segurança fecharemos a tela de login.\n");
            
            Menu();
            return cliente_logado;
          }
            else
            {
                Cliente.Cadastrar_cliente();
                
                return "blank";
            }
            
          }
          else
              return cliente_atual;
        }
        else
        {
              Cadastrar_cliente();/// Fazer o Cadastrar Cliente
              return "blank";
        }
           
        }
        
        // Verifica se possui itens na cesta do cliente logado e então efetua o logout
        public static void Efetuar_Logout()
        {
            if(acesso_atual)
            {
                String nome;
                String option;
                
                if(!Cesta.Itens_quant.isEmpty())
                {
                    System.out.println("\nVocê perderá os itens adicionados à sua cesta.\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = ler_string.nextLine();
                    
                    if("1".equals(option))
                    {
                        nome = nome_cliente_atual;
                
                        acesso_atual = false;
                        cliente_atual = "blank";
                        nome_cliente_atual = "blank";
                       
                        boolean ok;
                        
                        ok = Cesta.Esvaziar_cesta();
                        
                        if(ok)
                            System.out.println("\n"+nome+", sua conta foi encerrada com sucesso!\n");
                    }
                    else
                        Livro.Mostrar_catalogo();///RETORNAR MENU OU CATALOGO
                }
                else
                {
                    nome = nome_cliente_atual;
                
                    acesso_atual = false;
                    cliente_atual = "blank";
                    nome_cliente_atual = "blank";
                
                    System.out.println("\n"+nome+", sua conta foi encerrada com sucesso!\n");
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
        public static void Efetuar_Logout_()
        {
            if(acesso_atual)
            {
                String nome;
                String option;
                
                if(!Cesta.Itens_quant.isEmpty())
                {
                    System.out.println("\nVocê perderá os itens adicionados à sua cesta.\nDeseja continuar? 1-SIM  0-NÃO\n");
                    option = ler_string.nextLine();
                    
                    if("1".equals(option))
                    {
                        nome = nome_cliente_atual;
                
                        acesso_atual = false;
                        cliente_atual = "blank";
                        nome_cliente_atual = "blank";
                       
                        boolean ok;
                        
                        ok = Cesta.Esvaziar_cesta();
                        
                        if(ok)
                            System.out.println("\n"+nome+", sua conta foi encerrada com sucesso!\n");
                    }
                    else
                        Menu();
                    
                }
                else
                {
                    nome = nome_cliente_atual;
                
                    acesso_atual = false;
                    cliente_atual = "blank";
                    nome_cliente_atual = "blank";
                
                    System.out.println("\n"+nome+", sua conta foi encerrada com sucesso!\n");
                }
                
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
            livro_procurar = ler_string.nextLine();
            
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
                if(!acesso_atual)
                    System.out.println("\nDeseja comprar esse livro? Faça login agora mesmo!\n 1-Fazer login  0-Voltar para catálogo");
                else
                    System.out.println("\n"+nome_cliente_atual+", deseja comprar esse livro?!\n 1-SIM  0-Voltar para catálogo");
                
                opcao = ler_string.nextLine();
                
                if("1".equals(opcao))
                {
                    String cliente_log;
                    int quant;
                    
                    cliente_log = Cliente.Efetuar_Login();
                    
                    if(acesso_atual)
                    {
                        int indice_Clogado = Cliente.CPF.indexOf(cliente_atual);
                        
                        System.out.println("\nOlá "+Cliente.Nome.get(indice_Clogado)+".\nInforme a quantidade de '"+Titulo.get(i)+"' desejada: ");
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
                recomenda = ler_string.nextLine();
                
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
           if(acesso_atual) 
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
              
              cliente_l = Cliente.Efetuar_Login();
              
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
            if(acesso_atual)
            {
                int indice_Clogado;
                
                indice_Clogado = Cliente.CPF.indexOf(cliente_atual);
                
                System.out.println("\nOlá "+Cliente.Nome.get(indice_Clogado)+"!\n");
                
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.Nome.get(indice_Clogado)+", sua cesta está vazia!");
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
                
                indice_Clogado = Cliente.CPF.indexOf(cliente_atual);
                
                           
                if(Itens_quant.isEmpty())
                {
                    System.out.println("\n"+Cliente.Nome.get(indice_Clogado)+", sua cesta está vazia!");
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
                    livro_retirar = ler_string.nextLine();
                
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
            cupom = ler_string.nextLine();
            
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
    
    static String end_compra="blank";
    static String tipo_pag="blank";
    static int COD_compra = -1;
    
    public static class Compra
    {
        static List<Compra> Compras = new ArrayList<>();
        int Cod_compra;
        String Data_compra;
        float Valor_compra;
        String Endereco_entrega;
        String Tipo_pag;
        
        public Compra(){}
        
        public Compra(int cod, String data, float valor, String endereco, String tipo_pag)
        {
            this.Cod_compra = cod;
            this.Data_compra = data;
            this.Valor_compra = valor;
            this.Endereco_entrega = endereco;
            this.Tipo_pag = tipo_pag;
        }
        
      
        public static void Cadastrar_compra()
        {
            Compra comp = new Compra();
            
            comp.Endereco_entrega = end_compra;
            comp.Tipo_pag = tipo_pag;
            comp.Valor_compra = Cesta.Calcula_valor_compra();
            comp.Cod_compra = COD_compra;
           
            Compras.add(comp);
            
        }
        
        public static void Cancelar_compra()
        {
            String opcao;
            
            System.out.println("\nDeseja mesmo cancelar a compra? 1-SIM  2-NÃO");
            opcao = ler_string.nextLine();
            
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
        
        public static void Verificar_endereco()
        {
            int i;
            String opcao;
            
            i = Cliente.CPF.indexOf(cliente_atual);
                        
            System.out.println("\nO endereço de entrega será: '"+Cliente.Endereco.get(i)+"'?\n1-SIM  0- ALTERAR ENDEREÇO");
            opcao = ler_string.nextLine();
            
            if(opcao.equals("1"))
            {
                end_compra = Cliente.Endereco.get(i);
                
                Verificar_pagamento();
            }
            else
            {
                String novo_endereco;
                
                System.out.println("\nInforme o novo endereço de entrega: ");
                novo_endereco = ler_string.nextLine();
                
                end_compra = novo_endereco;
                
                Verificar_pagamento();
            }
        }
        
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
                tipo_p = ler_string.nextLine();
            
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
        
        public static void Finalizar_compra()
        {
            String opcao;
            
            System.out.println("\nSeu pedido é: ");
            
            Cesta.Listar_itens_cesta_();
            
            System.out.println("\nEndereço de entrega: "+end_compra);
            System.out.println("\nTipo de pagamento: "+tipo_pag);
            
            System.out.println("\nDeseja finalizar a compra?\n 1-SIM  |  0-CANCELAR COMPRA");
            opcao = ler_string.nextLine();
            
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
    
    public static void Exit()
    {
        System.out.println("\nObrigado por utilizar nossos serviços! A Livraria ABC agrade e volte sempre!");
    }
    
    public static void Menu()
    {
        String opcao = "";
        
        if(acesso_atual)///Verifica se tem alguem logado.
        {
            if(!opcao.equals("0"))
            {
                System.out.println("\nOlá "+nome_cliente_atual+"! O que deseja na Livraria Virtual ABC?");
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
            
                opcao = ler_string.nextLine();
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
                    livro_desejado = ler_string.nextLine();
                    
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
                        
                        Cesta.Adicionar_itens_a_cesta(Livro.ISBN.get(i), quantidade_livros, cliente_atual);
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
                    Cliente.Efetuar_Logout();
                    break;
                }
                case "6":
                {
                    Cesta.Retirar_itens_cesta();
                    break;
                }
/////////////////
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
                    Cliente.Menu_cliente();
                    break;
                }
                case "0":
                {
                    Cliente.Efetuar_Logout_();
                        
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
            
                opcao = ler_string.nextLine();
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
                    
                    Cliente.Efetuar_Login();
                    
                    Menu();
                    break;
                }
                case "4":
                {
                    Cliente.Efetuar_Login();
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
    
    static Random gera_num = new Random();
    static Scanner input = new Scanner(System.in);
    static Scanner ler_string = new Scanner(System.in);

    public static void main(String[] args)
    {
        Livraria livraria = new Livraria();
 
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
            
                        
            System.out.println("\n\t\t\t\tBem-vindo(a) a "+livraria.Nome+"!\n");
            Livro.Mostrar_catalogo();
                
            Menu();
                                    
    }
    
}
