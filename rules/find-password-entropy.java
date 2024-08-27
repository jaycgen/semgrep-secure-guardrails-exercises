
public class TypedMvars {

    public void unitTest(){
        
        // ok: find-password-entropy
        String password = "this-is-a-placeholder";
        Server.connect(password);

        // ruleid: find-password-entropy
        String password = "sdfDSSDFF14KLijelkj6666$lij*ds";
        Server.connect(password);

    }
}