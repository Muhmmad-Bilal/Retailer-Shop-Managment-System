package Beans;

public class ProductCategoryBean {
    private int prodCatIdd;
    private String prodCatName;
    private String remarks;

    public int getProdCatIdd() {
        return prodCatIdd;
    }

    public void setProdCatIdd(int prodCatIdd) {
        this.prodCatIdd = prodCatIdd;
    }

    public String getProdCatName() {
        return prodCatName;
    }

    public void setProdCatName(String prodCatName) {
        this.prodCatName = prodCatName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Override
    public String toString(){
        return prodCatName;
    }
}
