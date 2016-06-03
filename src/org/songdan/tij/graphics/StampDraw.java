package org.songdan.tij.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 绘制椭圆
 * @author Songdan
 * @date 2016/5/12
 */
public class StampDraw {

    /**
     * 印章名称距中心点偏移量,按照y轴方向
     */
    private int nameOffset = 50;
    /**
     * 印章宽度
     */
    private int width = 230;
    /**
     * 印章高度
     */
    private int height = 230;
    /**
     * 印章中心标志(默认为五角星)外接圆半径
     */
    private float radius = 30;
    /**
     * 印章所属单位的起始角度,以6点钟方向为中心,向两个方向平均扩展
     */
    private float firmAngle = 210;
    /**
     * 印章名称
     */
    private String name = "发票专用章";
    /**
     * 印章名称颜色
     */
    private Color nameColor = Color.RED;
    /**
     * 印章所属单位
     */
    private String firm = "中国人民银行";
    /**
     * 印章所属单位颜色
     */
    private Color firmColor = Color.RED;
    /**
     * 印章名称字体信息
     */
    private Font nameFont = new Font("仿宋体", Font.PLAIN, 16);
    /**
     * 印章所属单位字体信息
     */
    private Font firmFont = new Font("仿宋体", Font.PLAIN, 24);
    /**
     * 单位字体的宽度缩放比率(百分比).此参数可以使字体看起来瘦长
     */
    private float firmScale = 1.0F;
    /**
     * 边框线宽
     */
    private float borderWidth = 5F;
    /**
     * 边框颜色
     */
    private Color borderColor = Color.RED;
    /**
     * 印章标记(默认为五角星)线宽
     */
    private float signBorderWidth = 3F;
    /**
     * 印章标记颜色
     */
    private Color signBorderColor = Color.RED;
    /**
     * 印章标记填充颜色
     */
    private Color signFillColor = Color.RED;

    public void drawStamp() throws IOException {
        File file = new File("G:/test.png");
        BufferedImage bufferedImage = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        draw(graphics);
        ImageIO.write(bufferedImage, "png", file);
    }

    public void draw(Graphics2D g2d) {
        // 把绘制起点挪到圆中心点
        g2d.translate(width / 2, height / 2);

        Stroke stroke = g2d.getStroke();// 旧的线性
        // 填充五角星
        /*Polygon polygon = getPentaclePoints(radius);
        if (signFillColor != null) {
            g2d.setColor(signFillColor);
            g2d.fill(polygon);
        }

        // 绘制五角星边框
        g2d.setStroke(new BasicStroke(signBorderWidth));
        g2d.setColor(signBorderColor);
        g2d.draw(polygon);*/

        // 绘制印章边框
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.drawOval(-width / 2, -height / 2, width, height);
        g2d.setStroke(stroke);

        // 绘制印章名称
        g2d.setFont(nameFont);
        g2d.setColor(nameColor);
        FontMetrics fm = g2d.getFontMetrics();
        int w = fm.stringWidth(name);// 名称宽度
        int h = fm.getHeight();// 名称高度
        int y = fm.getAscent() - h / 2;// 求得中心线经过字体的高度的一半时的字体的起绘点
        g2d.drawString(name, -w / 2, y + nameOffset);

        // 绘制印章单位
        g2d.setFont(firmFont);
        g2d.setColor(firmColor);
        fm = g2d.getFontMetrics();
        h = fm.getHeight();// 字高度

        int count = firm.length();// 字数
        int r = width / 2;// 半径,就假设此印章是个矩形,方便计算

        float angle = (360 - firmAngle) / (count - 1);// 字间角度
        float start = 90 + firmAngle / 2;// 以x轴正向为0,顺时针旋转
        double vr = Math.toRadians(90);// 垂直旋转弧度
        char[] chars = firm.toCharArray();
        for (int i = 0; i < count; i++) {
            char c = chars[i];// 需要绘制的字符
            int cw = fm.charWidth(c);// 此字符宽度
            float a = start + angle * i;// 现在角度

            double radians = Math.toRadians(a);
            g2d.rotate(radians);// 旋转坐标系,让要绘制的字符处于x正轴
            float x = r - h;// 绘制字符的x坐标为半径减去字高度
            // g2d.drawLine(0, 0, (int) x, 0);// debug
            g2d.translate(x, 0);// 移动到此位置,此时字和x轴垂直
            g2d.rotate(vr);// 旋转90度,让字平行于x轴
            g2d.scale(firmScale, 1);// 缩放字体宽度
            g2d.drawString(String.valueOf(c), -cw / 2, 0);// 此点为字的中心点
            // 将所有设置还原,等待绘制下一个
            g2d.scale(1 / firmScale, 1);
            g2d.rotate(-vr);
            g2d.translate(-x, 0);
            g2d.rotate(-radians);
        }
    }
    /**
     * 获取具有指定半径外接圆的五角星顶点
     *
     * @param radius
     *            圆半径
     */
    private Polygon getPentaclePoints(float radius) {
        if (radius <= 0)
            return null;
        float lradius = radius * 0.381966f;// 根据radius求内圆半径
        double halfpi = Math.PI / 180f;
        Point[] points = new Point[10];
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 1)
                points[i] = new Point(
                        (int) (Math.sin(halfpi * 36 * i) * radius),
                        (int) (Math.cos(halfpi * 36 * i) * radius));
            else
                points[i] = new Point(
                        (int) (Math.sin(halfpi * 36 * i) * lradius),
                        (int) (Math.cos(halfpi * 36 * i) * lradius));
        }
        Polygon polygon = new Polygon();
        for (Point p : points) {
            polygon.addPoint(p.x, p.y);
        }
        return polygon;
    }

    public static void main(String[] args) {
        try {
            new StampDraw().drawStamp();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
