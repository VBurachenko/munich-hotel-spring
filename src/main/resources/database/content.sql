INSERT INTO `user` (`email`, `password`, `name`, `surname`, `tel_num`, `birthday`, `discount`, `gender_male`, `blocking`, `role`)
VALUES
('customer@mail.com', '$2a$10$k8DQUGjY7/p.no9xPwjUkexEC2F.6Xf9l1OS49mmXB6R9zd3Tlf3G', 'Customer', 'Custom', '+375123456789', CURDATE(), 0, 1, 0, 'CUSTOMER'),
('admin@mail.com', 'admin', 'Admin', 'Adam', '+375987654321', CURDATE(), 0, 1, 0, 'ADMIN'),
('moder@mail.com', 'moder', 'Moder', 'Moderator', '+375333333333', CURDATE(), 0, 1, 0, 'MODER');

INSERT INTO `room` (`id`, `berth_count`, `comfort_level`, `price_per_night`, `picture_link`, `available`) VALUES
(1001, 2, 3, 56.45, 'pic1', 1),
(1002, 1, 4, 73.22, 'pic2', 1),
(1003, 3, 4, 40.50, 'pic3', 1),
(1004, 2, 4, 81.60, 'pic4', 1),
(2001, 4, 5, 93.10, 'pic5', 1),
(2002, 2, 3, 59.10, 'pic6', 1),
(2003, 1, 3, 66.70, 'pic7', 0),
(2004, 3, 4, 87.67, 'pic8', 1),
(3001, 5, 5, 56.44, 'pic9', 1),
(3002, 2, 4, 66.48, 'pic10', 1),
(3003, 3, 4, 102.45, 'pic11', 1),
(3004, 1, 5, 110.22, 'pic12', 1);

INSERT INTO `invoice`(`id`, `appointment`, `nights_count`, `total_payment`)
VALUES
(108012019, '2019-01-08', 15, 2221.2);

INSERT INTO `booking` (`id`, `check_in`, `check_out`, `user_id`, `invoice_id`)
VALUES
(108012019, '2019-08-05', '2019-08-20', 10, 108012019);

INSERT INTO `booking_room` (`booking_id`, `room_id`)
VALUES
(108012019, 1004),
(108012019, 3002);
