
package Beans;

import java.sql.Date;
import retailershopmanagmentsystem.EncoderAndDecoder;

public class BillBean {

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCounter_no() {
        return counter_no;
    }

    public void setCounter_no(String counter_no) {
        this.counter_no = counter_no;
    }
    private int bill_id;
    private int customer_id;
    private Date date;
    private String discount;
    private String transaction_type;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getCash_paid() {
        return cash_paid;
    }

    public void setCash_paid(int cash_paid) {
        this.cash_paid = cash_paid;
    }

    public int getCash_back() {
        return cash_back;
    }

    public void setCash_back(int cash_back) {
        this.cash_back = cash_back;
    }
    private int cash_paid;
    private int cash_back;
    private String counter_no;
    
    @Override
    public String toString(){
//        return ""+EncoderAndDecoder.DecoderSimpleDateFormate(date);
        return ""+bill_id;
    }
    
}
