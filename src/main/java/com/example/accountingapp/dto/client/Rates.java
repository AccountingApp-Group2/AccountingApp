
package com.example.accountingapp.dto.client;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AUD",
    "BGN",
    "BRL",
    "CAD",
    "CHF",
    "CNY",
    "CZK",
    "DKK",
    "GBP",
    "HKD",
    "HRK",
    "HUF",
    "IDR",
    "ILS",
    "INR",
    "ISK",
    "JPY",
    "KRW",
    "MXN",
    "MYR",
    "NOK",
    "NZD",
    "PHP",
    "PLN",
    "RON",
    "SEK",
    "SGD",
    "THB",
    "TRY",
    "USD",
    "ZAR"
})
@Generated("jsonschema2pojo")
public class Rates {

    @JsonProperty("AUD")
    private Double aud;
    @JsonProperty("BGN")
    private Double bgn;
    @JsonProperty("BRL")
    private Double brl;
    @JsonProperty("CAD")
    private Double cad;
    @JsonProperty("CHF")
    private Double chf;
    @JsonProperty("CNY")
    private Double cny;
    @JsonProperty("CZK")
    private Double czk;
    @JsonProperty("DKK")
    private Double dkk;
    @JsonProperty("GBP")
    private Double gbp;
    @JsonProperty("HKD")
    private Double hkd;
    @JsonProperty("HRK")
    private Double hrk;
    @JsonProperty("HUF")
    private Double huf;
    @JsonProperty("IDR")
    private Integer idr;
    @JsonProperty("ILS")
    private Double ils;
    @JsonProperty("INR")
    private Double inr;
    @JsonProperty("ISK")
    private Double isk;
    @JsonProperty("JPY")
    private Double jpy;
    @JsonProperty("KRW")
    private Double krw;
    @JsonProperty("MXN")
    private Double mxn;
    @JsonProperty("MYR")
    private Double myr;
    @JsonProperty("NOK")
    private Double nok;
    @JsonProperty("NZD")
    private Double nzd;
    @JsonProperty("PHP")
    private Double php;
    @JsonProperty("PLN")
    private Double pln;
    @JsonProperty("RON")
    private Double ron;
    @JsonProperty("SEK")
    private Double sek;
    @JsonProperty("SGD")
    private Double sgd;
    @JsonProperty("THB")
    private Double thb;
    @JsonProperty("TRY")
    private Double _try;
    @JsonProperty("USD")
    private Double usd;
    @JsonProperty("ZAR")
    private Double zar;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AUD")
    public Double getAud() {
        return aud;
    }

    @JsonProperty("AUD")
    public void setAud(Double aud) {
        this.aud = aud;
    }

    @JsonProperty("BGN")
    public Double getBgn() {
        return bgn;
    }

    @JsonProperty("BGN")
    public void setBgn(Double bgn) {
        this.bgn = bgn;
    }

    @JsonProperty("BRL")
    public Double getBrl() {
        return brl;
    }

    @JsonProperty("BRL")
    public void setBrl(Double brl) {
        this.brl = brl;
    }

    @JsonProperty("CAD")
    public Double getCad() {
        return cad;
    }

    @JsonProperty("CAD")
    public void setCad(Double cad) {
        this.cad = cad;
    }

    @JsonProperty("CHF")
    public Double getChf() {
        return chf;
    }

    @JsonProperty("CHF")
    public void setChf(Double chf) {
        this.chf = chf;
    }

    @JsonProperty("CNY")
    public Double getCny() {
        return cny;
    }

    @JsonProperty("CNY")
    public void setCny(Double cny) {
        this.cny = cny;
    }

    @JsonProperty("CZK")
    public Double getCzk() {
        return czk;
    }

    @JsonProperty("CZK")
    public void setCzk(Double czk) {
        this.czk = czk;
    }

    @JsonProperty("DKK")
    public Double getDkk() {
        return dkk;
    }

    @JsonProperty("DKK")
    public void setDkk(Double dkk) {
        this.dkk = dkk;
    }

    @JsonProperty("GBP")
    public Double getGbp() {
        return gbp;
    }

    @JsonProperty("GBP")
    public void setGbp(Double gbp) {
        this.gbp = gbp;
    }

