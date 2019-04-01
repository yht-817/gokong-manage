package cn.gokong.www.gokongmain.vo.chioce;

import com.baomidou.mybatisplus.annotation.TableField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 精选信息
 * </p>
 *
 * @author mick
 * @since 2018-08-02
 */
public class ChoiceInfo {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 精选类型 1035
     */
    @TableField("choice_type")
    private String choiceType;
    /**
     * 投放用户编码
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 资源编码
     */
    @TableField("resource_no")
    private String resourceNo;
    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;
    /**
     * 搜索关键词
     */
    @TableField("search_keyword")
    private String searchKeyword;
    /**
     * 投放张数
     */
    @TableField("put_num")
    private Integer putNum;
    /**
     * 已购张数
     */
    @TableField("pay_num")
    private Integer payNum;
    /**
     * 每个用户最大购买数
     */
    @TableField("max_pay_num")
    private Integer maxPayNum;
    /**
     * 单位
     */
    private String unit;
    /**
     * 券面额
     */
    @TableField("coupon_amount")
    private BigDecimal couponAmount;
    /**
     * 购买金额
     */
    @TableField("pay_amount")
    private BigDecimal payAmount;
    /**
     * 悟空币可抵扣金额
     */
    @TableField("pay_amount_wukb")
    private BigDecimal payAmountWukb;
    /**
     * 券成本金额
     */
    @TableField("cost_amount")
    private BigDecimal costAmount;
    /**
     * 分享返利金额
     */
    @TableField("share_rebate_amount")
    private BigDecimal shareRebateAmount;
    /**
     * 砍价底价
     */
    @TableField("cut_min_amount")
    private BigDecimal cutMinAmount;
    /**
     * 票券开始日期
     */
    @TableField("start_date")
    private Date startDate;
    /**
     * 票券结束日期
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 标题描述
     */
    @TableField("title_desc")
    private String titleDesc;
    /**
     * 使用说明
     */
    @TableField("use_explain")
    private String useExplain;
    /**
     * 使用规则
     */
    @TableField("use_rules")
    private String useRules;
    /**
     * 文案链接
     */
    @TableField("copywrit_url")
    private String copywritUrl;
    /**
     * 布局位置 1036
     */
    @TableField("position_no")
    private String positionNo;
    /**
     * 详情banner图片
     */
    @TableField("banner_photo")
    private String bannerPhoto;
    /**
     * 详情大图
     */
    @TableField("detail_photo")
    private String detailPhoto;
    /**
     * 首页中图
     */
    @TableField("home_middle_photo")
    private String homeMiddlePhoto;
    /**
     * 首页小图
     */
    @TableField("home_small_photo")
    private String homeSmallPhoto;
    /**
     * 票券封面图
     */
    @TableField("coupon_cover_photo")
    private String couponCoverPhoto;
    /**
     * 点赞数
     */
    @TableField("praise_num")
    private Integer praiseNum;
    /**
     * 商家名称
     */
    @TableField("merchant_name")
    private String merchantName;
    /**
     * 联系人
     */
    @TableField("merchant_contract")
    private String merchantContract;
    /**
     * 联系电话
     */
    @TableField("merchant_phone")
    private String merchantPhone;
    /**
     * 商家地址
     */
    @TableField("merchant_address")
    private String merchantAddress;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 平台用户编码
     */
    @TableField("user_no_platform")
    private String userNoPlatform;
    /**
     * 平台用户名称
     */
    @TableField("user_name_platform")
    private String userNamePlatform;
    /**
     * 审核状态 1007
     */
    @TableField("audit_state")
    private String auditState;
    /**
     * 审核日期
     */
    @TableField("audit_date")
    private Date auditDate;
    /**
     * 执行状态 1021
     */
    @TableField("execute_state")
    private String executeState;

    /**
     * 分享图片
     */
    @TableField("share_photo")
    private String sharePhoto;

    /**
     * 商品销售位置
     */
    @TableField("position_goods")
    private String positionGoods;
    /**
     * 顶部封面图
     */
    @TableField("home_top_photo")
    private String homeTopPhoto;

    /**
     * 查看当前用户是否收藏
     */
    private boolean collecTion;

    /**
     * 用户返回的分享的图片
     */

    private String imgUrl;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Integer getPutNum() {
        return putNum;
    }

