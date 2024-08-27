
import our.own.Math;

public class TypedMvars {

    private java.util.logging.Logger utilLogger;
    private our.own.inhouse.Logger inhouseLogger;

    public void someExamples(double number, LogRecord r){

        Math m = new Math();

        // ok: typed-logger
        m.log(number); // not a logger

        // ruleid: typed-logger
        utilLogger.log(r);

        // ok: typed-logger
        inhouseLogger.log(r); // the correct logger

    }
}