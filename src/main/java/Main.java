import api.ApiApliccation;
import controller.Change;
import controller.Create;
import database.PostgreClient;
import database.repository.AccountRepository;
import database.repository.CategoryRepository;
import database.repository.TransactionRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        PostgreClient postgresql = new PostgreClient();

        AccountRepository account_repository = new AccountRepository(postgresql);
        CategoryRepository category_repository = new CategoryRepository(postgresql, account_repository);
        TransactionRepository transaction_repository = new TransactionRepository(postgresql, account_repository, category_repository);

        Create create = new Create(account_repository, category_repository, transaction_repository);
        Change change = new Change(account_repository, category_repository, transaction_repository);

        ApiApliccation api = new ApiApliccation(create, change, account_repository, category_repository, transaction_repository);

        api.run();

    }
}