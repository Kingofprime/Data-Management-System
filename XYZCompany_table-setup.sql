CREATE DATABASE IF NOT EXISTS XYZCompany_db;

USE XYZCompany_db;

/* Create a table called employees */
DROP TABLE IF EXISTS XYZemployees;

/*Employee ID, First Name, Last Name, Start Date, Start Salary, Employee Contract Signed (Y/N), 
Social Security Number, Birth date, Phone Number, 
Name of Person to Call in an Emergency, 
and Phone of Person to Call in an Emergency*/

CREATE TABLE IF NOT EXISTS `XYZemployees`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`last_name` VARCHAR(64) DEFAULT NULL,
`first_name` VARCHAR(64) DEFAULT NULL,
`start_date` DATE DEFAULT NULL,
`start_salary` DECIMAL(10,2) DEFAULT NULL,
`contract_signed` CHAR(1) DEFAULT NULL,
`ssn` bigINT(9) DEFAULT NULL,
`birth_date` DATE DEFAULT NULL,
`phone_number` bigINT(10) DEFAULT NULL,
`emg_contact_name` VARCHAR(64) DEFAULT NULL,
`emg_contact_number` bigint(10) DEFAULT NULL,
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (1, 'Smokin', 'Joe', '2000-03-04', 120000.00, 'Y', 123456789, '1930-05-31', 1648569352, 'Cowboy Jack', 1867530986);

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (2, 'Smith', 'Alice', '2011-01-01', 70000.00, 'Y', 001135607, '1986-12-31', 1176653344, 'Eve Smith', 7172236769);

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (3, 'Choy', 'Ester', '1999-12-20', 100000.00, 'Y', 0697773456, '1982-02-14', 1170696664, 'Nigel Ing', 7175741384);

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (4, 'Mehoy', 'Menoy', '1980-10-20', 100000.00, 'Y', 2157673486, '1960-05-14', 2150696764, 'Pin Head', 2153679465);

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (5, 'Burnham', 'Scott', '1970-9-20', 120000.00, 'Y', 6785463456, '1940-02-14', 1170903857, 'English Burnham', 6785741384);

INSERT INTO `XYZemployees` (`id`, `last_name`, `first_name`, `start_date`, `start_Salary`, `contract_signed`, `ssn`, 
							`birth_date`, `phone_number`, `emg_contact_name`, `emg_contact_number`)
VALUES (6, 'Choy', 'Katelyn', '1999-12-20', 100000.00, 'Y', 0697773456, '1982-02-14', 1170696664, 'Nigel Ing', 7175741384);