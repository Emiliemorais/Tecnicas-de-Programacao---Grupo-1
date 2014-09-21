package livraria_abc;

import java.util.*;
import java.io.*;

public class Payment 
{
    private int paymentCode; // Receives the payment code
    private Date paymentDate; // Receives the payment date
    private float paymentValue; // Receives the payment value
	
    // Payment code getter
    public int getPaymentCode() 
    {
	return paymentCode;
    }
    
    // Payment code setter
    public void setPaymentCode(int paymentCode) 
    {
        this.paymentCode = paymentCode;
    }
    
    // Payment date getter
    public Date getPaymentDate() 
    {
        return paymentDate;
    }
    
    // Payment date setter
    public void setPaymentDate(Date paymentDate) 
    {
	this.paymentDate = paymentDate;
    }
    
    // Payment value getter
    public float getPaymentValue() 
    {
	return paymentValue;
    }

    // Payment value setter
    public void setPaymentValue(float paymentValue) 
    {
	this.paymentValue = paymentValue;
    }
       
    /*
     * Payment constructor
     * @param paymentCode - Receives the payment code
     * @param paymentDate - Receives the payment date
     * @param paymentValue - Receives the payment value
     */
    public Payment(int paymentCode, Date paymentDate, float paymentValue) 
    {
	super();
	this.paymentCode = paymentCode;
	this.paymentDate = paymentDate;
	this.paymentValue = paymentValue;
    }
	
    
    File exitFile = new File("Pagamento.txt");       
    // exitFile - File containing the output of the software
	
    Scanner scan = new Scanner(System.in);
    // scan - Object that will receive data
    
    
    ArrayList<Integer> listCode = new ArrayList<>(); // List containing the codes
    ArrayList<Date> listDate = new ArrayList<>(); // List containing the dates
    ArrayList<Float> listPayment = new ArrayList<>(); // List containing the payments
	
    // Method used to add a new payment
    public void addPayment()
    {	
	String option; // Receives data from the "scan" object
        String integerToString; // Converts integer values to strings
        String floatToString; // Converts float values to strings
	int paymentCode; // Receives the payment code
        int i; // Used for control in the for
	
	do{	
		    Date currentDate = new Date();	
                    // currentDate - Instance of "Date" class
                    
		    setPaymentDate(currentDate);
		    paymentCode = (int)(Math.random()*1000000000);     
		    	
		    System.out.println("Digite o valor do pagamento: ");	 
		    paymentValue = scan.nextFloat();
		    
		    listCode.add(paymentCode);
		    listDate.add(getPaymentDate());
		    listPayment.add(paymentValue);
		    
		    System.out.println("Deseja cadastrar outro produto? Digite nao caso esteja satisfeito");
		    
		    option = scan.nextLine();
	    
	}while(!"nao".equals(option = scan.nextLine()));	
	
		try 
                {
			if(exitFile.exists() == false) 
                        {
                            exitFile.createNewFile();
			}
			
			FileWriter fileWriterInstance = new FileWriter(exitFile, true);
                        // fileWriterInstance - Instance of "FileWriter"
                        
			BufferedWriter bufferedWriterInstance = new BufferedWriter(fileWriterInstance);
                        // bufferedWriterInstance - Instance of "BufferedWriter"
						
			for(i=0; i<listPayment.size(); i++) 
                        {
				integerToString = Integer.toString(listCode.get(i));
				floatToString = Float.toString(listPayment.get(i));
				
				bufferedWriterInstance.newLine();
				bufferedWriterInstance.write("Codigo: " + integerToString);
				
				bufferedWriterInstance.write(" Valor: " + floatToString);
				bufferedWriterInstance.newLine();
			}
			
			bufferedWriterInstance.close();
			fileWriterInstance.close();
			
		} 
                catch (Exception e) 
                {
			
		}	
    }
	
