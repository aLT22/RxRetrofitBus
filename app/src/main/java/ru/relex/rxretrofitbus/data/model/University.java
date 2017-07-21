package ru.relex.rxretrofitbus.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class University {

    @SerializedName("alpha_two_code")
    @Expose
    private String alphaTwoCode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("web_page")
    @Expose
    private String webPage;

    @SerializedName("domain")
    @Expose
    private String domain;

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "University{" +
                "alphaTwoCode='" + alphaTwoCode + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", webPage='" + webPage + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}