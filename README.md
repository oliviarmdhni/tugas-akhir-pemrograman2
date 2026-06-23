# RestoApp - Aplikasi CRUD Restoran

Aplikasi **RestoApp** adalah aplikasi CRUD sederhana bertema restoran yang dibuat menggunakan bahasa pemrograman Java dengan GUI Swing NetBeans dan database MariaDB/MySQL. Aplikasi ini menggunakan konsep MVC agar struktur program lebih rapi dan mudah dikembangkan.

## Identitas

Nama: oliviaramadhani
NIM: 231011402280

## Fitur Aplikasi

* Login admin
* CRUD data menu restoran
* Pencarian data menu
* Transaksi pembelian menu
* Perhitungan total bayar dan kembalian
* Pengurangan stok otomatis setelah transaksi
* Penghapusan transaksi
* Pengembalian stok ketika transaksi dihapus
* Pencarian data transaksi

## Teknologi yang Digunakan

* Java
* Java Swing
* NetBeans IDE
* MariaDB/MySQL
* phpMyAdmin
* MariaDB Java Client JDBC Driver

## Struktur Project

```text
RestoApp
├── src
│   └── restoapp
│       ├── config
│       ├── controller
│       ├── dao
│       ├── model
│       └── view
├── database
│   └── db_restoran_231011402280.sql
├── lib
│   └── mariadb-java-client-3.5.9.jar
└── README.md
```

## Cara Menjalankan Aplikasi

1. Buka XAMPP, lalu jalankan Apache dan MySQL.
2. Buka phpMyAdmin.
3. Buat database baru dengan nama:

```sql
db_restoran_231011402280
```

4. Import file database yang ada di folder:

```text
database/db_restoran_231011402280.sql
```

5. Buka project menggunakan NetBeans.
6. Pastikan library MariaDB Java Client sudah ditambahkan ke project.
7. Jalankan file `Main.java`.
8. Login menggunakan akun berikut:

```text
Username: admin
Password: admin123
```

## Database

Database yang digunakan bernama:

```text
db_restoran_231011402280
```

Tabel yang digunakan:

* `users`
* `menu_restoran`
* `transaksi`

## Keterangan

Project ini dibuat sebagai tugas akhir mata kuliah Pemrograman 2 dengan studi kasus aplikasi CRUD restoran sederhana.
