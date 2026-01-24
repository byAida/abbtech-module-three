package com.example.task23.service;

import com.example.task23.entity.Payment;
import com.example.task23.entity.User;
import com.example.task23.exception.InsufficientBalanceException;
import com.example.task23.exception.UserNotFoundException;
import com.example.task23.repository.PaymentRepository;
import com.example.task23.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment createPayment(Long userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("İstifadəçi tapılmadı"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(amount);
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        BigDecimal newBalance = user.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("İstifadəçinin balansı kifayət etmir");
        }

        user.setBalance(newBalance);
        userRepository.save(user);

        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        return payment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Ödəniş tapılmadı"));
    }
}
