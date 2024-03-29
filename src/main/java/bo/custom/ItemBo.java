package bo.custom;

import bo.SuperBo;
import dto.ItemDto;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String itmCode) throws SQLException, ClassNotFoundException;

    List<ItemDto>getAll() throws SQLException, ClassNotFoundException;
}
