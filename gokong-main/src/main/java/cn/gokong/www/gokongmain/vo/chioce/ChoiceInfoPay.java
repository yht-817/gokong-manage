package cn.gokong.www.gokongmain.vo.chioce;

/**
 * <p>
 * 精选支付的信息
 * </p>
 *
 * @author mick
 * @since 2018-08-02
 */
public class ChoiceInfoPay {

    private String payNo;
    private String userNo;
    private Integer payType;
    private String peachSum;
    private String payAmount;
    private String resourceNo;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPeachSum() {
        return peachSum;
    }

    public void setPeachSum(String peachSum) {
        this.peachSum = peachSum;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }
}
