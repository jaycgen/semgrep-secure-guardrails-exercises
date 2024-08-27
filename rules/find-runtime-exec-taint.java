import java.lang.Runtime;

public class RuntimeExample {

    public void bad(HttpServletRequest req){
        String cmd = req.getParam("cmd");

        Runtime rt = Runtime.getRuntime();
        //ruleid: find-runtime-exec-taint
        rt.exec(cmd);
    }

    public void ok(HttpServletRequest req){
        String cmd = req.getParam("cmd");

        Other rt = new Other();
        //ok: find-runtime-exec-taint
        rt.exec(cmd);
    }

    public void ok2(HttpServletRequest req, String cmd){
        Runtime rt = Runtime.getRuntime();
        //ok: find-runtime-exec-taint
        rt.exec(cmd);
    }

    public void ok3(HttpServletRequest req, String cmd){
        String cmd = req.getSafeParam("cmd");
        
        Runtime rt = Runtime.getRuntime();
        //ok: find-runtime-exec-taint
        rt.exec(cmd);
    }

    public void ok4(HttpServletRequest req){
        String cmd = "cat local/file | wc -l "
        int count = Integer.parseInt(req.getParam("lineCount"));

        cmd += count;

        Runtime rt = Runtime.getRuntime();
        //ok: find-runtime-exec-taint
        rt.exec(cmd);
    }

}

class Other {
    public Other() { 
    }

    public void exec(String str) {
        System.out.println(str);
    }    
}