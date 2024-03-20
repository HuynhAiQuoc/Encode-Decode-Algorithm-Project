## CHƯƠNG 1:	ỨNG DỤNG THIẾT KẾ VÀ HƯỚNG DẪN SỬ DỤNG
## 1.1	 Giao diện màn hình chờ:
Khi người sử dụng chạy phầm mềm, giao diện màn hình chờ sẽ hiển thị như hình 1.1 
 ![1](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/afb2af08-d3aa-43bb-adb3-6b1ee8e03e57)

Hình 1. 1 Giao diện màn hình chờ
## 1.2    Giao diện chính:
Khi giao diện màn hình chờ chạy xong, giao diện chính của phần mềm sẽ như hình 1.2, người sử dụng có thể sử dụng các chức năng tại đây.
 ![2](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/725e6b6b-49ad-4ac3-ade4-9c2b4ef91320)
Hình 1. 2 Giao diện chính
 

## 1.3	 Giao diện mã hóa đối xứng(Symmetric Algorithm):
Như đã thấy trên hình, người sử dụng cần thực hiện chọn một vài tùy chọn cho phiên mã hóa và giải mã như sau:
##	Đối với File: 
•	Encrypt – Algorithm – Mode – Padding – Key size – Key File(cần tạo 1 file lưu khóa như keySize.txt) – Input File – Ouput File – Start.
•	Decrypt – Algorithm – Mode – Padding – Key size – Key File(cần tạo 1 file lưu khóa như keySize.txt) – Input File – Ouput File – Start.
 ![3](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/9ef95395-a8b5-495d-8022-9aeb643be6f6)
Hình 1. 3 Giao diện mã hóa đối xứng(với File)
 
##	Đối với chuỗi(String): 
•	Encrypt – Algorithm – Mode – Padding – Key size – Key File(cần tạo 1 file lưu khóa như keySize.txt) – Input Text – Ouput Text – Start.
•	Decrypt – Algorithm – Mode – Padding – Key size – Key File(cần tạo 1 file lưu khóa như keySize.txt) – Input Text – Ouput Text – Start.
 ![4](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/6a7a947a-e0e5-45b2-8bde-c60a8d279c0d)
Hình 1. 4 Giao diện mã hóa đối xứng(với chuỗi)
 
## 1.4	Giao diện mã hóa bất đối xứng(Asymmetric Algorithm):
Như đã thấy trên hình, người sử dụng cần thực hiện chọn một vài tùy chọn cho phiên mã hóa và giải mã như sau:
##	Đối với File: 
•	Encrypt – Algorithm – Key size – PublicKey(cần tạo 1 file lưu khóa công cộng như publicKeySize.txt) – Input File – Ouput File – Start.
•	Decrypt – Algorithm – Key size – PrivateKey(cần tạo 1 file lưu khóa riêng tư như privateKeySize.txt) – Input File – Ouput File – Start.

 ![5](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/e76487b0-c39c-4c0e-9578-44cff274433d)
Hình 1. 5 Giao diện mã hóa bất đối xứng(với File)
 
##	Đối với Chuỗi(String): 
•	Encrypt – Algorithm – Key size – PublicKey(cần tạo 1 file lưu khóa công cộng như publicKeySize.txt) – Input Text – Ouput – Start.
•	Decrypt – Algorithm – Key size – PrivateKey(cần tạo 1 file lưu khóa riêng tư như privateKeySize.txt) – Input Text – Ouput – Start.
 ![6](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/6150756b-a497-4386-9e07-1d407e0fc4ed)
