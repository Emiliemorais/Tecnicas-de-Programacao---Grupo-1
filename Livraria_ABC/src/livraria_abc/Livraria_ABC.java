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
    
    // Armazena o codigo da compra gerado pelo método 'Fechar_cesta()'
    static int COD_compra = -1;
    
    // Armazena o endereço para a entrega do produto
    static String end_compra = "blank";
    
    // Armazena o tipo de pagamento escolhido pelo cliente
    static String tipo_pag = "blank";
    
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
            
            Livro livro = new Livro();
            Cliente cliente = new Cliente();
            switch(opcao)
            {
                case "1":
                {
                    livro.Mostrar_catalogo();
                    break;
                }
                case "2":
                {
                    livro.Consultar_livro();
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
                    cliente.efetuarLogout();
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
                    cliente.menuCliente();
                    break;
                }
                case "0":
                {
                    cliente.efetuarLogout_();
                        
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
            
               
            Livro livro = new Livro();
            Cliente cliente = new Cliente();
            switch(opcao)
            {
                case "1":
                {
                    livro.Mostrar_catalogo();
                    break;
                }
                case "2":
                {
                    livro.Consultar_livro();
                    break;
                }
                case "3":
                {
                    System.out.println("\nÉ necessário fazer login primeiro.");
                    
                    cliente.efetuarLogin();
                    
                    Menu();
                    break;
                }
                case "4":
                {
                    cliente.efetuarLogin();
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
            
                        
        System.out.println("\n\t\t\t\tBem-vindo(a) a " + livraria.nome + "!\n");
        Livro.Mostrar_catalogo();
                
        Menu();           
    }
}