package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
@TableName("shop_city")
public class ShopCity implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 城市名
     */
         private String cityName;

        /**
     * 币种
     */
         private String currency;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ShopCity{" +
        "cityName=" + cityName +
        ", currency=" + currency +
        "}";
    }
}
