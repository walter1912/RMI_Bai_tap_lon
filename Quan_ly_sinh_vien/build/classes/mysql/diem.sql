use quan_ly_user;
CREATE TABLE Diem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sinhVienHocId INT NOT NULL,
    cc FLOAT NOT NULL,
    btl FLOAT NOT NULL,
    thi FLOAT NOT NULL,
    FOREIGN KEY (sinhVienHocId) REFERENCES SinhVienHoc(id)
);
