package blockChainService.provider;

import blockChainService.api.BlockChainService;
import org.apache.log4j.Logger;
import skyvotfabricsdk.ChaincodeManager;



import java.util.Map;
public class BlockChainServiceImpl implements BlockChainService {

    private static String QUERY_TRA_FUNC="1";
    private static String QUERY_BC_FUNC="2";
    private static String INSERT_TRA_FUNC="3";
    private static String INSERT_BC_FUNC="4";

    private FabricManager fabricmanager;
    private  ChaincodeManager chaincodeManager;
    private static Logger log = Logger.getLogger(BlockChainService.class);

    public String QueryTransaction(int recordId)
    {
        return Query(recordId, QUERY_TRA_FUNC);

    }
    public String QueryBalanceChange(int recordId)
    {
        return Query(recordId, QUERY_BC_FUNC);
    }

    private String Query(int recordId, String queryFunc) {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[1];
            args[0]=Integer.toString(recordId);
            Map<String, String> resultMap = chaincodeManager.query(queryFunc,args);
            if(resultMap.get("code")=="success")
            {
                return resultMap.get("data");
            }
            else
            {
                return null;
            }
        }catch (Exception e)
        {
            log.debug(e.getMessage());
            return null;
        }
    }

    public boolean InsertTransaction(
            int recordId,
            int recId,
            int payId,
            double amount,
            boolean transactionType,
            String date)
    {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[6];
            args[0]=Integer.toString(recordId);
            args[1]=Integer.toString(recId);
            args[2]=Integer.toString(payId);
            args[3]=Double.toString(amount);
            args[4]=Boolean.toString(transactionType);
            args[5]=date;

            Map<String, String> resultMap = chaincodeManager.invoke(INSERT_TRA_FUNC,args);
            if(resultMap.get("code")=="success")
            {
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            log.debug(e.getMessage());
            return false;
        }
    };
    public boolean InsertBalanceChange(
            int recordId,
            int userId,
            double amount,
            boolean recordType)
    {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[4];
            args[0]=Integer.toString(recordId);
            args[1]=Integer.toString(userId);
            args[2]=Double.toString(amount);
            args[3]=Boolean.toString(recordType);

            Map<String, String> resultMap = chaincodeManager.invoke(INSERT_BC_FUNC,args);
            if(resultMap.get("code")=="success")
            {
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            log.debug(e.getMessage());
            return false;
        }

    };
}