    // Method used to edit a payment
    public void editPayment() 
    {
		
		int index; // Collects the index number, from the user, to be edited
                int i; // Used for control in the if
		float correctValue; // Receives the correct value of the payment
		String converter; // Converts float values to strings
                String integerToString; // Converts integer values to strings
                String floatToString; // Converts float values to strings
		
		System.out.println("Codigo: " + listCode.toString());
		System.out.println("Valor do pagamento: " + listPayment.toString());
		
		System.out.println("Digite o indice do pagamento que se deseja alterar <0 em diante>: ");
		index = scan.nextInt();
		
		System.out.println("Digite o valor correto do pagamento: ");
		correctValue = scan.nextFloat();
		
		converter = Float.toString(correctValue);		
		
		listPayment.add(index, correctValue);;
		listPayment.remove(index +1);
		
		System.out.println("Pagamento :" + listPayment.toString());
		System.out.println("Data da compra: " + listDate.toString());
		System.out.println("Codigo do produto: " + listCode.toString());
		
		try 
                {			
			
			FileWriter fileWriterInstance = new FileWriter(exitFile, true);
                        // fileWriterInstance - Instance of "FileWriter"
                        
			BufferedWriter bufferedWriterInstance = new BufferedWriter(fileWriterInstance);
                        // bufferedWriterInstance - Instance of "BufferedWriter"
						
			for(i=0; i<listPayment.size(); i++) 
                        {
				if(i == index) 
                                {
                                    integerToString = Integer.toString(listCode.get(i));
                                    floatToString = Float.toString(listPayment.get(i));
								
					bufferedWriterInstance.newLine();
					bufferedWriterInstance.write("Codigo: " + integerToString);
				
					bufferedWriterInstance.write(" Valor: " + floatToString);
				}
			}
			
			bufferedWriterInstance.close();
			fileWriterInstance.close();
			
		} 
                catch (Exception e) 
                {
			
		}		
    }
	
    // Method used to consult a payment
    public void consultPayment() 
    {
		String readString;
				
		try 
                {
					
			if(exitFile.exists() == false) 
                        {
                            exitFile.createNewFile();
			}
						
			FileReader fileReaderInstance = new FileReader(exitFile);
                        // fileReaderInstance - Instance of "FileReader"
                        
			BufferedReader bufferedReaderInstance = new BufferedReader(fileReaderInstance);
                        // bufferedReaderInstance - Instance of "BufferedReader"
	
			readString = bufferedReaderInstance.readLine();
			
			while(readString != null) 
                        { 		
			    System.out.println(readString);
                            
			    readString = bufferedReaderInstance.readLine();				
			}
				
			bufferedReaderInstance.close();
			fileReaderInstance.close();
			
		} 
                catch (Exception e) 
                {
			
		}			
    }
	
    // Method used to delete a payment
    public void deletePayment()
    {
		String readString; // Reads the line to be deleted
		int i = 0; // Used for control in the for
                int lineNumber; // Receives the number of the line to be deleted
		
		ArrayList<String> listDelete = new ArrayList<String>(); // List to save the archive data
		
		try 
                {
			if(exitFile.exists() == false) 
                        {
                            exitFile.createNewFile();
			}			
			
			FileReader fileReaderInstance = new FileReader(exitFile);
                        // fileReaderInstance - Instance of "FileReader"
                        
			BufferedReader bufferedReaderInstance = new BufferedReader(fileReaderInstance);
                        // bufferedReaderInstance - Instance of "BufferedReader"
			
                        
			readString = bufferedReaderInstance.readLine();
			
			System.out.println("\nEscolha o numero da linha que deseja excluir: ");
			
			while(readString != null) 
                        {			
				listDelete.add(readString);
				System.out.println(i + " " +readString);
				readString = bufferedReaderInstance.readLine();				
				i++;
			}
			
			bufferedReaderInstance.close();
			fileReaderInstance.close();
			
			lineNumber = scan.nextInt();
			
			listDelete.remove(lineNumber);
			
		    FileWriter fileWriterInstance = new FileWriter(exitFile);	
                    // fileWriterInstance - Instance of "FileWriter"

		    BufferedWriter bufferedWriterInstance = new BufferedWriter(fileWriterInstance);
                    // bufferedWriterInstance - Instance of "BufferedWriter"

		    for(i=0; i<listDelete.size(); i++) 
                    {		    
		    	bufferedWriterInstance.write(listDelete.get(i));
		    	bufferedWriterInstance.newLine();
		    }
		    
		    bufferedWriterInstance.close();
		    fileWriterInstance.close();
			
		} 
                catch (Exception e) 
                {
			
		}		
    }		
}
