USE `assignment-dashboard-1`;

DELETE FROM Assignment WHERE id > 0;
ALTER TABLE Assignment AUTO_INCREMENT = 1;

INSERT INTO Assignment(NAME, description)
VALUES
('Web Server', 'Create Web Server'),
('MySQL Server', 'Create MySQL Server');