package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.PaymentDAO;
import com.business.project.project03.entity.Payment;
import com.business.project.project03.model.PaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from payment");
        ArrayList<Payment> paymentDTOS = new ArrayList<>();
        while (rst.next()) {
            paymentDTOS.add(new Payment(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getDouble( 5), rst.getDouble(6), rst.getString(7)));
        }
        return paymentDTOS;

    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into payment values (?, ?, ?, ?, ?, ?, ?)",
                entity.getReservation_id(), entity.getPay_id(), entity.getPayment_method(), entity.getDeposite(), entity.getAmount(), entity.getRemain_amount(), entity.getStatus());
    }

    @Override
    public void update(Payment entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update payment set reservation_id=?,  pay_method=?, deposite=?, amount=?, remain_amount=?, status=? where pay_id=?",
                entity.getReservation_id(), entity.getPayment_method(), entity.getDeposite(), entity.getAmount(), entity.getRemain_amount(), entity.getStatus(), entity.getPay_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from payment where pay_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select pay_id from payment order by pay_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("P%03d", newIndex);
        }
        return "P001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
    // empty
    }
}
