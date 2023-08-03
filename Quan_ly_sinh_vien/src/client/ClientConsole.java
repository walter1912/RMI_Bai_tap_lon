/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author THAIHB.B19CN638
 */
import server.interfaces.DiemInterface;
import server.interfaces.MonHocInterface;
import server.interfaces.SinhVienInterface;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Diem;
import models.MonHoc;
import models.SinhVien;
import models.SinhVienHoc;
import server.interfaces.SinhVienHocInterface;

public class ClientConsole {

    /**
     * @param args the command line arguments
     */
    private static int port = 1912;
    private static SinhVienInterface sinhVienService;
    private static MonHocInterface monHocService;
    private static DiemInterface diemService;
    private static SinhVienHocInterface sinhVienHocService;
    // Sử dụng Scanner để lấy dữ liệu từ người dùng
    private static Scanner scanner = new Scanner(System.in);

    public ClientConsole() {
    }

    public static void main(String[] args) {
        try {
            run();

            while (true) {

                System.out.println("Chọn chức năng:");
                System.out.println("1. Thêm sinh viên");
                System.out.println("2. Tìm kiếm sinh viên");
                System.out.println("3. Tìm sinh viên theo id");
                System.out.println("4. cập nhật sinh viên");
                System.out.println("5. Xóa sinh viên");

                System.out.println("6. Thêm môn học");
                System.out.println("7. Cập nhật môn học");
                System.out.println("8. Thêm điểm của sinh viên");
                System.out.println("9. Lấy danh sách điểm của môn học");
                System.out.println("10. lấy danh sách môn học");
                System.out.println("11. lấy danh sách sinh viên");

                System.out.println("0. Thoát");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addSinhVien();
                        break;
                    case 2:
                        searchSinhVien();
                        break;
                    case 3:
                        getSinhVienById();
                        break;
                    case 4:
                        updateSinhVien();
                        break;
                    case 5:
                        deleteSinhVien();
                        break;
                    case 6:
                        addMonHoc();
                        break;
                    case 7:
                        updateMonHoc();
                        break;
                    case 8:
                        addDiemsinhVien();
                        break;
                    case 9:
                        getDiemMonHoc();
                        break;
                    case 10:
                        List<MonHoc> listmh = monHocService.getAllMonHoc();
                        printListMonHoc(listmh);
                        break;
                    case 11:
                        List<SinhVien> listsv = sinhVienService.getAllSinhVien();
                        printListSV(listsv);
                        break;
                    case 0:
                        System.out.println("Bạn đã thoát khỏi chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

    }

    public static void run() throws AccessException, NotBoundException {
        try {
            // Kết nối đến registry đang chạy trên localhost và cổng 
            Registry registry = LocateRegistry.getRegistry("localhost", port);

            // Lấy đối tượng từ registry bằng tên đã đăng ký khi đăng ký service
            sinhVienService = (SinhVienInterface) registry.lookup("SinhVienRemote");
            monHocService = (MonHocInterface) registry.lookup("MonHocRemote");
            diemService = (DiemInterface) registry.lookup("DiemRemote");
            sinhVienHocService = (SinhVienHocInterface) registry.lookup("SinhVienHocRemote");

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
// các thao tác với sinh viên

    public static void addSinhVien() {
        try {
            System.out.println("Nhập tên sinh viên:");
            String ten = scanner.nextLine();

            System.out.println("Nhập mã sinh viên:");
            String ma = scanner.nextLine();

            System.out.println("Nhập địa chỉ:");
            String address = scanner.nextLine();

            System.out.println("Nhập năm sinh:");
            String birthday = scanner.nextLine();

            // Gọi phương thức từ xa để thêm sinh viên
            SinhVien newSinhVien = new SinhVien(0, ma, ten, address, birthday);
            SinhVien added = sinhVienService.addSinhVien(newSinhVien);
            System.out.println(added);
            System.out.println("Sinh viên đã được thêm thành công!");
        } catch (RemoteException ex) {
            System.out.println("Thêm sinh viên thất bại!");
            ex.printStackTrace();
        }
    }

    public static void updateSinhVien() {
        try {
            System.out.println("Nhập id của sinh viên cần chỉnh sửa: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            SinhVien svExisted = sinhVienService.getById(id);
            System.out.println("Thông tin cũ của sinh viên, nhập 0 nếu bạn không muốn thay đổi thông tin:");
            System.out.println(svExisted);
            System.out.println("Nhập tên sinh viên:");
            String ten = scanner.nextLine();

            System.out.println("Nhập mã sinh viên:");
            String ma = scanner.nextLine();

            System.out.println("Nhập địa chỉ:");
            String address = scanner.nextLine();

            System.out.println("Nhập năm sinh:");
            String birthday = scanner.nextLine();

            // Gọi phương thức từ xa để thêm sinh viên
            if (!ten.startsWith("0")) {
                svExisted.setTen(ten);
            }
            if (!ma.startsWith("0")) {
                svExisted.setMa(ma);
            }
            if (!address.startsWith("0")) {
                svExisted.setAddress(address);
            }
            if (!birthday.startsWith("0")) {
                svExisted.setBirthday(birthday);
            }
            SinhVien updated = sinhVienService.updateSinhVien(svExisted);

            System.out.println("Sinh viên đã được cập nhật thành công!");
            System.out.println(updated);
        } catch (RemoteException ex) {
            System.out.println("Cập nhật sinh viên thất bại!");
            ex.printStackTrace();
        }
    }

    public static void deleteSinhVien() {
        try {
            System.out.println("Nhập id của sinh viên cần xóa: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            sinhVienService.deleteSinhVien(id);
            System.out.println("Xóa sinh viên có id = " + id + " thành công");
        } catch (Exception ex) {
            System.out.println("Nhập id sinh viên bị sai");
        }
    }

    public static void getSinhVienById() {
        try {
            System.out.println("Nhập id của sinh viên: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            SinhVien sv = sinhVienService.getById(id);
            System.out.println(sv);
        } catch (Exception ex) {
            System.out.println("Nhập id sinh viên bị sai");
        }
    }

    public static void searchSinhVien() {
        try {
            System.out.println("Bạn hãy chọn loại để tìm kiếm: "
                    + "\n 31: Tìm kiếm theo tên"
                    + "\n 32: Tìm kiếm theo địa chỉ(tỉnh)"
                    + "\n 33: Tìm kiếm theo năm sinh");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nhập từ khóa:");
            String keyword = scanner.nextLine();
            List<SinhVien> listSearch = new ArrayList<SinhVien>();
            switch (choice) {
                case 31:
                    listSearch = sinhVienService.searchSinhVien("ten", keyword);
                    break;
                case 32:
                    listSearch = sinhVienService.searchSinhVien("address", keyword);
                    break;
                case 33:
                    listSearch = sinhVienService.searchSinhVien("birthday", keyword);
                    break;
                default:
                    listSearch = sinhVienService.getAllSinhVien();
            }
            printListSV(listSearch);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // các thao tác với môn học 
    // các thao tác với môn học
    public static void addMonHoc() {
        try {
            System.out.println("Nhập mã môn học:");
            String ma = scanner.nextLine();

            System.out.println("Nhập tên môn học:");
            String ten = scanner.nextLine();

            System.out.println("Nhập tín chỉ môn học:");
            int tinchi = scanner.nextInt();
            scanner.nextLine();
            // Gọi phương thức từ xa để thêm môn học
            MonHoc newMonHoc = new MonHoc(0, ma, ten, tinchi);
            monHocService.addMonHoc(newMonHoc);
            System.out.println("Môn học đã được thêm thành công!");
        } catch (RemoteException ex) {
            System.out.println("Thêm môn học thất bại!");
            ex.printStackTrace();
        }
    }

    public static void updateMonHoc() {
        try {
            System.out.println("Nhập id của môn học cần chỉnh sửa: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            MonHoc svExisted = monHocService.getById(id);
            System.out.println("Thông tin cũ của môn học, nhập 0 nếu bạn không muốn thay đổi thông tin:");
            System.out.println(svExisted);
            System.out.println("Nhập tên môn học:");
            String ten = scanner.nextLine();

            System.out.println("Nhập mã môn học:");
            String ma = scanner.nextLine();

            System.out.println("Nhập tín chỉ môn học:");
            int tinchi = scanner.nextInt();
            scanner.nextLine();
            // Gọi phương thức từ xa để thêm môn học
            if (!ten.startsWith("0")) {
                svExisted.setTen(ten);
            }
            if (!ma.startsWith("0")) {
                svExisted.setMa(ma);
            }
            if (tinchi != 0) {
                svExisted.setTinchi(tinchi);
            }
            monHocService.updateMonHoc(svExisted);
            System.out.println("Môn học đã được cập nhật thành công!");
        } catch (RemoteException ex) {
            System.out.println("Cập nhật môn học thất bại!");
            ex.printStackTrace();
        }
    }
// các thao tác với môn học 

    public static void addDiemsinhVien() {
        try {

            System.out.println("Nhập id môn học: ");
            int idMh = scanner.nextInt();
            scanner.nextLine();
            MonHoc monHocChon = null;
            try {
                monHocChon = monHocService.getById(idMh);
                if (monHocChon == null) {
                    System.out.println("Bạn nhập sai id môn học");
                } else {
                    System.out.println(monHocChon);
                }
            } catch (Exception ex) {
                System.out.println("Có lỗi khi chọn môn học");
                return;
            }
            System.out.println("Nhập id sinh viên: ");
            int idSv = scanner.nextInt();
            scanner.nextLine();
            SinhVien sinhVienChon = null;
            try {
                sinhVienChon = sinhVienService.getById(idSv);
                if (sinhVienChon == null) {
                    System.out.println("Bạn nhập sai id sinh viên");
                } else {
                    System.out.println(sinhVienChon);
                }
            } catch (Exception ex) {
                System.out.println("Có lỗi khi chọn sinh viên");
                return;
            }
            SinhVienHoc sinhVienHoc = null;
            if (monHocChon != null && sinhVienChon != null) {
                sinhVienHoc = new SinhVienHoc(idSv, idMh);
                try {
                    sinhVienHoc = sinhVienHocService.addSinhVienHoc(sinhVienHoc);
                    System.out.println("Thêm sinh viên vào lớp thành công");
                    System.out.println(sinhVienHoc);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Lỗi khi thêm sinh viên vào lớp");
                    return;
                }
            }
            System.out.println("Nhập theo thứ tự điểm thành phần, điểm bài tập lớn , điểm thi của sinh viên: \n tp btl thi");
            float tp = scanner.nextFloat();
            float btl = scanner.nextFloat();
            int thi = scanner.nextInt();
            scanner.nextLine();
            try {
                Diem diem = new Diem(sinhVienHoc.getId(), tp, btl, thi);
                diemService.addDiem(diem);
                System.out.println("Thêm điểm thành công");
                System.out.println(diem);
            } catch (Exception ex) {
                System.out.println("Thêm điểm bị lỗi");
                return;
            }
        } catch (Exception ex) {
            System.out.println("Thêm điểm sinh viên không thành công.");
            ex.printStackTrace();
        }
    }

    public static void getDiemMonHoc() {
        try {
            List<Diem> allDiem = diemService.getAllDiem();
            System.out.println("Nhập id môn học cần lấy điểm");
            int idMonHoc = scanner.nextInt();
            scanner.nextLine();
            MonHoc monHoc = null;
            try {
                monHoc = monHocService.getById(idMonHoc);
                System.out.println(monHoc);
            } catch (Exception ex) {
                System.out.println("Lỗi khi lấy môn học");
            }
            printDiemMonHoc(idMonHoc, allDiem);
        } catch (Exception ex) {
            System.out.println("Lỗi khi lấy danh sách điểm");
            return;
        }

    }

    // các thao tác in ra màn hình danh sách 
    public static void printListSV(List<SinhVien> list) {
        System.out.println("Danh sách kết quả sinh viên:");
        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }

    public static void printListMonHoc(List<MonHoc> list) {
        System.out.println("Danh sách kết quả môn học:");
        for (MonHoc sv : list) {
            System.out.println(sv);
        }
    }

    public static void printListDiem(List<Diem> list) {
        System.out.println("Danh sách  kết quả điểm:");
        for (Diem diem : list) {
            try {
                SinhVien sv = sinhVienService.getById(diem.getSinhVienHocId());
            } catch (RemoteException ex) {
                System.out.println("Không tìm thấy sinh viên");
            }
            System.out.println(diem);
        }
    }

    public static void printDiemMonHoc(int idMonHoc, List<Diem> allDiem) {
        System.out.println("Danh sách  kết quả điểm:");
        for (Diem diem : allDiem) {
            SinhVienHoc svh = new SinhVienHoc();
            try {
                svh = sinhVienHocService.getById(diem.getSinhVienHocId());
            } catch (RemoteException ex) {
                System.out.println("Không tìm thấy sinh viên");
            }
            if (svh.getMonHocId() == idMonHoc) {
                try {
                    SinhVien sv = sinhVienService.getById(svh.getSinhVienId());
                    System.out.println(sv);
                    System.out.println(diem);
                } catch (Exception ex) {
                    System.out.println("Lỗi khi lấy thông tin sinh viên");
                }
            }
        }
    }
}
