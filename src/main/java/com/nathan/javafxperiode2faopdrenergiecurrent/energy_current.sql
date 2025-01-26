-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2025 at 01:56 AM
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
-- Database: `energy_current`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `advance` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `advance`) VALUES
(28, 'Nathan', 'Geers', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `customer_rates`
--

CREATE TABLE `customer_rates` (
  `customer_id` int(11) NOT NULL,
  `rates_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_rates`
--

INSERT INTO `customer_rates` (`customer_id`, `rates_id`) VALUES
(28, 28);

-- --------------------------------------------------------

--
-- Table structure for table `customer_usage`
--

CREATE TABLE `customer_usage` (
  `customer_id` int(11) NOT NULL,
  `usage_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_usage`
--

INSERT INTO `customer_usage` (`customer_id`, `usage_id`) VALUES
(28, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rates`
--

CREATE TABLE `rates` (
  `id` int(11) NOT NULL,
  `current_rate` double NOT NULL,
  `gas_rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rates`
--

INSERT INTO `rates` (`id`, `current_rate`, `gas_rate`) VALUES
(28, 1.07, 1.1);

-- --------------------------------------------------------

--
-- Table structure for table `usage`
--

CREATE TABLE `usage` (
  `id` int(11) NOT NULL,
  `usage_kind` varchar(7) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_rates`
--
ALTER TABLE `customer_rates`
  ADD KEY `fk_customer_rates_customer` (`customer_id`),
  ADD KEY `fk_customer_rates_rate` (`rates_id`);

--
-- Indexes for table `customer_usage`
--
ALTER TABLE `customer_usage`
  ADD KEY `fk_customer_usage_customer` (`customer_id`),
  ADD KEY `fk_customer_usage_usage` (`usage_id`);

--
-- Indexes for table `rates`
--
ALTER TABLE `rates`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usage`
--
ALTER TABLE `usage`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1414124;

--
-- AUTO_INCREMENT for table `usage`
--
ALTER TABLE `usage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_rates`
--
ALTER TABLE `customer_rates`
  ADD CONSTRAINT `fk_customer_rates_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_customer_rates_rate` FOREIGN KEY (`rates_id`) REFERENCES `rates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `customer_usage`
--
ALTER TABLE `customer_usage`
  ADD CONSTRAINT `fk_customer_usage_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_customer_usage_usage` FOREIGN KEY (`usage_id`) REFERENCES `usage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
