public class Client implements java.io.Serializable {

    String name,surname,PIN,IBAN;

    public Client(String name,String surname,String PIN,String IBAN) {
        setName(name);
        setSurname(surname);
        setPIN(PIN);
        setIBAN(IBAN);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPIN() {
        return PIN;
    }
    
    public String getIBAN() {
        return IBAN;
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
    
    private boolean isIBAN(String seq) {
        char[] arr = seq.toCharArray();
        int i;
        for (i=0;i<arr.length;i++) {
            char a = (char)arr[i];
            if ((a >= '0' && a <= '9') || (a >= 'A' && a <= 'Z')) continue;
            else return false;
        }
        return true;
    }

    public void setName(String name) {
        if (!isWord(name)||name.equals("")) throw new IllegalArgumentException("Invalid name!");
        String prov = String.valueOf(name.charAt(0)).toUpperCase();
        prov = prov.concat(name.substring(1));
        this.name = prov.trim();
    }

    public void setSurname(String surname) {
        if (!isWord(surname)||surname.equals("")) throw new IllegalArgumentException("Invalid surname!");
        String prov = String.valueOf(surname.charAt(0)).toUpperCase();
        prov = prov.concat(surname.substring(1));
        this.surname = prov.trim();
    }

    public void setPIN(String pin) {
        if (pin.length()!=13||!isNumber(pin)||(int)pin.charAt(0)-48<1||(int)pin.charAt(0)-48>6) throw new IllegalArgumentException("Invalid PIN");
        this.PIN = pin;
    }
    
    public void setIBAN(String iban) {
        if (!isIBAN(iban) || iban.equals("")) throw new IllegalArgumentException("Invalid IBAN");
        this.IBAN = iban;
    }

    public String toString() {
        return name+" "+surname;
    }

}