Hình 1. 6 Giao diện mã hóa bất đối xứng(với chuỗi)

 
## 1.5	Giao diện mã hóa sử dụng password(mật khẩu) với PBE:
Như đã thấy trên hình, khi người sử dụng chọn xong file, người sử dụng bấm nút “Start”, lúc đó một hộp thoại tùy chọn sẽ hiện ra(Hình 1.8), người sử dụng cần thực hiện chọn một vài tùy chọn cho phiên mã hóa và giải mã như sau:
•	Encrypt – Algorithm – Password Hash Integration – Password – Re-password – Delete Orginal File(Yes/No) – Ok.
•	Decrypt(file sẽ có thêm đuôi .enx.) – Algorithm – Password Hash Integration – Password – Re-password – Delete Orginal File(Yes/No) – Ok.
 ![7](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/64511ce7-5fd5-48fd-bdb0-b7f40cf585cc)

Hình 1. 7 Giao diện mã hóa sử dụng mật khẩu với PBE
 
 ![8](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/e04e1c60-e01f-4d24-83d9-374da305f10e)
Hình 1. 8 Hộp thoại tùy chọn cho phiên mã hóa và giải mã với PBE
 
## 1.6	Giao diện tạo khóa(key) mã hóa đối xứng:
Như đã thấy trên hình, người sử dụng cần thực hiện chọn một vài tùy chọn để có thể tạo khóa(key) như sau:
	Algorithms – Key size – Key File(cần chọn một file để lưu khóa như keyFile.txt) – Generate Key.
 ![9](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/5626ef8e-7ec3-496f-88a8-d5dbe66f6747)
Hình 1. 9 Giao diện tạo khóa(key) mã hóa đối xứng
 
## 1.7	 Giao diện tạo khóa(public key – privatekey) của mã hóa bất đối xứng:
Như đã thấy trên hình, người sử dụng cần thực hiện chọn một vài tùy chọn để có thể tạo khóa(key) như sau:
	Generate Key – Algorithms – Key size – Public key(chọn file lưu khóa công cộng như publicKey.txt) – Private key(chọn file lưu khóa riêng tư như privateKey.txt) – Start.
 ![10](https://github.com/HuynhAiQuoc/Encode-Decode-Algorithm-Project/assets/73823742/398b8861-88f1-45dc-8d84-9248b218f99c)

Hình 1. 10 Giao diện tạo khóa(public key – private key) mã hóa bất đối xứng
 

## CHƯƠNG 2:	HƯỚNG DẪN CLONE PROJECT VÀ CHẠY TRÊN MÁY 
Hướng dẫn chạy ứng dụng trên máy tính:

•	Bước 1: Bạn clone project về máy tính hoặc download dưới dạng .zip theo link sau đây https://github.com/HuynhAiQuoc/AnToanVaBaoMatThongTin

•	Bước 2: Bạn cần giải nén file .zip bằng các phần mềm chuyên dụng, ở đây mình sẽ giới thiệu 2 cái:
	WinRar: 
	Link download: https://www.win-rar.com/download.html?&L=10
	7-zip:
	Link download: https://www.7-zip.org/download.html
•	Bước 3: Sau khi giải nén, bạn cần xác định được project này chạy JDK nào(Mình xài JDK 15 để xây dựng ứng dụng).
	 JDK 15:
	Link download: https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html
•	Bước 4: Bạn tìm file .jar(lưu ý: nếu chạy file .jar bạn cần mở bằng JDK 15 – Java(SE) Binary Platform) hoặc file .exe theo đường dẫn sau đây:
-	File security-semi-project.jar: Semi-project/dist/security-semi-project.jar.
-	File NongLamApplication.exe: 
File .exe/NongLamApplication.exe
•	Bước 5: Bạn chạy 2 file này như sau:
-	File security-semi-project.jar: nhấp chuột phải -> chọn Open with -> chọn Java(SE) Binary Platform(bạn cần tải JDK 15 về rồi mới mở được).
-	File NongLamApplicaton.exe: nhấp chuột trái 2 lần.
Vậy là bạn đã có thể chạy được phần mềm rồi đó. Chúc bạn một ngày tốt lành. Nếu có vấn đề thắc mắc, xin vui lòng gửi qua email sau đây: huynhaiquoc123@gmail.com.
