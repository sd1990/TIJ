package org.songdan.tij.file.util;

import static org.songdan.tij.file.util.ExcelHandler.parseExcel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 28 十一月 2017
 */
public class SkuTemplate {

    private static final String[] skuHeader = { "sku编码", "sku名称", "a数量", "b数量", "c数量", "过期时间", "预估废弃数量", "尺寸", "其它",
            "是否是必须项" };

    private static final String[] skuDisHeader = { "sku编码", "商品名称", "商品分类", "a级最大陈列数", "b级最大陈列数", "c级最大陈列数", "上下架状态" };

    private static final String skuHeaderComment = "商品铺货模版错误，格式为：sku编码,sku名称,a数量,b数量,c数量,过期时间,预估废弃数量,尺寸,其它,是否是必须项";

    private static final String skuDisHeaderComment = "商品陈列模版错误，格式为：sku编码,商品名称,商品分类,a级最大陈列数,b级最大陈列数,c级最大陈列数,上下架状态";

    private List<Sku> normalList;

    private List<Sku> hotList;

    private List<Sku> coldList;

    private List<Sku> deskList;

    private List<Sku> plugList;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd H:mm");

    private String suffix;

    public SkuTemplate(String suffix) {
        this.suffix = suffix;
    }

    public SkuTemplate invoke() {
        File normalGoodsFile = new File(DistributionConsts.BASE,
                DistributionConsts.GENERAL+ "-"+suffix+ DistributionConsts.SUFFIX);
        File hotGoodsFile = new File(DistributionConsts.BASE,
                DistributionConsts.HOT+ "-"+suffix + DistributionConsts.SUFFIX);
        File coldGoodsFile = new File(DistributionConsts.BASE,
                DistributionConsts.COLD+ "-"+suffix + DistributionConsts.SUFFIX);
        return getDisplayTemplate(normalGoodsFile, hotGoodsFile, coldGoodsFile);
    }

    public Result checkSkuHeader(String content) {
        return contrastHeader(content.split(","), skuHeader, skuHeaderComment);
    }

    public Result checkSkuDisHeader(String content) {
        return contrastHeader(content.split(","), skuDisHeader, skuDisHeaderComment);
    }

    private Result contrastHeader(String[] source, String[] target, String comment) {
        if (!Arrays.equals(source, target)) {
            return Result.fail(comment);
        } else {
            return Result.success();
        }
    }

    public Sku buildSku(String line) {
        String[] strs = line.split(",");
        // 33050018,农夫茶π西柚茉莉花茶500ml,4,1,1,2018/11/24 0:00,0,小,必须项
        return Sku.builder().code(strs[0]).name(strs[1]).aNum(Integer.parseInt(strs[2])).bNum(Integer.parseInt(strs[3]))
                .cNum(Integer.parseInt(strs[4])).expireTime(parseExpiredTime(strs[5])).size(strs[6]).other(strs[7])
                .build();

    }

    public Sku buildDisplaySku(String line) {
        String[] strs = line.split(",");
        // 36030064 果之恋雪花杏脯60g 零食 8 6 6 Y
        return Sku.builder().code(strs[0]).name(strs[1]).skuType(strs[2]).aNum(Integer.parseInt(strs[3]))
                .bNum(Integer.parseInt(strs[4])).cNum(Integer.parseInt(strs[5])).build();
    }

    protected Date parseExpiredTime(String str) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    protected SkuTemplate getDisplayTemplate(File normalGoodsFile, File hotGoodsFile, File coldGoodsFile) {
        setNormalList(parseExcel(normalGoodsFile, this::buildDisplaySku, null, this::checkSkuDisHeader));
        setHotList(parseExcel(hotGoodsFile, this::buildDisplaySku, null, this::checkSkuDisHeader));
        setColdList(parseExcel(coldGoodsFile, this::buildDisplaySku, null, this::checkSkuDisHeader));
        return this;
    }


    public List<Sku> getNormalList() {
        return normalList;
    }

    public void setNormalList(List<Sku> normalList) {
        this.normalList = normalList;
    }

    public List<Sku> getHotList() {
        return hotList;
    }

    public void setHotList(List<Sku> hotList) {
        this.hotList = hotList;
    }

    public List<Sku> getColdList() {
        return coldList;
    }

    public void setColdList(List<Sku> coldList) {
        this.coldList = coldList;
    }

    public List<Sku> getDeskList() {
        return deskList;
    }

    public void setDeskList(List<Sku> deskList) {
        this.deskList = deskList;
    }

    public List<Sku> getPlugList() {
        return plugList;
    }

    public void setPlugList(List<Sku> plugList) {
        this.plugList = plugList;
    }

    public static class Sku {

        private String code;

        private String name;

        private String skuType;

        private int aNum;

        private int bNum;

        private int cNum;

        private Date expireTime;

        private int expectDiscardNum;

        private String size;

        private String other;

        public Sku() {

        }

        public Sku(Builder builder) {
            this.code = builder.code;
            this.name = builder.name;
            this.skuType = builder.skuType;
            this.aNum = builder.aNum;
            this.bNum = builder.bNum;
            this.cNum = builder.cNum;
            this.expectDiscardNum = builder.expectDiscardNum;
            this.expireTime = builder.expireTime;
            this.size = builder.size;
            this.other = builder.other;
        }


        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getSkuType() {
            return skuType;
        }

        public int getANum() {
            return aNum;
        }

        public int getBNum() {
            return bNum;
        }

        public int getCNum() {
            return cNum;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public int getExpectDiscardNum() {
            return expectDiscardNum;
        }

        public String getSize() {
            return size;
        }

        public String getOther() {
            return other;
        }

        public static Builder builder() {
            return new Builder();
        }


        private static class Builder {
            private String code;

            private String name;

            private String skuType;

            private int aNum;

            private int bNum;

            private int cNum;

            private Date expireTime;

            private int expectDiscardNum;

            private String size;

            private String other;

            public Builder code(String code) {
                this.code = code;
                return this;
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder skuType(String skuType) {
                this.skuType = skuType;
                return this;
            }

            public Builder aNum(int aNum) {
                this.aNum = aNum;
                return this;
            }

            public Builder bNum(int bNum) {
                this.bNum = bNum;
                return this;
            }

            public Builder cNum(int cNum) {
                this.cNum = cNum;
                return this;
            }

            public Builder expireTime(Date expireTime) {
                this.expireTime = expireTime;
                return this;
            }

            public Builder expectDiscardNum(int expectDiscardNum) {
                this.expectDiscardNum = expectDiscardNum;
                return this;
            }

            public Builder size(String size) {
                this.size = size;
                return this;
            }

            public Builder other(String other) {
                this.other = other;
                return this;
            }

            public Sku build() {
                return new Sku(this);
            }
        }
    }

}
