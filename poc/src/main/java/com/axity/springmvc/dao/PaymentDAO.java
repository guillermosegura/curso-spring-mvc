package com.axity.springmvc.dao;

import java.util.List;

import com.axity.springmvc.entity.PaymentDO;

/**
 * Interface DAO para asociar pagos
 * 
 * @author guillermo.segura@axity.com
 */
public interface PaymentDAO {

	/**
	 * Busca todos los pagos asociados a un cliente
	 * 
	 * @param customerNumber
	 * @return
	 */
	List<PaymentDO> findAllByCustomerNumber(Long customerNumber);

	/**
	 * Busca un pago asociado a un cliente y asu número de cheque
	 * 
	 * @param customerNumber
	 * @param checkNumber
	 * @return
	 */
	PaymentDO get(Long customerNumber, String checkNumber);

	/**
	 * Crea un pago
	 * 
	 * @param payment
	 * @return
	 */
	void create(PaymentDO payment);

	/**
	 * Edita un pago
	 * 
	 * @param payment
	 * @return
	 */
	void edit(PaymentDO payment);

	/**
	 * Elimina un pago
	 * 
	 * @param customerNumber
	 * @param checkNumber
	 * @return
	 */
	void delete(Long customerNumber, String checkNumber);

}
