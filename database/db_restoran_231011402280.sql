-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2026 at 04:07 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_restoran_231011402280`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu_restoran`
--

CREATE TABLE `menu_restoran` (
  `id_menu` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `harga` double NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_restoran`
--

INSERT INTO `menu_restoran` (`id_menu`, `nama_menu`, `kategori`, `harga`, `stok`) VALUES
(1, 'Nasi Goreng Spesial', 'Makanan', 18000, 20),
(2, 'Mie Ayam Bakso', 'Makanan', 15000, 25),
(3, 'Ayam Geprek', 'Makanan', 17000, 30),
(4, 'Soto Ayam', 'Makanan', 16000, 16),
(5, 'Bakso Urat', 'Makanan', 20000, 15),
(6, 'Es Teh Manis', 'Minuman', 5000, 40),
(7, 'Es Jeruk', 'Minuman', 7000, 35),
(9, 'Kentang Goreng', 'Snack', 10000, 25),
(10, 'Pisang Coklat', 'Dessert', 12000, 22),
(11, 'Bakso Urat', 'Makanan', 20000, 15),
(12, 'Jus Alpukat', 'Minuman', 12000, 20),
(13, 'Jus Nanas', 'Minuman', 12000, 17),
(14, 'Pisang Kelapa', 'Dessert', 12000, 22),
(16, 'Pisang Coklat', 'Dessert', 12000, 17);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `nama_pelanggan` varchar(100) NOT NULL,
  `id_menu` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga` double NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` double NOT NULL,
  `bayar` double NOT NULL,
  `kembalian` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal`, `nama_pelanggan`, `id_menu`, `nama_menu`, `harga`, `jumlah`, `total`, `bayar`, `kembalian`) VALUES
(1, '2026-06-22 16:26:41', 'oliv', 13, 'Jus Nanas', 12000, 3, 36000, 40000, 4000),
(3, '2026-06-23 13:29:42', 'yuyu', 16, 'Pisang Coklat', 12000, 5, 60000, 100000, 40000),
(4, '2026-06-23 13:51:33', 'najhan', 4, 'Soto Ayam', 16000, 2, 32000, 50000, 18000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `nama`) VALUES
(1, 'admin', 'admin123', 'oliviaramadhani - 231011402280');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu_restoran`
--
ALTER TABLE `menu_restoran`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu_restoran`
--
ALTER TABLE `menu_restoran`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `menu_restoran` (`id_menu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
