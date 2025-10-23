package com.mycompany.assigment12;

import java.util.*;


public class Assigment2 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Nhanvien> staff = new ArrayList<>();
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Nhap danh sach nhan vien");
            System.out.println("2. Xuat danh sach nhan vien");
            System.out.println("3. Tim nhan vien theo ma");
            System.out.println("4. Xoa nhan vien theo ma");
            System.out.println("5. Cap nhat nhan vien theo ma");
            System.out.println("6. Tim theo khoang thu nhap");
            System.out.println("7. Sap xep theo ten");
            System.out.println("8. Sap xep theo thu nhap");
            System.out.println("9. Top 5 nhan vien thu nhap cao nhat");
            System.out.println("10. Thoat");
            System.out.print("Chon chuc nang: ");
            choice = readInt();

            switch (choice) {
                case 1 -> nhap();
                case 2 -> printAll();
                case 3 -> findById();
                case 4 -> deleteById();
                case 5 -> updateById();
                case 6 -> findByIncomeRange();
                case 7 -> sortByName();
                case 8 -> sortByIncome();
                case 9 -> top5ByIncome();
                case 10 -> System.out.println("Thoat!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 10);
    }

    

    private static void nhap() {
        System.out.print("Co bao nhieu nhan vien? ");
        int n = readInt();
        for (int i = 0; i < n; i++) {
            System.out.printf("Nhap nhan vien thu %d:\n", i + 1);
            System.out.print("Loai (1=Hanh chinh, 2=Tiep thi, 3=Truong phong): ");
            int type = readInt();
            System.out.print("Ma nhan vien: ");
            String id = sc.nextLine();
            System.out.print("Ho va ten: ");
            String name = sc.nextLine();
            System.out.print("Luong co ban: ");
            double salary = readDouble();

            if (type == 1) {
                staff.add(new AdminStaff(id, name, salary));
            } else if (type == 2) {
                System.out.print("Doanh so ban hang: ");
                double revenue = readDouble();
                System.out.print("Ti le hoa hong : ");
                double rate = readDouble();
                staff.add(new SalesStaff(id, name, salary, revenue, rate / 100));
            } else if (type == 3) {
                System.out.print("Luong trach nhiem: ");
                double allowance = readDouble();
                staff.add(new Manager(id, name, salary, allowance));
            } else {
                System.out.println("Loai khong hop le!");
            }
        }
    }

    private static void printAll() {
        if (staff.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (Nhanvien nv : staff) System.out.println(nv);
    }

    private static void findById() {
        System.out.print("Nhap ma nhan vien: ");
        String id = sc.nextLine();
        Nhanvien nv = findByIdHelper(id);
        if (nv == null) System.out.println("Khong tim thay!");
        else System.out.println(nv);
    }

    private static void deleteById() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String id = sc.nextLine();
        Nhanvien nv = findByIdHelper(id);
        if (nv == null) System.out.println("Khong tim thay!");
        else {
            staff.remove(nv);
            System.out.println("Da xoa nhan vien!");
        }
    }

    private static void updateById() {
        System.out.print("Nhap ma nhan vien can cap nhat: ");
        String id = sc.nextLine();
        Nhanvien nv = findByIdHelper(id);
        if (nv == null) {
            System.out.println("Khong tim thay!");
            return;
        }
        System.out.print("Cap nhat ten (enter de giu nguyen): ");
        String newName = sc.nextLine();
        System.out.print("Cap nhat luong co ban (-1 de giu nguyen): ");
        double newSalary = readDoubleAllowNegOne();

        String name = newName.isEmpty() ? nv.getFullName() : newName;
        double salary = newSalary < 0 ? nv.getSalary() : newSalary;

        if (nv instanceof AdminStaff) {
            staff.set(staff.indexOf(nv), new AdminStaff(nv.getId(), name, salary));
        } else if (nv instanceof SalesStaff s) {
            System.out.print("Cap nhat doanh so (-1 de giu nguyen): ");
            double rev = readDoubleAllowNegOne();
            System.out.print("Cap nhat hoa hong (%) (-1 de giu nguyen): ");
            double rate = readDoubleAllowNegOne();
            double newRev = rev < 0 ? s.getRevenue() : rev;
            double newRate = rate < 0 ? s.getCommissionRate() : rate / 100;
            staff.set(staff.indexOf(nv), new SalesStaff(nv.getId(), name, salary, newRev, newRate));
        } else if (nv instanceof Manager m) {
            System.out.print("Cap nhat phu cap (-1 de giu nguyen): ");
            double all = readDoubleAllowNegOne();
            double newAll = all < 0 ? m.getAllowance() : all;
            staff.set(staff.indexOf(nv), new Manager(nv.getId(), name, salary, newAll));
        }
        System.out.println("Da cap nhat!");
    }

    private static void findByIncomeRange() {
        System.out.print("Nhap thu nhap thap nhat: ");
        double low = readDouble();
        System.out.print("Nhap thu nhap cao nhat: ");
        double high = readDouble();
        boolean found = false;
        for (Nhanvien nv : staff) {
            double inc = nv.getIncome();
            if (inc >= low && inc <= high) {
                System.out.println(nv);
                found = true;
            }
        }
        if (!found) System.out.println("Khong co nhan vien trong khoang nay.");
    }

    private static void sortByName() {
        staff.sort(Comparator.comparing(Nhanvien::getLastName).thenComparing(Nhanvien::getFullName));
        System.out.println("Da sap xep theo ten.");
        printAll();
    }

    private static void sortByIncome() {
        staff.sort(Comparator.comparingDouble(Nhanvien::getIncome));
        System.out.println("Da sap xep theo thu nhap tang dan.");
        printAll();
    }

    private static void top5ByIncome() {
        staff.stream()
                .sorted(Comparator.comparingDouble(Nhanvien::getIncome).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Nhap sai, nhap lai so nguyen: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Nhap sai, nhap lai so thuc: ");
            }
        }
    }

    private static double readDoubleAllowNegOne() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Nhap sai, nhap lai (-1 de giu nguyen): ");
            }
        }
    }

    private static Nhanvien findByIdHelper(String id) {
        for (Nhanvien nv : staff) {
            if (nv.getId().equalsIgnoreCase(id)) return nv;
        }
        return null;
    }
}