    @JsonProperty("HKD")
    public Double getHkd() {
        return hkd;
    }

    @JsonProperty("HKD")
    public void setHkd(Double hkd) {
        this.hkd = hkd;
    }

    @JsonProperty("HRK")
    public Double getHrk() {
        return hrk;
    }

    @JsonProperty("HRK")
    public void setHrk(Double hrk) {
        this.hrk = hrk;
    }

    @JsonProperty("HUF")
    public Double getHuf() {
        return huf;
    }

    @JsonProperty("HUF")
    public void setHuf(Double huf) {
        this.huf = huf;
    }

    @JsonProperty("IDR")
    public Integer getIdr() {
        return idr;
    }

    @JsonProperty("IDR")
    public void setIdr(Integer idr) {
        this.idr = idr;
    }

    @JsonProperty("ILS")
    public Double getIls() {
        return ils;
    }

    @JsonProperty("ILS")
    public void setIls(Double ils) {
        this.ils = ils;
    }

    @JsonProperty("INR")
    public Double getInr() {
        return inr;
    }

    @JsonProperty("INR")
    public void setInr(Double inr) {
        this.inr = inr;
    }

    @JsonProperty("ISK")
    public Double getIsk() {
        return isk;
    }

    @JsonProperty("ISK")
    public void setIsk(Double isk) {
        this.isk = isk;
    }

    @JsonProperty("JPY")
    public Double getJpy() {
        return jpy;
    }

    @JsonProperty("JPY")
    public void setJpy(Double jpy) {
        this.jpy = jpy;
    }

    @JsonProperty("KRW")
    public Double getKrw() {
        return krw;
    }

    @JsonProperty("KRW")
    public void setKrw(Double krw) {
        this.krw = krw;
    }

    @JsonProperty("MXN")
    public Double getMxn() {
        return mxn;
    }

    @JsonProperty("MXN")
    public void setMxn(Double mxn) {
        this.mxn = mxn;
    }

    @JsonProperty("MYR")
    public Double getMyr() {
        return myr;
    }

    @JsonProperty("MYR")
    public void setMyr(Double myr) {
        this.myr = myr;
    }

    @JsonProperty("NOK")
    public Double getNok() {
        return nok;
    }

    @JsonProperty("NOK")
    public void setNok(Double nok) {
        this.nok = nok;
    }

    @JsonProperty("NZD")
    public Double getNzd() {
        return nzd;
    }

    @JsonProperty("NZD")
    public void setNzd(Double nzd) {
        this.nzd = nzd;
    }

    @JsonProperty("PHP")
    public Double getPhp() {
        return php;
    }

    @JsonProperty("PHP")
    public void setPhp(Double php) {
        this.php = php;
    }

    @JsonProperty("PLN")
    public Double getPln() {
        return pln;
    }

    @JsonProperty("PLN")
    public void setPln(Double pln) {
        this.pln = pln;
    }

    @JsonProperty("RON")
    public Double getRon() {
        return ron;
    }

    @JsonProperty("RON")
    public void setRon(Double ron) {
        this.ron = ron;
    }

    @JsonProperty("SEK")
    public Double getSek() {
        return sek;
    }

    @JsonProperty("SEK")
    public void setSek(Double sek) {
        this.sek = sek;
    }

    @JsonProperty("SGD")
    public Double getSgd() {
        return sgd;
    }

    @JsonProperty("SGD")
    public void setSgd(Double sgd) {
        this.sgd = sgd;
    }

    @JsonProperty("THB")
    public Double getThb() {
        return thb;
    }

    @JsonProperty("THB")
    public void setThb(Double thb) {
        this.thb = thb;
    }

    @JsonProperty("TRY")
    public Double getTry() {
        return _try;
    }

    @JsonProperty("TRY")
    public void setTry(Double _try) {
        this._try = _try;
    }

    @JsonProperty("USD")
    public Double getUsd() {
        return usd;
    }

    @JsonProperty("USD")
    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @JsonProperty("ZAR")
    public Double getZar() {
        return zar;
    }

    @JsonProperty("ZAR")
    public void setZar(Double zar) {
        this.zar = zar;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
