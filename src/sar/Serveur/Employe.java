
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Employe extends UnicastRemoteObject implements EmployeInterface {

    public Employe() throws RemoteException {
        super();
    }

    public String AddEmploye(String institut, String nom, String prenom, String cin, String tel) throws RemoteException {

        String FILENAME = "C:\\Users\\Attia\\Desktop\\sar\\" + institut + ".txt";
        System.out.print(FILENAME);
        FileWriter fw = null;

      
        try {

            String content = "nom = " + nom + " ; prénom = " + prenom + " ; cin = " + cin + " ; téléphone = " + tel;
            

            fw = new FileWriter(FILENAME, true);
            fw.write(content + "\r\n");
            fw.close();
            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

            return "1";
        }
    }

    public Vector SearchEmploye(String institut, String cin) throws RemoteException {
        Vector<String> V = new Vector<String>();
        try {
            File f = new File("C:\\Users\\Attia\\Desktop\\sar\\" + institut + ".txt");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
            }

            String line = null;
            int i = -1;

            while ((line = reader.readLine()) != null) {
                i++;
                if (line.contains(cin)) {
                    System.out.println(line);
                    String[] parts = line.split(" ; ");
                    String nom1 = parts[0]; 
                    String prenom1 = parts[1];
                    String cin1 = parts[2];
                    String tel1 = parts[3];
                    String[] partn = nom1.split(" = ");
                    String[] partp = prenom1.split(" = ");
                    String[] partc = cin1.split(" = ");
                    String[] partt = tel1.split(" = ");
                    System.out.println(partn[1] + partp[1] + partc[1] + partt[1]);
                    System.out.println(i);

                    V.add(partn[1]);
                    V.add(partp[1]);
                    V.add(partc[1]);
                    V.add(partt[1]);
                    V.add(line);

                }

            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return V;
    }

    public String DeleteEmploye(String institut, String cin) throws RemoteException {
       
        try {
            String x = null;
            Vector<String> V = new Vector<>();
            V = SearchEmploye(institut, cin);
            String lineToRemove = V.elementAt(4);
            if (!V.isEmpty()) 
            {
                x="sucess";
                String ch = institut + ".txt";
                File inputFile = new File("C:\\Users\\Attia\\Desktop\\sar\\" + institut + ".txt");
                File tempFile = new File("C:\\Users\\Attia\\Desktop\\sar\\tempfile.txt");

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(inputFile));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(tempFile));
                } catch (IOException ex) {
                    Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
                }
                String currentLine;

                try {
                    while ((currentLine = reader.readLine()) != null) {
                        // trim newline when comparing with lineToRemove
                        String trimmedLine = currentLine.trim();
                        System.out.println("hello curent" + currentLine);

                        if (!trimmedLine.equals(lineToRemove)) {
                            System.out.println(currentLine);

                            writer.write(currentLine + System.getProperty("line.separator"));
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    reader.close();
                    inputFile.delete();
                    tempFile.renameTo(new File("C:\\Users\\Attia\\Desktop\\sar\\" + institut + ".txt"));

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

           return "0";
            }
  
            }
            catch(Exception ex) {
            System.out.println(ex.getMessage())  ;
        }
            
          return "1";   
        }
    
    
    
  
        public Vector SearchName(String institut, String nom) throws RemoteException {
        Vector<String> V = new Vector<String>();
        try {
            File f = new File("C:\\Users\\Attia\\Desktop\\sar\\" + institut + ".txt");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
            }

            String line = null;
            int i = -1;

            while ((line = reader.readLine()) != null) {
                i++;
                String ch="nom = "+nom+" ;";
                if (line.contains(ch)) {
                    System.out.println(line);
                    String[] splits = line.split(" ; ");
                    String nom1 = splits[0]; 
                    String prenom1 = splits[1];
                    String cin1 = splits[2];
                    String tel1 = splits[3];
                    String[] splitn = nom1.split(" = ");
                    String[] splitp = prenom1.split(" = ");
                    String[] splitc = cin1.split(" = ");
                    String[] splitt = tel1.split(" = ");
                    System.out.println(splitn[1] + splitp[1] + splitc[1] + splitt[1]);
                    System.out.println(i);

                    V.add(splitn[1]);
                    V.add(splitp[1]);
                    V.add(splitc[1]);
                    V.add(splitt[1]);
                    V.add(line);

                }

            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Employe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return V;
    }
        
            public String ModifEmploye(String institut,String cin,String nom,String prenom,String tel) throws RemoteException {
                String ch1=DeleteEmploye(institut,cin);
                String ch2=AddEmploye(institut,nom,prenom,cin,tel);
                return null;
                
            
            }
            

}
    
