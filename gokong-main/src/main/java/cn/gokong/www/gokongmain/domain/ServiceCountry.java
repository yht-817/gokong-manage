package cn.gokong.www.gokongmain.domain;

import java.util.List;

/**
 * 国家列表
 */
public class ServiceCountry {
    // 国家名字
    private String country;
    private String countryNo;
    private String countryicon;
    private List<ServiceCity> citys;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }

    public List<ServiceCity> getCitys() {
        return citys;
    }

    public void setCitys(List<ServiceCity> citys) {
        this.citys = citys;
    }

    public String getCountryicon() {
        return countryicon;
    }

    public void setCountryicon(String countryicon) {
        this.countryicon = countryicon;
    }

    @Override
    public String toString() {
        return "ServiceCountry{" +
                "country='" + country + '\'' +
                ", countryNo='" + countryNo + '\'' +
                ", countryicon='" + countryicon + '\'' +
                ", citys=" + citys +
                '}';
    }
}
