package com.examly.springapp.service;

import java.util.List;


import com.examly.springapp.model.Payment;


public interface PaymentService {

    Payment createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Integer id);

    Payment updatePayment(Integer id, Payment payment);

    void deletePaymentById(Integer id);
}