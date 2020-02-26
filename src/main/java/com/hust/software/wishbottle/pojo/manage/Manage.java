package com.hust.software.wishbottle.pojo.manage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manage {
    private int managerId;
    private String managerAccount;
    private String managerPassword;
    private int managerType;

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public int getManagerType() {
        return managerType;
    }

    public void setManagerType(int managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return "Manage{" +
                "managerId=" + managerId +
                ", managerAccount='" + managerAccount + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerType=" + managerType +
                '}';
    }
}
