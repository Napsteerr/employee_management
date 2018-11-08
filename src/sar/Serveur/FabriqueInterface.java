import java.rmi.*; 
public interface FabriqueInterface extends Remote{
   public EmployeInterface newEmploye() throws RemoteException ;	
}