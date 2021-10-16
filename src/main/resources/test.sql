-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2021 at 10:50 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'для Котов'),
(2, 'для Собак'),
(3, 'для Попугаев');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`) VALUES
(1, 'Сухой корм для котов Orijen Regional Red', 'Вес упаковки: 340г. Высокопротеиновый (40%), с ограниченным количеством низкогликемических углеводов (17%), Regional Red обеспечивает питание всех котов на всех стадиях жизни, в соответствии с их эволюционными и биологическими потребностями.', 247),
(2, 'Контейнер-переноска MP Bergamo Gipsy', 'Размеры: 58х38х38см. Цвет: голубой. Легко собирается, имеет надежный замок и крепления, металлическая дверца. Самоблокирующаяся дверь и неподвижная ручка служит для большей безопасности при переноске и удобства транспортировки.', 565),
(3, 'Серебряная ложка с попугаем', 'Серебро: 925\".\r\nСредний вес: 23г.\r\nДлина: 14см.', 4710),
(4, 'Дверцы Staywell', 'Врезная дверь Staywell для котов и собак маленьких пород, усиленой конструкции представляет собой проем с дверцей, оборудованный в двери или стене, позволяющий вашей собаке или кошке свободно перемещаться внутри дома, а также выходить за его пределы, не вынуждая никого впускать или выпускать животное.\r\n\r\nДвери для животных Staywell серии 600 изготовлены из прочного алюминия. Гибкий клапан двери создает минимум шума. В комплекте замок для запирания дверки. Можно применять для входной двери или межкомнатных дверей.', 2211),
(5, 'Сухой корм для собак ACANA Adult Large Breed', '11,4кг. Ингредиенты: свежее мясо цыпленка без костей (13%), высушенное мясо цыпленка (12%), высушенное мясо индейки (11%), красная чечевица, зеленый горошек (8%), фасоль, ливер (печень, сердце, почки) (4%), высушенное мясо сельди (4%), свежие цельные яйца (4%), свежее мясо камбалы без костей (4%), жир из сельди (3%), куриный жир (3% ), вяленая люцерна, зеленая чечевица, желтый горошек, клетчатка горошка, свежие куриные хрящи (2%), высушенные водоросли, тыква, мускатная тыква, пастернак, капуста Кале, шпинат, зелень горчицы, зелень репы, морковь, яблоки, груши, лиофилизированная куриная печень, лиофилизированная печень индейки, клюква, черника, корень цикория, куркума, расторопша, корень лопуха, лаванда, корень алтея, плоды шиповника, цинка протеинат 100 мг.', 2939),
(6, 'Песок Versele-Laga из морских раковин для птиц', '5кг. Versele-Laga Prestige Marine – экологически чистый белый песок с измельченными раковинами устриц (40%), небольшими ракушками и минералами. Идеальный органический продукт без пыли и опасных для окружающей среды веществ, вредных бактерий и загрязнений. Стерилизован при высокой температуре.\r\n\r\nНаличие минерального морского песка в клетке особо важно для птиц в период смены оперения (линьки) и гнездования. ВЕРСЕЛЕ-ЛАГА МАРИН отлично усваивается организмом вашего пернатого питомца, улучшает пищеварение, насыщает организм минералами, кальцием, способствует здоровью костей, клюва и оперения.', 190),
(7, 'Корм молоко для птенцов', 'Versele-Laga NutriBird A21 0.8 кг. полноценный корм для ручного вскармливания птенцов всех зерноядных птиц с первого дня их жизни: от канареек и амадин до голубей, включая все виды попугаев. Характеризуется высоким содержанием белка для здорового роста птенцов, имеет сбалансированный состав с богатым комплексом витаминов и микроэлементов, позволяет избежать дефицита питательной ценности корма и несбалансированности рациона.\r\n\r\nБлагодаря включению в корм лактобактерий и энзимов, идентичных вырабатываемым зобом взрослых птиц, гарантирует максимальную «натуральность» искусственного вскармливания, способствует отличной усвояемости смеси и хорошему пищеварению.', 706);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE `product_category` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`id`, `product_id`, `category_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2),
(4, 3, 3),
(5, 4, 1),
(6, 4, 2),
(7, 5, 2),
(8, 6, 3),
(9, 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `region` varchar(30) NOT NULL,
  `comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `login`, `name`, `password`, `gender`, `region`, `comment`) VALUES
(1, 'olya.tiva@gmail.com', 'Olga', '3ef133dd063d51e0b360112af7684a2141ab9fc1513adfa684195e5049770180', 'F', 'Crimea', 'comment'),
(2, 'olya.tiva1@gmail.com', 'Olga1', '3ef133dd063d51e0b360112af7684a2141ab9fc1513adfa684195e5049770180', 'F', 'Crimea', 'comm'),
(3, 'olya.tiva2@gmail.com', 'Olga2', '3ef133dd063d51e0b360112af7684a2141ab9fc1513adfa684195e5049770180', 'F', 'Crimea', 'c');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `product_category`
--
ALTER TABLE `product_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `product_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
