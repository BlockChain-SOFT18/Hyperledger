package blockChainService.api;

public interface BlockChainService {
    String QueryTransaction(int recordId);
    String QueryBalanceChange(int recordId);
    boolean InsertTransaction(
            int recordId,
            int recId,
            int payId,
            double amount,
            boolean transactionType,
            String date
    );
    boolean InsertBalanceChange(
            int recordId,
            int userId,
            double amount,
            boolean recordType
    );
}