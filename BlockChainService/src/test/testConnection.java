import blockChainService.api.BlockChainService;
import blockChainService.provider.BlockChainServiceImpl;
import blockChainService.provider.FabricManager;
import org.junit.Test;
public class testConnection {

    @Test
    public void mytest(){
        BlockChainService bcs = new BlockChainServiceImpl();
        bcs.InsertTransaction(1000,100,12,34,23,"2018",true,23.33);
        System.out.println(bcs.QueryTransaction(1000));

    }
}