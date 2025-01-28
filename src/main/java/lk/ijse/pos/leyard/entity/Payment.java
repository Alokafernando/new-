package lk.ijse.pos.leyard.entity;

public class Payment {
    private String reservation_id;
    private String pay_id;
    private String payment_method;
    private double deposite;
    private double amount;
    private double remain_amount;
    private String status;

    public Payment() {}

    public Payment(String reservation_id, String pay_id, String payment_method, double deposite, double amount, double remain_amount, String status) {
        this.reservation_id = reservation_id;
        this.pay_id = pay_id;
        this.payment_method = payment_method;
        this.deposite = deposite;
        this.amount = amount;
        this.remain_amount = remain_amount;
        this.status = status;
    }

    public String getReservation_id() {return reservation_id;}

    public void setReservation_id(String reservation_id) {this.reservation_id = reservation_id;}

    public String getPay_id() {return pay_id;}

    public void setPay_id(String pay_id) {this.pay_id = pay_id;}

    public String getPayment_method() {return payment_method;}

    public void setPayment_method(String payment_method) {this.payment_method = payment_method;}

    public double getDeposite() {return deposite;}

    public void setDeposite(double deposite) {this.deposite = deposite;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}

    public double getRemain_amount() {return remain_amount;}

    public void setRemain_amount(double remain_amount) {this.remain_amount = remain_amount;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}


}
