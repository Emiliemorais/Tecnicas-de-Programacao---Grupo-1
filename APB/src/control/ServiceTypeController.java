
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.TipoServicoDAO;
import model.TipoServico;

public class ServiceTypeController
{
	// Stores the current instance of the class
	private static ServiceTypeController instance;

	/* Method that includes a new service type
	 * Parameter: serviceTypeToInclude - Service type to be included
	 */
	public boolean includeServiceType (TipoServico serviceTypeToInclude) throws SQLException
	{
		if( serviceTypeToInclude == null )
        {
			return false;
		}
		else
        {
			TipoServicoDAO.getInstance ().incluir(serviceTypeToInclude);
			return true;
		}
	}


	/* Method that modify a service type on the system
	 * Parameter: serviceTypeToNoChangeName - Contains the name of the service type to change
	 * Parameter: newServiceType - New service type that will replace the old one on DB
	 */
	public boolean modifyServiceType ( String serviceTypeToChangeName,
									   TipoServico newServiceType ) throws SQLException
	{
		if ( newServiceType == null )
        {
			return false;
		}
		else
        {
			// Auxiliar variable used to change the service type
			TipoServico changedServiceType = newServiceType;
			
			TipoServicoDAO.getInstance().alterar (serviceTypeToChangeName, changedServiceType,
												  newServiceType);
			return true;
		}
	}
	

	/* Method that delete a service type on the system
	 * Parameter: serviceTypeToDelete - Contains the service type to be deleted
	 */
	public boolean deleteServiceType (TipoServico serviceTypeToDelete) throws SQLException
	{
		if ( serviceTypeToDelete == null )
        {
			return false;
		}
		else
        {
			TipoServicoDAO.getInstance().excluir(serviceTypeToDelete);
			return true;
		}
	}

	// Class constructor
	private ServiceTypeController () 
	{
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static ServiceTypeController getInstance ()
	{
		if ( instance == null )
        {
            instance = new ServiceTypeController();
        }
        else
        {
            // Nothing to do
        }
		return instance;
	}


	/* Return a ResultSet interface object with the service types registered on the system
	 * Parameter: service - Never usede ahead, Check need.
	 */
	public ResultSet showRegistredServiceTypes (TipoServico service) throws SQLException
	{
		return TipoServicoDAO.getInstance().mostrarTipoServicoCadastrados(service);
	}


	/* Search for an specific service type name
	 * Parameter: serviceTypeToSearch - Service type to search for
	 */
	public ResultSet searchServiceTypeByName (TipoServico serviceTypeToSearch) throws SQLException
	{
		return TipoServicoDAO.getInstance().pesquisarPorNome(serviceTypeToSearch);
	}

}
