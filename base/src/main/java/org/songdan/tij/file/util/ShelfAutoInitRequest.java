package org.songdan.tij.file.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ericwong on 2017/11/30.
 */
public class ShelfAutoInitRequest implements Serializable {

    private String operatorId;

    private Long workOrderId;

    private Long transferOrderId;

    private String shelfCode;

    private String shelfLevel;

    private List<ActualAndExpectNumber> deviceList;

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getShelfLevel() {
        return shelfLevel;
    }

    public void setShelfLevel(String shelfLevel) {
        this.shelfLevel = shelfLevel;
    }

    public List<ActualAndExpectNumber> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<ActualAndExpectNumber> deviceList) {
        this.deviceList = deviceList;
    }

    public Long getTransferOrderId() {
        return transferOrderId;
    }

    public void setTransferOrderId(Long transferOrderId) {
        this.transferOrderId = transferOrderId;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    private static class ActualAndExpectNumber{
        private String actualNumber;

        private String expectNumber;

        public String getActualNumber() {
            return actualNumber;
        }

        public void setActualNumber(String actualNumber) {
            this.actualNumber = actualNumber;
        }

        public String getExpectNumber() {
            return expectNumber;
        }

        public void setExpectNumber(String expectNumber) {
            this.expectNumber = expectNumber;
        }
    }
}
