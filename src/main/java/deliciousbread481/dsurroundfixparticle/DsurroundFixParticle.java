package deliciousbread481.dsurroundfixparticle;  
  
import net.neoforged.fml.common.Mod;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
@Mod(DsurroundFixParticle.MOD_ID)  
public class DsurroundFixParticle {  
    public static final String MOD_ID = "dsurroundfixparticle";  
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);  
  
    public DsurroundFixParticle() {  
        LOGGER.info("DsurroundFixParticle loaded - ObjectArray.forEach null safety patch active");  
    }  
}