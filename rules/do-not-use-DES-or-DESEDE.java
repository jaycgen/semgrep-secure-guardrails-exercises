import javax.crypto.Cipher;

class CryptoStuff {

    public void someCiphers(){

        // ruleid: do-not-use-DES-or-DESEDE
        Cipher c = Cipher.getInstance("DESede");
        // ruleid: do-not-use-DES-or-DESEDE
        Cipher c = Cipher.getInstance("DES");
        // ruleid: do-not-use-DES-or-DESEDE
        Cipher c = Cipher.getInstance("TripleDES");

        // ok: do-not-use-DES-or-DESEDE
        Cipher c = Cipher.getInstance("AES");

    }
}