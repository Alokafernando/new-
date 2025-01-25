package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.PaymentBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.PaymentDAO;
import com.business.project.project03.dao.custom.impl.PaymentDAOImpl;
import com.business.project.project03.entity.Payment;
import com.business.project.project03.model.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        ArrayList<Payment> payments = paymentDAO.getAll();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(payment.getReservation_id(), payment.getPay_id(), payment.getPayment_method(), payment.getDeposite(), payment.getAmount(), payment.getRemain_amount(), payment.getStatus()));
        }
        return paymentDTOS;
    }

    @Override
    public void save(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        paymentDAO.save(new Payment(payment.getReservation_id(), payment.getPay_id(), payment.getPayment_method(), payment.getDeposite(), payment.getAmount(), payment.getRemain_amount(), payment.getStatus()));
    }

    @Override
    public void update(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        paymentDAO.update(new Payment(payment.getReservation_id(), payment.getPay_id(), payment.getPayment_method(), payment.getDeposite(), payment.getAmount(), payment.getRemain_amount(), payment.getStatus()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        paymentDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        paymentDAO.generateReport();
    }
}
