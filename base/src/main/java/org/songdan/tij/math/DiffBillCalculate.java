package org.songdan.tij.math;

import java.math.BigDecimal;

/**
 * 差额开票计算对象
 *
 * 不含税情况：<br/>
 * 金额，税率，折扣率，扣除金额<br/>
 * 发票的价税合计=金额*（1+税率）*（1-折扣率）<br/>
 * 发票的税额 = （金额-扣除金额）*税率-金额*折扣率*税率<br/>
 * 发票的金额 = 金额*（1+税率）-（金额-扣除金额）*税率-金额*折扣率<br/>
 * 含税情况：<br/>
 * 金额，税率，折扣率，扣除金额<br/>
 * 发票的价税合计=金额*（1-折扣率）<br/>
 * 税额= （（金额/（1+税率））-扣除金额）*税率- （（金额*折扣率）/（1+税率））*税率<br/>
 * 不含税金额 = 金额  -（金额/（1+税率）-扣除金额）*税率- （金额*折扣率）/（1+税率）<br/>
 *
 */
public class DiffBillCalculate{

    private IncludingTaxMarkEnum includingTaxMarkEnum;

    /** 折扣率 */
    private BigDecimal discountRate;

    /** 金额 */
    private BigDecimal originAmount;

    /** 扣除金额*/
    private BigDecimal costAmount;

    /** 税率*/
    private BigDecimal taxRate;

    public DiffBillCalculate(IncludingTaxMarkEnum includingTaxMarkEnum, BigDecimal discountRate,
            BigDecimal originAmount, BigDecimal costAmount, BigDecimal taxRate) {
        this.includingTaxMarkEnum = includingTaxMarkEnum;
        this.discountRate = discountRate;
        this.originAmount = originAmount;
        this.costAmount = costAmount;
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        //不含税：发票的税额 = （金额-扣除金额）*税率-金额*折扣率*税率
        if (includingTaxMarkEnum == IncludingTaxMarkEnum.EXCLUDE) {
            return originAmount.multiply(new BigDecimal(1).subtract(discountRate)).subtract(costAmount)
                    .multiply(taxRate);
        }else {
            //（（金额/（1+税率））-扣除金额）*税率- （（金额*折扣率）/（1+税率））*税率
            BigDecimal money = originAmount.divide(new BigDecimal(1).add(taxRate));
            return money.multiply(new BigDecimal(1).subtract(discountRate)).subtract(costAmount)
                    .multiply(taxRate);
        }

    }

    public BigDecimal getTotalAmount() {
        return originAmount.multiply(new BigDecimal(1).subtract(discountRate)).multiply(
                includingTaxMarkEnum == IncludingTaxMarkEnum.INCLUDE ? new BigDecimal(1) : new BigDecimal(1).add(taxRate));
    }

    public BigDecimal getMoney() {
        if (includingTaxMarkEnum == IncludingTaxMarkEnum.EXCLUDE) {
            //发票的金额 = 金额*（1+税率）-（金额-扣除金额）*税率-金额*折扣率
            return originAmount.multiply(new BigDecimal(1).subtract(discountRate)).add(costAmount.multiply(taxRate));
        }else {
            //不含税金额 = 金额  -（金额/（1+税率）-扣除金额）*税率- （金额*折扣率）/（1+税率）
            BigDecimal money = originAmount.divide(new BigDecimal(1).add(taxRate));
            return originAmount.subtract(money.subtract(costAmount).multiply(taxRate))
                    .subtract(money.multiply(discountRate));
        }
    }

    public static void main(String[] args) {
        DiffBillCalculate diffBillCalculate =
                new DiffBillCalculate(IncludingTaxMarkEnum.EXCLUDE, new BigDecimal("0"), new BigDecimal("100"),
                        new BigDecimal("40"), new BigDecimal("0.17"));
        System.out.println(new BigDecimal("117").compareTo(diffBillCalculate.getTotalAmount()) == 0);
        System.out.println(new BigDecimal("10.2").compareTo(diffBillCalculate.getTaxAmount()) == 0);
        System.out.println(new BigDecimal("106.8").compareTo(diffBillCalculate.getMoney()) == 0);
        DiffBillCalculate discountDiffBillCalculate =
                new DiffBillCalculate(IncludingTaxMarkEnum.EXCLUDE, new BigDecimal("0.1"), new BigDecimal("100"),
                        new BigDecimal("40"), new BigDecimal("0.17"));
        System.out.println(new BigDecimal("105.3").compareTo(discountDiffBillCalculate.getTotalAmount()) == 0);
        System.out.println(new BigDecimal("8.5").compareTo(discountDiffBillCalculate.getTaxAmount()) == 0);
        System.out.println(new BigDecimal("96.8").compareTo(discountDiffBillCalculate.getMoney()) == 0);

        DiffBillCalculate diffBillCalculate2 =
                new DiffBillCalculate(IncludingTaxMarkEnum.INCLUDE, new BigDecimal("0"), new BigDecimal("117"),
                        new BigDecimal("40"), new BigDecimal("0.17"));
        System.out.println(new BigDecimal("117").compareTo(diffBillCalculate2.getTotalAmount()) == 0);
        System.out.println(new BigDecimal("10.2").compareTo(diffBillCalculate2.getTaxAmount()) == 0);
        System.out.println(new BigDecimal("106.8").compareTo(diffBillCalculate2.getMoney()) == 0);
        DiffBillCalculate discountDiffBillCalculate2 =
                new DiffBillCalculate(IncludingTaxMarkEnum.INCLUDE, new BigDecimal("0.1"), new BigDecimal("117"),
                        new BigDecimal("40"), new BigDecimal("0.17"));
        System.out.println(new BigDecimal("105.3").compareTo(discountDiffBillCalculate2.getTotalAmount()) == 0);
        System.out.println(new BigDecimal("8.5").compareTo(discountDiffBillCalculate2.getTaxAmount()) == 0);
        System.out.println(new BigDecimal("96.8").compareTo(discountDiffBillCalculate2.getMoney()) == 0);

    }

}

/**
 * 含税标志枚举
 */
enum IncludingTaxMarkEnum{
    INCLUDE,EXCLUDE;
}