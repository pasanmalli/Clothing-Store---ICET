package controller.supplier;

import javafx.collections.ObservableList;
import model.Supplier;

public interface SupplierService {

    boolean addSupplier(Supplier supplier);

    ObservableList<Supplier> getSupplier();

    boolean updateSupplier(Supplier supplier);

    boolean deleteSupplier(String id);

    Supplier searchSupplier(String id);




}
