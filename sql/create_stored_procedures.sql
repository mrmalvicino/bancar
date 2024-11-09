DELIMITER $$

USE bancar_db

CREATE PROCEDURE insert_account (
    IN _Cbu VARCHAR(22),
    IN _Balance DECIMAL(10, 2),
    IN _AccountTypeId INT,
    IN _ClientId INT
) 
BEGIN
    INSERT INTO Accounts (Cbu, Balance, AccountTypeId, ClientId)
    VALUES (_Cbu, _Balance, _AccountTypeId, _ClientId);
END $$

CREATE PROCEDURE update_account (
    IN _Cbu VARCHAR(22),
    IN _Balance DECIMAL(10, 2),
    IN _AccountTypeId INT,
    IN _AccountId INT
) 
BEGIN
    UPDATE Accounts
    SET Cbu = _Cbu, Balance = _Balance, AccountTypeId = _AccountTypeId
    WHERE AccountId = _AccountId;
END $$

DELIMITER ;
