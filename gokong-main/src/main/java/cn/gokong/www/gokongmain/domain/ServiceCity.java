package cn.gokong.www.gokongmain.domain;

/**
 * 城市列表
 */
public class ServiceCity {

    private String city;
    private String cityNo;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    @Override
    public String toString() {
        return "ServiceCity{" +
                "city='" + city + '\'' +
                ", cityNo='" + cityNo + '\'' +
                '}';
    }
}
