package com.hust.software.wishbottle.pojo.manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishView {
    private int wishId;
    private String writerName;
    private Date createTime;
    private String wishContent;
    private String tagMeaning;
    private String pickerName;
    private int wishStatus;

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }

    public String getTagMeaning() {
        return tagMeaning;
    }

    public void setTagMeaning(String tagMeaning) {
        this.tagMeaning = tagMeaning;
    }

    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }

    public int getWishStatus() {
        return wishStatus;
    }

    public void setWishStatus(int wishStatus) {
        this.wishStatus = wishStatus;
    }

    @Override
    public String toString() {
        return "WishView{" +
                "wishId=" + wishId +
                ", writerName='" + writerName + '\'' +
                ", createTime=" + createTime +
                ", wishContent='" + wishContent + '\'' +
                ", tagMeaning='" + tagMeaning + '\'' +
                ", pickerName='" + pickerName + '\'' +
                ", wishStatus=" + wishStatus +
                '}';
    }
}
