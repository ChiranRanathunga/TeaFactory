-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2020 at 05:38 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tea_pot_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `advance`
--

CREATE TABLE `advance` (
  `advance_ID` int(11) NOT NULL,
  `advance_date` date NOT NULL,
  `advance_amount` int(11) NOT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advance`
--

INSERT INTO `advance` (`advance_ID`, `advance_date`, `advance_amount`, `TS_ID`) VALUES
(1, '2020-12-05', 3, 1),
(2, '2020-12-03', 300, 4);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_ID` int(11) NOT NULL,
  `bill_month` varchar(100) NOT NULL,
  `bill_year` int(11) NOT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_ID`, `bill_month`, `bill_year`, `TS_ID`) VALUES
(1, 'November', 2020, 1),
(2, 'November', 2020, 1),
(3, 'November', 2020, 1),
(4, 'November', 2020, 1),
(5, 'October', 2020, 2),
(6, 'December', 2020, 1),
(7, 'December', 2020, 1),
(8, 'December', 2020, 1),
(9, 'December', 2020, 1),
(10, 'December', 2020, 1),
(11, 'December', 2020, 1),
(12, 'December', 2020, 1);

-- --------------------------------------------------------

--
-- Table structure for table `fertilizer_selling`
--

CREATE TABLE `fertilizer_selling` (
  `fer_sell_ID` int(11) NOT NULL,
  `fer_sell_date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `fer_rate` int(11) NOT NULL,
  `fer_amount` int(11) NOT NULL,
  `paid` tinyint(4) DEFAULT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fertilizer_selling`
--

INSERT INTO `fertilizer_selling` (`fer_sell_ID`, `fer_sell_date`, `quantity`, `fer_rate`, `fer_amount`, `paid`, `TS_ID`) VALUES
(1, '2020-12-10', 3, 5, 15, 1, 2),
(2, '2020-12-02', 300, 10, 3000, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `other_cost`
--

CREATE TABLE `other_cost` (
  `OCost_ID` int(11) NOT NULL,
  `cost_type` varchar(100) NOT NULL,
  `cost_date` date NOT NULL,
  `cost_amount` int(11) NOT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `other_cost`
--

INSERT INTO `other_cost` (`OCost_ID`, `cost_type`, `cost_date`, `cost_amount`, `TS_ID`) VALUES
(1, 'Stamp Duty', '2020-12-09', 300, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tea_buying_rate`
--

CREATE TABLE `tea_buying_rate` (
  `tea_rate_ID` int(11) NOT NULL,
  `tYeae` int(11) NOT NULL,
  `tMonth` varchar(100) NOT NULL,
  `month_rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tea_for_consumption__selling`
--

CREATE TABLE `tea_for_consumption__selling` (
  `consum_sell_ID` int(11) NOT NULL,
  `consum_sell_date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `consum_tea_rate` int(11) NOT NULL,
  `consum_tea_amount` int(11) NOT NULL,
  `paid` tinyint(4) DEFAULT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tea_for_consumption__selling`
--

INSERT INTO `tea_for_consumption__selling` (`consum_sell_ID`, `consum_sell_date`, `quantity`, `consum_tea_rate`, `consum_tea_amount`, `paid`, `TS_ID`) VALUES
(1, '2020-12-08', 2, 10, 20, 1, 3),
(2, '2020-12-10', 12, 90, 1080, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tea_leaf_collection`
--

CREATE TABLE `tea_leaf_collection` (
  `TL_coll_ID` int(11) NOT NULL,
  `TL_coll_date` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `TS_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tea_leaf_collection`
--

INSERT INTO `tea_leaf_collection` (`TL_coll_ID`, `TL_coll_date`, `quantity`, `TS_ID`) VALUES
(1, '2020-12-01', 40, 1),
(2, '2020-11-30', 4, 1),
(3, '2020-12-15', 12, 2),
(4, '2020-12-02', 33, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tea_sup_reg`
--

CREATE TABLE `tea_sup_reg` (
  `TS_ID` int(11) NOT NULL,
  `TS_Name` varchar(100) NOT NULL,
  `TS_Address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tea_sup_reg`
--

INSERT INTO `tea_sup_reg` (`TS_ID`, `TS_Name`, `TS_Address`) VALUES
(1, 'Tharindu', 'Dompe'),
(2, 'Chiran', 'Indolamulla, Dompe'),
(3, 'Bandula', 'Colombo'),
(4, 'Srimal', 'Malambe');

-- --------------------------------------------------------

--
-- Table structure for table `tea_waste_deduction`
--

CREATE TABLE `tea_waste_deduction` (
  `TS_ID` int(11) NOT NULL,
  `wYear` varchar(100) NOT NULL,
  `wMonth` varchar(100) NOT NULL,
  `waste_quantity` int(11) NOT NULL,
  `deduct` varchar(100) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tea_waste_deduction`
--

INSERT INTO `tea_waste_deduction` (`TS_ID`, `wYear`, `wMonth`, `waste_quantity`, `deduct`) VALUES
(3, '2020', 'March ', 2, '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advance`
--
ALTER TABLE `advance`
  ADD PRIMARY KEY (`advance_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `fertilizer_selling`
--
ALTER TABLE `fertilizer_selling`
  ADD PRIMARY KEY (`fer_sell_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `other_cost`
--
ALTER TABLE `other_cost`
  ADD PRIMARY KEY (`OCost_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `tea_buying_rate`
--
ALTER TABLE `tea_buying_rate`
  ADD PRIMARY KEY (`tea_rate_ID`);

--
-- Indexes for table `tea_for_consumption__selling`
--
ALTER TABLE `tea_for_consumption__selling`
  ADD PRIMARY KEY (`consum_sell_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `tea_leaf_collection`
--
ALTER TABLE `tea_leaf_collection`
  ADD PRIMARY KEY (`TL_coll_ID`),
  ADD KEY `TS_ID` (`TS_ID`);

--
-- Indexes for table `tea_sup_reg`
--
ALTER TABLE `tea_sup_reg`
  ADD PRIMARY KEY (`TS_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `advance`
--
ALTER TABLE `advance`
  MODIFY `advance_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `fertilizer_selling`
--
ALTER TABLE `fertilizer_selling`
  MODIFY `fer_sell_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `other_cost`
--
ALTER TABLE `other_cost`
  MODIFY `OCost_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tea_buying_rate`
--
ALTER TABLE `tea_buying_rate`
  MODIFY `tea_rate_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tea_for_consumption__selling`
--
ALTER TABLE `tea_for_consumption__selling`
  MODIFY `consum_sell_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tea_leaf_collection`
--
ALTER TABLE `tea_leaf_collection`
  MODIFY `TL_coll_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tea_sup_reg`
--
ALTER TABLE `tea_sup_reg`
  MODIFY `TS_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advance`
--
ALTER TABLE `advance`
  ADD CONSTRAINT `advance_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);

--
-- Constraints for table `fertilizer_selling`
--
ALTER TABLE `fertilizer_selling`
  ADD CONSTRAINT `fertilizer_selling_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);

--
-- Constraints for table `other_cost`
--
ALTER TABLE `other_cost`
  ADD CONSTRAINT `other_cost_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);

--
-- Constraints for table `tea_for_consumption__selling`
--
ALTER TABLE `tea_for_consumption__selling`
  ADD CONSTRAINT `tea_for_consumption__selling_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);

--
-- Constraints for table `tea_leaf_collection`
--
ALTER TABLE `tea_leaf_collection`
  ADD CONSTRAINT `tea_leaf_collection_ibfk_1` FOREIGN KEY (`TS_ID`) REFERENCES `tea_sup_reg` (`TS_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
