package com.dade.module.chat.model;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息内容
 * Created by dade on 2016/1/10
 */
public class MessageContent
{
    private Integer type;               // 消息类型

    private String theme;               // 主题
    private String content;             // 文本内容
    private String url;                 // 跳转Url

    private String accountCompanyId;    // 客户公司ID
    private String accountCompanyName;  // 客户公司名称

    private String positionId;          // 职位ID
    private String positionName;        // 职位名称

    private String candidateName;       // 候选人名称
    private String candidateMobile;     // 候选人手机

    private String recommenderId;       // 推荐候选人ID
    private String recommenderName;     // 推荐候选人名称

    private Date interviewDate;         // 面试时间
    private String interviewAddress;    // 面试地址
    private String interviewRemark;     // 面试备注

    private Integer offerSalary;        // offer年薪，单位K
    private Double offerRevenue;        // 预期收益
    private String offerGuarantee;      // 保证期
    private Integer offerPay;           // 付款方式  [一次性付款 0 / 分期付款 1]
    private List<Integer> offerPercent; // 分期付款比例
    private Date onBoardDate;           // 候选人到岗日期

    private String cdnKey;              // 文件 CDN Key
    private String cdnUrl;              // 文件 CDN URL
    private String localName;           // 文件 upload local file name

    /**
     * 附带属性
     * 其他比较特殊的字段采用map存储
     * 参考 {@link MessageContentAttachKeyEnum}
     */
    private Map<String, String> attachObjects = new HashMap<>();


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccountCompanyId() {
        return accountCompanyId;
    }

    public void setAccountCompanyId(String accountCompanyId) {
        this.accountCompanyId = accountCompanyId;
    }

    public String getAccountCompanyName() {
        return accountCompanyName;
    }

    public void setAccountCompanyName(String accountCompanyName) {
        this.accountCompanyName = accountCompanyName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateMobile() {
        return candidateMobile;
    }

    public void setCandidateMobile(String candidateMobile) {
        this.candidateMobile = candidateMobile;
    }

    public String getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(String recommenderId) {
        this.recommenderId = recommenderId;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewRemark() {
        return interviewRemark;
    }

    public void setInterviewRemark(String interviewRemark) {
        this.interviewRemark = interviewRemark;
    }

    public Integer getOfferSalary() {
        return offerSalary;
    }

    public void setOfferSalary(Integer offerSalary) {
        this.offerSalary = offerSalary;
    }

    public Double getOfferRevenue() {
        return offerRevenue;
    }

    public void setOfferRevenue(Double offerRevenue) {
        this.offerRevenue = offerRevenue;
    }

    public String getOfferGuarantee() {
        return offerGuarantee;
    }

    public void setOfferGuarantee(String offerGuarantee) {
        this.offerGuarantee = offerGuarantee;
    }

    public Integer getOfferPay() {
        return offerPay;
    }

    public void setOfferPay(Integer offerPay) {
        this.offerPay = offerPay;
    }

    public List<Integer> getOfferPercent() {
        return offerPercent;
    }

    public void setOfferPercent(List<Integer> offerPercent) {
        this.offerPercent = offerPercent;
    }

    public Date getOnBoardDate() {
        return onBoardDate;
    }

    public void setOnBoardDate(Date onBoardDate) {
        this.onBoardDate = onBoardDate;
    }

    public String getCdnKey() {
        return cdnKey;
    }

    public void setCdnKey(String cdnKey) {
        this.cdnKey = cdnKey;
    }

    public String getCdnUrl() {
        return cdnUrl;
    }

    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public Map<String, String> getAttachObjects() {
        return attachObjects;
    }

    public void setAttachObjects(Map<String, String> attachObjects) {
        this.attachObjects = attachObjects;
    }

    @Override
    public String toString() {
        return "MessageContent{" +
                "type=" + type +
                ", theme='" + theme + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", accountCompanyId='" + accountCompanyId + '\'' +
                ", accountCompanyName='" + accountCompanyName + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", candidateName='" + candidateName + '\'' +
                ", candidateMobile='" + candidateMobile + '\'' +
                ", recommenderId='" + recommenderId + '\'' +
                ", recommenderName='" + recommenderName + '\'' +
                ", interviewDate=" + interviewDate +
                ", interviewAddress='" + interviewAddress + '\'' +
                ", interviewRemark='" + interviewRemark + '\'' +
                ", offerSalary=" + offerSalary +
                ", offerRevenue=" + offerRevenue +
                ", offerGuarantee='" + offerGuarantee + '\'' +
                ", offerPay=" + offerPay +
                ", offerPercent=" + offerPercent +
                ", onBoardDate=" + onBoardDate +
                ", cdnKey='" + cdnKey + '\'' +
                ", cdnUrl='" + cdnUrl + '\'' +
                ", localName='" + localName + '\'' +
                '}';
    }
}
