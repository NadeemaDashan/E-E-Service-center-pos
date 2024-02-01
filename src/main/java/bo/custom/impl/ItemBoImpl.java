package bo.custom.impl;

import bo.custom.ItemBo;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = new ItemDaoImpl();
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(dto.getCode(), dto.getName(),
                dto.getCategory(),
                dto.getStatus(),
                dto.getContact()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(dto.getCode(),
                dto.getName(),
                dto.getCategory(),
                dto.getStatus(),
                dto.getContact()));
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDao.delete(itemCode);
    }

    @Override
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        List<Item> allItems =itemDao.getAll();
        ArrayList arrayList = new ArrayList();
        for (Item item:allItems){
            arrayList.add(new ItemDto(item.getCode(),item.getName(),
                    item.getCategory(),item.getStatus(),
                    item.getContact()));
        }
        return arrayList;
    }
}
