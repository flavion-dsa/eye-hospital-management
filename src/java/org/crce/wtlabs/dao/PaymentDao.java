/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import java.util.List;
import org.crce.wtlabs.dto.Payment;

/**
 *
 * @author Flav
 */
public interface PaymentDao {
    public void addPayment(Payment payment);
    public List showAllPayments();
}
