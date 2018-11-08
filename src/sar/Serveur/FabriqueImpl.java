import java.rmi.*;
import java.rmi.server.*;

public class FabriqueImpl extends UnicastRemoteObject implements FabriqueInterface{
   public FabriqueImpl()throws RemoteException {}; 
   public EmployeInterface newEmploye() throws RemoteException {       
   return new Employe();}

 
}

