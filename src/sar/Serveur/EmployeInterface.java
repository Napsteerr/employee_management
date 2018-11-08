
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
public interface EmployeInterface extends Remote 
{
	String AddEmploye(String inst,String nom,String prenom,String cin,String tel) throws RemoteException;
        String DeleteEmploye(String inst, String cin)throws RemoteException;
        Vector SearchEmploye(String inst, String cin)throws RemoteException;
        Vector SearchName(String inst, String nom)throws RemoteException;
        String ModifEmploye(String institut,String cin,String nom,String prenom,String tel) throws RemoteException;

        
}
