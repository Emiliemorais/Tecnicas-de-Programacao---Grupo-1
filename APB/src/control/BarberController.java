
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.BarbeiroDAO;
import model.Barbeiro;


public class BarberController
{
	// Stores the current instance of the class
	private static BarberController instance;
	
	// Class constructor
	private BarberController()
	{
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static BarberController getInstance()
	{
		if (instance == null)
        {
            instance = new BarberController();
        }
        else
        {
            // Nothing to do
        }
		return instance;
	}

	/* Method that includes a new barber on DB
	 * Parameter: barberToInclude -  Barber to be included on DB
	 */
	public boolean includeBarber(Barbeiro barberToInclude) throws SQLException
	{
		if (barberToInclude == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		BarbeiroDAO.getInstance().incluir(barberToInclude);
		return true;
	}


	/* Method that modify a barber registered on DB
	 * Parameter: barberToChangeName - String that contains the barber name that will be replaced
	 * Parameter: newBarber - Barber that will replace the old one (where 'barberToChangeName' on DB)
	 */
	public boolean alterar(String barberToChangeName, Barbeiro newBarber) throws SQLException
	{
		if (newBarber == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		// Check use - Probably can be deleted
		Barbeiro changedBarber = newBarber;
		
		BarbeiroDAO.getInstance().alterar(barberToChangeName, changedBarber, newBarber);
		return true;
	}


	/* Method that delete a barber registered on DB
	 * Parameter: barberToDelete - Barber to be deleted
	 */
	public boolean deleteBarber(Barbeiro barberToDelete) throws SQLException
	{
		if (barberToDelete == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		BarbeiroDAO.getInstance().excluir(barberToDelete);
		return true;
	}


	// Method that return all barber table(on BarberDAO) - Check the need of this method
	public ResultSet searchBarbers() throws SQLException
	{
		return BarbeiroDAO.getInstance().pesquisar();
	}


	/* Method that return all registered barbers on DB
	 * Parameter: barber - Check the need of this parameter. Never used, should be deleted.
	 */
	public ResultSet showRegisteredBarbers(Barbeiro barber) throws SQLException
	{
		return BarbeiroDAO.getInstance().mostrarBarbeirosCadastrados(barber);
	}
	
	
	/* Method that search for a specific barber on DB
	 * Parameter: barberToSearchFor - Barber to search for on DB
	 */
	public ResultSet searchBarberByName(Barbeiro barberToSearchFor) throws SQLException
	{
		return BarbeiroDAO.getInstance().pesquisarPorNome(barberToSearchFor);
	}

}
