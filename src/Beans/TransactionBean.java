package Beans;

import java.util.Date;
import retailershopmanagmentsystem.EncoderAndDecoder;

public class TransactionBean {
    private int transactionId;
    private int prodId;
    private int cardId;
    ///note customer knows as bill ID
    private int customerId;
    private int unitPrice;
    private int quantity;
    private int totalPrice;
    private Date dateOfTrans;
    private String amountType;
    private String transType;
    private int counterNo;
    
    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCashPaid() {
        return cashPaid;
    }

    public void setCashPaid(int cashPaid) {
        this.cashPaid = cashPaid;
    }

    public int getCashBack() {
        return cashBack;
    }

    ///////////////////
    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }
    private int bill_id;
    private int discount;
    private int cashPaid;
    private int cashBack;
  
    private String prodCatName;
    private String customerName;
    private String barcode;
    private String prodName;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProdCatName() {
        return prodCatName;
    }

    public void setProdCatName(String prodCatName) {
        this.prodCatName = prodCatName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCounterNo() {
        return counterNo;
    }

    public void setCounterNo(int counterNo) {
        this.counterNo = counterNo;
    }
    private String remarks;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateOfTrans() {
        return dateOfTrans;
    }

    public void setDateOfTrans(Date dateOfTrans) {
        this.dateOfTrans = dateOfTrans;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Override
    public String toString(){
//        return ""+EncoderAndDecoder.DecoderSimpleDateFormate(dateOfTrans);
        return ""+transactionId;
    }
}
