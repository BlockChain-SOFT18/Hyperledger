package blockChainService.provider;

import blockChainService.api.BlockChainService;
import org.apache.log4j.Logger;
import skyvotfabricsdk.ChaincodeManager;



import java.util.Map;
public class BlockChainServiceImpl implements BlockChainService {

    private static String QUERY_TRA_FUNC="queryTransaction";
    private static String QUERY_BC_FUNC="queryBalanceChange";
    private static String INSERT_TRA_FUNC="createTransaction";
    private static String INSERT_BC_FUNC="createBalanceChange";

    private FabricManager fabricmanager;
    private  ChaincodeManager chaincodeManager;
    // private static Logger log = Logger.getLogger(BlockChainService.class);

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
            //log.debug(e.getMessage());
            return null;
        }
    }

    public boolean InsertTransaction(
            int recordId,
            int paymentInstitutionId,
            int paymentUserId,
            int collectionInstitutionId,
            int collectionUserId,
            String dateTime,
            boolean transactionType,
            double sum)
    {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[8];
            args[0]=Integer.toString(recordId);
            args[1]=Integer.toString(paymentInstitutionId);
            args[2]=Integer.toString(paymentUserId);
            args[3]=Integer.toString(collectionInstitutionId);
            args[4]=Integer.toString(collectionUserId);
            args[5]=dateTime;
            args[6]=Boolean.toString(transactionType);
            args[7]=Double.toString(sum);

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
            //    log.debug(e.getMessage());
            return false;
        }
    };
    public boolean InsertBalanceChange(
            int recordId,
            int institutionId,
            int userId,
            String dateTime,
            boolean recordType,
            double sum)
    {
        try{
            if(fabricmanager==null)fabricmanager= FabricManager.obtain();
            if(chaincodeManager==null)chaincodeManager=fabricmanager.getManager();
            String args[]=new String[6];
            args[0]=Integer.toString(recordId);
            args[1]=Integer.toString(institutionId);
            args[2]=Integer.toString(userId);
            args[3]=dateTime;
            args[4]=Boolean.toString(recordType);
            args[5]=Double.toString(sum);

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
            //   log.debug(e.getMessage());
            return false;
        }

    };
}