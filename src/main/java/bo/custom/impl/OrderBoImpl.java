package bo.custom.impl;

import bo.custom.OrderBo;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public boolean saveOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDao.save(new Orders(orderDto.getOrderId(),orderDto.getCustomerCode(),orderDto.getItemCode(),orderDto.getFault(),orderDto.getPrice()));
    }
}
