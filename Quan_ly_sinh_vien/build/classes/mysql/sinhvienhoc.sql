use quan_ly_user;
CREATE TABLE SinhVienHoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinhVienId INT NOT NULL,
    monHocId INT NOT NULL,
    FOREIGN KEY (sinhVienId) REFERENCES SinhVien(id),
    FOREIGN KEY (monHocId) REFERENCES MonHoc(id)
);