    public void setPutNum(Integer putNum) {
        this.putNum = putNum;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    public Integer getMaxPayNum() {
        return maxPayNum;
    }

    public void setMaxPayNum(Integer maxPayNum) {
        this.maxPayNum = maxPayNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmountWukb() {
        return payAmountWukb;
    }

    public void setPayAmountWukb(BigDecimal payAmountWukb) {
        this.payAmountWukb = payAmountWukb;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getShareRebateAmount() {
        return shareRebateAmount;
    }

    public void setShareRebateAmount(BigDecimal shareRebateAmount) {
        this.shareRebateAmount = shareRebateAmount;
    }

    public BigDecimal getCutMinAmount() {
        return cutMinAmount;
    }

    public void setCutMinAmount(BigDecimal cutMinAmount) {
        this.cutMinAmount = cutMinAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain;
    }

    public String getUseRules() {
        return useRules;
    }

    public void setUseRules(String useRules) {
        this.useRules = useRules;
    }

    public String getCopywritUrl() {
        return copywritUrl;
    }

    public void setCopywritUrl(String copywritUrl) {
        this.copywritUrl = copywritUrl;
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getBannerPhoto() {
        return bannerPhoto;
    }

    public void setBannerPhoto(String bannerPhoto) {
        this.bannerPhoto = bannerPhoto;
    }

    public String getDetailPhoto() {
        return detailPhoto;
    }

    public void setDetailPhoto(String detailPhoto) {
        this.detailPhoto = detailPhoto;
    }

    public String getHomeMiddlePhoto() {
        return homeMiddlePhoto;
    }

    public void setHomeMiddlePhoto(String homeMiddlePhoto) {
        this.homeMiddlePhoto = homeMiddlePhoto;
    }

    public String getHomeSmallPhoto() {
        return homeSmallPhoto;
    }

    public void setHomeSmallPhoto(String homeSmallPhoto) {
        this.homeSmallPhoto = homeSmallPhoto;
    }

    public String getCouponCoverPhoto() {
        return couponCoverPhoto;
    }

    public void setCouponCoverPhoto(String couponCoverPhoto) {
        this.couponCoverPhoto = couponCoverPhoto;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(String merchantContract) {
        this.merchantContract = merchantContract;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserNoPlatform() {
        return userNoPlatform;
    }

    public void setUserNoPlatform(String userNoPlatform) {
        this.userNoPlatform = userNoPlatform;
    }

    public String getUserNamePlatform() {
        return userNamePlatform;
    }

    public void setUserNamePlatform(String userNamePlatform) {
        this.userNamePlatform = userNamePlatform;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getExecuteState() {
        return executeState;
    }

    public void setExecuteState(String executeState) {
        this.executeState = executeState;
    }

    public String getSharePhoto() {
        return sharePhoto;
    }

    public void setSharePhoto(String sharePhoto) {
        this.sharePhoto = sharePhoto;
    }

    public String getPositionGoods() {
        return positionGoods;
    }

    public void setPositionGoods(String positionGoods) {
        this.positionGoods = positionGoods;
    }

    public String getHomeTopPhoto() {
        return homeTopPhoto;
    }

    public void setHomeTopPhoto(String homeTopPhoto) {
        this.homeTopPhoto = homeTopPhoto;
    }

    public boolean isCollecTion() {
        return collecTion;
    }

    public void setCollecTion(boolean collecTion) {
        this.collecTion = collecTion;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ChoiceInfo{" +
                "id='" + id + '\'' +
                ", choiceType='" + choiceType + '\'' +
                ", userNo='" + userNo + '\'' +
                ", resourceNo='" + resourceNo + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", putNum=" + putNum +
                ", payNum=" + payNum +
                ", maxPayNum=" + maxPayNum +
                ", unit='" + unit + '\'' +
                ", couponAmount=" + couponAmount +
                ", payAmount=" + payAmount +
                ", payAmountWukb=" + payAmountWukb +
                ", costAmount=" + costAmount +
                ", shareRebateAmount=" + shareRebateAmount +
                ", cutMinAmount=" + cutMinAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", titleDesc='" + titleDesc + '\'' +
                ", useExplain='" + useExplain + '\'' +
                ", useRules='" + useRules + '\'' +
                ", copywritUrl='" + copywritUrl + '\'' +
                ", positionNo='" + positionNo + '\'' +
                ", bannerPhoto='" + bannerPhoto + '\'' +
                ", detailPhoto='" + detailPhoto + '\'' +
                ", homeMiddlePhoto='" + homeMiddlePhoto + '\'' +
                ", homeSmallPhoto='" + homeSmallPhoto + '\'' +
                ", couponCoverPhoto='" + couponCoverPhoto + '\'' +
                ", praiseNum=" + praiseNum +
                ", merchantName='" + merchantName + '\'' +
                ", merchantContract='" + merchantContract + '\'' +
                ", merchantPhone='" + merchantPhone + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", createDate=" + createDate +
                ", userNoPlatform='" + userNoPlatform + '\'' +
                ", userNamePlatform='" + userNamePlatform + '\'' +
                ", auditState='" + auditState + '\'' +
                ", auditDate=" + auditDate +
                ", executeState='" + executeState + '\'' +
                ", sharePhoto='" + sharePhoto + '\'' +
                ", positionGoods='" + positionGoods + '\'' +
                ", homeTopPhoto='" + homeTopPhoto + '\'' +
                ", collecTion=" + collecTion +
                ", imgUrl=" + imgUrl +
                '}';
    }
}
