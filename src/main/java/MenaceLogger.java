import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenaceLogger {
    public void logMe(String message) {
        logger.info(message);
    }

    private static Logger logger = LogManager.getLogger(LogManager.class.getName());
}
