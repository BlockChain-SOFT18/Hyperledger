package blockChainService.api;

public interface BlockChainService {
    String QueryTransaction(int recordId);
    String QueryBalanceChange(int recordId);
    boolean InsertTransaction(
            int recordId,
            int paymentInstitutionID,
            int paymentUserID,
            int collectionInstitutionId,
            int collectionUserID,
            String dateTime,
            boolean transactionType,
            double sum

    );
    boolean InsertBalanceChange(
            int recordId,
            int institutionID,
            int userId,
            boolean recordType,
            double sum
    );
}