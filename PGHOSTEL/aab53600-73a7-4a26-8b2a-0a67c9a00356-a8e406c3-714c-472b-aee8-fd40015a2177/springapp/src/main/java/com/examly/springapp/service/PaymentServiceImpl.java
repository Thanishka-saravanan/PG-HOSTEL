package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Payment;
import com.examly.springapp.repository.PaymentRepo;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepo.findById(id).orElse(null);
    }

    @Override
    public Payment updatePayment(Integer id, Payment updatedPayment) {
        Payment payment = paymentRepo.findById(id).orElse(null);
        if (payment == null || updatedPayment == null) return null;

        payment.setAmount(updatedPayment.getAmount());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        payment.setBookingId(updatedPayment.getBookingId());

        return paymentRepo.save(payment);
    }

    @Override
    public void deletePaymentById(Integer id) {
        if (paymentRepo.existsById(id)) {
            paymentRepo.deleteById(id);
        }
    }
}