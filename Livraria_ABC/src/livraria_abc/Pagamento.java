package livraria_abc;

import java.util.*;
import java.io.*;

public class Pagamento 
{
    //Atributos
    private int cod_pag;
    private Date data_pag;
    private float valor_pag;
	
    //Métodos getters e setters
    public int getCod_pag() {
		return cod_pag;
	}

    public void setCod_pag(int cod_pag) {
		this.cod_pag = cod_pag;
	}
    
    public Date getData_pag() {
		return data_pag;
	}

    public void setData_pag(Date data_pag) {
		this.data_pag = data_pag;
	}

    public float getValor_pag() {
		return valor_pag;
	}

    public void setValor_pag(float valor_pag) {
		this.valor_pag = valor_pag;
	}
       
    //Construtor
    public Pagamento(int cod_pag, Date data_pag, float valor_pag) {
		super();
		this.cod_pag = cod_pag;
		this.data_pag = data_pag;
		this.valor_pag = valor_pag;
	}
	
    //Criação do arquivo Pagamento.txt
    File arquivo = new File("Pagamento.txt");          
	
    //Criação do objeto para entrada de dados
    Scanner scan = new Scanner(System.in);
	
    //Criação dos ArrayLists do tipo inteiro, float e Date
    ArrayList<Integer> codigo = new ArrayList<>();
    ArrayList<Date> data = new ArrayList<>();
    ArrayList<Float> pagamento = new ArrayList<>();
	
    //Criação do metodo cadastrar pagamento
    public void cadastrar_pag()
    {	
	 //Variaveis de auxilio do codigo	
	 String opcao, aux1, aux2;   
	 int cod_pag, i;
	
	do{	
			
		    Date dataAtual = new Date();		
		    setData_pag(dataAtual);
		    cod_pag = (int)(Math.random()*1000000000);                //Geração do código inteiro do produto
		    	
		    System.out.println("Digite o valor do pagamento: ");	 
		    valor_pag = scan.nextFloat();
		    
		    //Metodo para adicionar os valores no ArrayList
		    codigo.add(cod_pag);
		    data.add(getData_pag());
		    pagamento.add(valor_pag);
		    
		    System.out.println("Deseja cadastrar outro produto? Digite nao caso esteja satisfeito");
		    
		    opcao = scan.nextLine();
	    
	}while(!"nao".equals(opcao = scan.nextLine()));	
	
		//Tratamento de exceção para a criação do arquivo
		try {
		
			//Se não exitir o arquivo, é criado a partir desse ponto
			if(arquivo.exists() == false) {
			
				arquivo.createNewFile();
			}
			
			//Criação dos objetos para a escrita no arquivo
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
						
			for(i=0; i<pagamento.size(); i++) {
				
				aux1 = Integer.toString(codigo.get(i));
				aux2 = Float.toString(pagamento.get(i));
				
				//Escrita do codigo no arquivo
				bw.newLine();
				bw.write("Codigo: " + aux1);
				
				//Escrita do valor no arquivo
				bw.write(" Valor: " + aux2);
				bw.newLine();
			}
			
			//Fechamento do objeto para salvação dos dados no arquivo
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			
		}	
    }
	
    //Alterar os dados do pagamento presente no ArrayList
    public void alterar_pag() 
    {
		
		//Variaveis auxiliares
		int indice, i;
		float valor;
		String converter, aux1, aux2;
		
		//Impressão na tela dos valores disponiveis para alteração presente no respectivo ArrayList
		System.out.println("Codigo: " + codigo.toString());
		System.out.println("Valor do pagamento: " + pagamento.toString());
		
		System.out.println("Digite o indice do pagamento que se deseja alterar <0 em diante>: ");
		indice = scan.nextInt();
		
		System.out.println("Digite o valor correto do pagamento: ");
		valor = scan.nextFloat();
		
		converter = Float.toString(valor);		
		
		pagamento.add(indice, valor);;
		pagamento.remove(indice +1);
		
		System.out.println("Pagamento :" + pagamento.toString());
		System.out.println("Data da compra: " + data.toString());
		System.out.println("Codigo do produto: " + codigo.toString());
		
		try {			
			
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
						
			for(i=0; i<pagamento.size(); i++) {
				
				if(i == indice) {
				
				aux1 = Integer.toString(codigo.get(i));
				aux2 = Float.toString(pagamento.get(i));
								
					bw.newLine();
					bw.write("Codigo: " + aux1);
				
					bw.write(" Valor: " + aux2);
				}
			}
			
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			
		}
				
    }
	
    //Consultar os valores referentes aos pagamentos 
    public void consultar_pag() 
    {

		String lerLinha;
				
		try {
					
			if(arquivo.exists() == false) {
				
				arquivo.createNewFile();
			}
						
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
	
			//Ler a linha do arquivo
			lerLinha = br.readLine();
			
			while(lerLinha != null) { 	
				
				//Imprime na tela a linha do arquivo
			    System.out.println(lerLinha);		
			    
			    //Ler a proxima linha
				lerLinha = br.readLine();				
			}
				
			br.close();
			fr.close();
			
		} catch (Exception e) {
			
		}			
		
    }
	
    //Excluir os dados de um pagamento no arquivo
    public void excluir_pag()
    {
		String lerLinha;
		int i=0, numeroLinha;
		
		//Criação de novo ArrayList para armazenamento dos dados do arquivo
		ArrayList<String> exclusao = new ArrayList<String>();
		
		try {
		
			if(arquivo.exists() == false) {
				
				arquivo.createNewFile();
			}			
			
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			lerLinha = br.readLine();
			
			System.out.println("\nEscolha o numero da linha que deseja excluir: ");
			
			while(lerLinha != null) {
								
				exclusao.add(lerLinha);
				System.out.println(i + " " +lerLinha);
				lerLinha = br.readLine();				
				i++;
			}
			
			br.close();
			fr.close();
			
			numeroLinha = scan.nextInt();
			
			exclusao.remove(numeroLinha);
			
		    FileWriter fw = new FileWriter(arquivo);	
		    BufferedWriter bw = new BufferedWriter(fw);
			
		    for(i=0; i<exclusao.size(); i++) {		    
		    	bw.write(exclusao.get(i));
		    	bw.newLine();
		    }
		    
		    bw.close();
		    fw.close();
			
		} catch (Exception e) {
			
		}		
		
    }		
}
