import javax.swing.table.*;
import java.util.*;
import java.io.*;
public class Database extends AbstractTableModel {
    private ArrayList<Client> all = new ArrayList<Client>();
    private ArrayList<Client> subSet = new ArrayList<Client>();
    private ArrayList<Client> link;
    private File saveFile = null;
    private Timer saver = new Timer();
    private TimerTask saverTask = new TimerTask() {
            public void run() {
                if (isSaved()) {
                    save();
                }
            }
        };

    private Comparator<Client> sortNume = new Comparator<Client>() {
            public int compare(Client a1,Client a2) {
                return a1.getName().compareTo(a2.getName());
            }
        };

    private Comparator<Client> sortPrenume = new Comparator<Client>() {
            public int compare(Client a1,Client a2) {
                return a1.getSurname().compareTo(a2.getSurname());
            }
        };

    private Comparator<Client> sortPIN = new Comparator<Client>() {
            public int compare(Client a1,Client a2) {
                return a1.getPIN().compareTo(a2.getPIN());
            }
        };
    private Comparator<Client> sortIBAN = new Comparator<Client>() {
            public int compare(Client a1,Client a2) {
                return a1.getIBAN().compareTo(a2.getIBAN());
            }
        };

    public Database() {
        ArrayList<Client> temp = null;
        File address = null;
        try {
            File f = new File("last.dba");
            if (!f.exists()) f.createNewFile();
            else {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                address = (File)ois.readObject();
                ois.close();
                fis.close();
                if (address!=null) {
                    saveFile = address;
                    fis = new FileInputStream(address);
                    ois = new ObjectInputStream(fis);
                    temp = (ArrayList)ois.readObject();
                    ois.close();
                    fis.close();
                }
            }
        }
        catch(Exception e) {}
        if (temp!=null) all=temp;
        link = all;
        fireTableDataChanged();
        saver.schedule(saverTask,0,300000);
    }

    public boolean isSaved() {
        if (saveFile!=null) return true;
        else return false;
    }

    public void open(File f) {
        ArrayList<Client> temp = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            temp = (ArrayList)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e) {}
        if (temp!=null) {
            all = temp;
        }
        else {
            all = new ArrayList<Client>();
        }
        subSet = new ArrayList<Client>();
        set(true);
        saveFile = f;
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(all);
            oos.close();
            fos.close();
        }
        catch(Exception e) {}
    }

    public void save(File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(all);
            oos.close();
            fos.close();
        }
        catch(Exception e){}
        saveFile = f;
    }

    public void add(String nume,String prenume,String PIN,String IBAN) {
        int i;
        for (i=0;i<all.size();i++) {
            if (all.get(i).getPIN().equals(PIN)) throw new IllegalArgumentException("This client already exists!");
        }
        all.add(new Client(nume,prenume,PIN,IBAN));
        fireTableDataChanged();

    }

    public void delete(int row) {
        Client a = link.get(row);
        if (link == subSet) {
            Iterator i = all.iterator();
            while (i.hasNext()) {
                Client b = (Client)i.next();
                if (b == a) i.remove();
            }
        }
        else {
            Iterator i = subSet.iterator();
            while (i.hasNext()) {
                Client b = (Client)i.next();
                if (a == b) i.remove();
            }
        }
        link.remove(row);
        fireTableDataChanged();

    }

    public void set(boolean s) {
        if (s) link = all;
        else link = subSet;
        fireTableDataChanged();
    }

    public void search(String name,String surname,String PIN, String IBAN) {
        subSet = new ArrayList<Client>();
        Iterator i = all.iterator();
        while (i.hasNext()) {
            Client cl = (Client)i.next();
            int a = cl.getName().indexOf(name);
            int b = cl.getSurname().indexOf(surname);
            int c = cl.getPIN().indexOf(PIN);
            int d = cl.getIBAN().indexOf(IBAN);
            if (a!=-1 && b!=-1 && c!=-1 && d!=-1) subSet.add(cl);
        }
        set(false);
    }

    public void modify(String name,String surname,String PIN,String IBAN,int p) {
        Client a = link.get(p);
        a.setName(name);
        a.setSurname(surname);
        a.setPIN(PIN);
        a.setIBAN(IBAN);
        fireTableDataChanged();

    }

    public void sort(String choice) {
        switch(choice) {
            case("Nume"): Collections.sort(link,sortNume); break;
            case("Prenume"): Collections.sort(link,sortPrenume); break;
            case("PIN"): Collections.sort(link,sortPIN); break;
            case("IBAN"): Collections.sort(link,sortIBAN); break;
        }
        fireTableDataChanged();
    }

    public String getColumnName(int col) {
        switch(col) {
            case(0): return "Name";
            case(1): return "Surname";
            case(2): return "PIN";
            case(3): return "IBAN";
        }
        return "";
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return link.size();
    }

    public Object getValueAt(int row, int col) {
        switch(col) {
            case(0): return link.get(row).getName(); 
            case(1): return link.get(row).getSurname();
            case(2): return link.get(row).getPIN();
            case(3): return link.get(row).getIBAN(); 
        }
        return null;
    }

    public void last() {
        if (saveFile!=null) {
            try {
                File f = new File("last.dba");
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(saveFile);
                oos.close();
                fos.close();
            }
            catch(IOException e) {
                System.exit(0);
            }
        }
        else new File("last.dba").delete();
    }

    public void newData() {
        all = new ArrayList<Client>();
        subSet = new ArrayList<Client>();
        set(true);
        saveFile = null;
    }

    public String getSaveFilePath() {
        if (saveFile!=null) {
            return saveFile.getAbsolutePath();
        }
        return "Fisier nou";
    }

    public String toString() {
        return "Database";
    }

}