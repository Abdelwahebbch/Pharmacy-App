package com.pharmacy.Model;

public class DashboardStats {
    public int lowStock;
    public int totalPrescriptions;
    public int expItems;

    public DashboardStats(int lowStock, int totalPrescriptions, int expItems) {
        this.lowStock = lowStock;
        this.totalPrescriptions = totalPrescriptions;
        this.expItems = expItems;
    }
}
