import blockChainService.api.BlockChainService;
import blockChainService.provider.BlockChainServiceImpl;
import blockChainService.provider.FabricManager;
import junit.framework.Test;
public class testConnection {
    @org.junit.Test
    public void mytest(){
        BlockChainService bcs = new BlockChainServiceImpl();
        System.out.println(bcs.QueryBalanceChange(231));
    }
}