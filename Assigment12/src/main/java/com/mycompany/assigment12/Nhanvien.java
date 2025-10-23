
package com.mycompany.assigment12;


public abstract class Nhanvien {
        protected String id;
        protected String fullName;
        protected double salary;

        public Nhanvien(String id, String fullName, double salary) {
            this.id = id.trim();
            this.fullName = fullName.trim();
            this.salary = salary;
        }

        public String getId() { return id; }
        public String getFullName() { return fullName; }
        public double getSalary() { return salary; }

        public abstract double getIncome();

        public double getTax() {
            double income = getIncome();
            if (income < 9000000) return 0;
            else if (income <= 15000000) return income * 0.10;
            else return income * 0.12;
        }

        public String getLastName() {
            String[] parts = fullName.trim().split("\\s+");
            return parts[parts.length - 1].toLowerCase();
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Ho ten: %s | Luong co ban: %.0f | Thu nhap: %.0f | Thue: %.0f",
                    id, fullName, salary, getIncome(), getTax());
        }
    }

    
    class AdminStaff extends Nhanvien {
        public AdminStaff(String id, String fullName, double salary) {
            super(id, fullName, salary);
        }

        @Override
        public double getIncome() {
            return salary;
        }
    }

    
    class SalesStaff extends Nhanvien {
        private double revenue;
        private double commissionRate;

        public SalesStaff(String id, String fullName, double salary, double revenue, double commissionRate) {
            super(id, fullName, salary);
            this.revenue = revenue;
            this.commissionRate = commissionRate;
        }

        @Override
        public double getIncome() {
            return salary + revenue * commissionRate;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Ho ten: %s | Luong: %.0f | Doanh so: %.0f | Hoa hong: %.2f%% | Thu nhap: %.0f | Thue: %.0f",
                    id, fullName, salary, revenue, commissionRate * 100, getIncome(), getTax());
        }

        public double getRevenue() { return revenue; }
        public double getCommissionRate() { return commissionRate; }
    }

    
    class Manager extends Nhanvien {
        private double responsibilityAllowance;

        public Manager(String id, String fullName, double salary, double allowance) {
            super(id, fullName, salary);
            this.responsibilityAllowance = allowance;
        }

        @Override
        public double getIncome() {
            return salary + responsibilityAllowance;
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Ho ten: %s | Luong: %.0f | Luong trach nhiem: %.0f | Thu nhap: %.0f | Thue: %.0f",
                    id, fullName, salary, responsibilityAllowance, getIncome(), getTax());
        }

        public double getAllowance() { return responsibilityAllowance; }
    }
