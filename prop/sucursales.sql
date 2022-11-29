-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sucursales
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sucursales
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sucursales` DEFAULT CHARACTER SET utf8 ;
USE `sucursales` ;

-- -----------------------------------------------------
-- Table `sucursales`.`Sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sucursales`.`Sucursal` (
  `codigo` VARCHAR(45) NOT NULL,
  `referencia` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `zonaje` DOUBLE NOT NULL,
  `ubicacionX` INT NOT NULL,
  `ubicacionY` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sucursales`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sucursales`.`Empleado` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `salario` DOUBLE NULL,
  `sucursal` VARCHAR(45) NULL,
  PRIMARY KEY (`cedula`),
  INDEX `sucursal_idx` (`sucursal` ASC) VISIBLE,
  CONSTRAINT `sucursal`
    FOREIGN KEY (`sucursal`)
    REFERENCES `sucursales`.`Sucursal` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
