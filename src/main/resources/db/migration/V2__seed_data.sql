INSERT INTO employee (fio, address, position) VALUES
    ('Иванов И.И.', 'ул. Ленина, 1', 'Менеджер'),
    ('Петров П.П.', 'ул. Советская, 2', 'Разработчик'),
    ('Сидоров С.С.', 'ул. Победы, 3', 'Аналитик');


INSERT INTO phone (number, type) VALUES
    ('375291112233', 'mobile'),
    ('375172223344', 'office'),
    ('375293334455', 'home');


INSERT INTO employeephonerelation (employee_id, phone_id) VALUES
    (1, 1),
    (1, 2),
    (2, 3);