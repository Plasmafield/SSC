public class Abonat implements java.io.Serializable {

    String nume,prenume,cnp,nrFix,nrMob;
    NrTel nrTel;

    public Abonat(String nume,String prenume,String cnp,String nrFix,String nrMob) {
        setNume(nume);
        setPrenume(prenume);
        setCNP(cnp);
        nrTel = new NrTel(nrFix,nrMob);
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCNP() {
        return cnp;
    }

    public String getNrFix() {
        return nrTel.getNrFix();
    }

    public String getNrMob() {
        return nrTel.getNrMob();
    }

    private boolean isWord(String seq) {
        char[] arr = seq.toCharArray();
        int i;
        for (i=0;i<arr.length;i++) {
            int a = (int)arr[i];
            if (a<65||(a>90&&a<97)||a>122) return false;
        }
        return true;
    }

    private boolean isNumber(String seq) {
        char[] arr = seq.toCharArray();
        int i;
        for (i=0;i<arr.length;i++) {
            int a = (int)arr[i];
            if (a<48||a>57) return false;
        }
        return true;
    }

    public void setNume(String nume) {
        if (!isWord(nume)||nume.equals("")) throw new IllegalArgumentException("Nume invalid!");
        String prov = String.valueOf(nume.charAt(0)).toUpperCase();
        prov = prov.concat(nume.substring(1));
        this.nume = prov.trim();
    }

    public void setPrenume(String prenume) {
        if (!isWord(prenume)||prenume.equals("")) throw new IllegalArgumentException("Prenume invalid!");
        String prov = String.valueOf(prenume.charAt(0)).toUpperCase();
        prov = prov.concat(prenume.substring(1));
        this.prenume = prov.trim();
    }

    public void setCNP(String cnp) {
        if (cnp.length()!=13||!isNumber(cnp)||(int)cnp.charAt(0)-48<1||(int)cnp.charAt(0)-48>6) throw new IllegalArgumentException("CNP invalid");
        this.cnp = cnp;
    }

    public void setNrFix(String nrFix) {
        nrTel.setNrFix(nrFix);
    }

    public void setNrMob(String nrMob) {
        nrTel.setNrMob(nrMob);
    }

    public String toString() {
        return nume+" "+prenume;
    }

}