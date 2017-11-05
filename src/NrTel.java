public class NrTel implements java.io.Serializable {

    String nrFix,nrMob;

    public NrTel(String nrFix,String nrMob) {
        setNrFix(nrFix);
        setNrMob(nrMob);
    }

    public String getNrFix() {
        return nrFix;
    }

    public String getNrMob() {
        return nrMob;
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

    public void setNrFix(String nrFix) {
        if (!isNumber(nrFix)||nrFix.equals("")) throw new IllegalArgumentException("Numar fix invalid!");
        this.nrFix = nrFix.trim();
    }

    public void setNrMob(String nrMob) {
        if (!isNumber(nrMob)||nrMob.length()!=10) throw new IllegalArgumentException("Numar mobil invalid!");
        this.nrMob = nrMob;
    }

    public String toString() {
        return nrFix+" "+nrMob;
    }

}