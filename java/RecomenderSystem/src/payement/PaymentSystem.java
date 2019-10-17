package payement;

/**
 *
 */
public interface PaymentSystem {

    /**
     * @return the amount the customer should pay
     */
     double amountOwing();

    /**
     * Saves the current purchase data
     */
    void completeTransaction();
}
