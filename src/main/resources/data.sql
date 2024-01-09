CREATE PROCEDURE new_user_and_measurement(firstname_value VARCHAR(50), lastname_value VARCHAR(50),
                                          location_value VARCHAR(50),
                                          cold_water_value INT, gas_value INT, hot_water_value INT,
                                          comment_value VARCHAR(500), received_date_value TIMESTAMP, phone_number_value VARCHAR(50))
    MODIFIES SQL DATA
BEGIN
    ATOMIC
    DECLARE temp_id INT;
    INSERT INTO USER (FIRST_NAME, LAST_NAME, LOCATION, PHONE_NUMBER)
    VALUES (firstname_value, lastname_value, location_value, phone_number_value);
    SET temp_id = IDENTITY();
    INSERT INTO MEASUREMENT_USAGE (COLD_WATER, GAS, HOT_WATER, RECEIVED_DATE, USER_ID, COMMENT)
    VALUES (cold_water_value, gas_value, hot_water_value, received_date_value, temp_id, comment_value);
END ^;

CALL new_user_and_measurement('John', 'Wick', 'USA New York Continental', 3883, 998, 9871,
                              'Cool guy, quite nice doggy, any problem with getting measurement', now(), '312');
CALL new_user_and_measurement('Sherlock', 'Holmes', '221B Baker Street London', 3883, 998, 9871,
                              'Elementary, we can conclude that Sherlock used hot water because Mrs. Hudson complained that Holmes was too clean this morning', now(), '31223');
CALL new_user_and_measurement('Geralt', 'of Rivia', 'Toussaint Korvo Bianko', 3883, 998, 9871,
                              'Still have no any numbers! I cant get to Toussaint, too dangereous! Too many monsters, and magic portals are not stable. I am afraid that we will be forced to issue a fine', TIMESTAMP '1223-07-23', '312144');
