import javax.swing.table.*;
import java.util.*;
import java.io.*;
public class CarteDeTelefon extends AbstractTableModel {
    private ArrayList<Abonat> all = new ArrayList<Abonat>();
    private ArrayList<Abonat> subSet = new ArrayList<Abonat>();
    private ArrayList<Abonat> link;
    private File saveFile = null;
    private Timer saver = new Timer();
    private TimerTask saverTask = new TimerTask() {
            public void run() {
                if (isSaved()) {
                    save();
                }
            }
        };

    private Comparator<Abonat> sortNume = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getNume().compareTo(a2.getNume());
            }
        };

    private Comparator<Abonat> sortPrenume = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getPrenume().compareTo(a2.getPrenume());
            }
        };

    private Comparator<Abonat> sortCNP = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getCNP().compareTo(a2.getCNP());
            }
        };

    private Comparator<Abonat> sortFix = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getNrFix().compareTo(a2.getNrFix());
            }
        };

    private Comparator<Abonat> sortMob = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getNrMob().compareTo(a2.getNrMob());
            }
        };
    
 private Comparator<Abonat> sortAdresa = new Comparator<Abonat>() {
            public int compare(Abonat a1,Abonat a2) {
                return a1.getAdresa().compareTo(a2.getAdresa());
            }
        };

    public CarteDeTelefon() {
        ArrayList<Abonat> temp = null;
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
        ArrayList<Abonat> temp = null;
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
            all = new ArrayList<Abonat>();
        }
        subSet = new ArrayList<Abonat>();
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

    public void add(String nume,String prenume,String cnp,String nrFix,String nrMob,String adresa) {
        int i;
        for (i=0;i<all.size();i++) {
            if (all.get(i).getCNP().equals(cnp)) throw new IllegalArgumentException("Persoana deja exista!");
        }
        all.add(new Abonat(nume,prenume,cnp,nrFix,nrMob,adresa));
        fireTableDataChanged();

    }

    public void delete(int row) {
        Abonat a = link.get(row);
        if (link == subSet) {
            Iterator i = all.iterator();
            while (i.hasNext()) {
                Abonat b = (Abonat)i.next();
                if (b == a) i.remove();
            }
        }
        else {
            Iterator i = subSet.iterator();
            while (i.hasNext()) {
                Abonat b = (Abonat)i.next();
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

    public void search(String nume,String prenume,String cnp,String nrFix,String nrMob, String adresa) {
        subSet = new ArrayList<Abonat>();
        Iterator i = all.iterator();
        while (i.hasNext()) {
            Abonat ab = (Abonat)i.next();
            int a = ab.getNume().indexOf(nume);
            int b = ab.getPrenume().indexOf(prenume);
            int c = ab.getCNP().indexOf(cnp);
            int d = ab.getNrFix().indexOf(nrFix);
            int e = ab.getNrMob().indexOf(nrMob);
            int f = ab.getAdresa().indexOf(adresa);
            if (a!=-1&&b!=-1&&c!=-1&&d!=-1&&e!=-1&&f!=-1) subSet.add(ab);
        }
        set(false);
    }

    public void modify(String nume,String prenume,String cnp,String nrFix,String nrMob,int p,String adresa) {
        Abonat a = link.get(p);
        a.setNume(nume);
        a.setPrenume(prenume);
        a.setCNP(cnp);
        a.setNrFix(nrFix);
        a.setNrMob(nrMob);
        a.setAdresa(adresa);
        
        fireTableDataChanged();

    }

    public void sort(String choice) {
        switch(choice) {
            case("Nume"): Collections.sort(link,sortNume); break;
            case("Prenume"): Collections.sort(link,sortPrenume); break;
            case("CNP"): Collections.sort(link,sortCNP); break;
            case("Numar fix"): Collections.sort(link,sortFix); break;
            case("Numar mobil"): Collections.sort(link,sortMob); break;
            case("Adresa"): Collections.sort(link,sortAdresa); break;
        }
        fireTableDataChanged();
    }

    public String getColumnName(int col) {
        switch(col) {
            case(0): return "Nume";
            case(1): return "Prenume";
            case(2): return "CNP";
            case(3): return "Numar fix";
            case(4): return "Numar mobil";
            case(5): return "Adresa";
        }
        return "";
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return link.size();
    }

    public Object getValueAt(int row, int col) {
        switch(col) {
            case(0): return link.get(row).getNume(); 
            case(1): return link.get(row).getPrenume();
            case(2): return link.get(row).getCNP();
            case(3): return link.get(row).getNrFix(); 
            case(4): return link.get(row).getNrMob(); 
            case(5): return link.get(row).getAdresa();
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
        all = new ArrayList<Abonat>();
        subSet = new ArrayList<Abonat>();
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
        return "CarteDeTelefon";
    }

    void search(String text, String text0, String text1, String text2, String text3, String text4, String text5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 /*   void search(String text, String text0, String text1, String text2, String text3, String text4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    void add(String text, String text0, String text1, String text2, String text3, String text4, String text5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void modify(String text, String text0, String text1, String text2, String text3, int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}